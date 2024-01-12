package com.goodee.guestbook.model.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodee.guestbook.model.dao.GuestBookDao;
import com.goodee.guestbook.model.vo.GuestBookVo;

@Service
public class GuestBookService {

	@Autowired
	GuestBookDao dao;
	
	private static final Logger LOGGER =
			LoggerFactory.getLogger(GuestBookService.class);
	
	public List<GuestBookVo> selectBookList(){
		LOGGER.info("[GuestBookService] selectBookList();");
		List<GuestBookVo> list = dao.selectBookAll();
		return list;
	}
	
	// 데이터베이스에 추가하는것을 명령
	// int는 실패하면 0값을 리턴
	public int insertBookOne(GuestBookVo vo) {
		LOGGER.info("[GuestBookService] insetBookOne();");
		int result = dao.insertGuestBook(vo);
		return result;
	}
}
