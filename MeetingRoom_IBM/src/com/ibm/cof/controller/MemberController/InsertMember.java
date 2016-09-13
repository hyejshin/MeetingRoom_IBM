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

/* ȸ�ǽ� ����� ���ÿ� ȸ�������� �̷������ Controller */
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
		
		MemberDAO mdao = new MemberDAO(); // MemberInsert ȣ���� ���� ȸ�� DAO
		/*MemberDTO mdto = new MemberDTO(name,phone,email,site);*/
		RsvDAO rdao = new RsvDAO(); // RsvInsert ȣ���� ���� ���� DAO
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
			/* ����(Reservation) ���̺� �ִ� ���� */
			rdao.insert(date, start_time, end_time,
					title, site, confer_nm, name, phone, email, del_pw); 
			/* �̷�(History) ���̺� �ִ� ���� */
			rdao.insertHist(date, start_time, end_time,
					title, site, confer_nm, name, phone, email, del_pw);
			response.setContentType("text/html;charset=UTF-8");
			RequestDispatcher dispatcher = request.getRequestDispatcher("RsvView.jsp");
			dispatcher.forward(request, response);
		}
		
	}

}
