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

import com.ibm.cof.dao.AdminDAO;
import com.ibm.cof.dao.RsvDAO;
import com.ibm.cof.dto.RsvDTO;

/**
 * Servlet implementation class RsvEveryWeekByDay
 */
@WebServlet("/RsvEveryWeekByDay.do")
public class RsvEveryWeekByDay extends HttpServlet {
   private static final long serialVersionUID = 1L;
   private int repeat_seq;
   SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
   Calendar start_day = Calendar.getInstance();
   Calendar end_day = Calendar.getInstance();
   Date today = new Date();

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
         String color = request.getParameter("color");

         Date start = transFormat.parse(start_date); // String -> Date
         Date end = transFormat.parse(end_date); // String -> Date
         
         // 반복 repeat_seq 가져오기
         RsvDAO rdao = new RsvDAO();
         repeat_seq = rdao.selectRepeatSeq(site);
         
         /* 관리자가 제한횟수(달)을 가져온다 */
         AdminDAO adao = new AdminDAO();
         int month = adao.selectMonthbyName(site);
         
         /* 오늘날짜에 관리자가 제한한 달을 수를 더한다 */
         
         today.setMonth(today.getMonth()+month); // 오늘날짜 더하기 제한달
         
         int compare = start.compareTo(end); // 시작날짜와 종료날짜를 비교한다.
         int compare_admin = start.compareTo(today); // 시작날짜와 오늘날짜+관리자제한달수를 비교한다.
         int compare_result = end.compareTo(today);  // 종료날짜와 오늘날짜+관리자제한달수를 비교한다. 
                                       // 종료날짜가 관리자제한달보다 작으면 음수 , 크면 양수 반환
         
         RsvDTO rdto = new RsvDTO(start_time, end_time, title, site, confer_nm, name, phone, email, del_pw, color);
         
         System.out.println("오늘 : "+today+"시작날짜 : "+start+"종료날짜 : "+end);

         if( compare_result < 0 ) // 종료날짜 < 관리날짜
         {
        	 while (compare < 0) {

                 if (rdao.CheckRsv(confer_nm, start_time, end_time, site, transFormat.format(start)) == false) {
                    start_date = transFormat.format(start);
                    System.out.println(start_date + " is alreay booked.");
                    message = start_date + " is already booked.";
                    request.setAttribute("message", message);
                    out.println(message);
                    break;
                 }
                 start.setDate(start.getDate() + 7 *per);
                 System.out.println("제한날짜 < 종료날짜일경우 - 시작날짜 : "+start+"종료날짜 : "+end);
                 
                 compare = start.compareTo(end);

              }

              if (message.equals(start_date + " is already booked.") != true) {
                 start_date = request.getParameter("start_dt");
                 start = transFormat.parse(start_date);
                 compare = start.compareTo(end);

                 while (compare < 0) {
                    rdao.insertRepeat(rdto, start, end, repeat_seq+1);
                    start.setDate(start.getDate() + 7 *per);
                    compare = start.compareTo(end);
                 }

                 
              }
         }
         
         else { // 관리날짜 < 종료날짜
        	 
        	 while (compare_admin < 0) { // 시작날짜 < 관리날짜

                 if (rdao.CheckRsv(confer_nm, start_time, end_time, site, transFormat.format(start)) == false) {
                    start_date = transFormat.format(start);
                    System.out.println(start_date + " is alreay booked.");
                    message = start_date + " is already booked.";
                    request.setAttribute("message", message);
                    out.println(message);
                    break;
                 }
                 start.setDate(start.getDate() + 7 * per);
                 System.out.println("제한날짜 < 종료날짜일경우 - 시작날짜 : "+start+"오늘 : "+today);
                 compare_admin = start.compareTo(today);

              }

              if (message.equals(start_date + " is already booked.") != true) {
                 start_date = request.getParameter("start_dt");
                 start = transFormat.parse(start_date);
                 compare = start.compareTo(today);

                 while (compare < 0) {
                    rdao.insertRepeat(rdto, start, today, repeat_seq+1);
                    start.setDate(start.getDate() + 7 *per);
                    compare = start.compareTo(today);
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