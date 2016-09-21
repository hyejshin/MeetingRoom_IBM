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
		
		String message;


		RsvDAO rdao = new RsvDAO();
		RsvDTO rdto = new RsvDTO(date, start_time, end_time, title, site,
		confer_nm, name, phone, email, del_pw);
		 
		
		System.out.println("===========InsertReservation.java============");

		
		MemberDAO mdao = new MemberDAO();
		MemberDTO mdto = new MemberDTO(name, phone, email, site);
		if (mdao.isCheckID(phone) == false) {
			mdao.insert(mdto);
		} else {
			mdao.updateMemberPhone(mdto);
		}
		
		if(rdao.CheckRsv(confer_nm,start_time,end_time,site,date)){
			// 회의실 예약
			System.out.println("예약햇음");
			rdao.insert(rdto);
			// 회의실 예약 내역 추가
			/*rdao.insertHist(date, start_time, end_time, title, site, confer_nm,
					name, phone, email, del_pw);*/
		}else{
			message = "선택하시 날짜, 회의실, 시간에 예약이 되어있어 예약이 불가능 합니다.";
			request.setAttribute("message", message);
		}
				
		RequestDispatcher dispatcher = request.getRequestDispatcher("home.do");
		dispatcher.forward(request, response);
	}
}
