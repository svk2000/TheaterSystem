package com.mypackage.theatersystem;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import com.mypackage.theatersystem.dto.Person;
import com.mypackage.theatersystem.dto.Row;
import com.mypackage.theatersystem.dto.Section;

public class Theater {

	private static Scanner inputScanner;

	private static int totalTheaterSeatCount;

	private static int sectionMaxsize;

	private static int validRequestCount;

	private static LinkedHashMap<Integer, Row> rowSectionsMap = new LinkedHashMap<Integer, Row>();
	
	private static LinkedHashMap<String, Person> personRequestMap = new LinkedHashMap<String, Person>();

	public static void main(String[] args) throws Exception {
		try {
			inputScanner = new Scanner(System.in);
			int rowId = 0;
			int sectionId = 0;
			String tempLine = null;
			String[] stringSplit = null;
			Section newSection = null;
			Row row = null;
			Person person = null;
			inputScanner.nextLine();
			// Read Theater layout from Input
			while (inputScanner.hasNextLine()) {
				tempLine = inputScanner.nextLine();
				if (tempLine.equals("")) {
					break;
				} else {
					int[] sections = Pattern.compile(" ").splitAsStream(tempLine.trim()).mapToInt(Integer::parseInt)
							.toArray();
					rowId++;
					sectionId = 0;
					row = new Row(rowId);
					for (int section : sections) {
						sectionId++;
						newSection = new Section(sectionId, rowId,  section);
						row.addSection(newSection);
						totalTheaterSeatCount += section;
						sectionMaxsize = sectionMaxsize > section ? sectionMaxsize : section;
					}
					rowSectionsMap.put(rowId, row);

				}

			}

			// Read User Requests from Input
			while (inputScanner.hasNextLine()) {
				tempLine = inputScanner.nextLine();
				if (tempLine.equals("")) {
					break;
				} else {
					stringSplit = tempLine.trim().split(" ");
					person = new Person(stringSplit[0], Integer.parseInt(stringSplit[1]));
					validatePerson(person);
					personRequestMap.put(stringSplit[0], person);
				}
			}

			// Process Request Data
			processRequestData();
			printOutput();

		} catch (Exception exp) {
			exp.printStackTrace();
			System.out.println("Exception occurred while Processing data  :" + exp);
		} finally {
			inputScanner.close();
		}

	}
	
	/*
	 Initial Set of validations
	 Verify request count against theater Seat count and Section count
	*/  
	private static void validatePerson(Person person) {
		if (person.getRequestCount() > totalTheaterSeatCount) {
			person.setUserMessage("Sorry, we can't handle your party");
		} else if (person.getRequestCount() > sectionMaxsize) {
			person.setUserMessage("Call to split party");
		} else {
			validRequestCount += person.getRequestCount();
		}
	}

	// Process request data
	private static void processRequestData() {
		
		int processedRequestCount = 0;
		int remainedRequestCount = 0;
		int maxRowId;
		Person person = null;
		
		for (Map.Entry<String, Person> entry : personRequestMap.entrySet()) {
			person = entry.getValue();
			if(person.getUserMessage() == null || person.getUserMessage().isEmpty()) {
				remainedRequestCount = validRequestCount - processedRequestCount;
				maxRowId = findMaxRowForAllocation(remainedRequestCount, person.getRequestCount());
				findBestFit(maxRowId,person);
				processedRequestCount += person.getRequestCount();
			}
		}

	}

	//Finds BestFit section for request
	private static void findBestFit(int maxRowId, Person person) {
		Section bestFitSection = null;
		Section firstFitSection = null;
		Row row = null;
		for(Map.Entry<Integer, Row> entry : rowSectionsMap.entrySet()) {
			row = entry.getValue();
			if(row.getRowId() <= maxRowId){
				for (Section section : row.getSections()) {
					if (firstFitSection == null && section.getSeatsRemained() >= person.getRequestCount()) {
						firstFitSection = section;
					}
					if (bestFitSection == null && section.getSeatsRemained() == person.getRequestCount()) {
						bestFitSection = section;
					}
				}
			}
		}

		if(bestFitSection != null && firstFitSection != null && bestFitSection.getRowId() > firstFitSection.getRowId()) {
			bestFitSection.setSeatsAllocated(person.getRequestCount());
			person.setRowId(bestFitSection.getRowId());
			person.setSectionId(bestFitSection.getSectionId());
		} else if (bestFitSection == null && firstFitSection != null) {
			firstFitSection.setSeatsAllocated(person.getRequestCount());
			person.setRowId(firstFitSection.getRowId());
			person.setSectionId(firstFitSection.getSectionId());
		}else {
			bestFitSection.setSeatsAllocated(person.getRequestCount());
			person.setRowId(bestFitSection.getRowId());
			person.setSectionId(bestFitSection.getSectionId());
		}
		
		
	}

	// Finds Maximum RowID to allocate to the the request
	private static Integer findMaxRowForAllocation(int remainedRequestCount, int requestCount) {
		int consolidatedRowCount = 0;
		boolean isRequestBucketExists = false;
		for (Map.Entry<Integer, Row> entry : rowSectionsMap.entrySet()) {
			consolidatedRowCount += entry.getValue().getRowSeatsRemained();
			for(Section section : entry.getValue().getSections()) {
				if (section.getSeatsRemained() >= requestCount) {
					isRequestBucketExists = true;
				}
			}
			if (remainedRequestCount <= consolidatedRowCount && isRequestBucketExists) {
			      return entry.getKey();
			   }
		}
		return null;
		
	}
	
	// Prints request output in Console
		private static void printOutput() {
			personRequestMap.forEach((key, value) -> {
				System.out.println(value);
			});
			
		}
}
