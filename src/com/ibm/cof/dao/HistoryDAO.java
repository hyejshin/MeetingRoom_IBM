package com.ibm.cof.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.SQLException;
import javax.annotation.Resource;

import java.util.ArrayList;
import com.ibm.cof.dto.HistoryDTO;


public class HistoryDAO {
	@Resource(name = "jdbc/ibm")
	DBCon db = new DBCon();
	Connection conn = null;
	PreparedStatement pstmt = null;
	Statement st = null;
	ResultSet rs = null;

	public HistoryDAO() {

	}
	
	public void insert(HistoryDTO hdto) {
		String query = "insert into tb_history(hst_rsv_site, hst_rsv_confer_nm, hst_rsv_date, "
				+ "hst_rsv_start_time, hst_rsv_end_time, hst_rsv_title, "
				+ "hst_rsv_mem_nm, hst_rsv_mem_pn, hst_rsv_mem_em, hst_rsv_del_pw, hst_reg_date, hst_state)"
				+ " values(?,?,?,?,?,?,?,?,?,?,now(),?)";
		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, hdto.getHst_Rsv_Site());
			pstmt.setString(2, hdto.getHst_Rsv_Confer_Nm());
			pstmt.setString(3, hdto.getHst_Rsv_Date());
			pstmt.setString(4, hdto.getHst_Rsv_Start_Time());
			pstmt.setString(5, hdto.getHst_Rsv_End_Time());
			pstmt.setString(6, hdto.getHst_Rsv_Title());
			pstmt.setString(7, hdto.getHst_Rsv_Mem_Nm());
			pstmt.setString(8, hdto.getHst_Rsv_Mem_Pn());
			pstmt.setString(9, hdto.getHst_Rsv_Mem_Em());
			pstmt.setString(10, hdto.getHst_Rsv_Del_Pw());
			pstmt.setString(11, hdto.getHst_State());

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
	
	public ArrayList<HistoryDTO> select(String site, String startDate, String endDate) {
		ArrayList<HistoryDTO> dtos = new ArrayList<HistoryDTO>();
		HistoryDTO dto = null;
		
		String rsv_date, start_time, end_time, name, phone, conference, title, state, hst_date; 
		String query = "select * from tb_history where hst_rsv_site = ? and hst_reg_date >= ? and hst_reg_date <= ? order by hst_seq desc";

		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, site);
			pstmt.setString(2, startDate);
			pstmt.setString(3, endDate);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				rsv_date = rs.getString("hst_rsv_date");
				start_time = rs.getString("hst_rsv_start_time");
				end_time = rs.getString("hst_rsv_end_time");
				name = rs.getString("hst_rsv_mem_nm");
				phone = rs.getString("hst_rsv_mem_pn");
				conference = rs.getString("hst_rsv_confer_nm");
				title = rs.getString("hst_rsv_title");
				state = rs.getString("hst_state");
				hst_date = rs.getString("hst_reg_date");

				dto = new HistoryDTO(rsv_date, start_time, end_time, name, phone, conference, title, state, hst_date);
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
