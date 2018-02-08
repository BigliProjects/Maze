package com.recruitment.maze.factories;

import com.recruitment.maze.Gate;
import com.recruitment.maze.GateWithSensor;
import com.recruitment.maze.NormalGate;

public class GateFactory implements IGateFactory{

	@Override
	public Gate createWithSign(char signe) {
		Gate gate = null;
		
		switch(signe) {
		case '|':
			gate = new NormalGate(signe);
			break;
			
		case '$':
			gate = new GateWithSensor(signe);
			break;
		}
		return gate;
	}
	
	

}
