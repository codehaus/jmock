/* Copyright (c) 2000-2003, jMock.org. See LICENSE.txt */
package test.jmock.builder;

import junit.framework.TestCase;

import org.jmock.C;
import org.jmock.Constraint;
import org.jmock.builder.InvocationMockerBuilder;
import org.jmock.dynamic.matcher.ArgumentsMatcher;
import org.jmock.dynamic.matcher.InvokeOnceMatcher;
import org.jmock.dynamic.stub.ReturnStub;
import org.jmock.dynamic.stub.ThrowStub;
import org.jmock.dynamic.stub.VoidStub;

public class InvocationMockerBuilderTest extends TestCase {
    private MockStubMatchersCollection mocker = new MockStubMatchersCollection();
    private InvocationMockerBuilder builder = new InvocationMockerBuilder(mocker);

    public void testWhenPassedAddsArgumentsMatcher() {
    	mocker.addedMatcherType.setExpected(ArgumentsMatcher.class);
    	
    	assertNotNull("Should be Stub Builder", builder.whenPassed(new Constraint[0]));
    	
    	mocker.verifyExpectations();
    }
    
    public void testWhenPassedWithOneObjectArgumentAddsArgumentsMatcher() {
    	mocker.addedMatcherType.setExpected(ArgumentsMatcher.class);
    	
    	assertNotNull("Should be Stub Builder", builder.whenPassed(new Object()));
    	
    	mocker.verifyExpectations();
    }
    
    public void testWhenPassedWithTwoObjectArgumentsAddsArgumentsMatcher() {
    	mocker.addedMatcherType.setExpected(ArgumentsMatcher.class);
    	
    	assertNotNull("Should be Stub Builder", 
    				  builder.whenPassed(new Object(),new Object()));
    	
    	mocker.verifyExpectations();
    }
    
    public void testNoParamsAddsNoArgumentMatcher() {
    	mocker.addedMatcher.setExpected(C.NO_ARGS);
    	
    	assertNotNull("Should be Stub Builder", builder.noParams());
    	
    	mocker.verifyExpectations();
    }
    
    public void testIsVoidSetsVoidStub() {
        mocker.setStubType.setExpected(VoidStub.class);

        assertNotNull("Should be expectation builder", builder.isVoid());

        mocker.verifyExpectations();
    }

    public void testWillReturnSetsReturnStub() {
        mocker.setStubType.setExpected(ReturnStub.class);

        assertNotNull("Should be expectation builder", builder.willReturn("return value"));

        mocker.verifyExpectations();
    }

    public void testWillThrowSetsThrowStub() {
        mocker.setStubType.setExpected(ThrowStub.class);

        assertNotNull("Should be expectation builder", builder.willThrow(new Exception("thrown value")));

        mocker.verifyExpectations();
    }
    
    public void testExpectOnceAddsCallOnceMatcher() {
    	mocker.addedMatcherType.setExpected(InvokeOnceMatcher.class);
    	
    	assertNotNull("Should be ExpectationBuilder", builder.expectOnce() );
    	
    	mocker.verifyExpectations();
    }
}
