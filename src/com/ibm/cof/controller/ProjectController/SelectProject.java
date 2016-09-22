package com.ibm.cof.controller.ProjectController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import java.util.ArrayList;
import com.ibm.cof.dao.AdminDAO;
import com.ibm.cof.dto.AdminDTO;


/**
 * Servlet implementation class SelectProject
 */
@WebServlet("/SelectProject.do")
public class SelectProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectProject() {
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

		ArrayList<AdminDTO> adtos = new ArrayList<AdminDTO>();
		AdminDAO adao = new AdminDAO();
		adtos = adao.selectAll();		
	
		request.setAttribute("list", adtos);
		
		RequestDispatcher rd = request.getRequestDispatcher("AdminProject.jsp");
		rd.forward(request, response);
	}
}
