package com.ibm.cof.controller.AdminController;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.cof.dao.RsvDAO;
import com.ibm.cof.dto.RsvDTO;

/**
 * Servlet implementation class RsvEveryMonth
 */
@WebServlet("/RsvEveryMonth.do")
public class RsvEveryMonth extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private int repeat_seq = 1;
    SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    Date start,end =null;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RsvEveryMonth() {
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
		PrintWriter out = response.getWriter();
		String message = null;
		
		/* Start_date, End_date(String) -> transform to Date type */
		try {
			String start_date = request.getParameter("start_dt"); // 시작날짜
			String end_date = request.getParameter("end_dt"); // 종료날짜
			String phone = request.getParameter("phone");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String site = request.getParameter("site");
			String confer_nm = request.getParameter("confer_nm");
			String start_time = request.getParameter("start_time");
			String end_time = request.getParameter("end_time");
			String title = request.getParameter("title");
			String del_pw = request.getParameter("del_pw");
			
			Date start = transFormat.parse(start_date);
			Date end = transFormat.parse(end_date);
			
			int compare = start.compareTo(end);
			RsvDAO rdao = new RsvDAO();
			RsvDTO rdto = new RsvDTO(start_time, end_time, title,site,
					confer_nm, name, phone, email, del_pw);	
			while(compare < 0 ) {
				
				if(rdao.CheckRsv(confer_nm, start_time, end_time, site, transFormat.format(start)) == false) {
					start_date = transFormat.format(start);
					//System.out.println(start_date+" is alreay booked.");
					message = start_date + " is already booked.";
					request.setAttribute("message", message);
					break;
				} else {
					rdao.insertRepeat(rdto, start, end, repeat_seq);
					start.setMonth(start.getMonth()+1);
					compare = start.compareTo(end);
				}
				
			}
					
			repeat_seq = repeat_seq +1;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
				
		RequestDispatcher rd = request.getRequestDispatcher("AdminRsv.jsp");
        rd.forward(request, response);
        return;
	}
		
	
}


