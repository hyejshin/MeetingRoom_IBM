package com.ibm.cof.controller.AdminController;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.cof.dao.AdminDAO;
import com.ibm.cof.dao.RsvDAO;
import com.ibm.cof.dto.RsvDTO;

/**
 * Servlet implementation class RsvEveryMonthByDay
 */
@WebServlet("/RsvEveryMonthByDay.do")
public class RsvEveryMonthByDay extends HttpServlet {
   private static final long serialVersionUID = 1L;
   SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
   // SimpleDateFormat transFormat2 = new SimpleDateFormat("yyyy-MM-dd");
   private int repeat_seq = 1;
   
   /**
    * @see HttpServlet#HttpServlet()
    */
   public RsvEveryMonthByDay() {
      super();
      // TODO Auto-generated constructor stub
   }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
    *      response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      // TODO Auto-generated method stub
      doProcess(request, response);
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
    *      response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      // TODO Auto-generated method stub
      doProcess(request, response);
   }

   protected void doProcess(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      request.setCharacterEncoding("UTF-8");
      PrintWriter out = response.getWriter();
      String message = "Free day";

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
         
         Calendar start_day = Calendar.getInstance();
         Calendar end_day = Calendar.getInstance();
         Calendar today = Calendar.getInstance();
         Date start = transFormat.parse(start_date); // String -> Date
         Date end = transFormat.parse(end_date); // String -> Date
         Date today_dd = new Date();
         
         /* 관리자가 제한횟수(달)을 가져온다 */
         AdminDAO adao = new AdminDAO();
         int month = adao.selectMonthbyName(site);
         
         start_day.setTime(transFormat.parse(start_date)); // String -> Calendar
         end_day.setTime(transFormat.parse(end_date)); // String -> Calendar
         today.add(Calendar.MONTH, month);
         //today.setTime(today_dd); // Calendar -> Date
                
         int prevDayOfWeekInMonth; // 오늘이 이번달 몇째주
         int prevDayOfWeek; // 이번 달의 첫째일의 요일
        
         int compare = start_day.compareTo(end_day);
         int compare_admin = start_day.compareTo(today); // 시작날짜와 오늘날짜+관리자제한달수를 비교한다.
         int compare_result = end_day.compareTo(today);  // 종료날짜와 오늘날짜_관리자제한달수를 비교한다. 
                                       // 종료날짜가 관리자제한달보다 작으면 음수 , 크면 양수 반환
         
         RsvDAO rdao = new RsvDAO();
         RsvDTO rdto = new RsvDTO(start_time, end_time, title, site, confer_nm, name, phone, email, del_pw);
         
         System.out.println("오늘 : "+today.getTime()+"시작날짜 : "+start_day.getTime()+"종료날짜 : "+end_day.getTime());
         System.out.println("compare_result : "+compare_result);
         if(compare_result < 0 ) // 종료날짜 < 관리날짜
         {
        	 System.out.println("start_day : "+start_day.getTime());
        	 while (compare < 0) { // 시작날짜 < 종료날짜
        		
        		 
                 if (rdao.CheckRsv(confer_nm, start_time, end_time, site,
                       transFormat.format(start_day.getTime())) == false) {
                    message = start_date + " is already booked.";
                    request.setAttribute("message", message);
                    //out.println(message);
                    break;
                 }
                 // else {
                 // rdao.insertRepeatByDay(rdto, start_day, end_day, repeat_seq);
                 prevDayOfWeekInMonth = start_day.get(Calendar.DAY_OF_WEEK_IN_MONTH); // 오늘이 이번달 몇째주
                 prevDayOfWeek = start_day.get(Calendar.DAY_OF_WEEK); // 이번 달의 첫째일의 요일
                                                         
                 start_day.add(Calendar.MONTH, 1); // 한달 더하기 -> 10.22
                 // System.out.println(date.getTime());
                 start_day.set(Calendar.DAY_OF_WEEK, prevDayOfWeek); // 이번달의 첫째일을
                                                        // 설정
                 // System.out.println(date.getTime());
                 start_day.set(Calendar.DAY_OF_WEEK_IN_MONTH, prevDayOfWeekInMonth); // 오늘이
                                                                    // 이번달
                                                                    // 몇째주
                 compare = start_day.compareTo(end_day);
                 // }
              }
              if (message.equals(start_date + " is already booked.") != true) {
                 start_day.setTime(transFormat.parse(start_date)); // String ->
                                                        // Calendar
                 end_day.setTime(transFormat.parse(end_date)); // String ->
                                                     // Calendar
                 compare = start_day.compareTo(end_day);
                 while (compare < 0) {
                    rdao.insertRepeatByDay(rdto, start_day, end_day, repeat_seq);
                    prevDayOfWeekInMonth = start_day.get(Calendar.DAY_OF_WEEK_IN_MONTH); // 오늘이
                                                                          // 이번달
                                                                          // 몇째주
                    prevDayOfWeek = start_day.get(Calendar.DAY_OF_WEEK); // 이번
                                                              // 달의
                                                              // 첫째일의
                                                              // 요일

                    start_day.add(Calendar.MONTH, 1); // 한달 더하기 -> 10.22
                    // System.out.println(date.getTime());
                    start_day.set(Calendar.DAY_OF_WEEK, prevDayOfWeek); // 이번달의
                                                           // 첫째일을
                                                           // 설정
                    // System.out.println(date.getTime());
                    start_day.set(Calendar.DAY_OF_WEEK_IN_MONTH, prevDayOfWeekInMonth); // 오늘이
                                                                       // 이번달
                                                                       // 몇째주
                    compare = start_day.compareTo(end_day);
                 }
                 repeat_seq = repeat_seq + 1;
              }         	 
         } 
         
         else // 관리날짜 < 종료날짜
         {
        	 while (compare_admin < 0) { // 시작날짜 < 관리날짜 

                 if (rdao.CheckRsv(confer_nm, start_time, end_time, site,
                       transFormat.format(start_day.getTime())) == false) {
                    message = start_date + " is already booked.";
                    request.setAttribute("message", message);
                    out.println(message);
                    
                    break;
                 }
                 // else {
                 // rdao.insertRepeatByDay(rdto, start_day, end_day, repeat_seq);
                 prevDayOfWeekInMonth = start_day.get(Calendar.DAY_OF_WEEK_IN_MONTH); // 오늘이
                                                                       // 이번달
                                                                       // 몇째주
                 prevDayOfWeek = start_day.get(Calendar.DAY_OF_WEEK); // 이번 달의
                                                           // 첫째일의
                                                           // 요일

                 start_day.add(Calendar.MONTH, 1); // 한달 더하기 -> 10.22
                 // System.out.println(date.getTime());
                 start_day.set(Calendar.DAY_OF_WEEK, prevDayOfWeek); // 이번달의 첫째일을
                                                        // 설정
                 // System.out.println(date.getTime());
                 start_day.set(Calendar.DAY_OF_WEEK_IN_MONTH, prevDayOfWeekInMonth); // 오늘이
                                                                    // 이번달
                                                                    // 몇째주
                 compare_admin = start_day.compareTo(today);
                 // }
              }
              if (message.equals(start_date + " is already booked.") != true) {
                 start_day.setTime(transFormat.parse(start_date)); // String ->
                                                        // Calendar
                 end_day.setTime(transFormat.parse(end_date)); // String ->
                                                     // Calendar
                 compare = start_day.compareTo(today);
                 while (compare < 0) {
                	System.out.println("test_dayrepeat");
                    rdao.insertRepeatByDay(rdto, start_day, end_day, repeat_seq);
                    prevDayOfWeekInMonth = start_day.get(Calendar.DAY_OF_WEEK_IN_MONTH); // 오늘이
                                                                          // 이번달
                                                                          // 몇째주
                    prevDayOfWeek = start_day.get(Calendar.DAY_OF_WEEK); // 이번
                                                              // 달의
                                                              // 첫째일의
                                                              // 요일

                    start_day.add(Calendar.MONTH, 1); // 한달 더하기 -> 10.22
                    // System.out.println(date.getTime());
                    start_day.set(Calendar.DAY_OF_WEEK, prevDayOfWeek); // 이번달의
                                                           // 첫째일을
                                                           // 설정
                    // System.out.println(date.getTime());
                    start_day.set(Calendar.DAY_OF_WEEK_IN_MONTH, prevDayOfWeekInMonth); // 오늘이
                                                                       // 이번달
                                                                       // 몇째주
                    compare = start_day.compareTo(today);
                 }
                 repeat_seq = repeat_seq + 1;
              }
         }
         
         

         
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      

		if (message.equals("Free day") != true) {
			RequestDispatcher rd = request.getRequestDispatcher("fail_repeat.jsp");
			rd.forward(request, response);
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("success_repeat.jsp");
		rd.forward(request, response);
		return;
 }

}