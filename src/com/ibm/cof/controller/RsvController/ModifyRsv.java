package com.ibm.cof.controller.RsvController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ibm.cof.dao.AdminDAO;
import com.ibm.cof.dao.HistoryDAO;
import com.ibm.cof.dao.MemberDAO;
import com.ibm.cof.dao.RsvDAO;
import com.ibm.cof.dto.HistoryDTO;
import com.ibm.cof.dto.MemberDTO;
import com.ibm.cof.dto.RsvDTO;

/**
 * Servlet implementation class ModifyRsv
 */
@WebServlet("/ModifyRsv.do")
public class ModifyRsv extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModifyRsv() {
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
		
		String key_pw;
		String message;

		RsvDAO rdao = new RsvDAO();
		RsvDTO rdto = new RsvDTO(seq, date, start_time, end_time, title, site,
				confer_nm, name, phone, email, del_pw);


		System.out.println("===========ModifyRsv.java============");

		// 회원정보 등록 또는 업데이트
		MemberDAO mdao = new MemberDAO();
		MemberDTO mdto = new MemberDTO(name, phone, email, site);
		if (mdao.isCheckID(phone) == false) {
			mdao.insert(mdto);
		} else {
			mdao.updateMemberPhone(mdto);
		}
		
		/*
		if(rdao.CheckRsvSeq(seq,confer_nm,start_time,end_time,site,date)) {
			rdao.update(rdto);
		}else {
			System.out.println("modifyError!");
		}*/
		
		HistoryDAO hdao = new HistoryDAO();
		HistoryDTO hdto = new HistoryDTO(date, start_time, end_time, title, site,
				confer_nm, name, phone, email, del_pw, "수정");

		AdminDAO adao = new AdminDAO();
		
		//겹치는 일정 존재 여부 확인
		if(rdao.CheckRsvSeq(seq,confer_nm,start_time,end_time,site,date)) {
			key_pw = rdao.getPassword(seq);
			
			//관리자는 모든 예약 수정 가능
			if(session.getAttribute("admin") == "yes" && session.getAttribute("project") != "master"){
				String project = (String)session.getAttribute("project");
				String projPassword = adao.selectPassword(project);
				
				if(projPassword.equals(del_pw)){
					rdao.update(rdto);
					System.out.println("업데이트 완료");
					
					hdao.insert(hdto); // 회의실 예약 내역 추가
				}
			}
			else if(key_pw.equals(del_pw)) { //비밀번호 일치여부 확인
				rdao.update(rdto);
				System.out.println("업데이트 완료");
				
				hdao.insert(hdto); // 회의실 예약 내역 추가
			}
			else{
				message = "비밀번호가 일치하지 않습니다.";
				request.setAttribute("message", message);
				System.out.println(message);
			}
		}else{
			message = "시간이 겹쳐서 수정이 불가합니다.";
			request.setAttribute("message", message);
			System.out.println(message);
		}


		/*rdao.insertHist(date, start_time, end_time, title, site, confer_nm,
            name, phone, email, del_pw);*/


		RequestDispatcher rd = request.getRequestDispatcher("home.do");
		rd.forward(request, response);
	}
}