package com.ibm.cof.controller.ConfController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ibm.cof.dao.ConfDAO;
import com.ibm.cof.dao.RsvDAO;

/**
 * Servlet implementation class InsertConf
 */
@WebServlet("/UpdateConf.do")
public class UpdateConf extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateConf() {
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
		
		int seq = Integer.parseInt(request.getParameter("seq"));
		String name = request.getParameter("name");
		String prvName = request.getParameter("prvName");
		int order = Integer.parseInt(request.getParameter("order"));
		
		ConfDAO cdao = new ConfDAO();
		//ConfDTO cdto = new ConfDTO(name,stat);
		cdao.updateState(seq, name, order);
		
		if(!name.equals(prvName)){
			HttpSession session = request.getSession();
			String site = (String)session.getAttribute("project");
			RsvDAO rdao = new RsvDAO();
			rdao.updateConfName(site, prvName, name);
		}
        
        RequestDispatcher rd = request.getRequestDispatcher("SelectConf.do");
        rd.forward(request, response);
	}
}
