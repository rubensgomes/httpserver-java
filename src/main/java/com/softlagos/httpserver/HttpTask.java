/**
 * Copyright (C) 1999-2016 Rubens Gomes <rubens.s.gomes@gmail.com>.
 * All Rights Reserved.
 *
 * File: HttpTask.java
 *
 * Author: Rubens Gomes
 */
package com.softlagos.httpserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.Charset;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.softlagos.Constants;
import com.softlagos.reactor.SocketHandle;
import com.softlagos.threadpool.Task;

/**
 * A concrete HTTP Task being executed on the server to handle
 * an incoming HTTP Request and respond with an HTTP Response.
 *
 * @author Rubens Gomes
 */
public final class HttpTask extends Task
{
    /** The logger. */
    private static final Logger logger =
            LogManager.getLogger(HttpTask.class);

    /**
     * Instantiates a new http task.
     *
     * @param handle the Socket IO handle
     */
    public HttpTask(SocketHandle handle)
    {
        if( handle == null )
        {
            throw new IllegalArgumentException("handle cannot be null.");
        }

        v_handle = handle;

        Socket socket = v_handle.getSocket();
        try
        {
            if(logger.isTraceEnabled())
            {
                logger.trace("initializing input communication channel");
            }

            InputStream in = socket.getInputStream();
            InputStreamReader in_reader = new InputStreamReader(in);
            v_reader = new BufferedReader(in_reader);

            if(logger.isTraceEnabled())
            {
                logger.trace("initializing output communication channel");
            }

            OutputStream out = socket.getOutputStream();
            v_writer = new OutputStreamWriter(out, Charset.forName("UTF-8"));
        }
        catch(IOException ex)
        {
            String msg = "Error initializing input reading channel: " +
                    ex.getMessage();

            if(logger.isDebugEnabled())
            {
                logger.debug(msg);
            }

            throw new IllegalArgumentException(msg, ex);
        }

    }

    /* (non-Javadoc)
     * @see com.softlagos.threadpool.Task#run()
     */
    @Override
    public void run()
    {
        if(logger.isTraceEnabled())
        {
            logger.trace("running...");
        }

        Socket socket = v_handle.getSocket();
        try
        {
            HttpRequest request = readRequest(socket);
            // print out request
            request.trace();
            // TODO: retrieve Resource and respond.
        }
        catch(Exception ex)
        {
            HttpErrorHandler handler =
                    new HttpErrorHandler(ex, v_writer);
            try
            {
                handler.sendResponse();
            }
            catch(Exception ioex)
            {
                logger.error("error writing response: " + ioex.getMessage());
            }
        }

    }

    // ------ >>> Private <<< ------

    /**
     * Read request.
     *
     * @param socket the socket to read the client requests.
     * @return the HTTP Request
     * @throws HttpErrorException the http client/server
     * error exception
     */
    private HttpRequest readRequest(final Socket socket)
        throws HttpErrorException
    {
        HttpRequest request = null;
        HttpRequestHeader header = null;

        try
        {
            boolean start_line_flag = true;
            boolean message_body_flag = false;

            String input_line = null;
            HttpMessageBody message_body  = null;

            // read the HTTP request-line
            while( (input_line = v_reader.readLine()) != null)
            {
              // the very first line: the HTTP start-line.
              if( start_line_flag )
              {
                start_line_flag = false;

                if(logger.isTraceEnabled())
                {
                    logger.trace("instantiating request_line object [" +
                            input_line + "]");
                }

                HttpRequestLine req_line = new HttpRequestLine(input_line);
                header = new HttpRequestHeader(req_line);
                continue; // to go to the next line
              }

              // if the line contains CRLF alone then the next
              // lines should contain the message body.
              if( input_line.equals(Constants.CRLF))
              {
                  message_body_flag = false;
                  continue; // to go to the next line
              }

              // read the header fields
              if(! message_body_flag )
              {
                  String [] header_field = input_line.split(":", 2);

                  if (header_field == null || header_field.length == 0)
                  {
                      String msg = "HTTP Header field not found: " +
                              input_line;
                      logger.error(msg);
                      throw new HttpClientErrorException(400, msg);
                  }
                  else if ( header_field.length != 2 )
                  {
                      String msg = "Illegal HTTP Header field: " +
                              input_line;
                      if(logger.isInfoEnabled())
                      {
                          logger.info(msg);
                      }
                  }
                  else
                  {
                      header.addHeader(header_field[0].trim(),
                                       header_field[1].trim());
                  }
              }
              else // read the message body.
              {
                  message_body = new HttpMessageBody();
                  message_body.add(input_line);
              }

            } // end while

            request = new HttpRequest(header, message_body);
        }
        catch(IOException ex)
        {
            String msg = "IO Error: " + ex.getMessage();
            logger.error(msg, ex);
            throw new HttpServerErrorException(500, msg, ex);
        }

        return request;
    }

    /** The v_handle. */
    private final SocketHandle v_handle;

    /** The v_reader. */
    private final BufferedReader v_reader;

    /** The v_writer. */
    private final OutputStreamWriter v_writer;
}
