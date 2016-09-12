package com.ibm.cof.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.sql.DataSource;

import com.ibm.cof.dto.ProjectDTO;

public class ProjectDAO {
	@Resource(name="jdbc/ibm")
	private DataSource dataSource =null;
	DBCon db = new DBCon();
    Connection conn = null;
    PreparedStatement pstmt = null;
    Statement st = null;
    ResultSet rs = null;
    
    public ProjectDAO() {
    	
    }
    
    /* 프로젝트 추가(관리자기능) */
    public void insert(ProjectDTO pdto) {
		String query = "insert into tb_projet(proj_nm)"
				+ " values(?)";
		try {
			conn = db.connect();				
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pdto.getProj_Nm());
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
		
    /* 프로젝트 삭제(관리자기능) */
	public void delete(int proj_seq) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        DBCon db = new DBCon();

        try{
            conn = db.connect();
            String sql = "delete from tb_project where proj_seq=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, proj_seq);
            pstmt.executeUpdate();

        }catch(SQLException se){
            se.printStackTrace();   
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            db.close(pstmt, conn);
		}
	}
}
