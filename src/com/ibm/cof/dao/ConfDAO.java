package com.ibm.cof.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

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
	public void insert(ConfDTO cdto) {
		String query = "insert into tb_conference(confrn_nm,confrn_site,confrn_stat)"
				+ " values(?,?,?)";
		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, cdto.getConfrn_Nm());
			pstmt.setString(2, cdto.getConfrn_Site());
			pstmt.setString(3, cdto.getConfrn_Stat());

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

	/* 관리자는 회의실의 상태를 수정할 수 있다. */
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
}