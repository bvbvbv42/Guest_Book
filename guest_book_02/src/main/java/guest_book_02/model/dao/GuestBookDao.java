package guest_book_02.model.dao;  //2. 목록조회

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import guest_book_02.model.vo.GuestBookVo;
import static guest_book_02.util.JDBCTemplate.close;
public class GuestBookDao {

	public List<GuestBookVo> selectList(Connection conn) {
		//Connection conn = getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<GuestBookVo> list = new ArrayList<GuestBookVo>();
		try {
			String sql = "SELECT * FROM content_list";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				GuestBookVo vo = new GuestBookVo(); // 비어있는 vo를 만듦
				vo.setAuthor(rs.getString("g_author"));
				vo.setContent(rs.getString("g_content"));
				vo.setReg_date(rs.getDate("g_reg_date"));
				list.add(vo);
			}
		}catch(Exception e) {
			e.printStackTrace();
		} finally {  // try가 문제없이 실행됐을때
			close(rs);
			close(pstmt);
		}
		return list;
	}
	
	public void insertOne(Connection conn, String author, String content) {
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		try {
			String sql = "INSERT INTO content_list (g_author, g_content, g_reg_date) VALUES(?,?, NOW())";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, author);           // controller에서 service로 전달받은 author을 넣는다
			pstmt.setString(2,  content);
			rs = pstmt.executeQuery();            // rs는 쿼리의 실행결과를 받아옴
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstmt);
		}
	}
	
}
