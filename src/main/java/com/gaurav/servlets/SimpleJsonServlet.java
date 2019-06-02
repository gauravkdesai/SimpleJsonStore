package com.gaurav.servlets;


import com.gaurav.dao.SimpleJsonDAO;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * Created by gauravdesai on 01/06/19.
 * <p>
 * Main Servlet which is face of this application
 * exposes two methods, doGet and doPost
 * doGet expects and id in url path for which we search for document in DB
 * doPost expects a JSON object with id as one of the attribute. The JSON object is stored in DB
 */
@WebServlet(urlPatterns = "/database/*", asyncSupported = true)
public class SimpleJsonServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // extract id from request url path /database/id
        String pathInfo = request.getPathInfo();
        String id = pathInfo != null ? pathInfo.substring(1) : ""; // remove "/" from "/id" to get "id"
        System.out.println("Searching for id=" + id);

        // Start async context for asynchronous request processing
        AsyncContext asyncCtx = request.startAsync();

        // Get threadpool from context
        ThreadPoolExecutor executor = getThreadPoolExecutorFromContext(request);

        // Add async request to threadpool blocking queue
        executor.execute(() -> SimpleJsonDAO.findDocumentById(id, asyncCtx));
        System.out.println("doGet complete");

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Read json request body from request
        String documentJson = request.getReader().lines().collect(Collectors.joining());
        System.out.println("Document received=" + documentJson);

        // Start async context for asynchronous request processing
        AsyncContext asyncCtx = request.startAsync();

        // Get threadpool from context
        ThreadPoolExecutor executor = getThreadPoolExecutorFromContext(request);
        executor.execute(() -> SimpleJsonDAO.insertNewJsonToDB(documentJson, asyncCtx));
        System.out.println("doPost complete");
    }

    /**
     * Gets executor threadpool from context
     *
     * @param request
     * @return
     */
    private ThreadPoolExecutor getThreadPoolExecutorFromContext(HttpServletRequest request) {
        return (ThreadPoolExecutor) request.getServletContext().getAttribute("context.threadpool.executor");
    }

}
