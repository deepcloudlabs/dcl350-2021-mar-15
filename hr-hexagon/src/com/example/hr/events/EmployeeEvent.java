package com.example.hr.events;

import com.example.hr.domain.TcKimlikNo;

public abstract class EmployeeEvent {
	private TcKimlikNo kimlikNo;

	public EmployeeEvent(TcKimlikNo kimlikNo) {
		this.kimlikNo = kimlikNo;
	}

	public TcKimlikNo getKimlikNo() {
		return kimlikNo;
	}

}
