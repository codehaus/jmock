package org.jmock.test.unit.lib;

import java.util.Date;

import junit.framework.TestCase;

import org.jmock.api.Invocation;
import org.jmock.api.Invokable;
import org.jmock.internal.DispatcherControl;
import org.jmock.lib.JavaReflectionImposteriser;
import org.jmock.test.unit.support.SyntheticEmptyInterfaceClassLoader;

public class JavaReflectionImposteriserTests extends TestCase {
    JavaReflectionImposteriser imposteriser = new JavaReflectionImposteriser();
    Invokable mockObject = new Invokable() {
        public Object invoke(Invocation invocation) throws Throwable {
            return null;
        }
    };
    
    public void testCanOnlyImposteriseInterfaces() {
        assertTrue("should report that it can imposterise interfaces",
                   imposteriser.canImposterise(Runnable.class));
        assertTrue("should report that it cannot imposterise classes",
                   !imposteriser.canImposterise(Date.class));
        assertTrue("should report that it cannot imposterise primitive types",
                   !imposteriser.canImposterise(int.class));
        assertTrue("should report that it cannot imposterise void",
                   !imposteriser.canImposterise(void.class));
    }
    
    public void testCanMockTypesFromADynamicClassLoader() throws ClassNotFoundException {
        ClassLoader interfaceClassLoader = new SyntheticEmptyInterfaceClassLoader();
        Class<?> interfaceClass = interfaceClassLoader.loadClass("$UniqueTypeName$");
        
        Object o = imposteriser.imposterise(mockObject, interfaceClass, new Class[0]);
        
        assertTrue(interfaceClass.isInstance(o));
    }
    
    public void testCanSimultaneouslyMockTypesFromMultipleClassLoaders() throws ClassNotFoundException {
        Class<?> interfaceClass1 = (new SyntheticEmptyInterfaceClassLoader()).loadClass("$UniqueTypeName1$");
        Class<?> interfaceClass2 = DispatcherControl.class;
        
        Object o = imposteriser.imposterise(mockObject, interfaceClass1, interfaceClass2);
        
        assertTrue(interfaceClass1.isInstance(o));
        assertTrue(interfaceClass2.isInstance(o));
    }
}
