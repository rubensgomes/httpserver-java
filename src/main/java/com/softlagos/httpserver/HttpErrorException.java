/**
 * Copyright (C) 1999-2016 Rubens Gomes <rubens.s.gomes@gmail.com>.
 * All Rights Reserved.
 *
 * File: HttpErrorException.java
 *
 * Author: Rubens Gomes
 */
package com.softlagos.httpserver;

import com.softlagos.httpserver.enums.HttpStatusCodeType;

/**
 * A generic HTTP Error Exception that could be used
 * for either an HTTP Request or HTTP Response related
 * error.
 *
 * @author Rubens Gomes
 */
@SuppressWarnings("serial")
public class HttpErrorException extends Exception
{

    /**
     * Instantiates a generic HTTP error exception.
     *
     * @param status_code the HTTP client error status code
     * @param body_message a message body containing the payload
     * body if any.
     */
    public HttpErrorException(int status_code, String body_message)
    {
        super(body_message);

        HttpStatusCodeType http_status =
                HttpStatusCodeType.getEnum(status_code);
        if( http_status == null )
        {
            throw new IllegalArgumentException("status [" +
                    status_code + "] not valid.");
        }

        v_status_code = status_code;
        v_message_body = body_message;
    }

    /**
     * Instantiates a generic HTTP error exception.
     *
     * @param status_code the HTTP client error status code
     * @param body_message a message body containing the payload
     * body if any.
     * @param ex the exception being propagated up the stack.
     */
    public HttpErrorException(int status_code,
            String body_message, Exception ex)
    {
        super(body_message, ex);

        HttpStatusCodeType http_status =
                HttpStatusCodeType.getEnum(status_code);
        if( http_status == null )
        {
            throw new IllegalArgumentException("status [" +
                    status_code + "] not valid.");
        }

        v_status_code = status_code;
        v_message_body = body_message;
    }

    /**
     * Gets the status code.
     *
     * @return the HTTP message response client error
     * status code.
     */
    public int getStatusCode()
    {
        return v_status_code;
    }

    /**
     * Gets the message body.
     *
     * @return the HTTP message associated with the client
     * error code.
     */
    public String getMessageBody()
    {
        return v_message_body;
    }

    /** The v_status_code. */
    // ------ >>> Private <<< ------
    private final int v_status_code;

    /** The v_message_body. */
    private final String v_message_body;

}
