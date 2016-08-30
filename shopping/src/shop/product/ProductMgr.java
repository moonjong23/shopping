package shop.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ProductMgr {

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private DataSource ds;
	
	public ProductMgr() {
		try {
			Context context = new InitialContext();  //java naming;
			ds = (DataSource)context.lookup("java:comp/env/jdbc_maria"); //  "java:comp/env/~~" :필수키워드
		} catch (Exception e) {
			System.out.println("DB연결 실패: " + e);
		}
	}
	
	public ArrayList<ProductBean> getProductAll(){
		ArrayList<ProductBean> list = new ArrayList<>();
		try {
			conn = ds.getConnection();
			String sql = "select * from shop_product order by no desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				ProductBean bean = new ProductBean();
				bean.setNo(rs.getInt("no"));
				bean.setName(rs.getString("name"));
				bean.setPrice(rs.getString("price"));
				bean.setDetail(rs.getString("detail"));
				bean.setSdate(rs.getString("sdate"));
				bean.setStock(rs.getString("stock"));
				bean.setImage(rs.getString("image"));
				list.add(bean);
			}
		} catch (Exception e) {
			System.out.println("getProductAll err: " + e);
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
	
	public boolean insertProduct(HttpServletRequest request){
		boolean b = false;
		try {
			//upload할 이미지 경로(절대경로)
			String uploadDir="C:/Users/mj/git/shopping/shopping/WebContent/data";
			
			MultipartRequest multi = new MultipartRequest(
					request, uploadDir, 5* 1024 * 1024, "utf-8", 
					new DefaultFileRenamePolicy());
			//System.out.println(multi.getParameter("name"));
			String sql ="insert into shop_product(name,price,detail,sdate,stock,image) "
					+ "values(?,?,?,now(),?,?)";
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, multi.getParameter("name"));
			pstmt.setString(2, multi.getParameter("price"));
			pstmt.setString(3, multi.getParameter("detail"));
			pstmt.setString(4, multi.getParameter("stock"));
			if(multi.getFilesystemName("image") == null){
				//신상품 등록 시 이미지를 선택하지 않은 경우
				pstmt.setString(5, "ready.gif");
			}else{
				pstmt.setString(5, multi.getFilesystemName("image"));
			}
			
			if(pstmt.executeUpdate() > 0) b = true;
			
		}  catch (Exception e) {
			System.out.println("insertProduct err: " + e);
		}finally{
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return b;
	}
	
	public ProductBean getProduct(String no){ //no 값만 알고 있으면 bean을 통해 다른 정보들도 알 수 있게
		ProductBean bean = null;
		try {
			conn = ds.getConnection();
			String sql = "select * from shop_product where no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, no);
			rs = pstmt.executeQuery();
			while(rs.next()){
				bean = new ProductBean();
				bean.setNo(rs.getInt("no"));
				bean.setName(rs.getString("name"));
				bean.setPrice(rs.getString("price"));
				bean.setDetail(rs.getString("detail"));
				bean.setSdate(rs.getString("sdate"));
				bean.setStock(rs.getString("stock"));
				bean.setImage(rs.getString("image"));
				
			}
		} catch (Exception e) {
			System.out.println("getProductAll err: " + e);
		}finally{
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return bean;
	
	}
	
}//ProductMgr 끝

















