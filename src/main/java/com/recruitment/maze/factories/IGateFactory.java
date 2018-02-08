package com.recruitment.maze.factories;

import com.recruitment.maze.Gate;

public interface IGateFactory {
	
	public Gate createWithSign(char signe);

}
