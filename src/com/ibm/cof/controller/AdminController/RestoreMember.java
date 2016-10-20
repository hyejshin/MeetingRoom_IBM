package com.ibm.cof.controller.AdminController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ibm.cof.dao.MemberDAO;
import com.ibm.cof.dto.BlockDTO;
import com.ibm.cof.dto.MemberDTO;

/**
 * Servlet implementation class RestoreMember
 */
@WebServlet("/RestoreMember.do")
public class RestoreMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestoreMember() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		MemberDAO mdao = new MemberDAO();
		BlockDTO bdto = mdao.fetchAllBySeq_Block(seq);
		
		mdao.deleteBlock(seq);
		mdao.insertBlock(bdto);

		RequestDispatcher rd = request.getRequestDispatcher("SearchBlock.do?option=all");
		rd.forward(request, response);
    }


}
