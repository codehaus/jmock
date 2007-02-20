package org.jmock.lib;

import org.jmock.api.MockObjectNamingScheme;

/**
 * A naming scheme in which the implicit name for a mock object is
 * the mocked type's name with the first character in lower case.
 * E.g. A mock object of type HelloWorld would be called "helloWorld".
 * 
 * This is the naming scheme used by default.
 * 
 * @author npryce
 *
 */
public class DefaultNamingScheme implements MockObjectNamingScheme {
    public static final DefaultNamingScheme INSTANCE = new DefaultNamingScheme();
    
    public String defaultNameFor(Class<?> typeToMock) {
        String simpleName = typeToMock.getSimpleName();
        int lci = indexOfFirstLowerCaseLetter(simpleName);
        
        if (lci == 0) {
            return simpleName;
        }
        
        int capsEnd = (lci == 1 || lci == simpleName.length()) ? lci : lci - 1;
        
        String caps = simpleName.substring(0, capsEnd);
        String rest = simpleName.substring(capsEnd);
        
        return caps.toLowerCase() + rest;
    }

    private int indexOfFirstLowerCaseLetter(String simpleName) {
        int i = 0;
        
        while (i < simpleName.length() && !Character.isLowerCase(simpleName.charAt(i))) {
            i++;
        }
        
        return i;
    }
}
