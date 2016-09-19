package com.ibm.cof.dto;

public class RsvDTO {
	private String Rsv_Date;
	private String Rsv_Start_Time;
	private String Rsv_End_Time;
	private String Rsv_Title;
	private String Rsv_Reg_Date;
	private String Rsv_Site;
	private String Rsv_Confer_Nm;
	private String Rsv_Mem_Nm;
	private String Rsv_Mem_Pn;
	private String Rsv_Mem_Em;
	private String Rsv_Del_Pw;
	
	public RsvDTO() { };
	
	public RsvDTO(String title, String site, String confer_nm, String date, String start, String end, 
			String name, String phone){
		Rsv_Title = title;
		Rsv_Site = site;
		Rsv_Confer_Nm = confer_nm;
		Rsv_Date = date;
		Rsv_Start_Time = start;
		Rsv_End_Time = end;
		Rsv_Mem_Nm = name;
		Rsv_Mem_Pn = phone;
	}
	
	 public RsvDTO(String rsv_Date, String rsv_Start_Time, String rsv_End_Time,
	         String rsv_Title, String rsv_Site, String rsv_Confer_Nm,
	         String rsv_Mem_Nm, String rsv_Mem_Pn, String rsv_Mem_Em,
	         String rsv_Del_Pw) {
	      Rsv_Date = rsv_Date;
	      Rsv_Start_Time = rsv_Start_Time;
	      Rsv_End_Time = rsv_End_Time;
	      Rsv_Title = rsv_Title;
	      Rsv_Site = rsv_Site;
	      Rsv_Confer_Nm = rsv_Confer_Nm;
	      Rsv_Mem_Nm = rsv_Mem_Nm;
	      Rsv_Mem_Pn = rsv_Mem_Pn;
	      Rsv_Mem_Em = rsv_Mem_Em;
	      Rsv_Del_Pw = rsv_Del_Pw;
	   }

	
	public String getRsv_Date() {
		return Rsv_Date;
	}
	public void setRsv_Date(String rsv_Date) {
		Rsv_Date = rsv_Date;
	}
	public String getRsv_Start_Time() {
		return Rsv_Start_Time;
	}
	public void setRsv_Start_Time(String rsv_Start_Time) {
		Rsv_Start_Time = rsv_Start_Time;
	}
	public String getRsv_End_Time() {
		return Rsv_End_Time;
	}
	public void setRsv_End_Time(String rsv_End_Time) {
		Rsv_End_Time = rsv_End_Time;
	}
	public String getRsv_Title() {
		return Rsv_Title;
	}
	public void setRsv_Title(String rsv_Title) {
		Rsv_Title = rsv_Title;
	}
	public String getRsv_Reg_Date() {
		return Rsv_Reg_Date;
	}
	public void setRsv_Reg_Date(String rsv_Reg_Date) {
		Rsv_Reg_Date = rsv_Reg_Date;
	}
	public String getRsv_Site() {
		return Rsv_Site;
	}
	public void setRsv_Site(String rsv_Site) {
		Rsv_Site = rsv_Site;
	}
	public String getRsv_Confer_Nm() {
		return Rsv_Confer_Nm;
	}
	public void setRsv_Confer_Nm(String rsv_Confer_Nm) {
		Rsv_Confer_Nm = rsv_Confer_Nm;
	}
	public String getRsv_Mem_Nm() {
		return Rsv_Mem_Nm;
	}
	public void setRsv_Mem_Nm(String rsv_Mem_Nm) {
		Rsv_Mem_Nm = rsv_Mem_Nm;
	}
	public String getRsv_Mem_Pn() {
		return Rsv_Mem_Pn;
	}
	public void setRsv_Mem_Pn(String rsv_Mem_Pn) {
		Rsv_Mem_Pn = rsv_Mem_Pn;
	}
	public String getRsv_Mem_Em() {
		return Rsv_Mem_Em;
	}
	public void setRsv_Mem_Em(String rsv_Mem_Em) {
		Rsv_Mem_Em = rsv_Mem_Em;
	}
	public String getRsv_Del_Pw() {
		return Rsv_Del_Pw;
	}
	public void setRsv_Del_Pw(String rsv_Del_Pw) {
		Rsv_Del_Pw = rsv_Del_Pw;
	}
	
}