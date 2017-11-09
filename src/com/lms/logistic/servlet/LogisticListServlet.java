package com.lms.logistic.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lms.logistic.services.dao.LogisticServiceDao;
import com.lms.logistic.services.dao.impl.LogisticServicesDaoImpl;

/**
 * Servlet implementation class LogisticListServlet
 */
@WebServlet("/LogisticListServlet")
public class LogisticListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LogisticServicesDaoImpl logisticService = new LogisticServicesDaoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogisticListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
