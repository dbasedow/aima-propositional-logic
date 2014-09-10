package aima.test.core.unit.logic.propositional.inference;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import aima.core.logic.propositional.parsing.ast.PropositionSymbolImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import aima.core.logic.propositional.inference.DPLLSatisfiable;
import aima.core.logic.propositional.kb.KnowledgeBase;
import aima.core.logic.propositional.kb.data.Clause;
import aima.core.logic.propositional.kb.data.Model;
import aima.core.logic.propositional.parsing.PLParser;
import aima.core.logic.propositional.parsing.ast.Sentence;
import aima.core.logic.propositional.visitors.ConvertToConjunctionOfClauses;
import aima.core.logic.propositional.visitors.SymbolCollector;

/**
 * @author Ravi Mohan
 * 
 */
public class DPLLSatisfiableTest {

	private DPLLSatisfiable dpll;
	private PLParser parser;

	@Before
	public void setUp() {
		parser = new PLParser();
		dpll = new DPLLSatisfiable();
	}

	@Test
	public void testDPLLReturnsTrueWhenAllClausesTrueInModel() {
		Model model = new Model();
		model = model.union(new PropositionSymbolImpl("A"), true).union(
				new PropositionSymbolImpl("B"), true);
		Sentence sentence = parser.parse("A & B & (A | B)");
		Set<Clause> clauses = ConvertToConjunctionOfClauses.convert(sentence)
				.getClauses();
		List<PropositionSymbolImpl> symbols = new ArrayList<PropositionSymbolImpl>(
				SymbolCollector.getSymbolsFrom(sentence));

		boolean satisfiable = dpll.dpll(clauses, symbols, model);
		Assert.assertEquals(true, satisfiable);
	}

	@Test
	public void testDPLLReturnsFalseWhenOneClauseFalseInModel() {
		Model model = new Model();
		model = model.union(new PropositionSymbolImpl("A"), true).union(
				new PropositionSymbolImpl("B"), false);
		Sentence sentence = parser.parse("(A | B) & (A => B)");
		Set<Clause> clauses = ConvertToConjunctionOfClauses.convert(sentence)
				.getClauses();
		List<PropositionSymbolImpl> symbols = new ArrayList<PropositionSymbolImpl>(
				SymbolCollector.getSymbolsFrom(sentence));

		boolean satisfiable = dpll.dpll(clauses, symbols, model);
		Assert.assertEquals(false, satisfiable);
	}

	@Test
	public void testDPLLSucceedsWithAandNotA() {
		Sentence sentence = parser.parse("A & ~A");
		boolean satisfiable = dpll.dpllSatisfiable(sentence);
		Assert.assertEquals(false, satisfiable);
	}

	@Test
	public void testDPLLSucceedsWithChadCarffsBugReport() {
		KnowledgeBase kb = new KnowledgeBase();

		kb.tell("B12 <=> P11 | P13 | P22 | P02");
		kb.tell("B21 <=> P20 | P22 | P31 | P11");
		kb.tell("B01 <=> P00 | P02 | P11");
		kb.tell("B10 <=> P11 | P20 | P00");
		kb.tell("~B21");
		kb.tell("~B12");
		kb.tell("B10");
		kb.tell("B01");

		Assert.assertTrue(dpll.isEntailed(kb, parser.parse("P00")));
		Assert.assertFalse(dpll.isEntailed(kb, parser.parse("~P00")));
	}

	@Test
	public void testDPLLSucceedsWithStackOverflowBugReport1() {
		Sentence sentence = (Sentence) parser.parse("(A | ~A) & (A | B)");
		Assert.assertTrue(dpll.dpllSatisfiable(sentence));
	}

	@Test
	public void testDPLLSucceedsWithChadCarffsBugReport2() {
		KnowledgeBase kb = new KnowledgeBase();
		kb.tell("B10 <=> P11 | P20 | P00");
		kb.tell("B01 <=> P00 | P02 | P11");
		kb.tell("B21 <=> P20 | P22 | P31 | P11");
		kb.tell("B12 <=> P11 | P13 | P22 | P02");
		kb.tell("~B21");
		kb.tell("~B12");
		kb.tell("B10");
		kb.tell("B01");

		Assert.assertTrue(dpll.isEntailed(kb, parser.parse("P00")));
		Assert.assertFalse(dpll.isEntailed(kb, parser.parse("~P00")));
	}

	@Test
	public void testIssue66() {
		// http://code.google.com/p/aima-java/issues/detail?id=66
		Model model = new Model();
		model = model.union(new PropositionSymbolImpl("A"), false)
				.union(new PropositionSymbolImpl("B"), false)
				.union(new PropositionSymbolImpl("C"), true);
		Sentence sentence = parser.parse("((A | B) | C)");
		Set<Clause> clauses = ConvertToConjunctionOfClauses.convert(sentence)
				.getClauses();
		List<PropositionSymbolImpl> symbols = new ArrayList<PropositionSymbolImpl>(
				SymbolCollector.getSymbolsFrom(sentence));

		boolean satisfiable = dpll.dpll(clauses, symbols, model);
		Assert.assertEquals(true, satisfiable);
	}

	@Test
	public void testDoesNotKnow() {
		KnowledgeBase kb = new KnowledgeBase();
		kb.tell("A");

		Assert.assertFalse(dpll.isEntailed(kb, parser.parse("B")));
		Assert.assertFalse(dpll.isEntailed(kb, parser.parse("~B")));
	}
}
