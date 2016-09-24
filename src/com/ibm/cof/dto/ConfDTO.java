package com.ibm.cof.dto;

public class ConfDTO {
	private int Confrn_Seq;
	private String Confrn_Nm;
	private String Confrn_Site;
	private int Confrn_Order;
	
	public ConfDTO(int confrn_Seq, String confrn_Nm, String confrn_Site,
			int confrn_Order) {
		super();
		Confrn_Seq = confrn_Seq;
		Confrn_Nm = confrn_Nm;
		Confrn_Site = confrn_Site;
		Confrn_Order = confrn_Order;
	}
	
	public int getConfrn_Seq() {
		return Confrn_Seq;
	}
	public void setConfrn_Seq(int confrn_Seq) {
		Confrn_Seq = confrn_Seq;
	}
	public String getConfrn_Nm() {
		return Confrn_Nm;
	}
	public void setConfrn_Nm(String confrn_Nm) {
		Confrn_Nm = confrn_Nm;
	}
	public String getConfrn_Site() {
		return Confrn_Site;
	}
	public void setConfrn_Site(String confrn_Site) {
		Confrn_Site = confrn_Site;
	}
	public int getConfrn_Order() {
		return Confrn_Order;
	}
	public void setConfrn_Order(int confrn_Order) {
		Confrn_Order = confrn_Order;
	}
	
}
