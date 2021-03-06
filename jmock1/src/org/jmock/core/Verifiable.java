/*  Copyright (c) 2000-2004 jMock.org
 */
package org.jmock.core;

/**
 * A <em>Verifiable</em> is an object that can confirm at the end of a unit test that
 * the correct behvaiour has occurred.
 * 
 * @see org.jmock.util.Verifier Verifier to check all the Verifiables in an object.
 * @since 1.0
 */
public interface Verifiable
{

    /**
     * Throw an AssertionFailedException if any expectations have not been met.
     * Implementations of this method must be idempotent: jMock can call this method
     * more than once when verifying expectations at the end of a test.
     */
    public abstract void verify();
}
