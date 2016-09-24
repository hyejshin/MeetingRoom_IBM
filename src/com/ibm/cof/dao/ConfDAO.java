package com.ibm.cof.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ibm.cof.dto.ConfDTO;

public class ConfDAO {
	@Resource(name = "jdbc/ibm")
	private DataSource dataSource = null;
	DBCon db = new DBCon();
	Connection conn = null;
	PreparedStatement pstmt = null;
	Statement st = null;
	ResultSet rs = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConfDAO() {

	}

	/* 관리자는 회의실의 이름,어느 프로젝트 소속,상태등을 추가할 수 있다. */
	public void insert(String name, String site, int order) {
		String query = "insert into tb_conference(confrn_nm,confrn_site,confrn_order)"
				+ " values(?,?,?)";
		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setString(2, site);
			pstmt.setInt(3, order);

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

	public int getMaxValue(String site) {
		String query = "select MAX(confrn_order) from tb_conference where confrn_site = ?";
		int order = 1;
		
		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, site);
			pstmt.executeUpdate();
			
			while(rs.next()) {
				order = rs.getInt("confrn_order");
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
		return order;
	}
	
	/* 관리자는 회의실의 상태를 수정할 수 있다. */
	public void updateState(int seq, String name, int order) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		DBCon db = new DBCon();

		try {
			String query = "update tb_conference set confrn_nm=? , confrn_order=? where confrn_seq=?";
			conn = db.connect();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, name);
			pstmt.setInt(2, order);
			pstmt.setInt(3, seq);

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

	/* 관리자는 회의실의 이름,어느 프로젝트 소속,상태등을 삭제할 수 있다. */
	public void delete(int confrn_seq) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		DBCon db = new DBCon();

		try {
			conn = db.connect();
			String sql = "delete from tb_conference where confrn_seq=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, confrn_seq);
			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt, conn);
		}
	}
	
	// 프로젝트별 회의실 목록을 가져온다
	public JSONArray selectListByName(String sitename)
	{
		String query = "select confrn_nm, confrn_order from tb_conference where confrn_site = ? order by confrn_order, confrn_nm"; 
		JSONArray jarray = new JSONArray();
		String conf_name;
		int conf_order;
		
		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, sitename);
			rs = pstmt.executeQuery();

			while(rs.next()) {

				JSONObject json = new JSONObject();

				conf_name =  rs.getString("confrn_nm");
				conf_order =  rs.getInt("confrn_order"); 
				json.put("confname", conf_name);
				json.put("order", conf_order);
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
	
	/* 현재 등록되어있는 회의실을 뿌려준다. */
	public ArrayList<ConfDTO> printRoom(String proj) {
		ArrayList<ConfDTO> cdtos = new ArrayList<ConfDTO>();
		ConfDTO cdto = null;
		String query = "select * from tb_conference where confrn_site=? order by confrn_order, confrn_nm";
		String confer_nm, confer_site;
		int confer_order;
		int seq=0;
		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, proj);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				seq = rs.getInt("confrn_seq");
				confer_nm = rs.getString("confrn_nm");
				confer_site = rs.getString("confrn_site");
				confer_order = rs.getInt("confrn_order");
				cdto = new ConfDTO(seq, confer_nm, confer_site, confer_order);
				cdtos.add(cdto);
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
		return cdtos;
	}
	
	/* seq에 해당하는 회의실정보를 가져와서 회의실 수정탭에 채워진다. */
	public JSONArray selectBySeq(int seq)
	{
		String query = "select * from tb_conference where confrn_seq=?";
		JSONArray jarray = new JSONArray();
				
		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();
						
			while(rs.next()) {
				
				JSONObject json = new JSONObject();
				json.put("seq", rs.getString("confrn_seq"));
				json.put("name", rs.getString("confrn_nm"));
				json.put("order", rs.getInt("confrn_order"));
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