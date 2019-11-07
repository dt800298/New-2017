package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class SideGearRightSideBlue extends AutoMode {

private boolean isRed;
	
	public SideGearRightSideBlue() {
		super("Side gear right side blue");
	//	this.isRed = isRed;
		
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run() {
		driveDistanceWithArm(2.3, .9, Constants.AUTO_SIDE_DRIVE_DISTANCE, 1.5, Constants.GEARING_PEGHEIGHT);
		turnToAngleInTime(2, -57, false); //turn to gear spring (-) is red
		driveDistanceWithArm(1.7, .5, 1.8, .25,  Constants.GEARING_PEGHEIGHT); //drive toward gear spring
		GearIngestorExgeset(.7);
		driveDistanceWithArm(2, -.3, 2, .25, Constants.GEARING_MIN); //back away from gear thing
		//what was added
		turnToAngleInTime(1, 55, false);		
		driveDistance(10, .9, 21.00, 2, false);
		
	}
}
