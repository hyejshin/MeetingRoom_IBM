package com.ibm.cof.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.annotation.Resource;

import com.ibm.cof.dto.ConferDTO;
import com.ibm.cof.dao.DBCon;

public class ConferDAO {

	@Resource(name = "jdbc/ibm")

	DBCon db = new DBCon();
	Connection conn = null;
	PreparedStatement pstmt = null;
	Statement st = null;
	ResultSet rs = null;

	public ConferDAO() { }


	public void insert(ConferDTO cdto) {
		String query = "insert into tb_conference(confrn_nm,confrn_site,confrn_num)"
				+ " values(?,?,?)";
		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cdto.getConfrn_nm());
			pstmt.setString(2, cdto.getConfrn_site());
			pstmt.setInt(3, cdto.getConfrn_num());

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

	public void updateState(String state, int confrn_seq) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		DBCon db = new DBCon();

		try {
			String query = "update tb_conference set confrn_stat=? where confrn_seq=?";
			conn = db.connect();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, state);
			pstmt.setInt(2, confrn_seq);

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

	// 사이트 이름에 따라서 회의실 목록을 가져오는 함수
	public ArrayList<ConferDTO> list(String sitename) {

		ArrayList<ConferDTO> conflist = new ArrayList<ConferDTO>();
		String query = "select CONFRN_SEQ, CONFRN_NM, CONFRN_SITE, CONFRN_NUM, CONFRN_STAT from TB_CONFERENCE "
				+ "where CONFRN_SITE = ?";
		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, sitename);
			rs = pstmt.executeQuery();

			System.out.println("query is :"+query);
			while (rs.next()) {

				int c_confrn_seq = rs.getInt("CONFRN_SEQ");
				String c_confrn_nm = rs.getString("CONFRN_NM");
				String c_confrn_site = rs.getString("CONFRN_SITE");
				int c_confrn_num = rs.getInt("CONFRN_NUM");
				String c_confrn_stat = rs.getString("CONFRN_STAT");

				ConferDTO dto = new ConferDTO(c_confrn_seq, c_confrn_nm,
						c_confrn_site, c_confrn_num, c_confrn_stat);
				conflist.add(dto);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				db.close(rs, pstmt, conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return conflist;
	}
}

