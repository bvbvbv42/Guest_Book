package guest_book_02.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import guest_book_02.model.service.GuestBookService;
import guest_book_02.model.vo.GuestBookVo;

@WebServlet("/book")
public class GuestBookServlet extends HttpServlet{ // 요청을받으려면 상속을 받아야함
	private static final long serialVersionUID = 1L;
	
	public GuestBookServlet() {
		super();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)  //접근제한자가 protected다, 두개의 매개변수를 사용(HttpServletRequest, HttpServletResponse 객체를 쓰겠다)
			throws ServletException, IOException{
		// 1. 데이터베이스접근
		List<GuestBookVo> list = 
				new GuestBookService().selectList(); // ListVo가져와봐
		
		req.setAttribute("books", list); //books라는 list를 보낼거야
		RequestDispatcher rd = req.getRequestDispatcher("view/guestbook.jsp"); //dispatcher가 jsp로 request값을 전달함
		rd.forward(req,  resp);
		// 2. 목록 조회
		// 3. 결과 파싱
		// 4. view 전달
	}
	
	//	override 상속(부모)받아서 자식이 쓰는것을말함
	//	추상클래스 : 나를 상속받으렴
	//	overload랑 차이를 좀 알아봐라
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. form이 보낸 정보 받아오기
		req.setCharacterEncoding("UTF-8");
		String author = req.getParameter("author"); //form 태그에있는 name 기준으로 온다
		String content = req.getParameter("content");
		
		// 2. 데이터베이스에 insert한다
		new GuestBookService().insertOne(author,content); //servlet이 가지고있는 정보를 보내줌
		
		// 3. 목록으로 돌아가기
		resp.sendRedirect("/book");   // resp야 너가가지고 sendRedirect실행해
	}
	
}
