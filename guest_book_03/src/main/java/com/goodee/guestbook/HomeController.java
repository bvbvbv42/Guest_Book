package com.goodee.guestbook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);   //Log 기록하는것을 사용하겠다
	
	@RequestMapping(value= {"","/"},method=RequestMethod.GET)
	public String home() { // {"","/"} 이게들어가면 home 메소드를탐
		LOGGER.info("{HomeController} home();");  //log4j.xml 
		return "home";
//		<!-- Application Loggers -->
//		<logger name="com.goodee.guestbook">
//			<level value="info" />
//		</logger>
//      -- > console창에 log까지 나오게 하겠다		
		
	}
}
