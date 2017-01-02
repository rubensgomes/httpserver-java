/**
 * Copyright (C) 1999-2016 Rubens Gomes <rubens.s.gomes@gmail.com>.
 * All Rights Reserved.
 *
 * File: HttpMain.java
 *
 * Author: Rubens Gomes
 */
package com.softlagos.httpserver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.softlagos.Constants;
import com.softlagos.reactor.Acceptor;
import com.softlagos.reactor.AcceptorStrategyAbstractFactory;
import com.softlagos.reactor.InitiationDispatcher;
import com.softlagos.reactor.Reactor;
import com.softlagos.reactor.SystemPropertiesAcceptorStrategyFactory;
import com.softlagos.util.SystemProperties;

/**
 * The main start up method for the HTTP server.
 *
 * @author Rubens Gomes
 */
public final class HttpMain
{
    /** The logger. */
    private static final Logger logger =
            LogManager.getLogger(HttpMain.class);


    public static void main(String args[])
    {
        if(logger.isTraceEnabled())
        {
            logger.trace("creating acceptor strategy factor...");
        }

        AcceptorStrategyAbstractFactory factory =
                    new SystemPropertiesAcceptorStrategyFactory(
                                      HttpServiceHandler.class);

        int port_nr = SystemProperties.instance()
                .getPropertyAsInt(Constants.HTTPSERVER_PORT);

        if(logger.isTraceEnabled())
        {
            logger.trace("HTTP server port is: " + port_nr);
        }

        if(logger.isTraceEnabled())
        {
            logger.trace("creating HTTP acceptor ...");
        }

        Acceptor acceptor = new HttpAcceptor();

        if(logger.isTraceEnabled())
        {
            logger.trace("creating reactor...");
        }

        Reactor reactor = InitiationDispatcher.instance();

        if(logger.isTraceEnabled())
        {
            logger.trace("initializing HTTP acceptor ...");
        }

        acceptor.open(port_nr, reactor, factory);

        if(logger.isTraceEnabled())
        {
            logger.trace("start reactor: listen to events...");
        }

        reactor.handleEvents();
    }
}
