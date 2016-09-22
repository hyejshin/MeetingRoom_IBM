package com.ibm.cof.dto;

public class ProjectDTO {
	private int Proj_Seq;
	private String Proj_Nm;
	
	public ProjectDTO() { }
	
	public ProjectDTO(String proj_Nm) {
		Proj_Nm = proj_Nm;
	}
	
	public ProjectDTO(int seq, String proj_Nm) {
		Proj_Seq = seq;
		Proj_Nm = proj_Nm;
	}
	
	
	public int getProj_Seq() {
		return Proj_Seq;
	}
	public void setProj_Seq(int proj_Seq) {
		Proj_Seq = proj_Seq;
	}
	
	public String getProj_Nm() {
		return Proj_Nm;
	}
	public void setProj_Nm(String proj_Nm) {
		Proj_Nm = proj_Nm;
	}
}
