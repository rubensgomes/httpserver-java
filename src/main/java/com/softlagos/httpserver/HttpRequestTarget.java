/**
 * Copyright (C) 1999-2016 Rubens Gomes <rubens.s.gomes@gmail.com>.
 * All Rights Reserved.
 *
 * File: HttpRequestTarget.java
 *
 * Author: Rubens Gomes
 */
package com.softlagos.httpserver;

import com.softlagos.httpserver.enums.HttpRequestMethodType;
import com.softlagos.httpserver.enums.HttpRequestTargetFormatType;

/**
 * The HTTP Request Target.
 *
 * @author Rubens Gomes
 * @see "Section 5.3 of RFC7230 - Hypertext Transfer Protocol
 * (HTTP/1.1): Message Syntax and Routing"
 */
public final class HttpRequestTarget
{

    /**
     * Instantiates a new http request target.
     *
     * @param request_line the request_line
     * @throws HttpClientErrorException if the request-line is
     * malformed.
     */
    public HttpRequestTarget(String request_line)
            throws HttpClientErrorException
    {
        if( request_line == null ||
                request_line.trim().length() == 0 )
        {
            throw new IllegalArgumentException(
                               "request_line cannot be null or blank.");
        }

        // ensure this is a valid request_line
        HttpRequestLine.validate(request_line);

        String tokens [] = request_line.split(" ");
        String method = tokens[0];
        HttpRequestMethodType http_method =
                HttpRequestMethodType.getEnum(method);

        if(http_method == null)
        {
            String msg = "request-line [" + request_line +
                    "] had an invalid method [" + method + "]";
            throw new HttpClientErrorException(400, msg);
        }

        v_request_target = tokens[1];

        switch(http_method)
        {
            case CONNECT:
                v_format = HttpRequestTargetFormatType.AUTHORITY_FORM;
                break;

            case OPTIONS:
                v_format = HttpRequestTargetFormatType.ASTERISK_FORM;
                break;

            default:
                if(v_request_target.startsWith("/"))
                {
                    v_format = HttpRequestTargetFormatType.ORIGIN_FORM;
                }
                else if (v_request_target.startsWith("http://") ||
                        v_request_target.startsWith("https://") )
                {
                    v_format = HttpRequestTargetFormatType.ABSOLUTE_FORM;
                }
                else
                {
                    String msg = "request-line [" + request_line +
                            "] had an invalid request-target [" +
                            v_request_target + "]";
                    throw new HttpClientErrorException(400, msg);
                }
        }

    }

    /**
     * @return the request target
     */
    public String getRequestTarget()
    {
        return v_request_target;
    }

    /**
     * @return the HTTP request target format
     */
    public HttpRequestTargetFormatType getFormat()
    {
        return v_format;
    }

    // ------ >>> Private <<< ------
    private final String v_request_target;
    private final HttpRequestTargetFormatType v_format;
}
