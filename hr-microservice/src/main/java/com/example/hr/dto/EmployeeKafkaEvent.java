package com.example.hr.dto;

public class EmployeeKafkaEvent {
	private String identity;

	public EmployeeKafkaEvent() {
	}

	public EmployeeKafkaEvent(String identity) {
		this.identity = identity;
	}

	public String getIdentity() {
		return identity;
	}

	@Override
	public String toString() {
		return "EmployeeKafkaEvent [identity=" + identity + "]";
	}

}
