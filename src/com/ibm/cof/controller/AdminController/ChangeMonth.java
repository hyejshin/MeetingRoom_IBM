package com.ibm.cof.controller.AdminController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ibm.cof.dao.AdminDAO;

/**
 * Servlet implementation class ChangeMonth
 */
@WebServlet("/ChangeMonth.do")
public class ChangeMonth extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeMonth() {
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
	      
	      int newmonth = Integer.parseInt(request.getParameter("newmonth"));

	      System.out.println("new month:" +newmonth);
	      
	      AdminDAO adao = new AdminDAO();
	      adao.changeMonth(project, newmonth);

	   
	      RequestDispatcher rd = request.getRequestDispatcher("AdminSetting.do");
	      rd.forward(request, response);
	   }
}
