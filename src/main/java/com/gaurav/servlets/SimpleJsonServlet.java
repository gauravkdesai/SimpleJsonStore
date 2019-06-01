package com.gaurav.servlets;

import com.sun.deploy.net.HttpRequest;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO get json from DBs
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //TODO save json to DB
    }

    @Override
    public void destroy() {
        // TODO close DB connection
    }
}
