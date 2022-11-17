package com.study.mapper.board;

import java.util.List;

import com.study.domain.board.BoardDto;

public interface BoardMapper {

	
	int insert(BoardDto board);

	List<BoardDto> list(int offset, int records, String type, String keyword);

	BoardDto select(int id);

	int update(BoardDto board);

	int delete(int id);

	int countAll(String type, String keyword);

	int insertFile(int id, String fileName);

	int deleteFileByBoardId(int id);

	int deleteFileByBoardIdAndFileName(int id, String fileName);

}








