/**
 * Copyright (C) 1999-2016 Rubens Gomes <rubens.s.gomes@gmail.com>.
 * All Rights Reserved.
 *
 * File: HttpServiceHandler.java
 *
 * Author: Rubens Gomes
 */
package com.softlagos.httpserver;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.softlagos.reactor.EventType;
import com.softlagos.reactor.Handle;
import com.softlagos.reactor.ServiceHandler;
import com.softlagos.reactor.SocketHandle;
import com.softlagos.threadpool.Task;

/**
 * The HTTP service handler responsible for handling
 * the server end point of the client-server
 * communication protocol once a connection is established
 * between a client and server.
 *
 * @author Rubens Gomes
 */
public final class HttpServiceHandler
  extends ServiceHandler
{
    /** The logger. */
    private static final Logger logger =
            LogManager.getLogger(HttpServiceHandler.class);

    /**
     * Instantiates a new http service handler.
     */
    public HttpServiceHandler()
    {
        // TODO Auto-generated constructor stub
    }

    /* (non-Javadoc)
     * @see com.softlagos.reactor.ServiceHandler#open(com.softlagos.reactor.SocketHandle)
     */
    @Override
    public void open(SocketHandle socket_handle)
    {
        if(logger.isTraceEnabled())
        {
            logger.trace("initializing...");
        }

        if( socket_handle == null )
        {
            throw new IllegalArgumentException("socket_handle cannot be null.");
        }

        v_handle = socket_handle;
    }

    /* (non-Javadoc)
     * @see com.softlagos.reactor.ServiceHandler#close()
     */
    @Override
    public void close()
    {
        if(logger.isTraceEnabled())
        {
            logger.trace("closing...");
        }

        try
        {
            v_handle.close();
        }
        catch(IOException ex)
        {
            String msg = "Error closing IO handle: " + ex.getMessage();
            logger.error(msg);
        }
    }

    /* (non-Javadoc)
     * @see com.softlagos.reactor.ServiceHandler#run()
     */
    @Override
    public void run()
    {
        getTask().run();
    }

    /* (non-Javadoc)
     * @see com.softlagos.reactor.ServiceHandler#getTask()
     */
    @Override
    public Task getTask()
    {
        HttpTask task = new HttpTask(v_handle);
        return task;
    }

    /* (non-Javadoc)
     * @see com.softlagos.reactor.EventHandler#handleEvent(com.softlagos.reactor.Handle, com.softlagos.reactor.EventType, java.lang.String)
     */
    @Override
    public void handleEvent(Handle handle, EventType evtType, String message)
    {
        if(logger.isTraceEnabled())
        {
            logger.trace("handling event...");
        }

        if( handle == null )
        {
            throw new IllegalArgumentException("handle cannot be null.");
        }

        if( evtType == null )
        {
            throw new IllegalArgumentException("evtType cannot be null.");
        }

        if( evtType == EventType.ACCEPT  )
        {
            //HttpTask
        }
    }

    /* (non-Javadoc)
     * @see com.softlagos.reactor.EventHandler#getHandle()
     */
    @Override
    public Handle getHandle()
    {
        return v_handle;
    }

    /** The v_handle. */
    // ------ >>> Private <<< ------
    private SocketHandle v_handle;
}
