package com.liam.callcenter.item;

import java.util.concurrent.TimeUnit;

import com.liam.callcenter.util.CallHandler;

/**
 * TechnicalLead is an implementation of {@link Employee}.
 * It is a singleton object.
 * It can handle the {@link Call} which has callLevel not greater than 80;
 * 
 * @author Liam
 *
 */
public class TechnicalLead implements Employee {

	public static final int manageableCallLvl = 80;

	private String employeeId;
	private boolean isAvailable = true;
	private CallHandler callHandler;

	// Singleton
	private static TechnicalLead instance;

	private TechnicalLead(CallHandler callHandler) {
		this.callHandler = callHandler;
		this.employeeId = this.getClass().getSimpleName();
	}

	public synchronized static TechnicalLead getInstance(CallHandler callHandler) {

		if (instance == null) {
			instance = new TechnicalLead(callHandler);
		}

		return instance;
	}

	@Override
	public synchronized void proceedCall(Call call) {
		if (call.getCallLvl() > manageableCallLvl) {
			passCall(call);
		} else {
			isAvailable = false;

			System.out.println(employeeId + " is now on a call: " + call.getCallLvl() + " / " + call.getCallId());
			Thread thread = new Thread() {
				public void run() {
					try {
						TimeUnit.SECONDS.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					System.out.println(employeeId + " handled a call: " + call.getCallLvl() + " / " + call.getCallId());

					isAvailable = true;
				}
			};

			thread.start();
		}

	}

	@Override
	public void passCall(Call call) {
		callHandler.escalateCall(this, call);
	}

	@Override
	public boolean isAvailable() {
		return isAvailable;
	}

	@Override
	public String getEmployeeId() {
		return employeeId;
	}

}
