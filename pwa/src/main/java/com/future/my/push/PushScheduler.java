package com.future.my.push;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import nl.martijndwars.webpush.Subscription;

@Component
public class PushScheduler {

	@Autowired
	private PushNotificationService pushService;
	
	@Autowired
	private SubscriptionRepo subRepo;
	
	@Scheduled(fixedRate = 30000)	// 30초
	public void sendPushPush() {
		System.out.println("send start");
		String title = "정기 알림!!";
		String body = "이것은 30초 마다 보내는 정기 메세지입니다.";
		
		for(Subscription sub : subRepo.getSubList()) {
			System.out.println("구독자: " + sub);
			try {
				pushService.sendPush(sub, title, body);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
