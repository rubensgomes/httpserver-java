/**
 * Copyright (C) 1999-2016 Rubens Gomes <rubens.s.gomes@gmail.com>.
 * All Rights Reserved.
 *
 * File: HttpAcceptor.java
 *
 * Author: Rubens Gomes
 */
package com.softlagos.httpserver;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.softlagos.reactor.Acceptor;
import com.softlagos.reactor.AcceptorStrategyAbstractFactory;
import com.softlagos.reactor.ConcurrencyStrategy;
import com.softlagos.reactor.CreationStrategy;
import com.softlagos.reactor.EventType;
import com.softlagos.reactor.Handle;
import com.softlagos.reactor.Reactor;
import com.softlagos.reactor.ServerSocketHandle;
import com.softlagos.reactor.ServiceHandler;
import com.softlagos.reactor.SocketHandle;

/**
 * A concrete HTTP Acceptor that is responsible to
 * handle and delegate the incoming HTTP request to a specific
 * HTTP service handler.
 *
 * @author Rubens Gomes
 */
public final class HttpAcceptor
  extends Acceptor
{
    /** The logger. */
    private static final Logger logger =
            LogManager.getLogger(HttpAcceptor.class);

    /**
     * Instantiates a new http acceptor.
     */
    public HttpAcceptor()
    {
        v_service_handlers = new HashSet<ServiceHandler>();
    }

    /* (non-Javadoc)
     * @see com.softlagos.reactor.Acceptor#open(int, com.softlagos.reactor.Reactor, com.softlagos.reactor.AcceptorStrategyAbstractFactory)
     */
    @Override
    public void open (int port_nr,
            Reactor reactor,
            AcceptorStrategyAbstractFactory factory)
    {
        if( reactor == null )
        {
            throw new IllegalArgumentException("reactor cannot be null.");
        }

        if( factory == null )
        {
            throw new IllegalArgumentException("factory cannot be null.");
        }

        v_port_nr = port_nr;
        v_reactor = reactor;
        v_factory = factory;

        if(logger.isTraceEnabled())
        {
            logger.trace("creating server socket handle");
        }

        v_srv_socket_handle = new ServerSocketHandle(v_port_nr);

        if(logger.isTraceEnabled())
        {
            logger.trace("registering with Reactor");
        }

        v_reactor.registerHandler(this, EventType.ACCEPT);
    }

    /* (non-Javadoc)
     * @see com.softlagos.reactor.EventHandler#handleEvent(com.softlagos.reactor.Handle, com.softlagos.reactor.EventType, java.lang.String)
     */
    @Override
    public void handleEvent(final Handle handle,
            final EventType evtType, String message)
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

        if(! (handle instanceof SocketHandle) )
        {
            throw new IllegalArgumentException("handle must be SocketHandle.");
        }

        CreationStrategy creator = v_factory.createCreationStrategy();
        ConcurrencyStrategy concurrency = v_factory.createConcurrencyStrategy();
        SocketHandle socket_handle = (SocketHandle) handle;

        if( evtType == EventType.ACCEPT  )
        {
            ServiceHandler handler = null;

            try
            {

                if(logger.isTraceEnabled())
                {
                    logger.trace("creating service handler");
                }

                handler = creator.create(socket_handle);

                if(! v_service_handlers.contains(handler))
                {
                    v_service_handlers.add(handler);
                }

                if(logger.isTraceEnabled())
                {
                    logger.trace("activating service handler");
                }

                concurrency.activate(handler);
            }
            catch(InstantiationException ex)
            {
                String msg = "error instantiating service handler: " +
                        ex.getMessage();
                logger.error(msg);
                throw new RuntimeException(ex);
            }
        }
        else if( evtType == EventType.CLOSE ||
                 evtType == EventType.REMOVED )
        {

            if(logger.isTraceEnabled())
            {
                logger.trace("closing service handler");
            }

            for(ServiceHandler handler: v_service_handlers)
            {
                handler.close();
            }

        }
        else
        {
            logger.warn("The event type [" + evtType +
                        "] is not handled by the HTTP Acceptor.");
        }
    }

    /* (non-Javadoc)
     * @see com.softlagos.reactor.EventHandler#getHandle()
     */
    @Override
    public Handle getHandle()
    {
        return v_srv_socket_handle;
    }

    // ------ >>> Private <<< ------
    private int v_port_nr;
    private Reactor v_reactor;
    private ServerSocketHandle v_srv_socket_handle;
    private AcceptorStrategyAbstractFactory v_factory;
    private final Set<ServiceHandler> v_service_handlers;

}
