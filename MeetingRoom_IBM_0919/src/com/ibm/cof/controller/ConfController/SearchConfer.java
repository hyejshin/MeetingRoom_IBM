package com.ibm.cof.controller.ConfController;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ibm.cof.dao.ConfDAO;
import com.ibm.cof.dto.ConfDTO;


/**
 * Servlet implementation class SearchConfer
 */
@WebServlet("/SearchConfer.do")
public class SearchConfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
    String temp_id =null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchConfer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String idid = (String)session.getAttribute("id");
		
		//String temp_id = null;
		ArrayList<ConfDTO> cdtos = new ArrayList<ConfDTO>();
		
		
		if(idid.equals("amore")) {
			temp_id="아모레퍼시픽";
			
		}
		if(idid.equals("coway")) {
			temp_id = "코웨이";
			
		}
				
		ConfDAO cdao = new ConfDAO();
		cdtos = cdao.printRoom(temp_id);
		
		request.setAttribute("list", cdtos);
		
        RequestDispatcher rd = request.getRequestDispatcher("AdminConfer.jsp");
        rd.forward(request, response);
	}
	
}
