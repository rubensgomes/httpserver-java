/**
 * Copyright (C) 1999-2016 Rubens Gomes <rubens.s.gomes@gmail.com>.
 * All Rights Reserved.
 *
 * File: HttpStatusCodeType.java
 *
 * Author: Rubens Gomes
 */
package com.softlagos.httpserver.enums;

import com.softlagos.Constants;

/**
 * The HTTP Status Code enum.
 *
 * @author Rubens Gomes
 * @see "Section 6 of RFC7231 - Hypertext Transfer Protocol
 * (HTTP/1.1): Semantics and Content"
 */
public enum HttpStatusCodeType
{

    /** The STATU s_100. */
    STATUS_100 (100, "Continue", Constants.HTTP_INFORMATIONAL),

    /** The STATU s_101. */
    STATUS_101 (101, "Switching Protocols", Constants.HTTP_INFORMATIONAL),

    /** The STATU s_200. */
    STATUS_200 (200, "OK", Constants.HTTP_SUCCESSFUL),

    /** The STATU s_201. */
    STATUS_201 (201, "Created", Constants.HTTP_SUCCESSFUL),

    /** The STATU s_202. */
    STATUS_202 (202, "Accepted", Constants.HTTP_SUCCESSFUL),

    /** The STATU s_203. */
    STATUS_203 (203, "Non-Authoritative Information", Constants.HTTP_SUCCESSFUL),

    /** The STATU s_204. */
    STATUS_204 (204, "No Content", Constants.HTTP_SUCCESSFUL),

    /** The STATU s_205. */
    STATUS_205 (205, "Reset Content", Constants.HTTP_SUCCESSFUL),

    /** The STATU s_300. */
    STATUS_300 (300, "Multiple Choices", Constants.HTTP_REDIRECTION),

    /** The STATU s_301. */
    STATUS_301 (301, "Moved Permanently", Constants.HTTP_REDIRECTION),

    /** The STATU s_302. */
    STATUS_302 (302, "Found", Constants.HTTP_REDIRECTION),

    /** The STATU s_303. */
    STATUS_303 (303, "See Other", Constants.HTTP_REDIRECTION),

    /** The STATU s_305. */
    STATUS_305 (305, "Use Proxy", Constants.HTTP_REDIRECTION),

    /** The STATU s_306. */
    STATUS_306 (306, "Unused", Constants.HTTP_REDIRECTION),

    /** The STATU s_307. */
    STATUS_307 (307, "Temporary Redirect", Constants.HTTP_REDIRECTION),

    /** The STATU s_400. */
    STATUS_400 (400, "Bad Request", Constants.HTTP_CLIENT_ERROR),

    /** The STATU s_402. */
    STATUS_402 (402, "Payment Required", Constants.HTTP_CLIENT_ERROR),

    /** The STATU s_403. */
    STATUS_403 (403, "Forbidden", Constants.HTTP_CLIENT_ERROR),

    /** The STATU s_404. */
    STATUS_404 (404, "Not Found", Constants.HTTP_CLIENT_ERROR),

    /** The STATU s_405. */
    STATUS_405 (405, "Method Not Allowed", Constants.HTTP_CLIENT_ERROR),

    /** The STATU s_406. */
    STATUS_406 (406, "Not Acceptable", Constants.HTTP_CLIENT_ERROR),

    /** The STATU s_408. */
    STATUS_408 (408, "Request Timeout", Constants.HTTP_CLIENT_ERROR),

    /** The STATU s_409. */
    STATUS_409 (409, "Conflict", Constants.HTTP_CLIENT_ERROR),

    /** The STATU s_410. */
    STATUS_410 (410, "Gone", Constants.HTTP_CLIENT_ERROR),

    /** The STATU s_411. */
    STATUS_411 (411, "Length Required", Constants.HTTP_CLIENT_ERROR),

    /** The STATU s_413. */
    STATUS_413 (413, "Payload Too Large", Constants.HTTP_CLIENT_ERROR),

    /** The STATU s_414. */
    STATUS_414 (414, "URI Too Long", Constants.HTTP_CLIENT_ERROR),

    /** The STATU s_415. */
    STATUS_415 (415, "Unsupported Media Type", Constants.HTTP_CLIENT_ERROR),

    /** The STATU s_417. */
    STATUS_417 (417, "Expectation Failed", Constants.HTTP_CLIENT_ERROR),

    /** The STATU s_426. */
    STATUS_426 (426, "Upgrade Required", Constants.HTTP_CLIENT_ERROR),

    /** The STATU s_500. */
    STATUS_500 (500, "Internal Server Error", Constants.HTTP_SERVER_ERROR),

    /** The STATU s_501. */
    STATUS_501 (501, "Not Implemented", Constants.HTTP_SERVER_ERROR),

    /** The STATU s_502. */
    STATUS_502 (502, "Bad Gateway", Constants.HTTP_SERVER_ERROR),

    /** The STATU s_503. */
    STATUS_503 (503, "Service Unavailable", Constants.HTTP_SERVER_ERROR),

    /** The STATU s_504. */
    STATUS_504 (504, "Gateway Timeout", Constants.HTTP_SERVER_ERROR),

    /** The STATU s_505. */
    STATUS_505 (505, "HTTP Version Not Supported", Constants.HTTP_SERVER_ERROR);

    /**
     * Compares the given HTTP status code with this enum constant
     * variable instance code.
     *
     * @param status_code the status_code
     * @return true, if successful.
     */
    public boolean statusCodeCompare(int status_code)
    {
        return v_status_code == status_code;
    }

    /**
     * Compares the given HTTP reason phrase with this enum constant
     * variable instance message.
     *
     * @param reason_phrase the HTTP status code reason phrase
     * to compare
     * @return true, if successful.
     */
    public boolean reasonPhraseCompare(String reason_phrase)
    {
        if( reason_phrase == null || reason_phrase.trim().length() == 0 )
        {
            return false;
        }

        return v_reason_phrase.equalsIgnoreCase(reason_phrase.trim());
    }

    /**
     * Compares the given HTTP status class with this enum constant
     * variable instance status class.
     *
     * @param status_class the HTTP status class to compare
     * @return true, if comparison is successful.
     */
    public boolean statusClassCompare(String status_class)
    {
        if( status_class == null || status_class.trim().length() == 0 )
        {
            return false;
        }

        return v_status_class.equalsIgnoreCase(status_class.trim());
    }

    /**
     * @return the HTTP status code represenation of this
     * enum constant variable instance.
     */
    public int getStatusCode()
    {
        return v_status_code;
    }


    /**
     * @return the HTTP reason phrase represenation of this
     * enum constant variable instance.
     */
    public String getReasonPhrase()
    {
        return v_reason_phrase;
    }

    /**
     * Gets the status class.
     *
     * @return the HTTP status class represenation of this enum constant
     * variable instance.
     */
    public String getStatusClass()
    {
        return v_status_class;
    }

    /**
     * Gets an enum constant variable instance corresponding
     * to the given status code.
     *
     * @param status_code the HTTP status code to compare
     * @return the enum constant corresponding to the given
     * HTTP status code.  If an enum constant is not found for
     * the given code, null is returned.
     */
    public static final HttpStatusCodeType getEnum(int status_code)
    {
        if (null == TYPES)
        {
            TYPES = HttpStatusCodeType.class.getEnumConstants();
        }

        for (HttpStatusCodeType enum_type : TYPES)
        {
            if (enum_type.statusCodeCompare(status_code))
            {
                return enum_type;
            }
        }

        return null;
    }

    /**
     * Gets the enum constant variable instance corresponding
     * to the given message.
     *
     * @param reason_phrase the HTTP status code reason phrase
     * to compare
     * @return the enum constant corresponding to the given
     * reason phrase.  If an enum constant is not found for
     * the given reason phrase, null is returned.
     */
    public static final HttpStatusCodeType getEnum(String reason_phrase)
    {
        if( reason_phrase == null || reason_phrase.trim().length() == 0 )
        {
            return null;
        }

        if (null == TYPES)
        {
            TYPES = HttpStatusCodeType.class.getEnumConstants();
        }

        for (HttpStatusCodeType enum_type : TYPES)
        {
            if (enum_type.reasonPhraseCompare(reason_phrase))
            {
                return enum_type;
            }
        }

        return null;
    }

    /**
     * Gets the first enum constant variable instance
     * corresponding to the given HTTP status class.
     *
     * @param status_class the HTTP status class to compare
     * @return the first enum constant corresponding to the given
     * status class. If an enum constant is not found for the given
     * text, null is returned.
     */
    public static final HttpStatusCodeType getEnumAsClass(String status_class)
    {
        if( status_class == null || status_class.trim().length() == 0 )
        {
            return null;
        }

        if (null == TYPES)
        {
            TYPES = HttpStatusCodeType.class.getEnumConstants();
        }

        for (HttpStatusCodeType enum_type : TYPES)
        {
            if (enum_type.statusClassCompare(status_class))
            {
                return enum_type;
            }
        }

        return null;
    }

    /**
     * Checks if the given HTTP status code corresponds to one
     * of the enum constants defined.
     *
     * @param status_code the HTTP status code to compare
     * @return true, if the code is for a valid enum constant.
     */
    public static final boolean isStatusCode(int status_code)
    {
        return getEnum(status_code) == null ? false : true;
    }

    /**
     * Checks if the given message corresponds to one of the
     * enum constants defined.
     *
     * @param message the HTTP status code reason phrase to compare.
     * @return true, if the message is a valid enum constant reason
     * phrase.
     */
    public static final boolean isReasonPhrase(String message)
    {
        return getEnum(message) == null ? false : true;
    }

    /**
     * Checks if the given HTTP status class corresponds to at
     * least one of the enum constants defined status class.
     *
     * @param status_class the HTTP status class to compare
     * @return true, if the status_class is a valid for one of
     * the enum constants.
     */
    public static final boolean isStatusClass(String status_class)
    {
        return getEnumAsClass(status_class) == null ? false : true;
    }

    // ------ >>> Private <<< ------
    /**
     * Instantiates a new http status code type.
     *
     * @param code the HTTP status code
     * @param message the HTTP status code reason phrase
     * @param status_class the HTTP status class
     */
    private HttpStatusCodeType(int code, String message, String status_class)
    {
        v_status_code = code;
        v_reason_phrase = message;
        v_status_class = status_class;
    }

    /** The v_status_code. */
    private final int v_status_code;

    /** The v_message. */
    private final String v_reason_phrase;

    /** The v_status_class. */
    private final String v_status_class;

    /** The types. */
    private static HttpStatusCodeType TYPES [] = null;
}
