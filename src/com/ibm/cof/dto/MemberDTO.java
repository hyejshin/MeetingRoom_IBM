package com.ibm.cof.dto;

import java.util.Date;

public class MemberDTO {
	private Integer Mem_Seq;
	private String Mem_Nm;
    private String Mem_Pn;
    private String Mem_Em;
    private String Mem_Site;
    private Date Mem_Reg_Date;
    
    public MemberDTO() { };
    
	public MemberDTO(String name, String phone, String email, String site) {
		Mem_Nm = name;
		Mem_Pn = phone;
		Mem_Em = email;
		Mem_Site = site;
	}
	
	public MemberDTO(Integer seq, String name, String phone, String email, String site) {
		Mem_Seq = seq;
		Mem_Nm = name;
		Mem_Pn = phone;
		Mem_Em = email;
		Mem_Site = site;
	}

	
	public Integer getMem_Seq() {
		return Mem_Seq;
	}

	public void setMem_Seq(Integer mem_Seq) {
		Mem_Seq = mem_Seq;
	}

	public String getMem_Nm() {
		return Mem_Nm;
	}

	public void setMem_Nm(String mem_Nm) {
		Mem_Nm = mem_Nm;
	}

	public String getMem_Pn() {
		return Mem_Pn;
	}

	public void setMem_Pn(String mem_Pn) {
		Mem_Pn = mem_Pn;
	}

	public String getMem_Em() {
		return Mem_Em;
	}

	public void setMem_Em(String mem_Em) {
		Mem_Em = mem_Em;
	}

	public String getMem_Site() {
		return Mem_Site;
	}

	public void setMem_Site(String mem_Site) {
		Mem_Site = mem_Site;
	}

	public Date getMem_Reg_Date() {
		return Mem_Reg_Date;
	}

	public void setMem_Reg_Date(Date mem_Reg_Date) {
		Mem_Reg_Date = mem_Reg_Date;
	}
	
	
}