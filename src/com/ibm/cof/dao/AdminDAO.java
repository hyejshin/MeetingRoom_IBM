package com.ibm.cof.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ibm.cof.dto.AdminDTO;
import com.ibm.cof.dto.MemberDTO;
import com.ibm.cof.dto.ProjectDTO;


public class AdminDAO {

	@Resource(name = "jdbc/ibm")
	DBCon db = new DBCon();
	Connection conn = null;
	PreparedStatement pstmt = null;
	Statement st = null;
	ResultSet rs = null;


	public AdminDAO() {

	}

	/* 관리자 추가(MASTER 관리자 기능) */
	public void insert(AdminDTO adto) {
		String query = "insert into tb_admin(admin_proj, admin_id, admin_pw)"
				+ " values(?,?,?)";
		try {
			conn = db.connect();            
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, adto.getAdmin_Proj());
			pstmt.setString(2, adto.getAdmin_Id());
			pstmt.setString(3, adto.getAdmin_Pw());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				db.close(pstmt, conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	/* 관리자 삭제(MASTER관리자기능) */
	public void delete(int admin_seq) {
		String sql = "delete from tb_admin where admin_seq=?";

		try{
			conn = db.connect();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, admin_seq);
			pstmt.executeUpdate();

		}catch(SQLException e){
			e.printStackTrace();   
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			db.close(pstmt, conn);
		}
	}


	public int checkLogin(String id,String pw) {
		String query = "select * from tb_admin where admin_id=?";
		int result = -1;

		try {
			conn = db.connect();            
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			//아이디 일치 : rs.next() = true
			if(rs.next()){ 
				if(rs.getString("admin_pw") != null && rs.getString("admin_pw").equals(pw)){
					result = 1; //비밀번호 일치
				}else{
					result = 0; //비밀번호 불일치
				}
			}else{
				result = -1; //아이디 불일치
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				db.close(pstmt, conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	public String selectProjectName(String id)
	{
		String query = "select * from tb_admin where admin_id=?";
		String project = "";

		try {
			conn = db.connect();            
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				project = rs.getString("admin_proj");
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
		return project;
	}

	public ArrayList<AdminDTO> selectAll(){
		ArrayList<AdminDTO> dtos = new ArrayList<AdminDTO>();
		String query = "select * from tb_admin where admin_proj != ?";
		
		AdminDTO dto = null;
		String project, id, pw;
		int seq;
		
		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "master");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				seq = rs.getInt("admin_seq");
				project = rs.getString("admin_proj");
				id = rs.getString("admin_id");
				pw = rs.getString("admin_pw");
				
				dto = new AdminDTO(seq, project, id, pw);
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
	
	public String selectPassword(String project){
		String query = "select admin_pw from tb_admin where admin_proj=?";
		String password = "";

		try {
			conn = db.connect();            
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, project);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				password = rs.getString("admin_pw");
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
		return password;
	}
	
	public void changePassword(String project, String newPassword)
	{
		String query = "update tb_admin set admin_pw=? where admin_proj=?";

		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, newPassword);
			pstmt.setString(2, project);
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
	
	public ArrayList<String> Projlist() {
		ArrayList<String> projList = new ArrayList<String>(); 
		String query = "select * from tb_admin where admin_proj != ?";
		String project;
		
		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "master");
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				project = rs.getString("admin_proj");
				projList.add(project);
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt, conn);
		}
		
		return projList;
	}
	
	public JSONArray projList()	{
		String query = "select * from tb_admin where admin_proj != 'master'";
		JSONArray jarray = new JSONArray();
				
		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
						
			while(rs.next()) {				
				JSONObject json = new JSONObject();
				json.put("project", rs.getString("admin_proj"));
				jarray.add(json);
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
		return jarray;
	}
}