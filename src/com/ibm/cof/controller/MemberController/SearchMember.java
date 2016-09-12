package com.ibm.cof.controller.MemberController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.cof.dao.MemberDAO;
import com.ibm.cof.dto.MemberDTO;

/**
 * Servlet implementation class SearchMember
 */
@WebServlet("/SearchMember.do")
public class SearchMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchMember() {
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

		String option = request.getParameter("option");
		String context = request.getParameter("context");
		
		MemberDAO dao = new MemberDAO();
		ArrayList<MemberDTO> dtos = null;
		
		if(option.equals("all")) {
			dtos = dao.selectAll();
		}else{
			dtos = dao.selectByCondition(option, context);
		}
		
		request.setAttribute("list", dtos);
		
        RequestDispatcher rd = request.getRequestDispatcher("AdminMember.jsp");
        rd.forward(request, response);
    }
}
