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
}
