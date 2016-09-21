package com.ibm.cof.controller.RsvController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ibm.cof.dao.RsvDAO;

/**
 * Servlet implementation class DeleteRsv
 */
@WebServlet("/DeleteRsv.do")
public class DeleteRsv extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteRsv() {
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

		Integer seq = Integer.parseInt(request.getParameter("rsv_seq"));
		String del_pw =request.getParameter("del_pw");
		String message = "";

		RsvDAO rdao = new RsvDAO();
		String password = rdao.getPassword(seq);
		
		// 관리자는 모든 예약 삭제가능
		if(session.getAttribute("admin") == "yes" && session.getAttribute("project") != "master"){
			rdao.delete(seq);
			System.out.println("삭제완료");
		}
		else if(password.equals(del_pw)) { //비밀번호 일치여부 확인
			rdao.delete(seq);
			System.out.println("삭제완료");
		}
		else{
			message = "비밀번호가 일치하지 않습니다.";
			request.setAttribute("message", message);
			System.out.println(message);
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("home.do");
		dispatcher.forward(request, response);
	}
}
