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
	@Resource(name = "jdbc/ibm")
	DBCon db = new DBCon();
	Connection conn = null;
	PreparedStatement pstmt = null;
	Statement st = null;
	ResultSet rs = null;

	public RsvDAO() {

	}

	/* 사용자가 자신이 원하는 회의실을 예약할 때 사용자가 입력한 모든 정보가 입력된다. */
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

	/* 회의실 이름,예약날짜,예약시작시간,예약종료시간,예약목적을 수정할 수 있다. (추가로 수정될 사항이 있으면 추가하세요..) */
	public void update(RsvDTO rdto) {
		String query = "update tb_reservation set rsv_confer_nm=?, rsv_date=?, "
				+ "rsv_start_time=?, rsv_end_time=?, rsv_title=?,"
				+ "rsv_mem_nm=?, rsv_mem_pn=?, rsv_mem_em=?, where rsv_seq=? ";

		try {			
			conn = db.connect();
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, rdto.getRsv_Confer_Nm());
			pstmt.setString(2, rdto.getRsv_Date());
			pstmt.setString(3, rdto.getRsv_Start_Time());
			pstmt.setString(4, rdto.getRsv_End_Time());
			pstmt.setString(5, rdto.getRsv_Title());
			pstmt.setString(6, rdto.getRsv_Mem_Nm());
			pstmt.setString(7, rdto.getRsv_Mem_Pn());
			pstmt.setString(8, rdto.getRsv_Mem_Em());
			pstmt.setString(9, rdto.getRsv_Seq());

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


	/* 사용자가 예약한 것을 삭제할 수 있다. */
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

	/*
	 * 핸드폰번호(사용자를 구별할 수 있는 키)를 이용하여 사용자의 정보를 모두 가져와서 View에 뿌려준다. 하지만 핸드폰번호가 아니어도
	 * 사용자를 구별할 수 있는 것이라면 어떤것도 무방(수정가능)
	 */
	public ArrayList<RsvDTO> select(String phone) {
		ArrayList<RsvDTO> dtos = new ArrayList<RsvDTO>();
		RsvDTO dto = null;
		String site, confer_nm, date, start, end, title;
		String query = "select rsv_site,rsv_confer_nm,rsv_date,rsv_start_time,rsv_end_time,rsv_title "
				+ "from tb_reservation " + "where rsv_mem_pn = ?";

		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, phone);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				site = rs.getString("rsv_site");
				confer_nm = rs.getString("rsv_confer_nm");
				date = rs.getString("rsv_date");
				start = rs.getString("rsv_start_time");
				end = rs.getString("rsv_end_time");
				title = rs.getString("rsv_title");

				/* 정보에 맞게 생성자 구현 요망 */
				// dto = new RsvDTO(date,start,end,title,site,confer_nm);
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

	/*
	 * 회의실 삭제시 사용자는 자신이 예약시 등록한 비밀번호를 입력하게된다. 그렇기 때문에 삭제시 자신이 설정한 비밀번호와 일치하여야
	 * 삭제가 가능
	 */
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

	/* 현재날짜 이후로 예약 현황을 가져온다 날짜,시간 오름차순 정렬 */
	public ArrayList<RsvDTO> selectAll() {
		ArrayList<RsvDTO> dtos = new ArrayList<RsvDTO>();
		RsvDTO dto = null;
		String title, site, confer_nm, date, start, end, name, phone;
		String query = "select rsv_title, rsv_site, rsv_confer_nm, rsv_date, rsv_start_time, rsv_end_time, "
				+ "rsv_mem_nm, rsv_mem_pn from tb_reservation "
				+ "where rsv_date >= DATE_FORMAT(NOW(),'%Y-%m-%d') order by rsv_date, rsv_start_time";

		try {
			conn = db.connect();
			st = conn.createStatement();
			rs = st.executeQuery(query);

			while (rs.next()) {
				title = rs.getString("rsv_title");
				site = rs.getString("rsv_site");
				confer_nm = rs.getString("rsv_confer_nm");
				date = rs.getString("rsv_date");
				start = rs.getString("rsv_start_time");
				end = rs.getString("rsv_end_time");
				name = rs.getString("rsv_mem_nm");
				phone = rs.getString("rsv_mem_pn");

				dto = new RsvDTO(title, site, confer_nm, date, start, end,
						name, phone);
				dtos.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				db.close(rs, st, conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return dtos;
	}

	/* 조건(프로젝트, 회의제목, 예약자)에 따라 현재날짜 이후로 예약 현황을 가져온다 */
	public ArrayList<RsvDTO> selectByCondition(String option, String context) {
		ArrayList<RsvDTO> dtos = new ArrayList<RsvDTO>();
		RsvDTO dto = null;
		String title, site, confer_nm, date, start, end, name, phone;
		String query = "select rsv_title, rsv_site, rsv_confer_nm, rsv_date, rsv_start_time, rsv_end_time, "
				+ "rsv_mem_nm, rsv_mem_pn from tb_reservation "
				+ "where " + option + " = ? and "
				+ "rsv_date >= DATE_FORMAT(NOW(),'%Y-%m-%d') order by rsv_date, rsv_start_time";

		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, context);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				title = rs.getString("rsv_title");
				site = rs.getString("rsv_site");
				confer_nm = rs.getString("rsv_confer_nm");
				date = rs.getString("rsv_date");
				start = rs.getString("rsv_start_time");
				end = rs.getString("rsv_end_time");
				name = rs.getString("rsv_mem_nm");
				phone = rs.getString("rsv_mem_pn");

				dto = new RsvDTO(title, site, confer_nm, date, start, end,
						name, phone);
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

	public JSONArray selectBySiteDate(String site, String date) {
		JSONArray jarray = new JSONArray();
		String query = "select * from tb_reservation where rsv_site=? and rsv_date=? "
				+ "order by rsv_confer_nm, rsv_start_time";

		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, site);
			pstmt.setString(2, date);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				JSONObject json = new JSONObject();
				json.put("site", site);
				json.put("date", date);

				json.put("seq", rs.getString("rsv_seq"));
				json.put("title", rs.getString("rsv_title"));
				json.put("confer_nm", rs.getString("rsv_confer_nm"));
				json.put("start", rs.getString("rsv_start_time"));
				json.put("end", rs.getString("rsv_end_time"));
				json.put("name", rs.getString("rsv_mem_nm"));
				json.put("phone", rs.getString("rsv_mem_pn"));
				json.put("email", rs.getString("rsv_mem_em"));
				json.put("pwd", rs.getString("rsv_del_pw"));

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

	public JSONArray selectBySeq(String seq) {
		String query = "select * from tb_reservation where rsv_seq=?";
		JSONArray jarray = new JSONArray();

		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, seq);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				JSONObject json = new JSONObject();
				json.put("name", rs.getString("rsv_mem_nm"));
				json.put("phone", rs.getString("rsv_mem_pn"));
				json.put("email", rs.getString("rsv_mem_em"));
				json.put("date", rs.getString("rsv_date"));
				json.put("start_time", rs.getString("rsv_start_time"));
				json.put("end_time", rs.getString("rsv_end_time"));
				json.put("confer_nm", rs.getString("rsv_confer_nm"));
				json.put("title", rs.getString("rsv_title"));
				json.put("password", rs.getString("rsv_del_pw"));
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

	public boolean CheckRsvPssible(String confer_nm, String start_time, String end_time, String site, String date) {
		Boolean possible = true;
		String query = "select rsv_start_time,rsv_end_time from tb_reservation where rsv_site=? and rsv_date=? and rsv_confer_nm=?"
				+ "order by rsv_confer_nm, rsv_start_time";
		try {
			conn = db.connect();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, site);
			pstmt.setString(2, date);
			pstmt.setString(3, confer_nm);
			rs = pstmt.executeQuery();

			int mystart = Integer.parseInt(start_time);
			int myend = Integer.parseInt(end_time);
			int yourstart, yourend;
			
			while (rs.next() == true) {
				yourstart = Integer.parseInt(rs.getString(1));
				yourend = Integer.parseInt(rs.getString(2));

				//my time이 your time을 포함하는 경우
				if(mystart <= yourstart && myend >= yourend)
					possible = false;
				//your time이 my time을 포함
				else if(mystart >= yourstart && myend <= yourend)
					possible = false;
				//my time 시작이 your time 사이에 겹치는 경우
				else if(mystart >= yourstart && mystart < yourend)
					possible = false;
				//my time 끝이 your time 사이에 겹치는 경우
				else if(myend <= yourend && myend > yourstart)
					possible = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				db.close(rs, pstmt, conn);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return possible;
	}

}
