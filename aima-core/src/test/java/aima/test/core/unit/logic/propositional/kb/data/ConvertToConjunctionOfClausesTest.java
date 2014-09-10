package aima.test.core.unit.logic.propositional.kb.data;

import aima.core.logic.propositional.parsing.ast.SentenceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import aima.core.logic.propositional.kb.data.ConjunctionOfClauses;
import aima.core.logic.propositional.parsing.PLParser;
import aima.core.logic.propositional.visitors.ConvertToConjunctionOfClauses;

/**
 * @author Ravi Mohan
 * 
 */
public class ConvertToConjunctionOfClausesTest {
	private PLParser parser = new PLParser();

	@Before
	public void setUp() {
	}

	@Test
	public void testSymbolTransform() {
		SentenceImpl symbol = parser.parse("A");
		ConjunctionOfClauses transformed = ConvertToConjunctionOfClauses.convert(symbol);
		Assert.assertEquals("{{A}}", transformed.toString());
	}

	@Test
	public void testBasicSentenceTransformation() {
		SentenceImpl and = parser.parse("A & B");
		ConjunctionOfClauses transformedAnd = ConvertToConjunctionOfClauses.convert(and);
		Assert.assertEquals("{{A}, {B}}", transformedAnd.toString());

		SentenceImpl or = parser.parse("A | B");
		ConjunctionOfClauses transformedOr = ConvertToConjunctionOfClauses.convert(or);
		Assert.assertEquals("{{A, B}}", transformedOr.toString());

		SentenceImpl not = parser.parse("~C");
		ConjunctionOfClauses transformedNot = ConvertToConjunctionOfClauses.convert(not);
		Assert.assertEquals("{{~C}}", transformedNot.toString());
	}

	@Test
	public void testImplicationTransformation() {
		SentenceImpl impl = parser.parse("A => B");
		ConjunctionOfClauses transformedImpl = ConvertToConjunctionOfClauses.convert(impl);
		Assert.assertEquals("{{~A, B}}", transformedImpl.toString());
	}

	@Test
	public void testBiConditionalTransformation() {
		SentenceImpl bic = parser.parse("A <=> B");
		ConjunctionOfClauses transformedBic = ConvertToConjunctionOfClauses.convert(bic);
		Assert.assertEquals("{{~A, B}, {~B, A}}", transformedBic.toString());
	}

	@Test
	public void testTwoSuccessiveNotsTransformation() {
		SentenceImpl twoNots = parser.parse("~~A");
		ConjunctionOfClauses transformed = ConvertToConjunctionOfClauses.convert(twoNots);
		Assert.assertEquals("{{A}}", transformed.toString());
	}

	@Test
	public void testThreeSuccessiveNotsTransformation() {
		SentenceImpl threeNots = parser.parse("~~~A");
		ConjunctionOfClauses transformed = ConvertToConjunctionOfClauses.convert(threeNots);
		Assert.assertEquals("{{~A}}", transformed.toString());
	}

	@Test
	public void testFourSuccessiveNotsTransformation() {
		SentenceImpl fourNots = parser.parse("~~~~A");
		ConjunctionOfClauses transformed = ConvertToConjunctionOfClauses.convert(fourNots);
		Assert.assertEquals("{{A}}", transformed.toString());
	}

	@Test
	public void testDeMorgan1() {
		SentenceImpl dm = parser.parse("~(A & B)");
		ConjunctionOfClauses transformed = ConvertToConjunctionOfClauses.convert(dm);
		Assert.assertEquals("{{~A, ~B}}", transformed.toString());
	}

	@Test
	public void testDeMorgan2() {
		SentenceImpl dm = parser.parse("~(A | B)");
		ConjunctionOfClauses transformed = ConvertToConjunctionOfClauses.convert(dm);
		Assert.assertEquals("{{~A}, {~B}}", transformed.toString());
	}

	@Test
	public void testOrDistribution1() {
		SentenceImpl or =  parser.parse("A & B | C)");
		ConjunctionOfClauses transformed = ConvertToConjunctionOfClauses.convert(or);
		Assert.assertEquals("{{A, C}, {B, C}}", transformed.toString());
	}

	@Test
	public void testOrDistribution2() {
		SentenceImpl or = parser.parse("A | B & C");
		ConjunctionOfClauses transformed = ConvertToConjunctionOfClauses.convert(or);
		Assert.assertEquals("{{A, B}, {A, C}}", transformed.toString());
	}

	@Test
	public void testAimaExample() {
		SentenceImpl aimaEg = parser.parse("B11 <=> P12 | P21");
		ConjunctionOfClauses transformed = ConvertToConjunctionOfClauses.convert(aimaEg);
		Assert.assertEquals("{{~B11, P12, P21}, {~P12, B11}, {~P21, B11}}", transformed.toString());
	}
	
	@Test
	public void testNested() {
		SentenceImpl nested = parser.parse("A | (B | (C | (D & E)))");
		ConjunctionOfClauses transformed = ConvertToConjunctionOfClauses.convert(nested);
		Assert.assertEquals("{{A, B, C, D}, {A, B, C, E}}", transformed.toString());
		
		nested = parser.parse("A | (B | (C & (D & E)))");
		transformed = ConvertToConjunctionOfClauses.convert(nested);
		Assert.assertEquals("{{A, B, C}, {A, B, D}, {A, B, E}}", transformed.toString());
		
		nested = parser.parse("A | (B | (C & (D & (E | F))))");
		transformed = ConvertToConjunctionOfClauses.convert(nested);
		Assert.assertEquals("{{A, B, C}, {A, B, D}, {A, B, E, F}}", transformed.toString());
		
		nested = parser.parse("(A | (B | (C & D))) | E | (F | (G | (H & I)))");
		transformed = ConvertToConjunctionOfClauses.convert(nested);
		Assert.assertEquals("{{A, B, C, E, F, G, H}, {A, B, D, E, F, G, H}, {A, B, C, E, F, G, I}, {A, B, D, E, F, G, I}}", transformed.toString());
		
		nested = parser.parse("(((~P | ~Q) => ~(P | Q)) => R)");
		transformed = ConvertToConjunctionOfClauses.convert(nested);
		Assert.assertEquals("{{~P, ~Q, R}, {P, Q, R}}", transformed.toString());
		
		nested = parser.parse("~(((~P | ~Q) => ~(P | Q)) => R)");
		transformed = ConvertToConjunctionOfClauses.convert(nested);
		Assert.assertEquals("{{P, ~P}, {Q, ~P}, {P, ~Q}, {Q, ~Q}, {~R}}", transformed.toString());
	}
	
	@Test
	public void testIssue78() {
		// (  ( NOT J1007 )  OR  ( NOT ( OR J1008 J1009 J1010 J1011 J1012 J1013 J1014 J1015  )  )  )
		SentenceImpl issue78Eg = parser.parse("(  ( ~ J1007 )  |  ( ~ ( J1008 | J1009 | J1010 | J1011 | J1012 | J1013 | J1014 | J1015  )  ) )");
		ConjunctionOfClauses transformed = ConvertToConjunctionOfClauses.convert(issue78Eg);
		Assert.assertEquals("{{~J1007, ~J1008}, {~J1007, ~J1009}, {~J1007, ~J1010}, {~J1007, ~J1011}, {~J1007, ~J1012}, {~J1007, ~J1013}, {~J1007, ~J1014}, {~J1007, ~J1015}}", transformed.toString());
	}
}
