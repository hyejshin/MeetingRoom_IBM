package com.ibm.cof.controller.MemberController;

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

/* 회의실 예약과 동시에 회원가입이 이루어지는 Controller */
@WebServlet("/InsertMember.do")
public class InsertMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertMember() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doAction(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doAction(request,response);
	}
	
	protected void doAction(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String viewPage=null;
		
		
		String name = request.getParameter("term");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String site = request.getParameter("site");
				
		String confer_nm = request.getParameter("confer_nm");
		String date = request.getParameter("date");
		String start_time = request.getParameter("start_time");
		String end_time = request.getParameter("end_time");
		String title = request.getParameter("title");
		String del_pw = request.getParameter("del_pw");
		
		MemberDAO mdao = new MemberDAO(); // MemberInsert 호출을 위한 회원 DAO
		/*MemberDTO mdto = new MemberDTO(name,phone,email,site);*/
		RsvDAO rdao = new RsvDAO(); // RsvInsert 호출을 위한 예약 DAO
		/*RsvDTO rdto = new RsvDTO(date, start_time, end_time,
				title, site, confer_nm, name, phone, email, del_pw);*/
				
		if(mdao.isCheckID(phone) == true) {
			String msgerr = "Duplicated Name!!";
			//response.getWriter().print("<script>alert('"+msgerr +"')</script>");	
			System.out.println("Duplicated Error");
			RequestDispatcher dispatcher = request.getRequestDispatcher("RsvView.jsp");
			dispatcher.forward(request, response);
			
			
		} else {
			mdao.insert(name,phone,email,site);
			/* 예약(Reservation) 테이블에 넣는 로직 */
			rdao.insert(date, start_time, end_time,
					title, site, confer_nm, name, phone, email, del_pw); 
			/* 이력(History) 테이블에 넣는 로직 */
			rdao.insertHist(date, start_time, end_time,
					title, site, confer_nm, name, phone, email, del_pw);
			response.setContentType("text/html;charset=UTF-8");
			RequestDispatcher dispatcher = request.getRequestDispatcher("RsvView.jsp");
			dispatcher.forward(request, response);
		}
		
	}

}
