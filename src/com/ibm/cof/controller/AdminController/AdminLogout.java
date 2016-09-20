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
 * Servlet implementation class AdminLogout
 */
@WebServlet("/AdminLogout.do")
public class AdminLogout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogout() {
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
		
		HttpSession session=request.getSession();
		
		if(session.getAttribute("project").equals("master")){
			//총관리자일 경우 session을 끊어준다
			session.invalidate();
		}else{
			//프로젝트 관리자일 경우 
			session.setAttribute("admin", "no");
		}
		
        RequestDispatcher rd = request.getRequestDispatcher("home.do");
        rd.forward(request, response);
    }
}
