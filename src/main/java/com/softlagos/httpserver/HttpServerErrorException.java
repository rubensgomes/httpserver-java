/**
 * Copyright (C) 1999-2016 Rubens Gomes <rubens.s.gomes@gmail.com>.
 * All Rights Reserved.
 *
 * File: HttpServerErrorException.java
 *
 * Author: Rubens Gomes
 */
package com.softlagos.httpserver;

import com.softlagos.Constants;
import com.softlagos.httpserver.enums.HttpStatusCodeType;

/**
 * The HTTP Server Error (5xx).
 *
 * @author Rubens Gomes
 * @see "Section 6.5 of RFC7231 - Hypertext Transfer Protocol
 * (HTTP/1.1): Semantics and Content"
 */
@SuppressWarnings("serial")
public final class HttpServerErrorException
  extends HttpErrorException
{

    /**
     * Instantiates a new HTTP server error exception.
     *
     * @param status_code the HTTP server error status code
     * @param body_message a message body containing the payload
     * body if any.
     */
    public HttpServerErrorException(int status_code, String body_message)
    {
        super(status_code, body_message);

        HttpStatusCodeType http_status =
                HttpStatusCodeType.getEnum(status_code);

        if (http_status == null ||
           ! http_status.statusClassCompare(Constants.HTTP_SERVER_ERROR))
        {
            throw new IllegalArgumentException("status [" +
                    status_code + "] not a valid Server Error code.");
        }
    }

    /**
     * Instantiates a new HTTP server error exception.
     *
     * @param status_code the HTTP server error status code
     * @param body_message a message body containing the payload
     * body if any.
     * @param ex the exception being propagated up the stack.
     */
    public HttpServerErrorException(int status_code,
            String body_message,
            Exception ex)
    {
        super(status_code, body_message, ex);

        HttpStatusCodeType http_status =
                HttpStatusCodeType.getEnum(status_code);

        if (http_status == null ||
           ! http_status.statusClassCompare(Constants.HTTP_SERVER_ERROR))
        {
            throw new IllegalArgumentException("status [" +
                    status_code + "] not a valid Server Error code.");
        }
    }

}
