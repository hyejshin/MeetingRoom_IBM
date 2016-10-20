package com.ibm.cof.controller.MemberController;

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
 * Servlet implementation class SearchBlock
 */
@WebServlet("/SearchBlock.do")
public class SearchBlock extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchBlock() {
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

		String option = request.getParameter("option");
		String context = request.getParameter("context");
		
		HttpSession session=request.getSession();
		String site = (String)session.getAttribute("project");
		
		MemberDAO dao = new MemberDAO();
		ArrayList<BlockDTO> dtos = null;
		
		if(option.equals("all")) {
			dtos = dao.selectAll_Block(site);
		}else{
			dtos = dao.selectByCondition_Block(site, option, context);
		}
		
		request.setAttribute("list", dtos);
		
        RequestDispatcher rd = request.getRequestDispatcher("AdminBlock.jsp");
        rd.forward(request, response);
    }

}
