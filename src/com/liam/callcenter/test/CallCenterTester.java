package com.liam.callcenter.test;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.liam.callcenter.bean.Call;
import com.liam.callcenter.util.CallGenerator;
import com.liam.callcenter.util.CallHandler;

public class CallCenterTester {
	static ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
	static ScheduledFuture<?> t;
	static Integer callQty = null;
	static Integer attempts = 1;
	static CallHandler callHandler = CallHandler.getInstance(2); 

	@SuppressWarnings("resource")
	public static void main(String args[]) {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Please input the quantity of the calls you want to test: ");
		callQty = scanner.nextInt();


		t = executorService.scheduleAtFixedRate(CallCenterTester::testTask, 0, 3, TimeUnit.SECONDS);

	}

	private static void testTask() {
		// System.out.println(attempts);
		Call call = CallGenerator.getNewCall();
		callHandler.handleCall(call);
		
		attempts++;
		if (attempts > callQty) {
			t.cancel(false);
		}
	}

}
