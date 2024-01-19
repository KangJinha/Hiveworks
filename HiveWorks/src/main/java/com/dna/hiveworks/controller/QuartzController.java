package com.dna.hiveworks.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.dna.hiveworks.service.QuartzService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class QuartzController {
		
		private final QuartzService service;
		// 배치처리하기
		//@Scheduled(cron = "0 0 1 * * MON-FRI")  매월 1일 0시 0분에 실행되도록
		// 월-금 매월 매일 01시 00분 00초에 실행
		@Scheduled(cron = "0 0 1 ? * MON-FRI")
		public void insertQuartzWork() {
			for(int i =1; i<4; i++) {
				service.insertQuartzWork(i);
			}
		}
		// 매년 매월 매일 매시 1분마다 0초에 실행
//		@Scheduled(cron = "0 0/1 * * * MON-FRI")
//		public void insertQuartzWork() {
//			for(int i =7; i<10; i++) {
//				service.insertQuartzWork(i);
//			}
//		}
}