package com.mypackage.theatersystem.dto;

public class Person {

	public Person(String personName, Integer requestCount) {
		super();
		this.personName = personName;
		this.requestCount = requestCount;
	}

	private String personName;

	private Integer requestCount;

	private Integer rowId;

	private Integer sectionId;

	private String userMessage;

	public String getPersonName() {
		return personName;
	}

	public Integer getRequestCount() {
		return requestCount;
	}

	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	public Integer getSectionId() {
		return sectionId;
	}

	public void setSectionId(Integer sectionId) {
		this.sectionId = sectionId;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}
	
	public String getUserMessage() {
		return userMessage;
	}

	@Override
	public String toString() {
		if (userMessage == null) {
			return personName + " RowID: " + rowId + " sectionID: " + sectionId;
		} else {
			return personName + " " + userMessage;
		}

	}

}
