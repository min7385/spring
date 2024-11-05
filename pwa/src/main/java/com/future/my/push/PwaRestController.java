package com.future.my.push;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.martijndwars.webpush.Subscription;

@RestController
@RequestMapping("/api")
public class PwaRestController {
	
	@Autowired
	private PushNotificationService service;
	@Autowired
	private SubscriptionRepo repo;
	
	@PostMapping("/subscribe")
	public ResponseEntity<Void> subscribe(@RequestBody Subscription sub){
		repo.addSub(sub);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("/get-subscription")
	public ResponseEntity<ArrayList<Subscription>> getSubscription(){
		if(!repo.getSubList().isEmpty()) {
			return ResponseEntity.ok(repo.getSubList());
		}else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PostMapping("/unsubscribe")
	public ResponseEntity<String> unsubcribe(@RequestBody Subscription sub){
		ArrayList<Subscription> subList = new ArrayList<>(repo.getSubList());
		// 동일 endpoint url 구독자의 정보만 삭제
		subList.removeIf(s -> s.endpoint.equals(sub.endpoint));
		repo.setSubList(subList);
		return ResponseEntity.ok("구독 취소됨");
	}
	
	@PostMapping("/send-notification")
	public ResponseEntity<String> sendMessage(String title, String body){
		for(Subscription sub: repo.getSubList()) {
			try {
				service.sendPush(sub, title, body);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return ResponseEntity.ok("푸시 알림이 전송됨.");
	}
}
