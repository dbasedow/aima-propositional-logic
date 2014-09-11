package aima.test.core.unit.util;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import aima.test.core.unit.util.datastructure.FIFOQueueTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({FIFOQueueTest.class,
        SetOpsTest.class, UtilTest.class})
public class UtilTestSuite {

}
