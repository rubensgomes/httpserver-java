/**
 * Copyright (C) 1999-2016 Rubens Gomes <rubens.s.gomes@gmail.com>.
 * All Rights Reserved.
 *
 * File: HttpRequestLine.java
 *
 * Author: Rubens Gomes
 */
package com.softlagos.httpserver;

import com.softlagos.Constants;
import com.softlagos.httpserver.enums.HttpRequestMethodType;

/**
 * <p>
 * The HTTP request-line.
 * </p>
 * <pre>
 * request-line = method SP request-target SP HTTP-version CRLF
 * </pre>
 *
 * @author Rubens Gomes
 * @see "Section 3.1.1 of RFC7230 - Hypertext Transfer Protocol
 * (HTTP/1.1): Message Syntax and Routing"
 */
public final class HttpRequestLine
{
    /**
     * Instantiates a new HTTP client request line.
     *
     * @param requestLine a valid HTTP request-line
     * @throws HttpClientErrorException if the HTTP requestLine is malformed.
     */
    public HttpRequestLine(String requestLine)
            throws HttpClientErrorException
    {
        if( requestLine == null ||
                requestLine.trim().length() == 0 )
        {
            throw new IllegalArgumentException(
                               "requestLine cannot be null or blank.");
        }

        validate(requestLine);
        v_request_line = requestLine;
        v_method = parseMethod(requestLine);
        v_requestURI = parseRequestURI(requestLine);
        v_httpVersion = parseHttpVersion(requestLine);
    }

    /**
     * @return the HTTP request line
     */
    public String getRequestLine()
    {
        return v_request_line;
    }

    /**
     * @return the HTTP Method
     */
    public HttpRequestMethodType getMethod()
    {
        return v_method;
    }

    /**
     * @return the HTTP Request-URI
     */
    public String getRequestURI()
    {
        return v_requestURI;
    }

    /**
     * @return the HTTP HTTP-Version
     */
    public String getHttpVersion()
    {
        return v_httpVersion;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return v_request_line;
    }


    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result +
            ((v_request_line == null) ? 0 : v_request_line.hashCode());
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (!(obj instanceof HttpRequestLine))
        {
            return false;
        }
        HttpRequestLine other = (HttpRequestLine) obj;
        if (v_request_line == null)
        {
            if (other.v_request_line != null)
            {
                return false;
            }
        } else if (!v_request_line.equals(
                                          other.v_request_line))
        {
            return false;
        }
        return true;
    }

    /**
     * Validate the given request_line.  It raises an exception if
     * the given request_line is not valid.
     *
     * @param requestLine the request_line to validate.
     * @throws HttpClientErrorException the http client error exception
     */
    public static void validate(String requestLine)
        throws HttpClientErrorException
    {
        if( requestLine == null ||
                requestLine.trim().length() == 0 )
        {
            throw new IllegalArgumentException(
                               "requestLine cannot be null or blank.");
        }

        String tokens [] = requestLine.split(Constants.SP);
        if(tokens == null || tokens.length == 0)
        {
            String msg = "request_line [" + requestLine +
                    "] is not valid.";
            throw new HttpClientErrorException(400, msg);
        }

        // according to the HTTP 1.1 standards the request
        // line should look as following:
        // request-line = method SP request-target SP HTTP-version CRLF
        if( tokens.length != 3 )
        {
            String msg = "request-line [" + requestLine +
                    "] is invalid.";
            throw new HttpClientErrorException(400, msg);
        }

        // --- >>> Extract the Method from the request-line <<< ---
        String method = tokens[0];
        HttpRequestMethodType http_method =
                HttpRequestMethodType.getEnum(method);

        if(http_method == null)
        {
            String msg = "request-line [" + requestLine +
                    "] had an invalid method [" + method + "]";
            throw new HttpClientErrorException(400, msg);
        }

        // --- >>> Extract the Request-URI from the request-line <<< ---
        String request_target = tokens[1];
        if(request_target.length() >
            Constants.MAX_HTTP_REQUEST_TARGET_LENGTH)
        {
            // 414 URI Too Long
            String msg = "414 URI [" + request_target +
                    "] is too long. Maximum lenght is [" +
                    Constants.MAX_HTTP_REQUEST_TARGET_LENGTH + "]";
            throw new HttpClientErrorException(414, msg);
        }

        switch(http_method)
        {
            case CONNECT:
            case OPTIONS:
                break;

            default:
                if(request_target.startsWith("/"))
                {
                    break;
                }
                else if (request_target.startsWith("http://") ||
                         request_target.startsWith("https://") )
                {
                    break;
                }
                else
                {
                    String msg = "request-line [" + requestLine +
                            "] had an invalid request-target [" +
                            request_target + "]";
                    throw new HttpClientErrorException(400, msg);
                }
        }

        // --- >>> Extract the HTTP version from the request-line <<< ---
        String version = tokens[2];
        if( ! version.matches("HTTP/\\d\\.\\d") )
        {
            String msg = "request-line [" + requestLine +
                    "] had an invalid version [" +
                    version + "]";
            throw new HttpClientErrorException(400, msg);
        }

    }

    /**
     * This method assumes a valid request-line.
     * <p>
     * Request-Line   = Method SP Request-URI SP HTTP-Version CRLF
     * </p>
     * 
     * @param requestLine the non-blank and valid HTTP request-line
     * @return the corresponding request Method
     */
    private HttpRequestMethodType parseMethod(String requestLine)
            throws HttpClientErrorException
    {
        String tokens [] = requestLine.split(Constants.SP);

        // --- >>> Extract the HTTP method from the request-line <<< ---
        String method = tokens[0];
        HttpRequestMethodType http_method =
                HttpRequestMethodType.getEnum(method);

        if(http_method == null)
        {
            String msg = "request-line [" + requestLine +
                    "] had an invalid method [" + method + "]";
            throw new HttpClientErrorException(400, msg);
        }

        return http_method;
    }

    /**
     * This method assumes a valid request-line.
     * <p>
     * Request-Line   = Method SP Request-URI SP HTTP-Version CRLF
     * </p>
     * 
     * @param requestLine the non-blank and valid HTTP request-line
     * @return the corresponding Request-URI
     */
    private String parseRequestURI(String requestLine)
    {
        String tokens [] = requestLine.split(Constants.SP);

        // --- >>> Extract the Request-URI from the request-line <<< ---
        String request_target = tokens[1];
        return request_target;
    }

    /**
     * This method assumes a valid request-line.
     * <p>
     * Request-Line   = Method SP Request-URI SP HTTP-Version CRLF
     * </p>
     * 
     * @param requestLine the non-blank and valid HTTP request-line
     * @return the corresponding HTTP-Version
     */
    private String parseHttpVersion(String requestLine)
    {
        String tokens [] = requestLine.split(Constants.SP);

        // --- >>> Extract the HTTP version from the request-line <<< ---
        String version = tokens[2];
        return version;
    }

    // ------ >>> Private <<< ------
    private final String v_request_line;
    private final HttpRequestMethodType v_method;
    private final String v_requestURI;
    private final String v_httpVersion;

}
