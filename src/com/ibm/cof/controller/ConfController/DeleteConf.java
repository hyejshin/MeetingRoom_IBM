package com.ibm.cof.controller.ConfController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ibm.cof.dao.ConfDAO;
import com.ibm.cof.dao.RsvDAO;

/**
 * Servlet implementation class DeleteConf
 */
@WebServlet("/DeleteConf.do")
public class DeleteConf extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteConf() {
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

		ConfDAO cdao = new ConfDAO();
		int seq = Integer.parseInt(request.getParameter("seq"));
		String confer_nm = request.getParameter("confer_nm");
		cdao.delete(seq);
		
		//해당 회의실 예약내역 삭제
		HttpSession session = request.getSession();
		String site = (String)session.getAttribute("project");
		RsvDAO rdao = new RsvDAO();
		rdao.deleteConfName(site, confer_nm);
		
		System.out.println(confer_nm);
		
        RequestDispatcher rd = request.getRequestDispatcher("SelectConf.do");
        rd.forward(request, response);
    }
}
