package aima.test.core.unit.logic.propositional.parsing;

import aima.core.logic.propositional.parsing.ast.PropositionSymbolImpl;
import org.junit.Assert;
import org.junit.Test;

public class PropositionSymbolImplTest {

	@Test
	public void test_isAlwaysTrueSymbol() {
		Assert.assertTrue(PropositionSymbolImpl.isAlwaysTrueSymbol("True"));
		Assert.assertTrue(PropositionSymbolImpl.isAlwaysTrueSymbol("tRue"));
		Assert.assertTrue(PropositionSymbolImpl.isAlwaysTrueSymbol("trUe"));
		Assert.assertTrue(PropositionSymbolImpl.isAlwaysTrueSymbol("truE"));
		Assert.assertTrue(PropositionSymbolImpl.isAlwaysTrueSymbol("TRUE"));
		Assert.assertTrue(PropositionSymbolImpl.isAlwaysTrueSymbol("true"));
		//
		Assert.assertFalse(PropositionSymbolImpl.isAlwaysTrueSymbol("Tru3"));
		Assert.assertFalse(PropositionSymbolImpl.isAlwaysTrueSymbol("True "));
		Assert.assertFalse(PropositionSymbolImpl.isAlwaysTrueSymbol(" True"));
	}
	
	@Test
	public void test_isAlwaysFalseSymbol() {
		Assert.assertTrue(PropositionSymbolImpl.isAlwaysFalseSymbol("False"));
		Assert.assertTrue(PropositionSymbolImpl.isAlwaysFalseSymbol("fAlse"));
		Assert.assertTrue(PropositionSymbolImpl.isAlwaysFalseSymbol("faLse"));
		Assert.assertTrue(PropositionSymbolImpl.isAlwaysFalseSymbol("falSe"));
		Assert.assertTrue(PropositionSymbolImpl.isAlwaysFalseSymbol("falsE"));
		Assert.assertTrue(PropositionSymbolImpl.isAlwaysFalseSymbol("FALSE"));
		Assert.assertTrue(PropositionSymbolImpl.isAlwaysFalseSymbol("false"));
		//
		Assert.assertFalse(PropositionSymbolImpl.isAlwaysFalseSymbol("Fals3"));
		Assert.assertFalse(PropositionSymbolImpl.isAlwaysFalseSymbol("False "));
		Assert.assertFalse(PropositionSymbolImpl.isAlwaysFalseSymbol(" False"));
	}
	
	@Test
	public void test_isPropositionSymbol() {
		Assert.assertTrue(PropositionSymbolImpl.isPropositionSymbol("True"));
		Assert.assertTrue(PropositionSymbolImpl.isPropositionSymbol("False"));
		Assert.assertTrue(PropositionSymbolImpl.isPropositionSymbol("A"));
		Assert.assertTrue(PropositionSymbolImpl.isPropositionSymbol("A1"));
		Assert.assertTrue(PropositionSymbolImpl.isPropositionSymbol("A_1"));
		Assert.assertTrue(PropositionSymbolImpl.isPropositionSymbol("a"));
		Assert.assertTrue(PropositionSymbolImpl.isPropositionSymbol("a1"));
		Assert.assertTrue(PropositionSymbolImpl.isPropositionSymbol("A_1"));
		Assert.assertTrue(PropositionSymbolImpl.isPropositionSymbol("_"));
		Assert.assertTrue(PropositionSymbolImpl.isPropositionSymbol("_1"));
		Assert.assertTrue(PropositionSymbolImpl.isPropositionSymbol("_1_2"));
		Assert.assertTrue(PropositionSymbolImpl.isPropositionSymbol("$"));
		Assert.assertTrue(PropositionSymbolImpl.isPropositionSymbol("$1"));
		Assert.assertTrue(PropositionSymbolImpl.isPropositionSymbol("$1_1"));
		
		// Commas not allowed (only legal java identifier characters).
		Assert.assertFalse(PropositionSymbolImpl.isPropositionSymbol("A1,2"));
		Assert.assertFalse(PropositionSymbolImpl.isPropositionSymbol(" A"));
		Assert.assertFalse(PropositionSymbolImpl.isPropositionSymbol("A "));
		Assert.assertFalse(PropositionSymbolImpl.isPropositionSymbol("A B"));
	}
	
	@Test
	public void test_isPropositionSymbolDoesNotContainConnectiveChars() {
		// '~', '&', '|', '=', '<', '>'
		Assert.assertFalse(PropositionSymbolImpl.isPropositionSymbol("~"));
		Assert.assertFalse(PropositionSymbolImpl.isPropositionSymbol("&"));
		Assert.assertFalse(PropositionSymbolImpl.isPropositionSymbol("|"));
		Assert.assertFalse(PropositionSymbolImpl.isPropositionSymbol("="));
		Assert.assertFalse(PropositionSymbolImpl.isPropositionSymbol("<"));
		Assert.assertFalse(PropositionSymbolImpl.isPropositionSymbol(">"));
		
		Assert.assertFalse(PropositionSymbolImpl.isPropositionSymbol("A~"));
		Assert.assertFalse(PropositionSymbolImpl.isPropositionSymbol("A&"));
		Assert.assertFalse(PropositionSymbolImpl.isPropositionSymbol("A|"));
		Assert.assertFalse(PropositionSymbolImpl.isPropositionSymbol("A="));
		Assert.assertFalse(PropositionSymbolImpl.isPropositionSymbol("A<"));
		Assert.assertFalse(PropositionSymbolImpl.isPropositionSymbol("A>"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void test_IllegalArgumentOnConstruction() {
		new PropositionSymbolImpl("A_1,2");
	}
}
