package com.ibm.cof.dto;

import java.util.Date;

public class BlockDTO {
	private Integer Block_Seq;
	private String Block_Nm;
    private String Block_Pn;
    private String Block_Em;
    private String Block_Site;
    
	public Integer getBlock_Seq() {
		return Block_Seq;
	}
	public void setBlock_Seq(Integer block_Seq) {
		Block_Seq = block_Seq;
	}
	public String getBlock_Nm() {
		return Block_Nm;
	}
	public void setBlock_Nm(String block_Nm) {
		Block_Nm = block_Nm;
	}
	public BlockDTO(String block_Nm, String block_Pn, String block_Em,
			String block_Site) {
		super();
		Block_Nm = block_Nm;
		Block_Pn = block_Pn;
		Block_Em = block_Em;
		Block_Site = block_Site;
	}
	public String getBlock_Pn() {
		return Block_Pn;
	}
	public BlockDTO(Integer block_Seq, String block_Nm, String block_Pn,
			String block_Em, String block_Site) {
		super();
		Block_Seq = block_Seq;
		Block_Nm = block_Nm;
		Block_Pn = block_Pn;
		Block_Em = block_Em;
		Block_Site = block_Site;
	}
	public void setBlock_Pn(String block_Pn) {
		Block_Pn = block_Pn;
	}
	public String getBlock_Em() {
		return Block_Em;
	}
	public void setBlock_Em(String block_Em) {
		Block_Em = block_Em;
	}
	public String getBlock_Site() {
		return Block_Site;
	}
	public void setBlock_Site(String block_Site) {
		Block_Site = block_Site;
	}
    
}
