package com.ibm.cof.controller.RsvController;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

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
		String message = "";

		
		MemberDTO mdto = new MemberDTO(name, phone, email, site);
		
		RsvDAO rdao = new RsvDAO();
		RsvDTO rdto = new RsvDTO(seq, date, start_time, end_time, title, site,
				confer_nm, name, phone, email, del_pw);

		System.out.println("===========ModifyRsv.java============");
		
		/*
		if(rdao.CheckRsvSeq(seq,confer_nm,start_time,end_time,site,date)) {
			rdao.update(rdto);
		}else {
			System.out.println("modifyError!");
		}*/
		
		/*String inststate = "수정";
		byte[] euckrStringBuffer = inststate.getBytes(Charset.forName("euc-kr"));
		String decodedFromEucKr = new String(euckrStringBuffer, "euc-kr");
		byte[] utf8StringBuffer = decodedFromEucKr.getBytes("utf-8");
		String decodedFromUtf8 = new String(utf8StringBuffer, "utf-8");*/
		
		
		HistoryDAO hdao = new HistoryDAO();
		HistoryDTO hdto = new HistoryDTO(date, start_time, end_time, title, site,
				confer_nm, name, phone, email, del_pw, "modify");

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
					message = "sucess";
					memberUpdate(mdto); // 회원정보 등록 또는 업데이트
					hdao.insert(hdto); // 회의실 예약 내역 추가
				}
			}
			else if(key_pw.equals(del_pw)) { //비밀번호 일치여부 확인
				rdao.update(rdto);
				message = "sucess";
				memberUpdate(mdto); // 회원정보 등록 또는 업데이트
				hdao.insert(hdto); // 회의실 예약 내역 추가
			}
			else{
				message = "password not match";
				System.out.println(message);
			}
		}else{
			message = "not valid time";
			System.out.println(message);
		}


		JSONObject json = new JSONObject();
		json.put("message", message);
		
		JSONObject obj = new JSONObject();
		obj.put("result", json);
	
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(obj);
		response.setCharacterEncoding("UTF-8");
		
		
		/*request.setAttribute("message", message);
		RequestDispatcher rd = request.getRequestDispatcher("home.do");
		rd.forward(request, response);*/
	}
	
	// 회원정보 등록 또는 업데이트
	public void memberUpdate(MemberDTO mdto){
		MemberDAO mdao = new MemberDAO();
		if (mdao.isCheckID(mdto.getMem_Pn()) == false) {
			mdao.insert(mdto);
		} else {
			mdao.updateMemberPhone(mdto);
		}
	}
}