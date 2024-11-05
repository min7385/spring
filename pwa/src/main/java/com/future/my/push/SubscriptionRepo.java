package com.future.my.push;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import nl.martijndwars.webpush.Subscription;

@Component
public class SubscriptionRepo {
	
	private ArrayList<Subscription> subList = new ArrayList<>();

	public ArrayList<Subscription> getSubList() {
		return subList;
	}

	public void setSubList(ArrayList<Subscription> subList) {
		this.subList = subList;
	}
	public void addSub(Subscription subscription) {
		subList.add(subscription);
	}
}
