package com.ibm.cof.dto;

public class HistoryDTO {
	int Hst_Seq;
	String Hst_Rsv_Site;
	String Hst_Rsv_Confer_Nm;
	String Hst_Rsv_Date;
	String Hst_Rsv_Start_Time;
	String Hst_Rsv_End_Time;
	String Hst_Rsv_Title;
	String Hst_Rsv_Mem_Nm;
	String Hst_Rsv_Mem_Pn;
	String Hst_Rsv_Mem_Em;
	String Hst_Rsv_Del_Pw;
	String Hst_Reg_Date;
	String Hst_State;
	
	public HistoryDTO() { };
	
	public HistoryDTO(String date, String start_time, String end_time, String title, String site,
			String confer_nm, String name, String phone, String email, String del_pw, String state) {
		Hst_Rsv_Date=date;
		Hst_Rsv_Start_Time=start_time;
		Hst_Rsv_End_Time =end_time;
		Hst_Rsv_Title =title;
		Hst_Rsv_Site =site;
		Hst_Rsv_Confer_Nm =confer_nm;
		Hst_Rsv_Mem_Nm =name;
		Hst_Rsv_Mem_Pn =phone;
		Hst_Rsv_Mem_Em =email;
		Hst_Rsv_Del_Pw =del_pw;
		Hst_State = state;
	}

	public HistoryDTO(String date, String start_time, String end_time, String name, String phone, 
			String confer_nm, String title, String state, String hst_date) {
		Hst_Rsv_Date=date;
		Hst_Rsv_Start_Time=start_time;
		Hst_Rsv_End_Time =end_time;
		Hst_Rsv_Title =title;
		Hst_Rsv_Confer_Nm =confer_nm;
		Hst_Rsv_Mem_Nm =name;
		Hst_Rsv_Mem_Pn =phone;
		Hst_State = state;
		Hst_Reg_Date = hst_date;
	}
	
	public int getHst_Seq() {
		return Hst_Seq;
	}

	public void setHst_Seq(int hst_Seq) {
		Hst_Seq = hst_Seq;
	}

	public String getHst_Rsv_Site() {
		return Hst_Rsv_Site;
	}

	public void setHst_Rsv_Site(String hst_Rsv_Site) {
		Hst_Rsv_Site = hst_Rsv_Site;
	}

	public String getHst_Rsv_Confer_Nm() {
		return Hst_Rsv_Confer_Nm;
	}

	public void setHst_Rsv_Confer_Nm(String hst_Rsv_Confer_Nm) {
		Hst_Rsv_Confer_Nm = hst_Rsv_Confer_Nm;
	}

	public String getHst_Rsv_Date() {
		return Hst_Rsv_Date;
	}

	public void setHst_Rsv_Date(String hst_Rsv_Date) {
		Hst_Rsv_Date = hst_Rsv_Date;
	}

	public String getHst_Rsv_Start_Time() {
		return Hst_Rsv_Start_Time;
	}

	public void setHst_Rsv_Start_Time(String hst_Rsv_Start_Time) {
		Hst_Rsv_Start_Time = hst_Rsv_Start_Time;
	}

	public String getHst_Rsv_End_Time() {
		return Hst_Rsv_End_Time;
	}

	public void setHst_Rsv_End_Time(String hst_Rsv_End_Time) {
		Hst_Rsv_End_Time = hst_Rsv_End_Time;
	}

	public String getHst_Rsv_Title() {
		return Hst_Rsv_Title;
	}

	public void setHst_Rsv_Title(String hst_Rsv_Title) {
		Hst_Rsv_Title = hst_Rsv_Title;
	}

	public String getHst_Rsv_Mem_Nm() {
		return Hst_Rsv_Mem_Nm;
	}

	public void setHst_Rsv_Mem_Nm(String hst_Rsv_Mem_Nm) {
		Hst_Rsv_Mem_Nm = hst_Rsv_Mem_Nm;
	}

	public String getHst_Rsv_Mem_Pn() {
		return Hst_Rsv_Mem_Pn;
	}

	public void setHst_Rsv_Mem_Pn(String hst_Rsv_Mem_Pn) {
		Hst_Rsv_Mem_Pn = hst_Rsv_Mem_Pn;
	}

	public String getHst_Rsv_Mem_Em() {
		return Hst_Rsv_Mem_Em;
	}

	public void setHst_Rsv_Mem_Em(String hst_Rsv_Mem_Em) {
		Hst_Rsv_Mem_Em = hst_Rsv_Mem_Em;
	}

	public String getHst_Rsv_Del_Pw() {
		return Hst_Rsv_Del_Pw;
	}

	public void setHst_Rsv_Del_Pw(String hst_Rsv_Del_Pw) {
		Hst_Rsv_Del_Pw = hst_Rsv_Del_Pw;
	}

	public String getHst_Date() {
		return Hst_Reg_Date;
	}

	public void setHst_Date(String hst_Date) {
		Hst_Reg_Date = hst_Date;
	}

	public String getHst_State() {
		return Hst_State;
	}

	public void setHst_State(String hst_State) {
		Hst_State = hst_State;
	}
	
	
}
