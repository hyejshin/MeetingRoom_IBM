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
    PreparedStatement pstmt,pstmt1 = null;
    Statement st = null;
    ResultSet rs,rs1 = null;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RsvDAO() {
        super();
        // TODO Auto-generated constructor stub
    }

    /* 사용자가 자신이 원하는 회의실을 예약할 때 사용자가 입력한 모든 정보가 입력된다. */ 
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
    
    /* 예약과 동시에 예약정보가 예약이력관리(History)테이블에 들어간다 */
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

    /* 회의실 이름,예약날짜,예약시작시간,예약종료시간,예약목적을 수정할 수 있다. (추가로 수정될 사항이 있으면 추가하세요..) */
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
	
    /* 사용자가 예약한 것을 삭제할 수 있다. */
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
	
	/* 핸드폰번호(사용자를 구별할 수 있는 키)를 이용하여 사용자의 정보를 모두 가져와서 View에 뿌려준다. 하지만 핸드폰번호가 아니어도 사용자를 구별할 수 있는 것이라면 어떤것도 무방(수정가능) */
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
					
				/* 정보에 맞게 생성자 구현 요망 */
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
	
	/* 회의실 삭제시 사용자는 자신이 예약시 등록한 비밀번호를 입력하게된다. 그렇기 때문에 삭제시 자신이 설정한 비밀번호와 일치하여야 삭제가 가능 */
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
	
	public JSONArray selectListByCondition(String site,String date)
	{
		//Map mlist = new Map<MemberDTO>();
		
		// 예약테이블에서 예약정보를 가져오는 Query 
		String query = "select rsv_start_time,rsv_end_time,"
				+ "rsv_confer_nm,"
				+ "(select count(confrn_nm) from tb_conference where confrn_site=?)" 
				+ "from tb_reservation "
				+ "where rsv_date=? ";
		
		// 회의실테이블에서 회의실 수를 가져오는 Query
		/*String query1 = "select count(confrn_nm) "
				+ "from tb_conference where confrn_site=?"; */
						
		//JSONObject json = new JSONObject();
		JSONArray jarray = new JSONArray(); // 예약정보를 저장하는 JSONArray
		//JSONArray jarray1 = new JSONArray(); // 회의실정보를 저장하는 JSONArray
						
		try {
			conn = db.connect();
			/* 예약정보를 JSONArray로 받도록 하는 커넥션 */
			pstmt = conn.prepareStatement(query); 
			pstmt.setString(1, site);
			pstmt.setString(2, date);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				JSONObject json = new JSONObject();
				//json.put("room", rs.getString("rsv_confer_nm"));
				json.put("start_time", rs.getString("rsv_start_time"));
				json.put("end_time", rs.getString("rsv_end_time"));
				json.put("confer_nm",rs.getString("rsv_confer_nm"));
				json.put("room_count", rs.getInt(4));	
			
				
				//json.put("name", rs.getString("rsv_mem_nm"));
				//json.put("title", rs.getString("rsv_title"));
				jarray.add(json);
				//System.out.println("pn : "+pn+"em : "+em+"site : "+site);
			}
			
			/* 회의실정보를 JSONArray로 받도록 하는 커넥션 */
			/*pstmt1 = conn.prepareStatement(query1); 
			pstmt1.setString(1, site);
			//pstmt1.setString(2, date);
			rs1 = pstmt1.executeQuery();*/
			
			/*while(rs1.next()) {
				JSONObject json1 = new JSONObject();
				//json.put("room", rs.getString("rsv_confer_nm"));
				json1.put("count", rs1.getInt("count(confer_nm)"));	
				//json.put("name", rs.getString("rsv_mem_nm"));
				//json.put("title", rs.getString("rsv_title"));
				jarray.add(json1);
				//System.out.println("pn : "+pn+"em : "+em+"site : "+site);
			}*/
							
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
	




	
	
