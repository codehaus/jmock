/* Copyright (c) 2000-2003, jMock.org. See LICENSE.txt */
package test.jmock.examples.timedcache;

import junit.framework.Assert;
import org.jmock.core.Invocation;
import org.jmock.core.InvocationMatcher;


public class InvokeCountMatcher
        implements InvocationMatcher
{
    int expectedCount;
    int invocationCount = 0;

    public InvokeCountMatcher( int expectedCount ) {
        this.expectedCount = expectedCount;
    }

    public boolean matches( Invocation invocation ) {
        return invocationCount < expectedCount;
    }

    public void verify() {
        Assert.assertTrue("Invoked wrong number of times", expectedCount == invocationCount);
    }

    public boolean hasDescription() {
        return true;
    }

    public StringBuffer describeTo( StringBuffer buffer ) {
        return buffer.append("expected ").append(expectedCount)
                .append(" times, invokedObject ").append(invocationCount).append(" times");
    }

    public void invoked( Invocation invocation ) {
        invocationCount++;
    }
}