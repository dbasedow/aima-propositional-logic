package aima.test.core.unit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import aima.test.core.unit.logic.LogicTestSuite;
import aima.test.core.unit.util.UtilTestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		LogicTestSuite.class,
		UtilTestSuite.class })
public class AllAIMAUnitTestSuite {
}
