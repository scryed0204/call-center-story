package com.liam.callcenter.bean;

public interface Employee {

	public void proceedCall(Call call);

	public void passCall(Call call);

	public boolean isAvailiable();

	public String getEmployeeId();

}
