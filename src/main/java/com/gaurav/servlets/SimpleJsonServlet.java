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
 */
@WebServlet(urlPatterns = "/database/*", asyncSupported = true)
public class SimpleJsonServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void init() throws ServletException {
        //TODO initialize DB connection
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
        //TODO get json from DBs
        String pathInfo = request.getPathInfo();
        String id = pathInfo.substring(1);
//        response.getWriter().println("session=" + request.getSession(true).getId());

//        AsyncContext context = request.startAsync();
        AsyncContext asyncCtx = request.startAsync();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) request.getServletContext().getAttribute("executor");

        executor.execute(() -> SimpleJsonDAO.findDocumentById(id, asyncCtx));
        System.out.println("doGet complete");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("org.apache.catalina.ASYNC_SUPPORTED", true);
        //TODO get json from DBs
        AsyncContext asyncCtx = request.startAsync();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) request.getServletContext().getAttribute("executor");

        String documentJson = request.getReader().lines().collect(Collectors.joining());
        System.out.println("Document received=" + documentJson);

        executor.execute(() -> SimpleJsonDAO.insertNewJsonToDB(documentJson, asyncCtx));
        System.out.println("doPost complete");
    }

    @Override
    public void destroy() {
        // TODO close DB connection
    }


}
