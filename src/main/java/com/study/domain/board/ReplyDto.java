package com.study.domain.board;

import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.Data;

@Data
public class ReplyDto {
	private int id;
	private int boardId;
	private String content;
	
	@JsonFormat(shape = Shape.STRING)
	private LocalDateTime inserted;
	
	public String getAgo() {
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime oneDayBefore = now.minusDays(1);
		LocalDateTime oneMonthBefore = now.minusMonths(1);
		LocalDateTime oneYearBefore = now.minusYears(1);
		
		String result = "";
		// 작성일과 현재가 
		
		if (oneDayBefore.isBefore(inserted)) {
			// 하루 차이면 시간을 출력
			result = inserted.toLocalTime().toString();
		} else if (oneMonthBefore.isBefore(inserted)) {
			// 1달 내이면 n일 전
			result = Period.between(inserted.toLocalDate(), now.toLocalDate())
						.getDays() + "일 전";
		} else if (oneYearBefore.isBefore(inserted)) {
			// 1년 이내면 n달 전
			result = Period.between(inserted.toLocalDate(), now.toLocalDate())
					.get(ChronoUnit.MONTHS) + "달 전";
		} else {
			// n년 전
			result = Period.between(inserted.toLocalDate(), now.toLocalDate())
					.getYears() + "년 전";
		}
		
		return result;
	}
}






