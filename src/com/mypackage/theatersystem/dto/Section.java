package com.mypackage.theatersystem.dto;

public class Section {

	public Section(Integer sectionId, Integer rowId, Integer totqlSeatCount) {
		super();
		this.sectionId = sectionId;
		this.rowId = rowId;
		this.totalSeatCount = totqlSeatCount;
	}

	private Integer sectionId;

	private Integer rowId;

	private int totalSeatCount;

	private int seatsAllocated;

	public Integer getSectionId() {
		return sectionId;
	}

	public Integer getRowId() {
		return rowId;
	}

	public Integer getTotalSeatCount() {
		return totalSeatCount;
	}

	public void setTotalSeatCount(int totalSeatCount) {
		this.totalSeatCount = totalSeatCount;
	}

	public Integer getSeatsAllocated() {
		return seatsAllocated;
	}

	public void setSeatsAllocated(int seatsAllocated) {
		this.seatsAllocated += seatsAllocated;
	}

	public Integer getSeatsRemained() {
		return (totalSeatCount - seatsAllocated);
	}

}
