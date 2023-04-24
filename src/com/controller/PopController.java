package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pop.command.Command;
import com.pop.command.PopCommand;
import com.utils.CommandAction;

/**
 * Servlet implementation class PopController
 */
@WebServlet("*.po")
public class PopController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PopController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String ctxp = request.getContextPath();
		String sp = uri.substring(ctxp.length());
		
		Command com = null;
		
		if(sp.equalsIgnoreCase("/Pop.po")) {
			com = new PopCommand();		
		}

		if (com != null) {
			CommandAction action=com.execute(request, response);
			if (action.isRedirect()) {
				response.sendRedirect(action.getWhere());
			}else {
				RequestDispatcher dis=request.getRequestDispatcher(action.getWhere());
				dis.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
