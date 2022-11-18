package com.study.controller.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.domain.board.ReplyDto;
import com.study.service.board.ReplyService;

@Controller
@RequestMapping("reply")
public class ReplyController {
	
	@Autowired
	private ReplyService service;
	
	@PutMapping("modify")
	@ResponseBody
	@PreAuthorize("@replySecurity.checkWriter(authentication.name, #reply.id)")
	public Map<String, Object> modify(@RequestBody ReplyDto reply) {
		Map<String, Object> map = new HashMap<>();
		
		int cnt = service.modify(reply);
		
		if (cnt == 1) {
			map.put("message", "댓글이 수정되었습니다.");
		} else {
			map.put("message", "댓글이 수정되지 않았습니다.");
		}
		
		return map;
	}
	
	@GetMapping("get/{id}")
	@ResponseBody
	public ReplyDto get(@PathVariable int id) {
		return service.getById(id);
	}
	
	@DeleteMapping("remove/{id}")
	@ResponseBody
	@PreAuthorize("@replySecurity.checkWriter(authentication.name, #id)")
	public Map<String, Object> remove(@PathVariable int id) {
		Map<String, Object> map = new HashMap<>();
		
		int cnt = service.removeById(id);
		if (cnt == 1) {
			map.put("message", "댓글이 삭제되었습니다.");
		} else {
			map.put("message", "댓글이 삭제되지 않았습니다.");
		}
		return map;
	}
	
	@GetMapping("list/{boardId}")
	@ResponseBody
	public List<ReplyDto> list(@PathVariable int boardId, Authentication authentication) {
		
		String username = "";
		if (authentication != null) {
			username = authentication.getName();
		}
		
		return service.listReplyByBoardId(boardId, username);
	}

	@PostMapping("add")
	@ResponseBody
	@PreAuthorize("isAuthenticated()")
	public Map<String, Object> add(@RequestBody ReplyDto reply, Authentication authentication) {
//		System.out.println(reply);
		reply.setWriter(authentication.getName());
		
		Map<String, Object> map = new HashMap<>();
		
		int cnt = service.addReply(reply);
		if (cnt == 1) {
			map.put("message", "새 댓글이 등록되었습니다.");
		} else {
			map.put("message", "새 댓글이 등록되지 않았습니다.");
		}
		
		return map;
	}
}








