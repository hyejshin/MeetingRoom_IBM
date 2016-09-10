package com.ibm.cof.dto;

import java.util.Date;


public class MemberDTO {
    private String mem_nm;
    private String mem_pn;
    private String mem_em;
    private String mem_site;
    private String mem_role;
    private Date mem_reg_date;
    
    public MemberDTO() { }
    
	public MemberDTO(String mem_nm, String mem_pn, String mem_em, String mem_site) {
		this.mem_nm = mem_nm;
		this.mem_pn = mem_pn;
		this.mem_em = mem_em;
		this.mem_site = mem_site;
	}

	public String getMem_nm() {
		return mem_nm;
	}

	public void setMem_nm(String mem_nm) {
		this.mem_nm = mem_nm;
	}

	public String getMem_pn() {
		return mem_pn;
	}

	public void setMem_pn(String mem_pn) {
		this.mem_pn = mem_pn;
	}

	public String getMem_em() {
		return mem_em;
	}

	public void setMem_em(String mem_em) {
		this.mem_em = mem_em;
	}

	public String getMem_site() {
		return mem_site;
	}

	public void setMem_site(String mem_site) {
		this.mem_site = mem_site;
	}

	public String getMem_role() {
		return mem_role;
	}

	public void setMem_role(String mem_role) {
		this.mem_role = mem_role;
	}

	public Date getMem_reg_date() {
		return mem_reg_date;
	}

	public void setMem_reg_date(Date mem_reg_date) {
		this.mem_reg_date = mem_reg_date;
	}
	
	
	

}