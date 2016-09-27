package com.ibm.cof.controller.RsvController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ibm.cof.dao.AdminDAO;
import com.ibm.cof.dao.ProjectDAO;
import com.ibm.cof.dto.ProjectDTO;

/**
 * Servlet implementation class SelectByCondition
 */
@WebServlet("/home.do")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
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
	
		String key = request.getParameter("key");
		String nextPage = "RsvView.jsp";
		
		AdminDAO adao = new AdminDAO();
		String project = adao.selectProjectName(key);
		
		// session이 없으면 관리자 로그인 페이지로 이동
		if(session.getAttribute("admin") == null){
			if(key == null || project.equals("")){
		        nextPage = "AdminLogin.jsp";
			}else{
				session.setMaxInactiveInterval(10*60*60);
				session.setAttribute("admin", "no");
		        session.setAttribute("project", project);
			}
		}else if(session.getAttribute("project").equals("master")){
			ArrayList<String> proj = adao.Projlist(); 
	        request.setAttribute("proj", proj);
		}
		
		String selectDate = request.getParameter("selectDate");
		if(selectDate != null)
			request.setAttribute("selectDate", selectDate);
		
		String message = request.getParameter("message");
		if(message != null)
			request.setAttribute("message", message);
		
        RequestDispatcher rd = request.getRequestDispatcher(nextPage);
        rd.forward(request, response);
    }
}
