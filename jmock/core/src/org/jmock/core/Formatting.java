/*  Copyright (c) 2000-2004 jMock.org
 */
package org.jmock.core;

import java.lang.reflect.Array;
import java.lang.reflect.Proxy;
import java.util.Collection;

/**
 * @since 1.0
 */
public class Formatting
{
    public static String toReadableString( Object element ) {
        if (element == null) {
            return "null";
        } else if (Proxy.isProxyClass(element.getClass())) {
            return unpackProxy(element).toString();
        } else if (element.getClass().isArray()) {
            return join(element, new StringBuffer()).toString();
        } else {
            return element.toString();
        }
    }

    private static Object unpackProxy( Object element ) {
        Object invocationHandler = Proxy.getInvocationHandler(element);
        return (invocationHandler instanceof DynamicMock
                ? invocationHandler
                : element);
    }

    public static StringBuffer join( Object array, StringBuffer buf ) {
        return join(array, buf, "[", "]");
    }

    public static StringBuffer join( Collection collection, StringBuffer buf, String prefix, String postfix ) {
        return join(collection.toArray(), buf, prefix, postfix);
    }

    public static StringBuffer join( Collection collection, StringBuffer buf,
                                     String prefix, String separator, String postfix ) {
        return join(collection.toArray(), buf, prefix, separator, postfix);
    }

    public static StringBuffer join( Object array, StringBuffer buf, String prefix, String postfix ) {
        return join(array, buf, prefix, ", ", postfix);
    }

    public static StringBuffer join( Object array, StringBuffer buf,
                                     String prefix, String separator, String postfix ) {
        buf.append(prefix);
        for (int i = 0; i < Array.getLength(array); i++) {
            if (i > 0) buf.append(separator);

            Object element = Array.get(array, i);

            if (null == element) {
                buf.append("<null>");
            } else if (element.getClass().isArray()) {
                join(element, buf);
            } else {
                buf.append("<");
                buf.append(toReadableString(element));
                buf.append(">");
            }
        }
        buf.append(postfix);
        return buf;
    }

    public static String classShortName( Class c ) {
        String fullTypeName = c.getName();
        return fullTypeName.substring(Math.max(fullTypeName.lastIndexOf('.'), fullTypeName.lastIndexOf('$')) + 1);
    }
}