package com.ibm.cof.controller.ConfController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.cof.dao.ConfDAO;
import com.ibm.cof.dao.MemberDAO;
import com.ibm.cof.dto.ConfDTO;
import com.ibm.cof.dto.MemberDTO;

/**
 * Servlet implementation class UpdateConfer
 */
@WebServlet("/UpdateConfer.do")
public class UpdateConfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateConfer() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		
		int seq = Integer.parseInt(request.getParameter("seq"));
		String name = request.getParameter("name");
		String stat = request.getParameter("stat");
		System.out.println("name : "+name+" stat : "+stat);
		
		ConfDAO cdao = new ConfDAO();
		//ConfDTO cdto = new ConfDTO(name,stat);
		cdao.updateState(seq, name, stat);
        
        RequestDispatcher rd = request.getRequestDispatcher("SearchConfer.do");
        rd.forward(request, response);
	
	}

}
