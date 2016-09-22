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

import com.ibm.cof.dao.AdminDAO;
import com.ibm.cof.dao.HistoryDAO;
import com.ibm.cof.dto.HistoryDTO;
import com.ibm.cof.dto.MemberDTO;

/**
 * Servlet implementation class SearchHistory
 */
@WebServlet("/SearchHistory.do")
public class SearchHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchHistory() {
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
		
		String start_date = request.getParameter("start_date");
		String end_date = request.getParameter("end_date");
		
		HttpSession session=request.getSession();
		String site = (String)session.getAttribute("project");
		
		HistoryDAO hdao = new HistoryDAO();
		ArrayList<HistoryDTO> dtos = null;
		dtos = hdao.select(site, start_date, end_date);
		request.setAttribute("list", dtos);

        RequestDispatcher rd = request.getRequestDispatcher("AdminRsvHist.jsp");
        rd.forward(request, response);
    }
}
