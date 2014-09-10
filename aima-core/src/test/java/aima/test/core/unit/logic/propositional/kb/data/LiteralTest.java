package aima.test.core.unit.logic.propositional.kb.data;

import aima.core.logic.propositional.parsing.ast.PropositionSymbolImpl;
import org.junit.Assert;
import org.junit.Test;

import aima.core.logic.propositional.kb.data.Literal;

/**
 * 
 * @author Ciaran O'Reilly
 *
 */
public class LiteralTest {
	private final PropositionSymbolImpl SYMBOL_P = new PropositionSymbolImpl("P");
	private final PropositionSymbolImpl SYMBOL_Q = new PropositionSymbolImpl("Q");

	@Test
	public void testIsPositiveLiteral() {
		Literal literal = new Literal(SYMBOL_P);
		Assert.assertTrue(literal.isPositiveLiteral());
		
		literal = new Literal(SYMBOL_P, true);
		Assert.assertTrue(literal.isPositiveLiteral());

		literal = new Literal(SYMBOL_P, false);
		Assert.assertFalse(literal.isPositiveLiteral());
	}
	
	@Test
	public void testIsNegativeLiteral() {
		Literal literal = new Literal(SYMBOL_P);
		Assert.assertFalse(literal.isNegativeLiteral());
		
		literal = new Literal(SYMBOL_P, true);
		Assert.assertFalse(literal.isNegativeLiteral());

		literal = new Literal(SYMBOL_P, false);
		Assert.assertTrue(literal.isNegativeLiteral());
	}
	
	@Test
	public void testGetAtomicSentence() {
		Literal literal = new Literal(SYMBOL_P);
		Assert.assertSame(literal.getAtomicSentence(), SYMBOL_P);
	}
	
	@Test
	public void testIsAlwaysTrue() {
		Literal literal = new Literal(SYMBOL_P);
		Assert.assertFalse(literal.isAlwaysTrue());
		
		literal = new Literal(PropositionSymbolImpl.TRUE);
		Assert.assertTrue(literal.isAlwaysTrue());
		
		literal = new Literal(PropositionSymbolImpl.TRUE, false);
		Assert.assertFalse(literal.isAlwaysTrue());
		
		literal = new Literal(PropositionSymbolImpl.FALSE);
		Assert.assertFalse(literal.isAlwaysTrue());
		
		literal = new Literal(PropositionSymbolImpl.FALSE, false);
		Assert.assertTrue(literal.isAlwaysTrue());
	}
	
	@Test
	public void testIsAlwaysFalse() {
		Literal literal = new Literal(SYMBOL_P);
		Assert.assertFalse(literal.isAlwaysFalse());
		
		literal = new Literal(PropositionSymbolImpl.TRUE);
		Assert.assertFalse(literal.isAlwaysFalse());
		
		literal = new Literal(PropositionSymbolImpl.TRUE, false);
		Assert.assertTrue(literal.isAlwaysFalse());
		
		literal = new Literal(PropositionSymbolImpl.FALSE);
		Assert.assertTrue(literal.isAlwaysFalse());
		
		literal = new Literal(PropositionSymbolImpl.FALSE, false);
		Assert.assertFalse(literal.isAlwaysFalse());
	}
	
	@Test
	public void testToString() {
		Literal literal = new Literal(SYMBOL_P);
		Assert.assertEquals("P", literal.toString());
		
		literal = new Literal(SYMBOL_P, false);
		Assert.assertEquals("~P", literal.toString());
	}
	
	@Test
	public void testEquals() {
		Literal literal1 = new Literal(SYMBOL_P);
		Literal literal2 = new Literal(SYMBOL_P);
		Assert.assertTrue(literal1.equals(literal2));
		
		literal1 = new Literal(SYMBOL_P, false);
		literal2 = new Literal(SYMBOL_P, false);
		Assert.assertTrue(literal1.equals(literal2));
		
		literal1 = new Literal(SYMBOL_P);
		literal2 = new Literal(SYMBOL_P, false);
		Assert.assertFalse(literal1.equals(literal2));
		
		literal1 = new Literal(SYMBOL_P);
		literal2 = new Literal(SYMBOL_Q);
		Assert.assertFalse(literal1.equals(literal2));
		
		literal1 = new Literal(SYMBOL_P);
		Assert.assertFalse(literal1.equals(SYMBOL_P));
	}
	
	@Test
	public void testHashCode() {
		Literal literal1 = new Literal(SYMBOL_P);
		Literal literal2 = new Literal(SYMBOL_P);
		Assert.assertTrue(literal1.hashCode() == literal2.hashCode());
		
		literal1 = new Literal(SYMBOL_P, false);
		literal2 = new Literal(SYMBOL_P, false);
		Assert.assertTrue(literal1.hashCode() == literal2.hashCode());
		
		literal1 = new Literal(SYMBOL_P);
		literal2 = new Literal(SYMBOL_P, false);
		Assert.assertFalse(literal1.hashCode() == literal2.hashCode());
	}
}
