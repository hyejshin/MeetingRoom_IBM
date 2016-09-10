package com.ibm.cof.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.annotation.Resource;

import com.ibm.cof.dto.RsvDTO;

public class RsvDAO {
	@Resource(name = "jdbc/ibm")
	DBCon db = new DBCon();
	Connection conn = null;
	PreparedStatement pstmt = null;
	Statement st = null;
	ResultSet rs = null;

	public RsvDAO() { }

	public void insert(RsvDTO rdto) {
		String query = "insert into tb_reservation(rsv_site, rsv_confer_nm, rsv_date, rsv_start_time,"
				+ " rsv_end_time, rsv_title, rsv_mem_nm, rsv_mem_pn, rsv_mem_em, rsv_reg_date)"
				+ " values(?,?,?,?,?,?,?,?,?,now())";
		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, rdto.getRsv_site());
			pstmt.setString(2, rdto.getRsv_confer_nm());
			pstmt.setString(3, rdto.getRsv_date());
			pstmt.setString(4, rdto.getRsv_start_time());
			pstmt.setString(5, rdto.getRsv_end_time());
			pstmt.setString(6, rdto.getRsv_title());
			pstmt.setString(7, rdto.getRsv_mem_nm());
			pstmt.setString(8, rdto.getRsv_mem_pn());
			pstmt.setString(9, rdto.getRsv_mem_em());

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

	public void update(RsvDTO rdto) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		DBCon db = new DBCon();

		try {
			String query = "update tb_reservation set rsv_site=?, rsv_confer_nm=?, rsv_date=?, rsv_start_time=?,"
					+ " rsv_end_time=?, rsv_title=?, rsv_mem_nm=?, rsv_mem_pn=?, rsv_mem_em=? where rsv_seq=?";
			conn = db.connect();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, rdto.getRsv_site());
			pstmt.setString(2, rdto.getRsv_confer_nm());
			pstmt.setString(3, rdto.getRsv_date());
			pstmt.setString(4, rdto.getRsv_start_time());
			pstmt.setString(5, rdto.getRsv_end_time());
			pstmt.setString(6, rdto.getRsv_title());
			pstmt.setString(7, rdto.getRsv_mem_nm());
			pstmt.setString(8, rdto.getRsv_mem_pn());
			pstmt.setString(9, rdto.getRsv_mem_em());
			pstmt.setInt(10, rdto.getRsv_seq());

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

	public void delete(int rsv_seq) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		DBCon db = new DBCon();

		try {
			conn = db.connect();
			String sql = "delete from tb_reservation where rsv_seq=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, rsv_seq);
			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			db.close(pstmt, conn);
		}
	}


	public ArrayList<RsvDTO> selectList(String site, String date, String confer_nm) 	{
		ArrayList<RsvDTO> dtos = new ArrayList<RsvDTO>();
		String query = "select * from tb_reservation where rsv_site=? and rsv_date=? and rsv_confer_nm=?";
		query += " order by rsv_start_time";
		RsvDTO dto =null;
		String start_time, end_time, title, name, phone, email;

		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, site);
			pstmt.setString(2, date);
			pstmt.setString(3, confer_nm);
			rs = pstmt.executeQuery();

			while(rs.next()) {
				start_time = rs.getString("rsv_start_time");
				end_time = rs.getString("rsv_end_time");
				title = rs.getString("rsv_title");
				name = rs.getString("rsv_mem_nm");
				phone = rs.getString("rsv_mem_pn");
				email = rs.getString("rsv_mem_em");

				System.out.println(title);
				System.out.println(name);

				dto = new RsvDTO(site, confer_nm, date, start_time, end_time, title, name, phone, email);
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
}
