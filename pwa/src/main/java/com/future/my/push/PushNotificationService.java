package com.future.my.push;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import nl.martijndwars.webpush.Notification;
import nl.martijndwars.webpush.PushService;
import nl.martijndwars.webpush.Subscription;

@Service
public class PushNotificationService {

	@Value("${vapid.publicKey}")
	private String publicKey;
	
	@Value("${vapid.privateKey}")
	private String privateKey;
	
	@Value("${vapid.subject}")
	private String subjeck;

	public PushNotificationService() {
		if(Security.getProvider("BC") == null) {
			// web push protocol에서 메시지를 암호화하고 복호하하기 위해 추가
			Security.addProvider(new BouncyCastleProvider());
		}
	}
	public void sendPush(Subscription subscription, String title, String body) {
		PushService pushService = new PushService();
		try {
			pushService.setPublicKey(publicKey);
			pushService.setPrivateKey(privateKey);
			pushService.setSubject(subjeck);
			
			String payload = String.format("{\"title\":\"%s\", \"body\":\"%s\"}", title, body);
			Notification notification = new Notification(subscription, payload);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
