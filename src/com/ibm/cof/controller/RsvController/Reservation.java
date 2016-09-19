package com.ibm.cof.controller.RsvController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.cof.dao.MemberDAO;
import com.ibm.cof.dao.RsvDAO;
import com.ibm.cof.dto.MemberDTO;
import com.ibm.cof.dto.RsvDTO;

/**
 * Servlet implementation class Reservation
 */
@WebServlet("/Reservation.do")
public class Reservation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Reservation() {
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

		String phone = request.getParameter("phone");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String site = request.getParameter("site");

		String confer_nm = request.getParameter("confer_nm");
		String date = request.getParameter("date");
		String start_time = request.getParameter("start_time");
		String end_time = request.getParameter("end_time");
		String title = request.getParameter("title");
		String del_pw = request.getParameter("del_pw");


		RsvDAO rdao = new RsvDAO();
		RsvDTO rdto = new RsvDTO(date, start_time, end_time, title, site,
		confer_nm, name, phone, email, del_pw);
		 
		System.out.println("===========InsertMember.java============");
		System.out.println("name:" + name);
		System.out.println("phone:" + phone);
		System.out.println("email: "+email);
		System.out.println("site"+ site);
		System.out.println("confer_nm"+confer_nm);

		
		MemberDAO mdao = new MemberDAO(); 
		if (mdao.isCheckID(phone) == false) {
			MemberDTO mdto = new MemberDTO(name, phone, email, site);	
			mdao.insert(mdto);
		}

		// 회의실 예약
		rdao.insert(rdto);
		// 회의실 예약 내역 추가
		/*rdao.insertHist(date, start_time, end_time, title, site, confer_nm,
				name, phone, email, del_pw);*/
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("RsvView.jsp");
		dispatcher.forward(request, response);
	}
}
