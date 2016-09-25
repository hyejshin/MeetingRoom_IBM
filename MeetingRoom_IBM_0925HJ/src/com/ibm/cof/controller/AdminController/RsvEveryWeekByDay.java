package com.ibm.cof.controller.AdminController;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
 * Servlet implementation class RsvEveryWeekByDay
 */
@WebServlet("/RsvEveryWeekByDay.do")
public class RsvEveryWeekByDay extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
	Calendar start_day = Calendar.getInstance();
	Calendar end_day = Calendar.getInstance();
	int repeat_seq = 1;
	
	
       
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
   		String message =null;
   		
   		/* Start_date, End_date(String) -> transform to Date type */
		try {
			Integer per = Integer.parseInt(request.getParameter("per"));
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
					
						
			System.out.println("Per : "+per+"Start : "+start_date+"End : "+end_date);
			//start_day.setTime(transFormat.parse(start_date)); // String -> Calendar
			//end_day.setTime(transFormat.parse(end_date)); // String -> Calendar
			
			Date start = transFormat.parse(start_date); // String -> Date 
			Date end = transFormat.parse(end_date); // String -> Date
								
			int compare = start.compareTo(end);
			RsvDAO rdao = new RsvDAO();
			RsvDTO rdto = new RsvDTO(start_time, end_time, title,site,
					confer_nm, name, phone, email, del_pw);	
			while(compare < 0 ) {
				
				if(rdao.CheckRsv(confer_nm, start_time, end_time, site, transFormat.format(start_day.getTime())) == false) {
					message = start_date + " is already booked.";
					request.setAttribute("message", message);
					break;
				} else {					
					rdao.insertRepeat(rdto, start, end, repeat_seq);
					start.setDate(start.getDate() + 7 * per);							
					compare = start.compareTo(end);
				}
				
			}
					
			repeat_seq = repeat_seq +1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("home.do");
        rd.forward(request, response);
   	}

}
