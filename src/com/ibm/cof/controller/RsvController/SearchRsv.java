package com.ibm.cof.controller.RsvController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ibm.cof.dao.RsvDAO;
import com.ibm.cof.dto.RsvDTO;

/**
 * Servlet implementation class SearchRsv
 */
@WebServlet("/SearchRsv.do")
public class SearchRsv extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchRsv() {
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
		
		RsvDAO dao = new RsvDAO();
		ArrayList<RsvDTO> dtos = null;
		
		HttpSession session=request.getSession();
		String site = (String)session.getAttribute("project");
		
		if(site.equals("master")){
			if(option.equals("all")) {
				dtos = dao.selectAllMaster();
			}else{
				dtos = dao.selectByConditionMaster(option, context);
			}
		}else{
			if(option.equals("all")) {
				dtos = dao.selectAll(site);
			}else{
				dtos = dao.selectByCondition(option, context, site);
			}
		}
		
		request.setAttribute("list", dtos);
		
        RequestDispatcher rd = request.getRequestDispatcher("Search.jsp");
        rd.forward(request, response);
    }
}
