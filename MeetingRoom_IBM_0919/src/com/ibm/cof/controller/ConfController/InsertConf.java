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

/**
 * Servlet implementation class InsertConf
 */
@WebServlet("/InsertConf.do")
public class InsertConf extends HttpServlet {
	private static final long serialVersionUID = 1L;
    String temp_id =null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertConf() {
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
		HttpSession session = request.getSession();
		String site = (String)session.getAttribute("id");
		String name = request.getParameter("name");
		String stat = request.getParameter("stat");
		
		if(site.equals("amore")) {
			temp_id="아모레퍼시픽";
			
		}
		if(site.equals("coway")) {
			temp_id = "코웨이";
			
		}		
		
		ConfDAO cdao = new ConfDAO();
		cdao.insert(name,temp_id,stat);
		
        
        RequestDispatcher rd = request.getRequestDispatcher("SearchConfer.do");
        rd.forward(request, response);
    }
}
