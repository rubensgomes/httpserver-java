/**
 * Copyright (C) 1999-2016 Rubens Gomes <rubens.s.gomes@gmail.com>.
 * All Rights Reserved.
 *
 * File: HttpRequest.java
 *
 * Author: Rubens Gomes
 */
package com.softlagos.httpserver;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.softlagos.httpserver.enums.HttpHeaderType;
import com.softlagos.httpserver.enums.HttpRequestMethodType;

/**
 * The HTTP Request message.
 *
 * @author Rubens Gomes
 */
public final class HttpRequest
{

    /** The logger. */
    private static final Logger logger =
            LogManager.getLogger(HttpRequest.class);

    /**
     * Instantiates a new http request.
     *
     * @param header the HTTP request header consisting of
     * start-line and header fields.
     * @param message_body the HTTP message body which could
     * be null if the request did not have a message body.
     * @throws HttpClientErrorException if the HTTP request is malformed.
     */
    public HttpRequest(HttpRequestHeader header,
                       HttpMessageBody message_body)
    throws HttpClientErrorException
    {
        if(header == null)
        {
            throw new IllegalArgumentException("header cannot be null.");
        }

        v_header = header;
        v_message_body = message_body;
        validateHeader();
    }

    /**
     * @return the HTTP request header containing the
     * start-line and the header-fields.
     */
    public HttpRequestHeader getHeader()
    {
        return v_header;
    }

    /**
     * @return the HTTP request message body when it is found
     * on the HTTP request.  It may return null if the
     * message-body was not found on the request.
     */
    public HttpMessageBody getMessageBody()
    {
        return v_message_body;
    }

    /**
     * Print out this HTTP request using the logger.trace method. It displays a warning 
     * to the log if the logger. trace is not enabled for this object.
     */
    public void trace()
    {
        if(! logger.isTraceEnabled())
        {
            logger.warn("CANNOT trace request because logger trace is DISABLED!");
        }

        HttpRequestHeader httpHeader = this.getHeader();
        if(httpHeader == null)
        {
            throw new IllegalArgumentException("request [" + this + "] is missing an HTTP header");
        }

        HttpRequestLine requestLine = httpHeader.getRequestLine();
        if(requestLine == null)
        {
            throw new IllegalArgumentException("request [" + this + "] is missing an HTTP request-line");
        }

        // method
        HttpRequestMethodType method = requestLine.getMethod();
        logger.trace("method: " + method.toString());

        // Request-URI
        String requestUri = requestLine.getRequestURI();
        logger.trace("request-URI: " + requestUri);

        // HTTP-Version
        String version = requestLine.getHttpVersion();
        logger.trace("HTTP-Version: " + version);

        // headers
        Map<String, String> header = httpHeader.getHeaders();
        if(header != null && ! header.isEmpty())
        {
            String value = null;

            for(String key : header.keySet())
            {
                value = header.get(key);
                logger.trace("header: " + key + "=" + value);
            }

        }

    }

    /**
     * Validate the request to ensure it complies with the given HTTP version protocol.
     * 
     * @throws HttpClientErrorException
     */
    private void validateHeader()
            throws HttpClientErrorException
    {
        String version = v_header.getRequestLine().getHttpVersion();
        String msg = null;

        if(version.matches("HTTP/1.1"))
        {
            Map<String, String> headers = v_header.getHeaders();
            if ( headers.get(HttpHeaderType.HOST) == null)
            {
                msg = "A client MUST include a Host header field in all HTTP/1.1 request messages";
                throw new HttpClientErrorException(400, msg);
            }
            
        }
    }

    // ------ >>> Private <<< ------
    private final HttpRequestHeader v_header;
    private final HttpMessageBody v_message_body;
}
