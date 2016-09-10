package com.ibm.cof.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.sql.DataSource;

import com.ibm.cof.dto.MemberDTO;


public class MemberDAO {
	@Resource(name="jdbc/ibm")
	private DataSource dataSource =null;
	DBCon db = new DBCon();
    Connection conn = null;
    PreparedStatement pstmt = null;
    Statement st = null;
    ResultSet rs = null;
    
    public MemberDAO() {  }

    
	public void insert(MemberDTO mdto) {	
		String query = "insert into tb_member(mem_nm,mem_pn,mem_em,mem_site,mem_reg_date)"
				+ " values(?,?,?,?,now())";
		try {
			conn = db.connect();				
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mdto.getMem_nm());
			pstmt.setString(2, mdto.getMem_pn());
			pstmt.setString(3, mdto.getMem_em());
			pstmt.setString(4, mdto.getMem_site());
			
			pstmt.executeUpdate();
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				db.close(pstmt, conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
			
	}
	
	public ArrayList<MemberDTO> selectList() {
		ArrayList<MemberDTO> dtos = new ArrayList<MemberDTO>();
		MemberDTO dto =null;
		String name, phone, email, site;
		String query = "select * from tb_member";
		
		try {
			conn = db.connect();
			st = conn.createStatement();
			rs = st.executeQuery(query);
			
			while(rs.next()) {
				name = rs.getString("mem_nm");
				phone = rs.getString("mem_pn");
				email = rs.getString("mem_em");
				site = rs.getString("mem_site");
				
				dto = new MemberDTO(name, phone, email, site);
				dtos.add(dto);
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				db.close(rs, pstmt, conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	}
	
	public MemberDTO select(String phone) {
		MemberDTO dto =null;
		String name, email, site;
		String query = "select * from tb_member where mem_pn = ?";
		
		try {
			conn = db.connect();			
			pstmt = conn.prepareStatement(query);
            pstmt.setString(1, phone);
            rs = pstmt.executeQuery(); 
			
			if (rs.next()) {
				name = rs.getString("mem_nm");
				email = rs.getString("mem_em");
				site = rs.getString("mem_site");
				
				dto = new MemberDTO(name, phone, email, site);
			} else {
				dto = new MemberDTO("", "", "", "");
				return dto;
			}
					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				db.close(rs, pstmt, conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dto;
	}
}
