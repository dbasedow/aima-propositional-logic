package aima.test.core.unit.logic.propositional;

import aima.test.core.unit.logic.propositional.parsing.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import aima.test.core.unit.logic.propositional.kb.data.ClauseTest;
import aima.test.core.unit.logic.propositional.kb.data.ConvertToConjunctionOfClausesTest;
import aima.test.core.unit.logic.propositional.kb.data.LiteralTest;
import aima.test.core.unit.logic.propositional.kb.data.ModelTest;
import aima.test.core.unit.logic.propositional.parsing.PropositionSymbolImplTest;
import aima.test.core.unit.logic.propositional.visitors.ConvertToCNFTest;
import aima.test.core.unit.logic.propositional.visitors.SymbolCollectorTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ModelTest.class,
        ComplexSentenceTest.class, ListTest.class, PLLexerTest.class,
        PLParserTest.class, PropositionSymbolImplTest.class,
        ConvertToCNFTest.class, ClauseTest.class,
        ConvertToConjunctionOfClausesTest.class, LiteralTest.class,
        SymbolCollectorTest.class})
public class PropositionalTestSuite {

}
