/*  Copyright (c) 2000-2004 jMock.org
 */
package org.jmock.core;

import junit.framework.AssertionFailedError;


public abstract class AbstractDynamicMock
        implements DynamicMock
{
    private InvocationDispatcher invocationDispatcher;
    private Class mockedType;
    private String name;

    public AbstractDynamicMock( Class mockedType,
                                String name,
                                InvocationDispatcher invocationDispatcher ) {
        this.mockedType = mockedType;
        this.name = name;
        this.invocationDispatcher = invocationDispatcher;
    }

    public Class getMockedType() {
        return mockedType;
    }

    protected Object mockInvocation( Invocation invocation )
            throws Throwable {
        try {
            return invocationDispatcher.dispatch(invocation);
        }
        catch (AssertionFailedError failure) {
            DynamicMockError mockFailure =
                    new DynamicMockError(this, invocation, invocationDispatcher, failure.getMessage());

            mockFailure.fillInStackTrace();
            throw mockFailure;
        }
    }

    public String toString() {
        return this.name;
    }

    public static String mockNameFromClass( Class c ) {
        return "mock" + Formatting.classShortName(c);
    }
}
