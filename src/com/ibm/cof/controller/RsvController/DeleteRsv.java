package com.ibm.cof.controller.RsvController;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

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
		
		String inststate = "삭제";
		byte[] euckrStringBuffer = inststate.getBytes(Charset.forName("euc-kr"));
		String decodedFromEucKr = new String(euckrStringBuffer, "euc-kr");
		byte[] utf8StringBuffer = decodedFromEucKr.getBytes("utf-8");
		String decodedFromUtf8 = new String(utf8StringBuffer, "utf-8");


		// 회의실 예약 내역 추가하기 위해 정보 가져오기
		RsvDTO rdto = rdao.selectBySeq(seq);
		System.out.println(rdto.getRsv_Date());
		System.out.println(rdto.getRsv_Start_Time());
		System.out.println(rdto.getRsv_Title());
		System.out.println(rdto.getRsv_Site());
		System.out.println(rdto.getRsv_Confer_Nm());
		System.out.println(rdto.getRsv_Mem_Nm());
		System.out.println(rdto.getRsv_Mem_Pn());
		System.out.println(rdto.getRsv_Mem_Em());
		System.out.println(rdto.getRsv_Del_Pw());
		
		HistoryDAO hdao = new HistoryDAO();
		HistoryDTO hdto = new HistoryDTO(rdto.getRsv_Date(), rdto.getRsv_Start_Time(), rdto.getRsv_End_Time(), 
				rdto.getRsv_Title(), rdto.getRsv_Site(), rdto.getRsv_Confer_Nm(), rdto.getRsv_Mem_Nm(),
				rdto.getRsv_Mem_Pn(), rdto.getRsv_Mem_Em(), rdto.getRsv_Del_Pw(), decodedFromUtf8);
	
		AdminDAO adao = new AdminDAO();
		
		// 관리자는 모든 예약 삭제가능
		if(session.getAttribute("admin") == "yes" && session.getAttribute("project") != "master"){
			String project = (String)session.getAttribute("project");
			String projPassword = adao.selectPassword(project);
			
			if(projPassword.equals(del_pw)){
				rdao.delete(seq);
				hdao.insert(hdto);
				message = "삭제가 완료되었습니다.";
				System.out.println("삭제완료");
			}			
		}
		else if(password.equals(del_pw)) { //비밀번호 일치여부 확인
			rdao.delete(seq);
			hdao.insert(hdto);
			message = "삭제가 완료되었습니다.";
			System.out.println("삭제완료");
		}
		else{
			message = "비밀번호가 일치하지 않습니다.";
			System.out.println(message);
		}

		request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher("home.do");
		dispatcher.forward(request, response);
	}
	
}
