package com.ibm.cof.controller.RsvController;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.ibm.cof.dao.MemberDAO;
import com.ibm.cof.dto.MemberDTO;

/**
 * Servlet implementation class test_auto
 */
@WebServlet("/auto_fill2.do")
public class auto_fill2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public auto_fill2() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doAction(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doAction(request,response);
	}
	
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* �ڵ��ϼ����� */
		//response.setContentType("text/html;charset=UTF-8");
		String phone = request.getParameter("phone");
		//System.out.println("Phone : "+phone);
		
		//JSONArray json = new JSONArray();
		MemberDAO mdao = new MemberDAO();
		ArrayList<String> list = mdao.getNameList(phone);
		
		String searchList = new Gson().toJson(list);
		//System.out.println("After Gson()");
        response.getWriter().write(searchList);
	
	}

}
