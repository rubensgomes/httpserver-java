/**
 * Copyright (C) 1999-2016 Rubens Gomes <rubens.s.gomes@gmail.com>.
 * All Rights Reserved.
 *
 * File: HttpRequestHeader.java
 *
 * Author: Rubens Gomes
 */
package com.softlagos.httpserver;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.softlagos.httpserver.enums.HttpHeaderType;

/**
 * The HTTP Request Header containing the HTTP start-line
 * (request-line) and the HTTP Request header-fields.
 *
 * @author Rubens Gomes
 * @see "Section 3 of RFC7230 - Hypertext Transfer Protocol
 * (HTTP/1.1): Message Syntax and Routing"
 */
public final class HttpRequestHeader
{
    /** The logger. */
    private static final Logger logger =
            LogManager.getLogger(HttpRequestHeader.class);

    /**
     * Instantiates a new HTTP request header using the given
     * request line.
     *
     * @param request_line a valid HTTP request line
     */
    public HttpRequestHeader(final HttpRequestLine request_line)
    {
        if(request_line == null )
        {
            throw new IllegalArgumentException("request_line cannot be null.");
        }

        v_request_line = request_line;
        v_headers = new HashMap<String, String>();
    }

    /**
     * Adds the given header field to the HTTP headers.
     *
     * @param key the HTTP header field key
     * @param value the HTTP header field value
     * @throws HttpClientErrorException if the header key is
     * invalid.
     */
    public synchronized void addHeader(String key, String value)
        throws HttpClientErrorException
    {
        if( key == null || key.trim().length() == 0)
        {
            throw new IllegalArgumentException("key cannot be null or blank.");
        }

        if( value == null || value.trim().length() == 0)
        {
            throw new IllegalArgumentException("value cannot be null or blank.");
        }

        if( ! HttpHeaderType.isHeaderField(key) )
        {
            String msg = "header field [" + key + "] is not supported.";
            if(logger.isInfoEnabled())
            {
                logger.info(msg);
            }
        }

        v_headers.put(key, value);
    }

    /**
     * @return the HTTP request-line
     */
    public HttpRequestLine getRequestLine()
    {
        return v_request_line;
    }

    /**
     * @return the HTTP headers
     */
    public Map<String, String> getHeaders()
    {
        return v_headers;
    }

    // ------ >>> Private <<< ------
    private final HttpRequestLine v_request_line;
    private final Map<String, String> v_headers;
}
