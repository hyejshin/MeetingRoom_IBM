package com.ibm.cof.dto;

import java.util.Date;

public class MemberDTO {
	private String Member_Name;
    private String Member_Pn;
    private String Member_Em;
    private String Member_Site;
    private Date Member_Reg_Date;
    
	public MemberDTO(String member_Name, String member_Pn, String member_Em,
			String member_Site) {
		super();
		Member_Name = member_Name;
		Member_Pn = member_Pn;
		Member_Em = member_Em;
		Member_Site = member_Site;
	}
	
	public String getMember_Name() {
		return Member_Name;
	}
	public void setMember_Name(String member_Name) {
		Member_Name = member_Name;
	}
	public String getMember_Pn() {
		return Member_Pn;
	}
	public void setMember_Pn(String member_Pn) {
		Member_Pn = member_Pn;
	}
	public String getMember_Em() {
		return Member_Em;
	}
	public void setMember_Em(String member_Em) {
		Member_Em = member_Em;
	}
	public String getMember_Site() {
		return Member_Site;
	}
	public void setMember_Site(String member_Site) {
		Member_Site = member_Site;
	}
	public Date getMember_Reg_Date() {
		return Member_Reg_Date;
	}
	public void setMember_Reg_Date(Date member_Reg_Date) {
		Member_Reg_Date = member_Reg_Date;
	}
}
