package com.recruitment.maze;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.recruitment.maze.factories.GateFactory;

public class Maze {
	
	private List<Entry> entries;
	private List<Entry> closedEntries;
	private GateFactory gateFactory;
	private char currentRoomName;
	private int indexOflastEntry;
	
	
	
	
	
	public Maze(String ...entries) {
		gateFactory = new GateFactory();
		this.entries = new ArrayList<>();
		closedEntries = null;
		indexOflastEntry = -1;
		
		for(String entry : entries) {
			Room firstRoom = new Room(entry.charAt(0));
			Room secondRoom = new Room(entry.charAt(2));
			Gate gate = gateFactory.createWithSign(entry.charAt(1));
			
			
			this.entries.add(new Entry(firstRoom, gate, secondRoom));
		}
		
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
		
		return -1;
	}
	
	

	public void walkTo(String roomName) {
		int indexOfEntry = isExistAnEntryWith(currentRoomName, roomName.charAt(0));
		if(indexOfEntry != -1) {
			//System.out.println(currentRoomName + " -> " + roomName + " OK ");
			currentRoomName = roomName.charAt(0);
			indexOflastEntry = indexOfEntry;
		}
		
		else {
			throw new IllegalMoveException("room "+roomName+" does not exist");
		}
		
	}

	public void closeLastDoor() {
		if(closedEntries == null) {
			closedEntries = new ArrayList<>();
		}
		else {
			closedEntries.add(entries.get(indexOflastEntry));
			entries.remove(indexOflastEntry);
			System.out.println("entry " + indexOflastEntry + " was removed");
			
			
		}
		
	}

	public BigDecimal readSensors() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
