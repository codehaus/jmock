/*
 * Copyright (c) 2000-2004 jMock.org
 */
package org.jmock.easy.internal;

import org.jmock.core.OrderedInvocationDispatcher;

public class EasyInvocationDispatcher extends OrderedInvocationDispatcher {
	public EasyInvocationDispatcher() {
		super(new OrderedInvocationDispatcher.FIFOInvokablesCollection());
	}
}