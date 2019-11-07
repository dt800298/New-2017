package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class HershIdealMiddleGear extends AutoMode{

	boolean isRed;
	public HershIdealMiddleGear(boolean isRed) {
		super("Hersh Middle Gear Only");
		// TODO Auto-generated constructor stub
		this.isRed = isRed;
	}

	@Override
	protected void run() {		
		if(isRed){
			driveDistanceWithArm(1.6, .9, 5.925, 1.7, Constants.GEARING_PEGHEIGHT);
			GearIngestorExgeset(.7);
			driveDistanceWithArm(.63, -.2, .5, 0, Constants.GEARING_MIN);
			driveDistanceWithArm(.6, -.5, 1.5, .1, Constants.GEARING_MIN);
			turnToAngleInTime(2, 90, false);
			driveDistance(2, .5, 4.5, 0, false);
			turnToAngleInTime(2, -90, false);
			driveDistance(3, .9, 17, 1, false);
		}
		else{
			driveDistanceWithArm(1.6, .9, 5.925, 1.7, Constants.GEARING_PEGHEIGHT);
			GearIngestorExgeset(.7);
			driveDistanceWithArm(.63, -.2, .5, 0, Constants.GEARING_MIN);
			driveDistanceWithArm(.6, -.5, 1.5, .1, Constants.GEARING_MIN);
			//what was added
			turnToAngleInTime(2, -90, false);
			driveDistance(2, .5, 4.5, 0, false);
			turnToAngleInTime(2, 90, false);
			driveDistance(3, .9, 17, 1, false);
		}	
	}
}
