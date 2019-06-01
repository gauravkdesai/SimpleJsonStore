package com.gaurav.servlets;


import com.gaurav.dao.SimpleJsonDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by gauravdesai on 01/06/19.
 */
public class SimpleJsonServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        //TODO initialize DB connection
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO get json from DBs
        String pathInfo = request.getPathInfo();
        String id = pathInfo.substring(1);
        SimpleJsonDAO.findDocumentById(id, response);
//        response.getWriter().println("session=" + request.getSession(true).getId());


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //TODO get json from DBs
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println("<h1>Hello Servlet</h1>");
        response.getWriter().println("session=" + request.getSession(true).getId());
    }

    @Override
    public void destroy() {
        // TODO close DB connection
    }




}
