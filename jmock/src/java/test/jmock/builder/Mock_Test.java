/* Copyright (c) 2000-2003, jMock.org. See LICENSE.txt */
package test.jmock.builder;

import org.jmock.builder.Mock;

import junit.framework.TestCase;
import test.jmock.dynamic.DummyInterface;
import test.jmock.dynamic.testsupport.MockDynamicMock;

public class Mock_Test extends TestCase {

    private MockDynamicMock mockCoreMock = new MockDynamicMock();
    private Mock mock = new Mock(mockCoreMock);

    public void testToStringComesFromUnderlyingDynamicMock() {
        mockCoreMock.toStringResult = "some string here";
        assertEquals("Should be same string", "some string here", mock.toString());
    }
    
    public void testPassesExplicitNameToUnderlyingCoreMock() {
        String explicitName = "EXPLICIT NAME";
        
    	Mock mock = new Mock( DummyInterface.class, explicitName );
        
        assertEquals( "should be explicit name", explicitName, mock.toString() );
    }
    
    public void testMethodAddsInvocationMockerAndReturnsMethodExpectation() {
        mockCoreMock.addCalls.setExpected(1);

        assertNotNull("Should be method expectation", mock.method("methodname"));
        mockCoreMock.verifyExpectations();
    }

    public void testVerifyCallsUnderlyingMock() {
        mockCoreMock.verifyCalls.setExpected(1);

        mock.verify();

        mockCoreMock.verifyExpectations();
    }
}