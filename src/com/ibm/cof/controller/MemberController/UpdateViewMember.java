package com.ibm.cof.controller.MemberController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ibm.cof.dao.MemberDAO;

/**
 * Servlet implementation class UpdateViewMember
 */
@WebServlet("/UpdateViewMember.do")
public class UpdateViewMember extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateViewMember() {
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

		String seq = request.getParameter("seq");
		
		JSONArray json = new JSONArray();
		MemberDAO mdao = new MemberDAO();
		json = mdao.selectBySeq(seq);
		
		JSONObject obj = new JSONObject();
		obj.put("result",json);
		//System.out.println(obj);
		
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(obj);
		response.setCharacterEncoding("UTF-8");
    }
	
}
