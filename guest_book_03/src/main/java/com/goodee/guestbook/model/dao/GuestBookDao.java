package com.goodee.guestbook.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.goodee.guestbook.model.vo.GuestBookVo;

@Repository
public class GuestBookDao {

	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static final Logger LOGGER = 
			LoggerFactory.getLogger(GuestBookDao.class);
	
	public List<GuestBookVo> selectBookAll(){
		LOGGER.info("[GuestBookDao] selectBookAll()");// 메소드를 탔는지 안탔는지 확인
		List<GuestBookVo> list = new ArrayList<GuestBookVo>(); // 왜 autowired를 안쓰냐면 db갔다오는 마지막단계이기떄문에
		// db접근하려면 try-catch있어야함
		try {
			String sql = "SELECT * FROM content_list";
			list = jdbcTemplate.query(sql, new RowMapper<GuestBookVo>() {//jdbctemplate에 sql를 날려줄꺼다
				@Override
				public GuestBookVo mapRow(ResultSet rs, int rowNum) throws SQLException{
					GuestBookVo vo = new GuestBookVo();
					// db에서 정보를가져옴
					vo.setAuthor(rs.getString("g_author"));
					vo.setContent(rs.getString("g_content"));
					vo.setReg_date(rs.getDate("g_reg_date"));
					return vo;
					
				}
			}); 
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public int insertGuestBook(GuestBookVo vo) {
		LOGGER.info("[GuestBookDao] insertGuestBook();");
		String sql = "INSERT INTO content_list (g_author, g_content, g_reg_date) "
				+ "VALUES('"+vo.getAuthor()+"','"+vo.getContent()+"', NOW())";
		int result = -1;
		// sql문을 db에 날려버리기~~~~~~
		try {
			result = jdbcTemplate.update(sql);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// 실패하면 -1 , 업데이트갯수된것만큼 숫자가나옴
		return result;
	}
	
}
