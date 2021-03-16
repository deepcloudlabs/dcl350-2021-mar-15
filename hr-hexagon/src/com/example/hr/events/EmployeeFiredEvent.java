package com.example.hr.events;

import com.example.hr.domain.TcKimlikNo;

public class EmployeeFiredEvent extends EmployeeEvent {

	public EmployeeFiredEvent(TcKimlikNo kimlikNo) {
		super(kimlikNo);
	}

}
