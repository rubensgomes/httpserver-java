/**
 * Copyright (C) 1999-2016 Rubens Gomes <rubens.s.gomes@gmail.com>.
 * All Rights Reserved.
 *
 * File: HttpResponseHeader.java
 *
 * Author: Rubens Gomes
 */
package com.softlagos.httpserver;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.softlagos.Constants;
import com.softlagos.httpserver.enums.HttpRequestMethodType;

/**
 * The HTTP Response Header containing the HTTP start-line
 * (status-line) and the HTTP Response header-fields.
 *
 * @author Rubens Gomes
 * @see "Section 7 of RFC7231 - ypertext Transfer Protocol (HTTP/1.1):
 * Semantics and Content"
 */
public final class HttpResponseHeader
{

    /**
     * Instantiates a new HTTP response header using the given
     * status line.
     *
     * @param status_line a valid HTTP status line
     */
    public HttpResponseHeader(final HttpStatusLine status_line)
    {
        if(status_line == null )
        {
            throw new IllegalArgumentException("status_line cannot be null.");
        }

        v_status_line = status_line;
        v_headers = new HashMap<String, String>();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        StringBuilder buff = new StringBuilder();
        buff.append(v_status_line);

        for (String key : v_headers.keySet())
        {
            buff.append(key);
            buff.append(":");
            buff.append(v_headers.get(key));
            buff.append(Constants.CRLF);
        }

        return buff.toString();
    }

    /**
     * @return the HTTP response headers
     */
    public Map<String, String> getHeaders()
    {
        return v_headers;
    }

    /**
     * @return the HTTP response start line (status-line)
     */
    public String getStartLine()
    {
        return v_status_line.toString();
    }

    /**
     * Adds the given header field to the HTTP headers.
     *
     * @param key the HTTP header field key
     * @param value the HTTP header field value
     */
    public synchronized void addHeader(String key, String value)
    {
        if( key == null || key.trim().length() == 0)
        {
            throw new IllegalArgumentException("key cannot be null or blank.");
        }

        if( value == null || value.trim().length() == 0)
        {
            throw new IllegalArgumentException("value cannot be null or blank.");
        }

        v_headers.put(key, value);
    }

    /**
     * Adds the current Date to the HTTP header.
     * <p>
     * For example:
     * <pre>
     * Date: Mon, 27 Jul 2009 12:28:53 GMT
     * </pre>
     */
    public void addCurrentDate()
    {
        // Mon, 27 Jul 2009 12:28:53 GMT
        SimpleDateFormat formatter =
                new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
        Date now = new Date();
        String output = formatter.format(now);

        addHeader("Date", output);
    }

    /**
     * Adds the Allow HTTP header.
     * <p>
     * The "Allow" header field lists the set
     * of methods advertised as supported by the
     * target resource.
     * <p>
     * For example:
     * <pre>
     * Allow: GET, HEAD, PUT
     * </pre>
     *
     * @param methods the HTTP methods
     * @see "Section 4.3 of [RFC7231]"
     */
    public void addAllow(Set<HttpRequestMethodType> methods)
    {
        if(methods == null)
        {
            throw new IllegalArgumentException("methods cannot be null");
        }

        if(methods.isEmpty())
        {
            throw new IllegalArgumentException("methods cannot be empty");
        }

        StringBuilder buff = new StringBuilder();
        for(HttpRequestMethodType method : methods)
        {
            buff.append(method.getMethod());
            buff.append(Constants.SP);
        }

        // removes trailing SP
        int last_pos = buff.length() - 1;
        buff.delete(last_pos, last_pos);

        addHeader("Allow", buff.toString());
    }

    /**
     * Adds the HTTP content length haeder.
     * <p>
     * The Content-Length header field provides the
     * anticipated size, as a decimal number of octets, for
     * a potential payload body.
     * <p>
     * For example:
     * <pre>
     * Content-Length: 3495
     * </pre>
     * @param length the content length.
     * @see "Section 3.3.2 of [RFC7230]"
     */
    public void addContentLength(int length)
    {
        if( length <= 0 )
        {
            throw new IllegalArgumentException("length must be greater than 0");
        }

        addHeader("Content-Length", "" + length);
    }

    /**
     * Adds the HTTP Server header.
     * <p>
     * The "Server" header field contains information
     * about the software used by the origin server to
     * handle the request, which is often used by clients
     * to help identify the scope of reported interoperability
     * problems, to work around or tailor requests to avoid
     * particular server limitations, and for analytics
     * regarding server or operating system use.
     * <p>
     * For example:
     * <pre>
     * Server: CERN/3.0 libwww/2.17
     * </pre>
     * @see "Section 7.4.2 of [RFC7231]"
     */
    public void addServer()
    {
        addHeader("Server", Constants.HTTP_SERVER);
    }

    /**
     * Adds the HTTP Last-Modifed header.
     * <p>
     * The "Last-Modified" header field in a response
     * provides a timestamp indicating the date and time
     * at which the origin server believes the selected
     * representation was last modified, as determined at
     * the conclusion of handling the request.
     * <p>
     * For example:
     * <pre>
     * Last-Modified: Wed, 22 Jul 2009 19:15:56 GMT
     * </pre>
     *
     * @param date the date to be assigned to the Last-Modified
     * header
     * @see "Section 2.2 of [RFC7232]"
     */
    public void addLastModified(Date date)
    {
        if(date == null)
        {
            throw new IllegalArgumentException("date cannot be null.");
        }

        // Mon, 27 Jul 2009 12:28:53 GMT
        SimpleDateFormat formatter =
                new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
        String output = formatter.format(date);

        addHeader("Last-Modified", output);
    }

    // ------ >>> Private <<< ------
    private final HttpStatusLine v_status_line;
    private final Map<String, String> v_headers;
}
