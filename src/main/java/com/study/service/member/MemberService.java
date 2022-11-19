package com.study.service.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.study.domain.board.BoardDto;
import com.study.domain.member.MemberDto;
import com.study.mapper.board.BoardMapper;
import com.study.mapper.board.ReplyMapper;
import com.study.mapper.member.MemberMapper;
import com.study.service.board.BoardSerivce;

@Service
@Transactional
public class MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private ReplyMapper replyMapper;
	
	@Autowired
	private BoardSerivce boardService;
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public int insert(MemberDto member) {
		
		String pw = member.getPassword();
		
		member.setPassword(passwordEncoder.encode(pw));
		
		return memberMapper.insert(member);
	}

	public List<MemberDto> list() {
		// TODO Auto-generated method stub
		return memberMapper.selectAll();
	}

	public MemberDto getById(String id) {
		// TODO Auto-generated method stub
		return memberMapper.selectById(id);
	}

	public int modify(MemberDto member) {
		int cnt = 0;
		
		try {
			if (member.getPassword() != null) {
				String encodedPw = passwordEncoder.encode(member.getPassword());
				member.setPassword(encodedPw);
			}
			
			return memberMapper.update(member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt; 
	}


	public int remove(String id) {
		// 좋아요 지우기
		boardMapper.deleteLikeByMemberId(id);
		
		// 댓글 지우기
		replyMapper.deleteByMemeberId(id);
		
		// 사용자가 쓴 게시물 목록 조회
		List<BoardDto> list = boardMapper.listByMemberId(id);
		
		for (BoardDto board : list) {
			boardService.remove(board.getId());
		}
		
		return memberMapper.deleteById(id);
	}

	public MemberDto getByEmail(String email) {
		// TODO Auto-generated method stub
		return memberMapper.selectByEmail(email);
	}

	public MemberDto getByNickName(String nickName) {
		// TODO Auto-generated method stub
		return memberMapper.selectByNickName(nickName);
	}

}






