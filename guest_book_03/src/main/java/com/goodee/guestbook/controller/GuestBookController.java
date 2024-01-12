package com.goodee.guestbook.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.goodee.guestbook.model.service.GuestBookService;
import com.goodee.guestbook.model.vo.GuestBookVo;

@Controller
@RequestMapping("/book")
public class GuestBookController {
	
	@Autowired
	GuestBookService service; 
	
	private static final Logger LOGGER = 
			LoggerFactory.getLogger(GuestBookController.class);
		
	@RequestMapping(method=RequestMethod.GET)
		// 1. ModelAndView (전달하는것과 view 밑에 jsp(전달하는화면)까지있을때)
		// 2. Model  (전달하는 것만있을때)
		// 3. String (view만있을때)
		
	public ModelAndView guestBookList() { //guestBookList메소드가 실행되지만 결과물은 ModelAndView이다
		LOGGER.info("[GuestBookController] guestBookList()탔다;");
		List<GuestBookVo> list = service.selectBookList(); //List에 GuestBookVo를 담을거야
		ModelAndView mav = new ModelAndView();
		mav.setViewName("listup");    //WEB-INF/view에 listup
		mav.addObject("list",list);
		return mav;
	
	}

	
	//  1. RequestParam으로 각각 정보 가져오기
	//	@RequestMapping(method=RequestMethod.POST)
	//	public String guestBookAdd(@RequestParam("author") String author,
	//							   @RequestParam("content") String content) {
	//		// 1. 데이터 추가
	//		// 2. 화면(목록) 이동
	//		service.insertBookOne();
	//		// request 정보 받아오는법
	//		// service, dao에 전달하는 법
	//	
	//		return "";
	//	}
		
	
	// 2. RequestParam으로 모든 정보 Map으로 가져오기
	// @RequestParam
	// httprequestservlet에 있는 파라미터를 가져와서 쓰겠다
	//	@RequestMapping(method=RequestMethod.POST)
	//	public String guestBookAdd(@RequestParam Map<String,String> param) {
	//		String author = param.get("author");
	//		String content = param.get("content");
	//		service.insertBookOne();
	//		return "";
	//}	
	
	// 3. Vo로 가져오기
	@RequestMapping(method=RequestMethod.POST)
	public String guestBookAdd(GuestBookVo vo) {
		LOGGER.info("[GuestBookController] guestBookAdd();");
		service.insertBookOne(vo);  
		return "redirect:/book";   // redirect: 요청 받은것을 다시 요청을 보내라(맨위 get함수 list실행??) 
	}		
	
	
	
	
	
	
	
	
	
	
	
	
	
}
