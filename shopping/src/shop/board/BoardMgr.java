package shop.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardMgr {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	
	//paging
	private int tot; //전체 레코드 수
	private int pList = 5; //페이지 당 출력 행 수
	private int pageSu; //전체 페이지 수

	public BoardMgr() {
		try {
			Context context = new InitialContext();  //java naming;
			ds = (DataSource)context.lookup("java:comp/env/jdbc_maria"); //  "java:comp/env/~~" :필수키워드
		} catch (Exception e) {
			System.out.println("DB연결 실패: " + e);
		}
	}
	
	public int currentGetNum(){ //최대 num 구하기
		String sql = "select max(num) from board";
				int cnt = 0;
				try {
					conn = ds.getConnection();
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					if(rs.next()) cnt = rs.getInt(1);
				} catch (Exception e) {
					System.out.println("currentGetNum err : " + e); 
				}finally{
					try {
						if(rs != null) rs.close();
						if(pstmt != null) pstmt.close();
						if(conn != null) conn.close();
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
				return cnt;
	}
	
	public void saveData(BoardBean bean){
		String sql ="insert into board values(?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bean.getNum());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getPass());
			pstmt.setString(4, bean.getMail());
			pstmt.setString(5, bean.getTitle());
			pstmt.setString(6, bean.getCont());
			pstmt.setString(7, bean.getBip());
			pstmt.setString(8, bean.getBdate());
			pstmt.setInt(9, 0); //readcnt
			pstmt.setInt(10, bean.getGnum());
			pstmt.setInt(11, 0); //onum
			pstmt.setInt(12, 0); //nested (들여쓰기)
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println("saveData Err: " + e);
		}finally{
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}
	
	public void totalList(){ //전체 레코드 건수

		String sql = "select count(*) from board";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			tot = rs.getInt(1);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
	}

	public int getPageSu(){
		pageSu = tot / pList;
		if(tot % pList > 0) pageSu++; //짜투리 페이지 처리
		return pageSu;
	}
	
	public ArrayList<BoardDto> getDataAll(int spage,String stype, String sword){
		ArrayList<BoardDto> list = new ArrayList<>();
		String sql = "select * from board";
		try {
			conn = ds.getConnection();
			if(sword == null){
				sql += " order by gnum desc, onum asc";
				pstmt = conn.prepareStatement(sql);
			}else{ //검색을 할 경우	
				sql += " where " + stype + " like ?";
				sql += " order by gnum desc, onum asc";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + sword + "%");
			}
			
			rs = pstmt.executeQuery();
			
			for (int i = 0; i < (spage -1) * pList; i++){
				rs.next(); //레코드 포인터 위치 0 , 7 ,15
			}
			
			int k = 0;
			
			while(rs.next() && k < pList){
				BoardDto dto = new BoardDto();
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setBdate(rs.getString("bdate"));
				dto.setReadcnt(rs.getInt("readcnt"));
				dto.setNested(rs.getInt("nested"));
				list.add(dto);
				k++;
			}
			
		} catch (Exception e) {
			System.out.println("getDataAll err: " + e);
		}finally{
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		return list;
	}
}





















