package aima.core.logic.propositional.parsing.ast;


import aima.core.logic.propositional.parsing.PLVisitor;

public interface Sentence {
    public Connective getConnective();

    public int getNumberSimplerSentences();

    public Sentence getSimplerSentence(int offset);

    public boolean isNotSentence();

    public boolean isAndSentence();

    public boolean isOrSentence();

    public boolean isImplicationSentence();

    public boolean isBiconditionalSentence();

    public boolean isPropositionSymbol();

    public boolean isUnarySentence();

    public boolean isBinarySentence();

    public <A, R> R accept(PLVisitor<A, R> plv, A arg);

    public String bracketSentenceIfNecessary(Connective parentConnective, Sentence childSentence);
}
