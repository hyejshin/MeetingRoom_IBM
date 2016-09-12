package com.ibm.cof.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

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

    /* 회의실 예약과 동시에 회원가입이 이루어짐 */
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
	
	/* 회원의 모든 정보를 가져올 수 있음 */
	public ArrayList<MemberDTO> selectList()
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
	
	/* 추후에 관리자 기능이 추가될 경우 회원수정이 있을 수도 있다고 생각하여 넣음.. 안써도 무방 */
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

	/* 회원이 퇴직 또는 어떠한 사정에 의해 더이상 사이트의 소속이 아니게 될 경우 관리자는 회원을 삭제할 수 있다. */
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
