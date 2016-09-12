package com.ibm.cof.controller.MemberController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.cof.dao.MemberDAO;
import com.ibm.cof.dto.MemberDTO;

/**
 * Servlet implementation class ModifyMember
 */
@WebServlet("/UpdateMember.do")
public class UpdateMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMember() {
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

		
		int seq = Integer.parseInt(request.getParameter("seq"));
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String site = request.getParameter("site");
						
		MemberDAO mdao = new MemberDAO();
		MemberDTO mdto = new MemberDTO(name, phone, email, site);
		
		mdao.updateMemberState(mdto, seq);
        
        RequestDispatcher rd = request.getRequestDispatcher("AdminMember.jsp");
        rd.forward(request, response);
    }
}
