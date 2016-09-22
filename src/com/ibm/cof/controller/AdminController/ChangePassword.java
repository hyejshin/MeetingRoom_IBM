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
import com.ibm.cof.dto.AdminDTO;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/ChangePassword.do")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangePassword() {
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
		String project = (String)session.getAttribute("project");
		
		String oldpassword = request.getParameter("oldpw");
		String newpassword = request.getParameter("newpw");

		AdminDAO adao = new AdminDAO();
		String correctPW = adao.selectPassword(project);

		String message = "";
		if(correctPW.equals(oldpassword)){
			adao.changePassword(project, newpassword);
			message = "비밀번호가 성공적으로 변경되었습니다.";
		}else{
			message = "비밀번호가 일치하지 않습니다.";
		}
		
		request.setAttribute("message", message);
		RequestDispatcher rd = request.getRequestDispatcher("AdminSetting.jsp");
		rd.forward(request, response);
	}

}
