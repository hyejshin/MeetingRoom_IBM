package com.ibm.cof.controller.ConfController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ibm.cof.dao.ConfDAO;
import com.ibm.cof.dto.ConfDTO;

/**
 * Servlet implementation class SelectConf
 */
@WebServlet("/SelectConf.do")
public class SelectConf extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectConf() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		String project = (String)session.getAttribute("project");
		
		ArrayList<ConfDTO> cdtos = new ArrayList<ConfDTO>();
		
		ConfDAO cdao = new ConfDAO();
		cdtos = cdao.printRoom(project);
		
		request.setAttribute("list", cdtos);

		RequestDispatcher rd = request.getRequestDispatcher("AdminConference.jsp");
		rd.forward(request, response);
	}
}
