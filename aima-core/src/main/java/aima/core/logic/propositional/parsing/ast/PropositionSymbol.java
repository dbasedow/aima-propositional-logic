package aima.core.logic.propositional.parsing.ast;


public interface PropositionSymbol extends Sentence {
    public boolean isAlwaysTrue();

    public boolean isAlwaysFalse();

    public String getSymbol();
}
