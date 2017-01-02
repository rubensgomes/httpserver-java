/**
 * Copyright (C) 1999-2016 Rubens Gomes <rubens.s.gomes@gmail.com>.
 * All Rights Reserved.
 *
 * File: HttpStatusLine.java
 *
 * Author: Rubens Gomes
 */
package com.softlagos.httpserver;

import com.softlagos.Constants;
import com.softlagos.httpserver.enums.HttpStatusCodeType;

/**
 * <p>
 * The HTTP status-line.  Instances of this class should be created
 * using one of the static factory make... methods.
 * </p>
 * <pre>
 * status-line = HTTP-version SP status-code SP reason-phrase CRLF
 * </pre>
 *
 * @author Rubens Gomes
 * @see "Section 3.1.2 of RFC7230 - Hypertext Transfer Protocol
 * (HTTP/1.1): Message Syntax and Routing"
 */
public final class HttpStatusLine
{

    /**
     * <p>
     * A factory method to make HTTP status line:
     * </p>
     * <pre>
     * status-line = HTTP-version SP status-code SP reason-phrase CRLF
     * </pre>
     *
     * @param ex the exception to parse.
     * @return the HTTP status line
     */
    public static HttpStatusLine makeHttpStatusLine(Exception ex)
    {
        if(ex == null)
        {
            throw new IllegalArgumentException("ex cannot be null.");
        }

        HttpStatusLine status_line = null;

        StringBuilder buff = new StringBuilder();
        buff.append(Constants.HTTP_SERVER_VERSION);

        buff.append(Constants.SP);

        int status_code = 0;
        if( ex instanceof HttpErrorException )
        {
            status_code = ((HttpErrorException) ex).getStatusCode();
            buff.append(status_code);
        }
        else
        {
            status_code = 500;
            buff.append(status_code);
        }

        buff.append(Constants.SP);

        HttpStatusCodeType status_enum =
                HttpStatusCodeType.getEnum(status_code);
        if( status_enum == null )
        {
            throw new IllegalArgumentException("invalid status_code [" +
                    status_code + "]");
        }

        buff.append(status_enum.getReasonPhrase());

        buff.append(Constants.CRLF);

        status_line = new HttpStatusLine(buff.toString());

        return status_line;
    }

    /**
     * @return the HTTP status line
     */
    public String getStatusLine()
    {
        return v_status_line;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString()
    {
        return v_status_line;
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
            ((v_status_line == null) ? 0 : v_status_line.hashCode());
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
        if (!(obj instanceof HttpStatusLine))
        {
            return false;
        }
        HttpStatusLine other = (HttpStatusLine) obj;
        if (v_status_line == null)
        {
            if (other.v_status_line != null)
            {
                return false;
            }
        } else if (!v_status_line.equals(other.v_status_line))
        {
            return false;
        }
        return true;
    }

    /**
     * Validate the given status_line.  It raises an exception if
     * the given status_line is not valid.  Since the status_line
     * is created on the server end, the exception raised is an
     * IllegalArgumentException
     *
     * @param status_line the outgoing HTTP status-line to validate.
     * @throws IllegalArgumentException if the HTTP status_line is
     * not valid.
     */
    public static void validate(String status_line)
    {
        if( status_line == null ||
                status_line.trim().length() == 0 )
        {
            throw new IllegalArgumentException(
                               "status_line cannot be null or blank.");
        }

        String tokens [] = status_line.split(Constants.SP);
        if(tokens == null || tokens.length == 0)
        {
            String msg = "status_line [" + status_line +
                    "] is not valid.";
            throw new IllegalArgumentException(msg);
        }

        // according to the HTTP 1.1 standards the response
        // status line should look as following:
        // status-line = HTTP-version SP status-code SP reason-phrase CRLF
        if( tokens.length != 3 )
        {
            String msg = "status-line [" + status_line +
                    "] is invalid.";
            throw new IllegalArgumentException(msg);
        }

        // --- >>> Parse the HTTP version <<< ---
        String version = tokens[0];
        if( ! version.matches("HTTP/\\d{1}\\.\\d{1}") )
        {
            String msg = "status-line [" + status_line +
                    "] had an invalid version [" +
                    version + "]";
            throw new IllegalArgumentException(msg);
        }

        // --- >>> Parse the HTTP status code <<< ---
        String status_code = tokens[1];
        if( ! status_code.matches("\\d{3}") )
        {
            String msg = "status-line [" + status_line +
                    "] had a non 3 digit invalid status code [" +
                    status_code + "]";
            throw new IllegalArgumentException(msg);
        }

        int code = Integer.parseInt(status_code);
        if( HttpStatusCodeType.isStatusCode(code) )
        {
            String msg = "status-line [" + status_line +
                    "] had an invalid status code [" +
                    status_code + "]";
            throw new IllegalArgumentException(msg);
        }

        // --- >>> Parse the HTTP status reason-phrase  <<< ---
        String reason_phrase = tokens[2];
        if( status_code.trim().length() >
            Constants.MAX_HTTP_REASON_PHRASE_LEN )
        {
            String msg = "status-line [" + status_line +
                    "] reason-phrase too lengthy [" +
                    reason_phrase + "]";
            throw new IllegalArgumentException(msg);
        }

        if( ! HttpStatusCodeType.isReasonPhrase(reason_phrase) )
        {
            String msg = "status-line [" + status_line +
                    "] reason-phrase is not valid [" +
                    reason_phrase + "]";
            throw new IllegalArgumentException(msg);
        }

        // --- >>> Ensure it ends in CRLF <<< ---
        if( ! status_line.endsWith(Constants.CRLF) )
        {
            String msg = "status-line [" + status_line +
                    "] does not end with CRLF";
            throw new IllegalArgumentException(msg);
        }
    }

    // ------ >>> Private <<< ------
    private final String v_status_line;

    private HttpStatusLine(String status_line)
    {
        if( status_line == null ||
                status_line.trim().length() == 0 )
        {
            throw new IllegalArgumentException(
                               "status_line cannot be null or blank.");
        }

        v_status_line = status_line;
    }

}
