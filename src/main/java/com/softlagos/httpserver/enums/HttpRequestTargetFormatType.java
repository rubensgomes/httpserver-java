/**
 * Copyright (C) 1999-2016 Rubens Gomes <rubens.s.gomes@gmail.com>.
 * All Rights Reserved.
 *
 * File: HttpRequestTargetFormatType.java
 *
 * Author: Rubens Gomes
 */
package com.softlagos.httpserver.enums;

/**
 * The different HTTP request target formats.
 *
 * @author Rubens Gomes
 * @see "Section 5.3 of RFC7230 - Hypertext Transfer Protocol
 * (HTTP/1.1): Message Syntax and Routing"
 */
public enum HttpRequestTargetFormatType
{
    ORIGIN_FORM ("ORIGIN_FORM"),
    ABSOLUTE_FORM ("ABSOLUTE_FORM"),
    AUTHORITY_FORM ("AUTHORITY_FORM"),
    ASTERISK_FORM ("ASTERISK_FORM");

    /**
     * Compares the given text with this enum constant
     * variable instance.
     *
     * @param type the enum constant text to compare
     * @return true, if successful.
     */
    public boolean typeCompare(String type)
    {
        if( type == null || type.trim().length() == 0 )
        {
            return false;
        }

        return v_type.equalsIgnoreCase(type.trim());
    }

    /**
     * Gets the corresponding text for this enum constant
     * variable instance.
     *
     * @return the text represenation of this enum constant
     * variable instance.
     */
    public String getType()
    {
        return v_type;
    }

    /**
     * Gets the enum constant variable instance corresponding
     * to the given text.
     *
     * @param type the enum constant text to compare
     * @return the enum constant corresponding to the given
     * text.  If an enum constant is not found for the given
     * text, null is returned.
     */
    public static final HttpRequestTargetFormatType getEnum(String type)
    {
        if( type == null || type.trim().length() == 0 )
        {
            return null;
        }

        if (null == TYPES)
        {
            TYPES = HttpRequestTargetFormatType.class.getEnumConstants();
        }

        for (HttpRequestTargetFormatType enum_type : TYPES)
        {
            if (enum_type.typeCompare(type))
            {
                return enum_type;
            }
        }

        return null;
    }

    /**
     * Checks if the given text corresponds to one of the
     * enum constants defined.
     *
     * @param type the enum constant text to compare
     * @return true, if the type is a valid enum constant.
     */
    public static final boolean isType(String type)
    {

        return getEnum(type) == null ? false : true;
    }

    // ------ >>> Private <<< ------
    private HttpRequestTargetFormatType(String type)
    {
        v_type = type;
    }

    private final String v_type;
    private static HttpRequestTargetFormatType TYPES [] = null;
}
