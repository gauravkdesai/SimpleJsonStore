package com.gaurav.listeners;

import com.gaurav.util.DBAccessUtil;
import com.gaurav.util.Parameters;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by gauravdesai on 02/06/19.
 * <p>
 * Context listener to execute tasks in separate thread than HTTP request thread
 * Creates a executor threadpool and keeps in in context
 * Shuts down the threadpool and DB connections when context is closed.
 */
@WebListener
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(Parameters.getCorePoolSize(), Parameters.getMaximumPoolSize(), Parameters.getKeepAliveTime(),
                TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(Parameters.getThreadpoolCapacity()));
        servletContextEvent.getServletContext().setAttribute(Parameters.getContextThreadpoolExecutorName(),
                executor);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        // Shutdown executor so that it closes all open thread once they finish their tasks if any
        ThreadPoolExecutor executor = (ThreadPoolExecutor) servletContextEvent
                .getServletContext().getAttribute(Parameters.getContextThreadpoolExecutorName());
        executor.shutdown();

        // Close down DB client
        DBAccessUtil.close();
    }
}
