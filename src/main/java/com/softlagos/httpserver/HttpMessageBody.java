/**
 * Copyright (C) 1999-2016 Rubens Gomes <rubens.s.gomes@gmail.com>.
 * All Rights Reserved.
 *
 * File: HttpMessageBody.java
 *
 * Author: Rubens Gomes
 */
package com.softlagos.httpserver;

/**
 * The HTTP message body.
 *
 * @author Rubens Gomes
 * @see "Section 3.3 of RFC7230 - Hypertext Transfer Protocol
 * (HTTP/1.1): Message Syntax and Routing"
 */
public final class HttpMessageBody
{

    /**
     * Instantiates a new http message body.
     */
    public HttpMessageBody()
    {
        v_message_body = new StringBuilder();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return getMessageBody();
    }

    /**
     * Adds the given input line to the message body.
     *
     * @param input_line the HTTP message body input line.
     */
    public synchronized void add(String input_line)
    {
        if(input_line == null)
        {
            throw new IllegalArgumentException("input_line cannot be null.");
        }

        v_message_body.append(input_line);
    }

    /**
     * Gets the message body.
     *
     * @return the HTTP message body
     */
    public String getMessageBody()
    {
        return v_message_body.toString();
    }

    // ------ >>> Private <<< ---
    private final StringBuilder v_message_body;
}
