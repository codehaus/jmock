package org.jmock.test.acceptance.junit4;

import junit.framework.TestCase;

import org.jmock.test.acceptance.junit4.testdata.JUnit4WithRulesExamples;

public class JUnit4WithRulesTestRunnerTests extends TestCase {
    FailureRecordingRunListener listener = new FailureRecordingRunListener();
    
    public void testTheJUnit4TestRunnerReportsPassingTestsAsSuccessful() {
        listener.runTestIn(JUnit4WithRulesExamples.SatisfiesExpectations.class);
        listener.assertTestSucceeded();
    }
    
    public void testTheJUnit4TestRunnerAutomaticallyAssertsThatAllExpectationsHaveBeenSatisfied() {
        listener.runTestIn(JUnit4WithRulesExamples.DoesNotSatisfyExpectations.class);
        listener.assertTestFailedWith(AssertionError.class);
    }
    
    
    public void testTheJUnit4TestRunnerLooksForTheMockeryInBaseClasses() {
        listener.runTestIn(JUnit4WithRulesExamples.DerivedAndDoesNotSatisfyExpectations.class);
        listener.assertTestFailedWith(AssertionError.class);
    }

    
    // See issue JMOCK-156
    public void testReportsMocksAreNotSatisfiedWhenExpectedExceptionIsThrown() {
        listener.runTestIn(JUnit4WithRulesExamples.ThrowsExpectedException.class);
        listener.assertTestFailedWith(AssertionError.class);
    }
    
    public void testWorksWhenMoreThanOneMockeryRuleCreated() {
        listener.runTestIn(JUnit4WithRulesExamples.CreatesTwoMockeriesAndDoesNotSatisfyExpectations.class);
        listener.assertTestFailedWith(AssertionError.class);
    }

    public void testAutoInstantiatesMocks() {
        listener.runTestIn(JUnit4WithRulesExamples.AutoInstantiatesMocks.class);
        listener.assertTestSucceeded();
    }
}
