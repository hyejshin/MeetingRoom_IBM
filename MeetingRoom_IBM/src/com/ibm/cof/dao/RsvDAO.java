package com.ibm.cof.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ibm.cof.dto.RsvDTO;

public class RsvDAO {
	@Resource(name="jdbc/ibm")
	DBCon db = new DBCon();
    Connection conn = null;
    PreparedStatement pstmt = null;
    Statement st = null;
    ResultSet rs = null;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RsvDAO() {
        super();
        // TODO Auto-generated constructor stub
    }

    /* ����ڰ� �ڽ��� ���ϴ� ȸ�ǽ��� ������ �� ����ڰ� �Է��� ��� ������ �Էµȴ�. */ 
    public void insert(String date,String start_time,String end_time,
			String title,String site,String confer_nm,String name,String phone,String email,String del_pw) {
		String query = "insert into tb_reservation(rsv_site,rsv_confer_nm,rsv_date, "
				+ "rsv_start_time, rsv_end_time, rsv_title, "
				+ "rsv_mem_nm,rsv_mem_pn,rsv_mem_em,rsv_del_pw,rsv_reg_date)"
				+ " values(?,?,?,?,?,?,?,?,?,?,now())";
		try {
			conn = db.connect();				
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, site);
			pstmt.setString(2, confer_nm);
			pstmt.setString(3, date);
			pstmt.setString(4, start_time);
			pstmt.setString(5, end_time);
			pstmt.setString(6, title);
			pstmt.setString(7, name);
			pstmt.setString(8, phone);
			pstmt.setString(9, email);
			pstmt.setString(10, del_pw);
						
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
    
    /* ����� ���ÿ� ���������� �����̷°���(History)���̺� ���� */
    public void insertHist(String date,String start_time,String end_time,
			String title,String site,String confer_nm,String name,String phone,String email,String del_pw) {
		String query = "insert into tb_history(hst_rsv_site,hst_rsv_confer_nm,hst_rsv_date, "
				+ "hst_rsv_start_time, hst_rsv_end_time, hst_rsv_title, "
				+ "hst_rsv_mem_nm,hst_rsv_mem_pn,hst_rsv_mem_em,hst_rsv_del_pw,hst_rsv_reg_date)"
				+ " values(?,?,?,?,?,?,?,?,?,?,now())";
		try {
			conn = db.connect();				
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, site);
			pstmt.setString(2, confer_nm);
			pstmt.setString(3, date);
			pstmt.setString(4, start_time);
			pstmt.setString(5, end_time);
			pstmt.setString(6, title);
			pstmt.setString(7, name);
			pstmt.setString(8, phone);
			pstmt.setString(9, email);
			pstmt.setString(10, del_pw);
						
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

    /* ȸ�ǽ� �̸�,���೯¥,������۽ð�,��������ð�,��������� ������ �� �ִ�. (�߰��� ������ ������ ������ �߰��ϼ���..) */
    public void update(RsvDTO rdto,int rsv_seq) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        DBCon db = new DBCon();
        
		try {
			String query = "update tb_reservation set rsv_confer_nm=?, rsv_date=?, "
				+ "rsv_start_time=?, rsv_end_time=?, rsv_title=? where rsv_seq=?";
			conn = db.connect();				
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, rdto.getRsv_Confer_Nm());
			pstmt.setString(2, rdto.getRsv_Date());
			pstmt.setString(3, rdto.getRsv_Start_Time());
			pstmt.setString(4, rdto.getRsv_End_Time());
			pstmt.setString(5, rdto.getRsv_Title());
			pstmt.setInt(6, rsv_seq);
			
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
	
    /* ����ڰ� ������ ���� ������ �� �ִ�. */
	public void delete(int rsv_seq) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        DBCon db = new DBCon();

        try{
            conn = db.connect();
            String sql = "delete from tb_reservation where rsv_seq=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, rsv_seq);
            pstmt.executeUpdate();

        }catch(SQLException se){
            se.printStackTrace();   
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            db.close(pstmt, conn);
		}
	}
	
	/* �ڵ�����ȣ(����ڸ� ������ �� �ִ� Ű)�� �̿��Ͽ� ������� ������ ��� �����ͼ� View�� �ѷ��ش�. ������ �ڵ�����ȣ�� �ƴϾ ����ڸ� ������ �� �ִ� ���̶�� ��͵� ����(��������) */
	public ArrayList<RsvDTO> select(String phone)
	{
		ArrayList<RsvDTO> dtos =new ArrayList<RsvDTO>();
		RsvDTO dto = null;
		String site,confer_nm,date,start,end,title;
		String query = "select rsv_site,rsv_confer_nm,rsv_date,rsv_start_time,rsv_end_time,rsv_title "
				+ "from tb_reservation "
				+ "where rsv_mem_pn = ?";
		
		try {
			conn = db.connect();			
			pstmt = conn.prepareStatement(query);
            pstmt.setString(1, phone);
            rs = pstmt.executeQuery(); 
			
			while(rs.next()) {
				site = rs.getString("rsv_site");
				confer_nm = rs.getString("rsv_confer_nm");
				date = rs.getString("rsv_date");
				start = rs.getString("rsv_start_time");
				end = rs.getString("rsv_end_time");
				title = rs.getString("rsv_title");
					
				/* ������ �°� ������ ���� ��� */
				//dto = new RsvDTO(date,start,end,title,site,confer_nm);
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
	
	/* ȸ�ǽ� ������ ����ڴ� �ڽ��� ����� ����� ��й�ȣ�� �Է��ϰԵȴ�. �׷��� ������ ������ �ڽ��� ������ ��й�ȣ�� ��ġ�Ͽ��� ������ ���� */
	public Boolean isCheckID(String rsv_del_pw) {
		
		Boolean bool = false;
		try {
			conn = db.connect();
			
			String query = "select * from tb_reservation where rsv_del_pw=?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, rsv_del_pw);
			rs = pstmt.executeQuery();

			if (rs.next())
				bool = true;
			else
				bool = false;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				db.close(rs, pstmt, conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

		return bool;
	}
	
	public JSONArray selectListByCondition(String confer_nm,String date)
	{
		//Map mlist = new Map<MemberDTO>();
		
		String query = "select * from tb_reservation where rsv_confer_nm=? and rsv_date=? ";
		System.out.println("ID1 : "+confer_nm+" ID2 : "+date);
		//JSONObject json = new JSONObject();
		JSONArray jarray = new JSONArray();
						
		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, confer_nm);
			pstmt.setString(2, date);
			rs = pstmt.executeQuery();
						
			while(rs.next()) {
				
				JSONObject json = new JSONObject();
				//json.put("room", rs.getString("rsv_confer_nm"));
				json.put("start_time", rs.getString("rsv_start_time"));
				json.put("end_time", rs.getString("rsv_end_time"));
				json.put("name", rs.getString("rsv_mem_nm"));
				json.put("title", rs.getString("rsv_title"));
				jarray.add(json);
				//System.out.println("pn : "+pn+"em : "+em+"site : "+site);
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
	




	
	
