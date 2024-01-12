package guest_book_01;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/book")
public class GuestBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public GuestBookServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1. 데이터베이스 접근
		Connection conn = null;
		PreparedStatement pstmt = null; // 쿼리문을 날려줌?
		ResultSet rs = null;   // 쿼리문의 결과를 받아올수 있는 객체
		try {
			String url = "jdbc:mariadb://127.0.0.1:3306/guest_book";  // 그냥 문자열 적은것이라고 생각
			String user = "root";
			String pw = "1234";
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,pw); // DB 접근하기위한 정보(내가연결하고싶은 정보)
			
			String sql = "SELECT * FROM content_list";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery(); // 쿼리문 실행
			// 2. 데이터 파싱(데이터가지고와서 원하는 타입에 담는다)
			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>(); // 빈 list 형태를 만듦
			
			while(rs.next()) { // 하나의 행당 하나의 조합이 있음?
				Map<String,Object> map = new HashMap<String,Object>(); // 빈 Map 형태를 만듦
				map.put("author", rs.getString("g_author")); // 기본적으로 Map은 순서가없고 key를 기준으로 함
				map.put("content", rs.getString("g_content"));
				map.put("date",  rs.getDate("g_reg_date"));
				list.add(map); // list에 넣어라
			}
			
			// System.out.println(list.get(0).get("author"));   // list항목에 첫번째항목인 key가 author를 가져오겠다
			// 3. view에 list 전달
			request.setAttribute("books", list);  // 어떤이름으로, 어떤데이터를 전달할껀지 순서대로
			RequestDispatcher rd = request.getRequestDispatcher("view/guestbook.jsp"); // 매개변수로 어디에 전달할지 적음
			rd.forward(request, response); //forward -> 전달(실행)이라고생각
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	// doget,dopost는 method함수 // get,post는 request 메소드를 뜻함
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. form 정보 받아오기
		request.setCharacterEncoding("UTF-8"); // request야 니정보를 한국어로 받고싶어
		String author = request.getParameter("author"); // author 정보를 가져온다
		String content =request.getParameter("content");
		//System.out.println(author);
		//System.out.println(content);
		
		
		// 2. 데이터베이스 연결
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String url = "jdbc:mariadb://127.0.0.1:3306/guest_book"; // 내컴퓨터 자체 localhost(127.0.0.1:3306)를 뜻함
			String user = "root";
			String pw = "1234";
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,pw);
			
		// 3. insert
			String sql ="INSERT INTO content_list (g_author, g_content, g_reg_date) VALUES(?,?, NOW())";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, author);  // 첫번째 물음표에
			pstmt.setString(2, content); // 두번째 물음표에
			
			rs = pstmt.executeQuery();
		
			// 4. 목록 조회
			response.sendRedirect("/book");  // doget이 다시한번 동작하게됨
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	
	
	}

}
