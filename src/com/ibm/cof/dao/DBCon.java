package com.ibm.cof.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBCon {
	
	public Connection connect(){
		Connection conn = null;
		
		/* 혜정
	      String url = "jdbc:mysql://75.126.23.246:3307/d19baf15a17a54d5b9ab2f1aacfdd976e";
	      String id = "uwq3hYSeeQti0";
	      String pw = "puc5ZwRkCvmSI";
	      */
	      
	      /* 은진
	      String url = "jdbc:mysql://75.126.23.246:3307/d3d08d061490e4199ab26a0b6060ec763";
	      String id = "uk29GZRyVE2fT";
	      String pw = "pWv7uXkGBeHzX";
	      */
	      
	      // 로컬
	      String url = "jdbc:mysql://localhost:3306/IBM";
	      String id = "bluemix";
	      String pw = "ibmbluemix123";
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, id, pw);
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return conn;
	}
	
	
	public void close(PreparedStatement pstmt, Connection conn){
		try{
			if(pstmt != null){
				pstmt.close();
			}
			if(conn != null){
				conn.close();
			}
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void close(ResultSet rs, Statement stmt, Connection conn){
		try{
			if(rs != null){
				rs.close();
			}
			if(stmt != null){
				stmt.close();
			}
			if(conn != null){
				conn.close();
			}
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void close(ResultSet rs, PreparedStatement pstmt, Connection conn){
		try{
			if(rs != null){
				rs.close();
			}
			if(pstmt != null){
				pstmt.close();
			}
			if(conn != null){
				conn.close();
			}
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}