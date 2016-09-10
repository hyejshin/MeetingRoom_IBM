package com.ibm.cof.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ibm.cof.dto.MemberDTO;

public class MemberDAO {
	private DataSource dataSource =null;
	DBCon db = new DBCon();
    Connection conn = null;
    PreparedStatement pstmt = null;
    Statement st = null;
    ResultSet rs = null;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDAO() {
    
    }

    /* ȸ�ǽ� ����� ���ÿ� ȸ�������� �̷���� */
	public void insert(MemberDTO mdto) {
	
		String query = "insert into tb_member(mem_nm,mem_pn,mem_em,mem_site,mem_reg_date)"
				+ " values(?,?,?,?,now())";
		try {
			conn = db.connect();				
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mdto.getMember_Name());
			pstmt.setString(2, mdto.getMember_Pn());
			pstmt.setString(3, mdto.getMember_Em());
			pstmt.setString(4, mdto.getMember_Site());
			
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
	
	/* ȸ���� ��� ������ ������ �� ���� */
	/*public ArrayList<MemberDTO> selectList()
	{
		ArrayList<MemberDTO> dtos = new ArrayList<MemberDTO>();
		String query = "select * from tb_member";
		MemberDTO dto =null;
		String nm,pn,em,site;
		
		try {
			conn = db.connect();
			st = conn.createStatement();
			rs = st.executeQuery(query);
			
			while(rs.next()) {
				nm = rs.getString("mem_nm");
				pn = rs.getString("mem_pn");
				em = rs.getString("mem_em");
				site = rs.getString("mem_site");
				
				dto = new MemberDTO(nm,pn,em,site);
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
	*/
	/* test - Ajax�� �̸��� �´� ������� tb_member���� ������ �� ���� */
	public JSONArray selectListByName(String name)
	{
		//Map mlist = new Map<MemberDTO>();
		
		String query = "select * from tb_member where mem_nm=?";
		//JSONObject json = new JSONObject();
		JSONArray jarray = new JSONArray();
		String pn,em,site;
				
		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			
			if(rs == null) {
				System.out.println("No Result");
			} else {
				System.out.println("Result Ok");
			}
			
			while(rs.next()) {
				
				JSONObject json = new JSONObject();
				json.put("phone", rs.getString("mem_pn"));
				json.put("email", rs.getString("mem_em"));
				json.put("site", rs.getString("mem_site"));
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
	
	/* ���Ŀ� ������ ����� �߰��� ��� ȸ�������� ���� ���� �ִٰ� �����Ͽ� ����.. �Ƚᵵ ���� */
	public void updateMemberState(MemberDTO mdto, int mem_seq) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		DBCon db = new DBCon();

		try {
			String query = "update tb_member set mem_nm=?,mem_pn=?,mem_em=?,mem_site=?"
					+ " where mem_seq=?";
			conn = db.connect();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mdto.getMember_Name());
			pstmt.setString(2, mdto.getMember_Pn());
			pstmt.setString(3, mdto.getMember_Em());
			pstmt.setString(4, mdto.getMember_Site());
			pstmt.setInt(5, mem_seq);

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

	/* ȸ���� ���� �Ǵ� ��� ������ ���� ���̻� ����Ʈ�� �Ҽ��� �ƴϰ� �� ��� �����ڴ� ȸ���� ������ �� �ִ�. */
	public void delete(int mem_seq) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		DBCon db = new DBCon();

		try {
			conn = db.connect();
			String sql = "delete from tb_member where mem_seq=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mem_seq);
			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt, conn);
		}
	}
}
