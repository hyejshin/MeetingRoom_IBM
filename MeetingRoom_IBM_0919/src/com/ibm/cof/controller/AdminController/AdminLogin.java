package com.ibm.cof.controller.AdminController;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ibm.cof.dao.AdminDAO;
import com.sun.net.httpserver.HttpServer;

/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/AdminLogin.do")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		System.out.println("ID : "+id+"PW : "+pw);
		HttpSession session = request.getSession();
		
		AdminDAO adao = new AdminDAO();
		if(adao.checkLogin(id, pw) == true) {
			session.setAttribute("id", id);
			
			Enumeration se = session.getAttributeNames();
			  
			  while(se.hasMoreElements()){
			   String getse = se.nextElement()+"";
			   System.out.println("@@@@@@@ session : "+getse+" : "+session.getAttribute(getse));
			  }
			//RequestDispatcher rd = request.getRequestDispatcher("RsvView.jsp");
	        //rd.forward(request, response);
	        response.sendRedirect("RsvView.jsp");
		} else {
			System.out.println("error");
		}
			
	}
	

}
