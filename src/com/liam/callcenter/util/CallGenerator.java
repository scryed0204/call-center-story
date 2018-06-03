package com.liam.callcenter.util;

import java.util.Random;

import com.liam.callcenter.bean.Call;

public class CallGenerator {

	public static Call getNewCall() {
		int callLvl = new Random().nextInt(100) + 1;
		Call call = new Call(callLvl);
		return call; 
	}

}
