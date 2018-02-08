package com.recruitment.maze;

public class Entry {
	
	private Room firstRoom;
	private Room secondRoom;
	private Gate gate;
	
	
	
	public Entry(Room firstRoom, Gate gate, Room secondRoom) {
		this.firstRoom = firstRoom;
		this.secondRoom = secondRoom;
		this.gate = gate;
	}
	
	
	public boolean firstRoomHasName(char name) {
		return (firstRoom.getName() == name);
	}
	
	
	public boolean secondRoomHasName(char name) {
		return (secondRoom.getName() == name);
	}
	
	public String getDescription() {
		return (Character.toString(firstRoom.getName()) + Character.toString(secondRoom.getName()));
	}
	
	public boolean hasSensor() {
		
		return (gate instanceof GateWithSensor);
	}
	
	
	
	
	

}
