package aima.test.core.unit.logic.propositional.kb.data;

import aima.core.logic.propositional.parsing.ast.PropositionSymbolImpl;
import aima.core.logic.propositional.parsing.ast.SentenceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import aima.core.logic.propositional.kb.data.Model;
import aima.core.logic.propositional.parsing.PLParser;

/**
 * @author Ravi Mohan
 * 
 */
public class ModelTest {
	private Model m;

	private PLParser parser;

	SentenceImpl trueSentence, falseSentence, andSentence, orSentence,
			impliedSentence, biConditionalSentence;

	@Before
	public void setUp() {
		parser = new PLParser();
		trueSentence = (SentenceImpl) parser.parse("true");
		falseSentence = (SentenceImpl) parser.parse("false");
		andSentence = (SentenceImpl) parser.parse("(P  &  Q)");
		orSentence = (SentenceImpl) parser.parse("(P  |  Q)");
		impliedSentence = (SentenceImpl) parser.parse("(P  =>  Q)");
		biConditionalSentence = (SentenceImpl) parser.parse("(P  <=>  Q)");
		m = new Model();
	}

	@Test
	public void testEmptyModel() {
		Assert.assertEquals(null, m.getValue(new PropositionSymbolImpl("P")));
		Assert.assertEquals(true, m.isUnknown(new PropositionSymbolImpl("P")));
	}

	@Test
	public void testExtendModel() {
		String p = "P";
		m = m.union(new PropositionSymbolImpl(p), true);
		Assert.assertEquals(Boolean.TRUE, m.getValue(new PropositionSymbolImpl("P")));
	}

	@Test
	public void testTrueFalseEvaluation() {
		Assert.assertEquals(true, m.isTrue(trueSentence));
		Assert.assertEquals(false, m.isFalse(trueSentence));
		Assert.assertEquals(false, m.isTrue(falseSentence));
		Assert.assertEquals(true, m.isFalse(falseSentence));
	}

	@Test
	public void testSentenceStatusWhenPTrueAndQTrue() {
		String p = "P";
		String q = "Q";
		m = m.union(new PropositionSymbolImpl(p), true);
		m = m.union(new PropositionSymbolImpl(q), true);
		Assert.assertEquals(true, m.isTrue(andSentence));
		Assert.assertEquals(true, m.isTrue(orSentence));
		Assert.assertEquals(true, m.isTrue(impliedSentence));
		Assert.assertEquals(true, m.isTrue(biConditionalSentence));
	}

	@Test
	public void testSentenceStatusWhenPFalseAndQFalse() {
		String p = "P";
		String q = "Q";
		m = m.union(new PropositionSymbolImpl(p), false);
		m = m.union(new PropositionSymbolImpl(q), false);
		Assert.assertEquals(true, m.isFalse(andSentence));
		Assert.assertEquals(true, m.isFalse(orSentence));
		Assert.assertEquals(true, m.isTrue(impliedSentence));
		Assert.assertEquals(true, m.isTrue(biConditionalSentence));
	}

	@Test
	public void testSentenceStatusWhenPTrueAndQFalse() {
		String p = "P";
		String q = "Q";
		m = m.union(new PropositionSymbolImpl(p), true);
		m = m.union(new PropositionSymbolImpl(q), false);
		Assert.assertEquals(true, m.isFalse(andSentence));
		Assert.assertEquals(true, m.isTrue(orSentence));
		Assert.assertEquals(true, m.isFalse(impliedSentence));
		Assert.assertEquals(true, m.isFalse(biConditionalSentence));
	}

	@Test
	public void testSentenceStatusWhenPFalseAndQTrue() {
		String p = "P";
		String q = "Q";
		m = m.union(new PropositionSymbolImpl(p), false);
		m = m.union(new PropositionSymbolImpl(q), true);
		Assert.assertEquals(true, m.isFalse(andSentence));
		Assert.assertEquals(true, m.isTrue(orSentence));
		Assert.assertEquals(true, m.isTrue(impliedSentence));
		Assert.assertEquals(true, m.isFalse(biConditionalSentence));
	}

	@Test
	public void testComplexSentence() {
		String p = "P";
		String q = "Q";
		m = m.union(new PropositionSymbolImpl(p), true);
		m = m.union(new PropositionSymbolImpl(q), false);
		SentenceImpl sent = (SentenceImpl) parser.parse("((P | Q) &  (P => Q))");
		Assert.assertFalse(m.isTrue(sent));
		Assert.assertTrue(m.isFalse(sent));
		SentenceImpl sent2 = (SentenceImpl) parser.parse("((P | Q) & (Q))");
		Assert.assertFalse(m.isTrue(sent2));
		Assert.assertTrue(m.isFalse(sent2));
	}
}