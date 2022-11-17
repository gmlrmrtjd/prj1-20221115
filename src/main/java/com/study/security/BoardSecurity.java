package com.study.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.study.domain.board.BoardDto;
import com.study.mapper.board.BoardMapper;

@Component
public class BoardSecurity {
	
	@Autowired
	private BoardMapper mapper;
	
	public boolean checkWriter(String username, int boardId) {
		BoardDto board = mapper.select(boardId);
		
		return board.getWriter().equals(username);
	}
}







