package com.ibm.cof.dto;

public class ConferDTO {
	private int confrn_seq;
	   private String confrn_nm;
	   private String confrn_site;
	   private int confrn_num;
	   private String confrn_stat;
	   
	   public ConferDTO(){ }
	   
	   public ConferDTO(int confrn_seq, String confrn_nm, String confrn_site, int confrn_num, String confrn_stat){   
	      this.confrn_seq = confrn_seq; 
	      this.confrn_nm = confrn_nm;
	      this.confrn_site = confrn_site;
	      this.confrn_num = confrn_num;
	      this.confrn_stat = confrn_stat;
	   }
	   
	   public int getConfrn_seq() {
	      return confrn_seq;
	   }

	   public void setConfrn_seq(int confrn_seq) {
	      this.confrn_seq = confrn_seq;
	   }

	   public String getConfrn_nm() {
	      return confrn_nm;
	   }

	   public void setConfrn_nm(String confrn_nm) {
	      this.confrn_nm = confrn_nm;
	   }

	   public String getConfrn_site() {
	      return confrn_site;
	   }

	   public void setConfrn_site(String confrn_site) {
	      this.confrn_site = confrn_site;
	   }

	   public int getConfrn_num() {
	      return confrn_num;
	   }

	   public void setConfrn_num(int confrn_num) {
	      this.confrn_num = confrn_num;
	   }

	   public String getConfrn_stat() {
	      return confrn_stat;
	   }

	   public void setConfrn_stat(String confrn_stat) {
	      this.confrn_stat = confrn_stat;
	   }
	   
}
