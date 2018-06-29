package com.thanos.session.core;

import java.io.IOException;

import javax.servlet.ServletException;

import com.thanos.session.core.SessionManager;
import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.ValveBase;

/**
 * Tomcat clustering with Redis data-cache implementation.
 *
 * Valve that implements per-request session persistence. It is intended to be
 * used with non-sticky load-balancers.
 *
 * @author Ranjith Manickam
 * @since 2.0
 */
public class SessionHandlerValue extends ValveBase {

    private SessionManager manager;

    /**
     * To set session manager
     *
     * @param manager
     */
    public void setSessionManager(SessionManager manager) {
        this.manager = manager;
    }

    /** {@inheritDoc} */
    @Override
    public void invoke(Request request, Response response) throws IOException, ServletException {
        try {
            getNext().invoke(request, response);
        } finally {
            manager.afterRequest(request);
        }
    }
}