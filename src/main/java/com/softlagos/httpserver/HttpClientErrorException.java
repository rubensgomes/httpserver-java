/**
 * Copyright (C) 1999-2016 Rubens Gomes <rubens.s.gomes@gmail.com>.
 * All Rights Reserved.
 *
 * File: HttpClientErrorException.java
 *
 * Author: Rubens Gomes
 */
package com.softlagos.httpserver;

import com.softlagos.Constants;
import com.softlagos.httpserver.enums.HttpStatusCodeType;

/**
 * The HTTP Client Error (4xx).
 *
 * @author Rubens Gomes
 * @see "Section 6.5 of RFC7231 - Hypertext Transfer Protocol
 * (HTTP/1.1): Semantics and Content"
 */
@SuppressWarnings("serial")
public final class HttpClientErrorException
  extends HttpErrorException
{

    /**
     * Instantiates a new HTTP client error exception.
     *
     * @param status_code the HTTP client error status code
     * @param body_message a message body containing the payload
     * body if any.
     */
    public HttpClientErrorException(int status_code, String body_message)
    {
        super(status_code, body_message);

        HttpStatusCodeType http_status =
                HttpStatusCodeType.getEnum(status_code);

        if (http_status == null ||
           ! http_status.statusClassCompare(Constants.HTTP_CLIENT_ERROR))
        {
            throw new IllegalArgumentException("status [" +
                    status_code + "] not a valid Client Error code.");
        }
    }
}
