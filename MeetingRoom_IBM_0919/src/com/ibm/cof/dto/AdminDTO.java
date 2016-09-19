package com.ibm.cof.dto;

public class AdminDTO {

   private int Admin_Seq;
   private String Admin_Proj;
   private String Admin_Pw;
   
   public AdminDTO() { }
   
   public AdminDTO(int admin_Seq, String admin_Proj, String admin_Pw) {
      super();
      Admin_Seq = admin_Seq;
      Admin_Proj = admin_Proj;
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
   
   public String getAdmin_Pw() {
      return Admin_Pw;
   }
   public void setAdmin_Pw(String admin_Pw) {
      Admin_Proj = admin_Pw;
   }   
   
}