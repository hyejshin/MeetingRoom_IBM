package com.ibm.cof.dto;

public class RsvDTO {
	private int rsv_seq;
	private String rsv_site;
	private String rsv_confer_nm;
	private String rsv_date;
	private String rsv_start_time;
	private String rsv_end_time;
	private String rsv_title;
	private String rsv_mem_nm;
	private String rsv_mem_pn;
	private String rsv_mem_em;
	private String rsv_reg_date;
	
	public RsvDTO() { }
	
	public RsvDTO(String rsv_site, String rsv_confer_nm, String rsv_date, String rsv_start_time,
			String rsv_end_time, String rsv_title, String rsv_mem_nm, String rsv_mem_pn, String rsv_mem_em) {
		this.rsv_site = rsv_site;
		this.rsv_confer_nm = rsv_confer_nm;
		this.rsv_date = rsv_date;
		this.rsv_start_time = rsv_start_time;
		this.rsv_end_time = rsv_end_time;
		this.rsv_title = rsv_title;
		this.rsv_mem_nm = rsv_mem_nm;
		this.rsv_mem_pn = rsv_mem_pn;
		this.rsv_mem_em = rsv_mem_em;
	}

	public int getRsv_seq() {
		return rsv_seq;
	}

	public void setRsv_seq(int rsv_seq) {
		this.rsv_seq = rsv_seq;
	}

	public String getRsv_site() {
		return rsv_site;
	}

	public void setRsv_site(String rsv_site) {
		this.rsv_site = rsv_site;
	}

	public String getRsv_confer_nm() {
		return rsv_confer_nm;
	}

	public void setRsv_confer_nm(String rsv_confer_nm) {
		this.rsv_confer_nm = rsv_confer_nm;
	}

	public String getRsv_date() {
		return rsv_date;
	}

	public void setRsv_date(String rsv_date) {
		this.rsv_date = rsv_date;
	}

	public String getRsv_start_time() {
		return rsv_start_time;
	}

	public void setRsv_start_time(String rsv_start_time) {
		this.rsv_start_time = rsv_start_time;
	}

	public String getRsv_end_time() {
		return rsv_end_time;
	}

	public void setRsv_end_time(String rsv_end_time) {
		this.rsv_end_time = rsv_end_time;
	}

	public String getRsv_title() {
		return rsv_title;
	}

	public void setRsv_title(String rsv_title) {
		this.rsv_title = rsv_title;
	}

	public String getRsv_mem_nm() {
		return rsv_mem_nm;
	}

	public void setRsv_mem_nm(String rsv_mem_nm) {
		this.rsv_mem_nm = rsv_mem_nm;
	}

	public String getRsv_mem_pn() {
		return rsv_mem_pn;
	}

	public void setRsv_mem_pn(String rsv_mem_pn) {
		this.rsv_mem_pn = rsv_mem_pn;
	}
	
	public String getRsv_mem_em() {
		return rsv_mem_em;
	}

	public void setRsv_mem_em(String rsv_mem_em) {
		this.rsv_mem_em = rsv_mem_em;
	}

	public String getRsv_reg_date() {
		return rsv_reg_date;
	}

	public void setRsv_reg_date(String rsv_reg_date) {
		this.rsv_reg_date = rsv_reg_date;
	}
	
}