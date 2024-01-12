package guest_book_02.model.service;

import static guest_book_02.util.JDBCTemplate.getConnection;
import static guest_book_02.util.JDBCTemplate.close;

import java.sql.Connection;
import java.util.List;

import guest_book_02.model.dao.GuestBookDao;
import guest_book_02.model.vo.GuestBookVo;

public class GuestBookService {

	// 새로운 객체 이용
//	public List<GuestBookVo> selectList() {
//		Connection conn = getConnection();
//		List<GuestBookVo> list = new GuestBookDao().selectList(conn); //selectList값을 가져오고싶어 dao야 conn을 전달해줄테니 GuestBookVo를가져와봐 
//		close(conn);
//		return list;
//	}
	
	// 의존성 주입버젼
	public List<GuestBookVo> selectList(GuestBookDao dao){
		Connection conn = getConnection();
		List<GuestBookVo> list = dao.selectList(conn);
		close(conn);
		return list;
	}
	
	public void insertOne(String author, String content) {
		//Dao야 내가 정보를 줄게
		
		
		Connection conn = getConnection();  //getConnection()은 JDBCTemplate에서 가져옴
		new GuestBookDao().insertOne(conn, author, content);
		close(conn);
	}
	
	
}
