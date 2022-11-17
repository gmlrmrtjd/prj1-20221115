package com.study.mapper.member;

import java.util.List;

import com.study.domain.member.MemberDto;

public interface MemberMapper {

	int insert(MemberDto member);

	List<MemberDto> selectAll();

	MemberDto selectById(String id);

	int update(MemberDto member);

	int deleteById(String id);

	MemberDto selectByEmail(String email);
	
	MemberDto selectByNickName(String nickName);

}
