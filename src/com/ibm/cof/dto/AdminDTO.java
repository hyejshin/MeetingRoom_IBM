package com.ibm.cof.dto;

public class AdminDTO {

   private int Admin_Seq;
   private String Admin_Proj;
   private String Admin_Id;
   private String Admin_Pw;
   private int Admin_Month; 
   
   public AdminDTO() { }

   public AdminDTO(int admin_Seq, String admin_Proj, String admin_Id, String admin_Pw) {
      Admin_Seq = admin_Seq;
      Admin_Proj = admin_Proj;
      Admin_Id = admin_Id;
      Admin_Pw = admin_Pw; 
   }
   
   public AdminDTO(String admin_Proj, String admin_Id, String admin_Pw) {
      Admin_Proj = admin_Proj;
      Admin_Id = admin_Id;
      Admin_Pw = admin_Pw; 
   }

   public int getAdmin_Seq() {
      return Admin_Seq;
   }
   public void setAdmin_Seq(int admin_Seq) {
      Admin_Seq = admin_Seq;
   }

   public String getAdmin_Proj() {
      return Admin_Proj;
   }
   public void setAdmin_Proj(String admin_Proj) {
      Admin_Proj = admin_Proj;
   }

   public String getAdmin_Id() {
      return Admin_Id;
   }

   public void setAdmin_Id(String admin_Id) {
      Admin_Id = admin_Id;
   }

   public String getAdmin_Pw() {
      return Admin_Pw;
   }
   public void setAdmin_Pw(String admin_Pw) {
      Admin_Proj = admin_Pw;
   }   

   public int getAdmin_Month() {
      return Admin_Month; 
   }
   public void setAdmin_Month(int admin_month){
      Admin_Month = admin_month; 
   }
}