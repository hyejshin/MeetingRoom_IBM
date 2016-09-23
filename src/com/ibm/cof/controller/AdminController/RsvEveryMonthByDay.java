package com.ibm.cof.controller.AdminController;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.cof.dao.RsvDAO;
import com.ibm.cof.dto.RsvDTO;

/**
 * Servlet implementation class RsvEveryMonthByDay
 */
@WebServlet("/RsvEveryMonthByDay.do")
public class RsvEveryMonthByDay extends HttpServlet {
	private static final long serialVersionUID = 1L;
	SimpleDateFormat transFormat1 = new SimpleDateFormat("yyyy-MM-dd");  
	SimpleDateFormat transFormat2 = new SimpleDateFormat("yyyy-MM-dd"); 
	private int repeat_seq = 1;
	Calendar start_day = Calendar.getInstance();
	Calendar end_day = Calendar.getInstance();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RsvEveryMonthByDay() {
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
						
			//System.out.println("start : "+transFormat1+"end : "+transFormat2);
			start_day.setTime(transFormat1.parse(start_date)); // String -> Calendar
			end_day.setTime(transFormat1.parse(end_date)); // String -> Calendar
			
			Date start = transFormat1.parse(start_date); // String -> Date 
			Date end = transFormat1.parse(end_date); // String -> Date
			
			int prevDayOfWeekInMonth; // 오늘이 이번달 몇째주
			int prevDayOfWeek; // 이번 달의 첫째일의 요일
						
			/*start_day.add(Calendar.MONTH, 1); // 한달 더하기 -> 10.22
			//System.out.println(date.getTime());
			start_day.set(Calendar.DAY_OF_WEEK, prevDayOfWeek); //이번달의 첫째일을 설정 
			//System.out.println(date.getTime());
			start_day.set(Calendar.DAY_OF_WEEK_IN_MONTH, prevDayOfWeekInMonth); // 오늘이 이번달 몇째주 
			//System.out.println(date.getTime());
*/		
			
			
			
			
			int compare = start_day.compareTo(end_day);
			RsvDAO rdao = new RsvDAO();
			RsvDTO rdto = new RsvDTO(start_time, end_time, title,site,
					confer_nm, name, phone, email, del_pw);	
			while(compare < 0 ) {
				
				if(rdao.CheckRsv(confer_nm, start_time, end_time, site, transFormat1.format(start_day.getTime())) == false) {
					System.out.println("Error");
					break;
				} else {
					rdao.insertRepeatByDay(rdto, start_day, end_day, repeat_seq);
					prevDayOfWeekInMonth = start_day.get(Calendar.DAY_OF_WEEK_IN_MONTH); // 오늘이 이번달 몇째주
					prevDayOfWeek = start_day.get(Calendar.DAY_OF_WEEK); // 이번 달의 첫째일의 요일
								
					start_day.add(Calendar.MONTH, 1); // 한달 더하기 -> 10.22
					//System.out.println(date.getTime());
					start_day.set(Calendar.DAY_OF_WEEK, prevDayOfWeek); //이번달의 첫째일을 설정 
					//System.out.println(date.getTime());
					start_day.set(Calendar.DAY_OF_WEEK_IN_MONTH, prevDayOfWeekInMonth); // 오늘이 이번달 몇째주
					compare = start_day.compareTo(end_day);
				}
				
			}
					
			repeat_seq = repeat_seq +1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//int per = Integer.parseInt(request.getParameter("per")); // 반복횟수 
	
   	}

}