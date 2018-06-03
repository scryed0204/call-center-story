package com.liam.callcenter.bean;

import com.liam.callcenter.util.CallHandler;

public class Fresher implements Employee {

	public final int manageableCallLvl = 50;

	private boolean isAvailiable = true;

	@Override
	public void proceedCall(Call incomingCall) {
		if (incomingCall.getCallLvl() > manageableCallLvl || !isAvailiable) {
			CallHandler.escalateCall(incomingCall);
		} else {
			this.isAvailiable = false;

		}

	}

	@Override
	public void passCall() {
		// TODO Auto-generated method stub

	}

	public boolean isAvailiable() {
		return isAvailiable;
	}	

}
