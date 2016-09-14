package com.ibm.cof.controller.RsvController;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ibm.cof.dao.RsvDAO;
import com.ibm.cof.dao.ConfDAO;

/**
 * Servlet implementation class SelectRsvBySiteDate
 */
@WebServlet("/SelectRsvBySiteDate.do")
public class SelectRsvBySiteDate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectRsvBySiteDate() {
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

		String site = request.getParameter("site");
		String date = request.getParameter("date");
		
		JSONObject obj = new JSONObject();
		
		RsvDAO rdao = new RsvDAO();
		JSONArray json = new JSONArray();
		json = rdao.selectBySiteDate(site, date);
		
		ConfDAO cdao = new ConfDAO();
		JSONArray json2 = new JSONArray();
		json2 = cdao.selectListByName(site);
		
		obj.put("meetings",json);
		obj.put("confers",json2);
		System.out.println(obj);
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(obj);
		response.setCharacterEncoding("UTF-8");
    }
}
