/*
 * Created on 11-Jun-2004
 */
package org.jmock.easy.internal;

import java.lang.reflect.Method;

import org.jmock.builder.InvocationMockerDescriber;
import org.jmock.core.Constraint;
import org.jmock.core.InvocationMatcher;
import org.jmock.core.InvocationMocker;
import org.jmock.core.Stub;
import org.jmock.core.constraint.IsEqual;
import org.jmock.core.matcher.ArgumentsMatcher;
import org.jmock.core.matcher.InvokeOnceMatcher;
import org.jmock.core.matcher.MethodNameMatcher;
import org.jmock.core.stub.DefaultResultStub;
import org.jmock.easy.EasyCoreMock;


public class InvocationMatch {
	private InvocationMatcher methodNameMatcher;
	private Object[] methodArguments;
    private Class[] parameterTypes;
	private InvocationMatcher callCountMatcher;
	private Stub         stub;
	
	public void setFromInvocation(EasyCoreMock mock, Method method, Object[] args) {
        parameterTypes = method.getParameterTypes();
		callCountMatcher = new InvokeOnceMatcher();
		methodNameMatcher = new MethodNameMatcher(method.getName());
		methodArguments = args;
		stub = new DefaultResultStub();
	}

	public void addInvocationMockerTo(EasyCoreMock mock) {
		if (isUnset())
			return;
		
        if (isDefault()) {
            mock.addDefaultInvokable(
                    createInvocationMocker(new ArgumentTypesMatcher(parameterTypes)));
        } else {
        	mock.addInvokable(
                    createInvocationMocker(new ArgumentsMatcher(equalArgs(methodArguments))));
        }
	}
	
	public void flush() {
        parameterTypes = null;
        methodArguments = null;
		methodNameMatcher = null;
		callCountMatcher = null;
		stub = null;
	}

	public void expectCallCount(Range range, Stub aStub) {
        setCallMatchAndStub(new InvokeRangeMatcher(range), aStub);
	}

    public void setDefault(Stub aStub) {
        setCallMatchAndStub(null, aStub);
    }

    private void setCallMatchAndStub(InvocationMatcher callCountMatcher, Stub stub) {
        this.callCountMatcher = callCountMatcher;
        this.stub = stub;
    }
    
	private InvocationMocker createInvocationMocker(InvocationMatcher argumentMatcher) {
		InvocationMocker mocker = new InvocationMocker(new InvocationMockerDescriber());
        if (isExpectation())
        		mocker.addMatcher(callCountMatcher);
		mocker.addMatcher(methodNameMatcher);
		mocker.addMatcher(argumentMatcher);
		mocker.setStub(stub);
		return mocker;
	}

	private boolean isUnset() {
		return methodNameMatcher == null;
	}

    private boolean isDefault() {
        return callCountMatcher == null;
    }

    private boolean isExpectation() {
		return callCountMatcher != null;
	}

	static private Constraint[] equalArgs(Object[] args) {
		Constraint[] result = new Constraint[arrayCount(args)];
		for (int i = 0; i < result.length; i++) {
			result[i] = new IsEqual(args[i]);
		}
		return result;
	}

	static private int arrayCount(Object[] args) {
		return args == null ? 0 : args.length;
	}
}