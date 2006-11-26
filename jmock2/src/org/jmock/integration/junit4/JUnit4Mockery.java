package org.jmock.integration.junit4;

import org.jmock.Mockery;
import org.jmock.lib.AssertionErrorTranslator;

public class JUnit4Mockery extends Mockery {
    public JUnit4Mockery() {
        setExpectationErrorTranslator(AssertionErrorTranslator.INSTANCE);
    }
}
