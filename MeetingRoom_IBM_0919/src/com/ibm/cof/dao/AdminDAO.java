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
import com.ibm.cof.dto.ProjectDTO;

public class AdminDAO {

   @Resource(name = "jdbc/ibm")
   private DataSource dataSource = null;
   DBCon db = new DBCon();
   Connection conn = null;
   PreparedStatement pstmt = null;
   Statement st = null;
   ResultSet rs = null;

      
    public AdminDAO() {
       
    }
    
    /* 관리자 추가(MASTER 관리자 기능) */
    public void insert(AdminDTO adto) {
      String query = "insert into tb_admin(Admin_Proj, Admin_Pw)"
            + " values(?,?)";
      try {
         conn = db.connect();            
         pstmt = conn.prepareStatement(query);
         pstmt.setString(1, adto.getAdmin_Proj());
         pstmt.setString(2, adto.getAdmin_Pw());
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
    
    
    /* 관리자 삭제(MASTER관리자기능) */
    public void delete(int admin_seq) {
       Connection conn = null;
         PreparedStatement pstmt = null;
         DBCon db = new DBCon();

         try{
             conn = db.connect();
             String sql = "delete from tb_admin where admin_seq=?";
             pstmt = conn.prepareStatement(sql);
             pstmt.setInt(1, admin_seq);
             pstmt.executeUpdate();

         }catch(SQLException se){
             se.printStackTrace();   
         }catch(Exception e){
             e.printStackTrace();
         }finally{
             db.close(pstmt, conn);
       }
    }
    
    public boolean checkLogin(String id,String pw) {
        String query = "select * from tb_admin where ADMIN_PROJ=? and ADMIN_PW=?";
        boolean flag = false;
        String ididid= null;
        String pwpwpw= null;
        try {
           conn = db.connect();            
           pstmt = conn.prepareStatement(query);
           pstmt.setString(1, id);
           pstmt.setString(2, pw);
           rs = pstmt.executeQuery();
           
           if (rs.next())
	            flag = true;
	         else
	            flag = false;
           
                 
        } catch (Exception e) {
           e.printStackTrace();
        } finally {
           try {
              db.close(pstmt, conn);
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
        return flag;
     }
    
    
}