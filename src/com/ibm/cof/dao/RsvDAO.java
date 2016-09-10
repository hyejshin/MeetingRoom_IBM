package com.ibm.cof.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;

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
    public void insert(RsvDTO rdto) {
		String query = "insert into tb_reservation(rsv_site,rsv_confer_nm,rsv_date, "
				+ "rsv_start_time, rsv_end_time, rsv_title, "
				+ "rsv_mem_nm,rsv_mem_pn,rsv_mem_em,rsv_del_pw,rsv_reg_date)"
				+ " values(?,?,?,?,?,?,?,?,?,?,now())";
		try {
			conn = db.connect();				
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, rdto.getRsv_Site());
			pstmt.setString(2, rdto.getRsv_Confer_Nm());
			pstmt.setString(3, rdto.getRsv_Date());
			pstmt.setString(4, rdto.getRsv_Start_Time());
			pstmt.setString(5, rdto.getRsv_End_Time());
			pstmt.setString(6, rdto.getRsv_Title());
			pstmt.setString(7, rdto.getRsv_Mem_Nm());
			pstmt.setString(8, rdto.getRsv_Mem_Pn());
			pstmt.setString(9, rdto.getRsv_Mem_Em());
			pstmt.setString(10, rdto.getRsv_Del_Pw());
						
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
}
	
		


	
	
