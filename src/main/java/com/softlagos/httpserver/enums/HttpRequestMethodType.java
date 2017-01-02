/**
 * Copyright (C) 1999-2016 Rubens Gomes <rubens.s.gomes@gmail.com>.
 * All Rights Reserved.
 *
 * File: HttpRequestMethodType.java
 *
 * Author: Rubens Gomes
 */
package com.softlagos.httpserver.enums;

/**
 * The different HTTP Request Methods.
 *
 * @author Rubens Gomes
 * @see "Section 4 of RFC7231 - Hypertext Transfer Protocol
 * (HTTP/1.1): Semantics and Content"
 */
public enum HttpRequestMethodType
{
    /*
     * Transfer a current representation of the target
     * resource.
     */
    GET ("GET"),
    /*
     * Same as GET, but only transfer the status line
     * and header section.
     */
    HEAD ("HEAD"),
    /*
     * Perform resource-specific processing on the
     * request payload.
     */
    POST ("POST"),
    /*
     * Replace all current representations of the
     * target resource with the request payload.
     */
    PUT ("PUT"),
    /*
     * Remove all current representations of the
     * target resource.
     */
    DELETE ("DELETE"),
    /*
     * Establish a tunnel to the server identified by
     * the target resource.
     */
    CONNECT ("CONNECT"),
    /*
     * Describe the communication options for the
     * target resource.
     */
    OPTIONS ("OPTIONS"),
    /*
     * Perform a message loop-back test along the path
     * to the target resource.
     */
    TRACE ("TRACE");

    /**
     * Compares the given method with this enum constant
     * variable instance.
     *
     * @param method the method name to compare
     * @return true, if successful.
     */
    public boolean methodCompare(String method)
    {
        if(method == null || method.trim().length() == 0 )
        {
            return false;
        }

        return v_method.equalsIgnoreCase(method.trim());
    }

    /**
     * Gets the corresponding method for this enum constant
     * variable instance.
     *
     * @return the method represenstation of this enum constant
     * variable instance.
     */
    public String getMethod()
    {
        return v_method;
    }

    /**
     * Gets the enum constant variable instance corresponding
     * to the given method.
     *
     * @param method the method to compare
     * @return the enum constant corresponding to the given
     * method.  If an enum constant is not found for the given
     * method, null is returned.
     */
    public static final HttpRequestMethodType getEnum(String method)
    {
        if( method == null || method.trim().length() == 0)
        {
            return null;
        }

        if (null == TYPES)
        {
            TYPES = HttpRequestMethodType.class.getEnumConstants();
        }

        for (HttpRequestMethodType enum_type : TYPES)
        {
            if (enum_type.methodCompare(method))
            {
                return enum_type;
            }
        }

        return null;
    }

    /**
     * Checks if the given method corresponds to one of the
     * enum constants defined.
     *
     * @param method the method to compare
     * @return true, if the method is valid for one of the
     * enum constants.
     */
    public static final boolean isMethod(String method)
    {
        if( method == null || method.trim().length() == 0)
        {
            return false;
        }

        return getEnum(method) == null ? false : true;
    }

    // ------ >>> Private <<< ------
    private HttpRequestMethodType(String method)
    {
        v_method = method;
    }

    private final String v_method;
    private static HttpRequestMethodType TYPES [] = null;

}
