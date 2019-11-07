package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Climber;
import org.usfirst.frc.team548.robot.Constants;

public class MiddleGear extends AutoMode {

	public MiddleGear() {
		super("Middle Gear Only");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run() {		
		driveDistanceWithArm(2, .67, 5.925, 2, Constants.GEARING_PEGHEIGHT);
		GearIngestorExgeset(.7);
		driveDistanceWithArm(.63, -.2, .5, 0, Constants.GEARING_MIN);
		driveDistanceWithArm(.6, -.5, 2, .1, Constants.GEARING_MIN);
	}
}
