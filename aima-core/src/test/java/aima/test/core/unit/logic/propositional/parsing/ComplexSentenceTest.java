package aima.test.core.unit.logic.propositional.parsing;

import aima.core.logic.propositional.parsing.ast.PropositionSymbolImpl;
import aima.core.logic.propositional.parsing.ast.SentenceImpl;
import org.junit.Test;

import aima.core.logic.propositional.parsing.ast.ComplexSentence;
import aima.core.logic.propositional.parsing.ast.Connective;

public class ComplexSentenceTest {
	@Test(expected = IllegalArgumentException.class)
	public void test_IllegalArgumentOnConstruction_1() {
		new ComplexSentence(null, new SentenceImpl[] {new PropositionSymbolImpl("A"), new PropositionSymbolImpl("B")});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_IllegalArgumentOnConstruction_2() {
		new ComplexSentence(Connective.NOT, (SentenceImpl[]) null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_IllegalArgumentOnConstruction_3() {
		new ComplexSentence(Connective.NOT, new SentenceImpl[]{});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_IllegalArgumentOnConstruction_4() {
		new ComplexSentence(Connective.NOT, new SentenceImpl[] {new PropositionSymbolImpl("A"), new PropositionSymbolImpl("B")});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_IllegalArgumentOnConstruction_5() {
		new ComplexSentence(Connective.AND, new SentenceImpl[]{new PropositionSymbolImpl("A")});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_IllegalArgumentOnConstruction_6() {
		new ComplexSentence(Connective.AND, new SentenceImpl[]{new PropositionSymbolImpl("A"), new PropositionSymbolImpl("B"), new PropositionSymbolImpl("C")});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_IllegalArgumentOnConstruction_7() {
		new ComplexSentence(Connective.OR, new SentenceImpl[]{new PropositionSymbolImpl("A")});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_IllegalArgumentOnConstruction_8() {
		new ComplexSentence(Connective.OR, new SentenceImpl[]{new PropositionSymbolImpl("A"), new PropositionSymbolImpl("B"), new PropositionSymbolImpl("C")});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_IllegalArgumentOnConstruction_9() {
		new ComplexSentence(Connective.IMPLICATION, new SentenceImpl[]{new PropositionSymbolImpl("A")});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_IllegalArgumentOnConstruction_10() {
		new ComplexSentence(Connective.IMPLICATION, new SentenceImpl[]{new PropositionSymbolImpl("A"), new PropositionSymbolImpl("B"), new PropositionSymbolImpl("C")});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_IllegalArgumentOnConstruction_11() {
		new ComplexSentence(Connective.BICONDITIONAL, new SentenceImpl[]{new PropositionSymbolImpl("A")});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_IllegalArgumentOnConstruction_12() {
		new ComplexSentence(Connective.BICONDITIONAL, new SentenceImpl[]{new PropositionSymbolImpl("A"), new PropositionSymbolImpl("B"), new PropositionSymbolImpl("C")});
	}
}