package com.mypackage.theatersystem.dto;

import java.util.ArrayList;

public class Row {
	
	public Row(Integer rowId) {
		super();
		this.sections = new ArrayList<Section>();
		this.rowId = rowId;
	}

	public Integer getRowId() {
		return rowId;
	}

	private Integer rowId;
	
	private ArrayList<Section> sections;

	private int rowSeatCount;

	private int rowSeatsRemained;

	private int rowSeatsAllocated;
	
	// stores row count from Row 1 to this Row
	private int consolidateRowCount;

	public int getConsolidateRowCount() {
		return consolidateRowCount;
	}

	public void setConsolidateRowCount(int consolidateRowCount) {
		this.consolidateRowCount = consolidateRowCount;
	}

	public ArrayList<Section> getSections() {
		return sections;
	}

	public void addSection(Section section) {
		this.sections.add(section);
	}

	public Integer getRowSeatCount() {
		rowSeatCount = 0;
		for (Section section : sections) {
			rowSeatCount = rowSeatCount + section.getTotalSeatCount();
		}
		return rowSeatCount;
	}

	public Integer getRowSeatsRemained() {
		rowSeatsRemained = 0;
		for (Section section : sections) {
			rowSeatsRemained = rowSeatsRemained + section.getSeatsRemained();
		}
		return rowSeatsRemained;
	}

	public Integer getRowSeatsAllocated() {
		rowSeatsAllocated = 0;
		for (Section section : sections) {
			rowSeatsAllocated = rowSeatsAllocated + section.getSeatsAllocated();
		}
		return rowSeatsAllocated;
	}

}
