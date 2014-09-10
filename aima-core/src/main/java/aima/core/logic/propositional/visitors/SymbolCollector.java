package aima.core.logic.propositional.visitors;

import java.util.LinkedHashSet;
import java.util.Set;

import aima.core.logic.propositional.parsing.ast.PropositionSymbolImpl;
import aima.core.logic.propositional.parsing.ast.Sentence;

/**
 * Utility class for collecting propositional symbols from sentences. Will
 * exclude the always false and true symbols.
 * 
 * @author Ravi Mohan
 * @author Ciaran O'Reilly
 */
public class SymbolCollector extends BasicGatherer<PropositionSymbolImpl> {

	/**
	 * Collect a set of propositional symbols from a list of given sentences.
	 * 
	 * @param sentences
	 *            a list of sentences from which to collect symbols.
	 * @return a set of all the proposition symbols that are not always true or
	 *         false contained within the input sentences.
	 */
	public static Set<PropositionSymbolImpl> getSymbolsFrom(Sentence... sentences) {
		Set<PropositionSymbolImpl> result = new LinkedHashSet<PropositionSymbolImpl>();

		SymbolCollector symbolCollector = new SymbolCollector();
		for (Sentence s : sentences) {
			result = s.accept(symbolCollector, result);
		}

		return result;
	}

	@Override
	public Set<PropositionSymbolImpl> visitPropositionSymbol(PropositionSymbolImpl s,
			Set<PropositionSymbolImpl> arg) {
		// Do not add the always true or false symbols
		if (!s.isAlwaysTrue() && !s.isAlwaysFalse()) {
			arg.add(s);
		}
		return arg;
	}
}
