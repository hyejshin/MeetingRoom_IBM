package com.ibm.cof.controller.ProjectController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import com.ibm.cof.dao.AdminDAO;
import com.ibm.cof.dto.AdminDTO;

/**
 * Servlet implementation class InsertProject
 */
@WebServlet("/InsertProject.do")
public class InsertProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertProject() {
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

		String name = request.getParameter("name");  //project(site) name 
		String id = request.getParameter("id"); //admin id
		String pw = request.getParameter("passwd"); //admin password 

		System.out.println("Admin에 insert합니다.");
		AdminDAO adao = new AdminDAO();  
		AdminDTO adto = new AdminDTO(name, id, pw); 

		adao.insert(adto); 
		
		RequestDispatcher rd = request.getRequestDispatcher("SelectProject.do");
		rd.forward(request, response);
	}
}
