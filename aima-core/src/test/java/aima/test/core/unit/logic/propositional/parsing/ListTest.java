package aima.test.core.unit.logic.propositional.parsing;

import java.util.ArrayList;
import java.util.List;

import aima.core.logic.propositional.parsing.ast.PropositionSymbolImpl;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Ravi Mohan
 * 
 */
public class ListTest {

	@Test
	public void testListOfSymbolsClone() {
		ArrayList<PropositionSymbolImpl> l = new ArrayList<PropositionSymbolImpl>();
		l.add(new PropositionSymbolImpl("A"));
		l.add(new PropositionSymbolImpl("B"));
		l.add(new PropositionSymbolImpl("C"));
		List<PropositionSymbolImpl> l2 = new ArrayList<PropositionSymbolImpl>(l);
		l2.remove(new PropositionSymbolImpl("B"));
		Assert.assertEquals(3, l.size());
		Assert.assertEquals(2, l2.size());
	}

	@Test
	public void testListRemove() {
		List<Integer> one = new ArrayList<Integer>();
		one.add(new Integer(1));
		Assert.assertEquals(1, one.size());
		one.remove(0);
		Assert.assertEquals(0, one.size());
	}
}
