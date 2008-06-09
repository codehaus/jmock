/*  Copyright (c) 2000-2006 jMock.org
 */
package org.jmock.test.unit.integration.junit3;

import junit.framework.TestCase;

import org.jmock.integration.junit3.VerifyingTestCase;


public class VerifyingTestCaseTests extends TestCase {
    public static class ExampleTestCase extends VerifyingTestCase {
        public ExampleTestCase() {
            setName("testMethod");
        }
        
        public void testMethod() {
            // Success!
        }
    }
    
    public void testCanBeConstructedWithAName() {
        String name = "NAME";

        VerifyingTestCase testCase = new VerifyingTestCase(name) {
        };

        assertEquals("name", name, testCase.getName());
    }

    private boolean verifierWasRun = false;
    
    public void testRunsVerifiersAfterTest() throws Throwable {
        ExampleTestCase testCase = new ExampleTestCase();
        
        testCase.addVerifier(new Runnable() {
            public void run() {
                verifierWasRun = true;
            }
        });
        
        testCase.runBare();
        
        assertTrue(verifierWasRun);
    }

    public void testOverridingRunTestDoesNotAffectVerification() throws Throwable {
        ExampleTestCase testCase = new ExampleTestCase() {
            @Override
            public void runTest() {
            }
        };
        
        testCase.addVerifier(new Runnable() {
            public void run() {
                verifierWasRun = true;
            }
        });
        
        testCase.runBare();
        
        assertTrue(verifierWasRun);
    }
    
    public void testOverridingSetUpAndTearDownDoesNotAffectVerification() throws Throwable {
        ExampleTestCase testCase = new ExampleTestCase() {
            @Override public void setUp() { }

            @Override public void tearDown() { }
        };

        testCase.addVerifier(new Runnable() {
            public void run() {
                verifierWasRun = true;
            }
        });
        
        testCase.runBare();
        
        assertTrue(verifierWasRun);
    }

    public static class FailingExampleTestCase extends VerifyingTestCase {
        public static final Exception tearDownException = new Exception("tear down");
        public static final Exception testException = new Exception("test");

        private FailingExampleTestCase(String testName) {
            super(testName);
        }

        @Override public void tearDown() throws Exception { 
            throw tearDownException;

        }
        public void testDoesNotThrowException() throws Exception {
            // no op
        }

        public void testThrowsExpectedException() throws Exception {
            throw testException;
        }
    }

    public void testThrowsTestExceptionRatherThanTearDownException() throws Throwable {
        try {
            new FailingExampleTestCase("testThrowsExpectedException").runBare();
            fail("should have thrown exception");
        } catch (Exception actual) {
            assertSame(FailingExampleTestCase.testException, actual);
        }
    }

    public void testThrowsTearDownExceptionWhenNoTestException() throws Throwable {
        try {
            new FailingExampleTestCase("testDoesNotThrowException").runBare();
            fail("should have thrown exception");
        } catch (Exception actual) {
            assertSame(FailingExampleTestCase.tearDownException, actual);
        }
    }

}
