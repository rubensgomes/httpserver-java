/**
 * Copyright (C) 1999-2016 Rubens Gomes <rubens.s.gomes@gmail.com>.
 * All Rights Reserved.
 *
 * File: HttpResponse.java
 *
 * Author: Rubens Gomes
 */
package com.softlagos.httpserver;

import com.softlagos.Constants;

/**
 * The HTTP Response Message.
 *
 * @author Rubens Gomes
 */
public final class HttpResponse
{

    /**
     * Instantiates a new http response.
     *
     * @param header the HTTP header consisting of start-line
     * and header fields.
     * @param message_body the HTTP message body which could
     * be null if the request did not have a message body.
     */
    public HttpResponse(HttpResponseHeader header,
                        HttpMessageBody message_body)
    {
        if(header == null)
        {
            throw new IllegalArgumentException("header cannot be null.");
        }

        v_header = header;
        v_message_body = message_body;
    }

    /**
     * @return the HTTP header containing the start-line
     * and the header-fields.
     */
    public HttpResponseHeader getHeader()
    {
        return v_header;
    }

    /**
     * @return the message body when it is found on the
     * HTTP request.  It may return null if the message-body
     * was not found on the request.
     */
    public HttpMessageBody getMessageBody()
    {
        return v_message_body;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuilder buff = new StringBuilder();
        buff.append(v_header);
        buff.append(Constants.CRLF);
        buff.append(v_message_body);
        return buff.toString();
    }

    // ------ >>> Private <<< ------
    private final HttpResponseHeader v_header;
    private final HttpMessageBody v_message_body;
}
