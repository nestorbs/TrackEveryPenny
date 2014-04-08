package com.nestorbs.toolkit;

public class ProgrammerMistake extends RuntimeException {
	public ProgrammerMistake(IllegalArgumentException illegalArgumentException) {
		super(illegalArgumentException);
	}
}
