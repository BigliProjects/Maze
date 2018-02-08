package com.recruitment.maze;

import java.util.ArrayList;
import java.util.List;

import com.recruitment.maze.factories.GateFactory;

public class Maze {
	
	private static final int NOT_FOUND = -1;
	private static final String SENSORS_LIST_SEPARATOR = ";";
	
	private List<Entry> entries;
	private List<Entry> closedEntries;
	private GateFactory gateFactory;
	private char currentRoomName;
	private int indexOflastEntry;
	private boolean isLastEntryClosed;
	private StringBuilder listSensors;
	
	
	
	
	
	public Maze(String ...entries) {
		initialize();
		for(String entry : entries) {
			saveEntry(entry);
		}
		
	}
	
	
	public void initialize() {
		gateFactory = new GateFactory();
		this.entries = new ArrayList<>();
		closedEntries = new ArrayList<>();
		indexOflastEntry = NOT_FOUND;
		listSensors = new StringBuilder();
	}
	
	
	public void saveEntry(String entry) {
		Room firstRoom = new Room(entry.charAt(0));
		Room secondRoom = new Room(entry.charAt(2));
		Gate gate = gateFactory.createWithSign(entry.charAt(1));
		
		
		this.entries.add(new Entry(firstRoom, gate, secondRoom));
	}
	
	
	

	public void popIn(String roomName) {
		currentRoomName = roomName.charAt(0);
	}
	
	
	public int isExistAnEntryWith(char firstRoomName, char secondRoomName) {
		for(Entry entry : entries) {
			if((entry.firstRoomHasName(firstRoomName) && entry.secondRoomHasName(secondRoomName))
					||
					(entry.firstRoomHasName(secondRoomName) && entry.secondRoomHasName(firstRoomName))) {
				return entries.indexOf(entry);
			}
		}
		
		return NOT_FOUND;
	}
	
	
	public boolean isEntryAlreadyRemoved(char firstRoomName, char secondRoomName) {
		for(Entry entry : closedEntries) {
			if((entry.firstRoomHasName(firstRoomName) && entry.secondRoomHasName(secondRoomName))
					||
					(entry.firstRoomHasName(secondRoomName) && entry.secondRoomHasName(firstRoomName))) {
				return true;
			}
		}
		return false;
	}
	
	

	public void walkTo(String roomName) {
		int indexOfEntry = isExistAnEntryWith(currentRoomName, roomName.charAt(0));
		if(indexOfEntry != NOT_FOUND) {
			currentRoomName = roomName.charAt(0);
			indexOflastEntry = indexOfEntry;
			isLastEntryClosed = false;
			saveSensor(indexOflastEntry);
			
		}
		else if(isEntryAlreadyRemoved(currentRoomName, roomName.charAt(0))) {
			throw new ClosedDoorException();
		}
		
		else {
			throw new IllegalMoveException();
		}
		
	}
	
	
	public void saveSensor(int indexOfEntry) {
		Entry entry = entries.get(indexOfEntry);
		if(entry.hasSensor()) {
			listSensors.append(entries.get(indexOflastEntry).getDescription() + SENSORS_LIST_SEPARATOR);
		}
		
	}

	public void closeLastDoor() {
		if(!isLastEntryClosed) {
			closedEntries.add(entries.get(indexOflastEntry));
			entries.remove(indexOflastEntry);
			isLastEntryClosed = true;
		}else {
			throw new DoorAlreadyClosedException();
		}

	}
	
	
	

	public String readSensors() {

		String result = listSensors.substring(0, listSensors.length() - 1).toString();
		return result;
	}

	

}
