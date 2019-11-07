package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class SideGear extends AutoMode {
private boolean isRed;
	
	public SideGear(boolean isRed) {
		super("Side Gear");
		this.isRed = isRed;	
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void run() {
		driveDistanceWithArm(2.3, .9, Constants.AUTO_SIDE_DRIVE_DISTANCE, 1.5, Constants.GEARING_PEGHEIGHT);
		if(isRed)turnToAngleInTime(2, -57, false); //turn to gear spring (-) is red
		else turnToAngleInTime(2, 57, false);
		driveDistanceWithArm(1.5, .5, 1.5, .25,  Constants.GEARING_PEGHEIGHT); //drive toward gear spring
		GearIngestorExgeset(.7);
		driveDistanceWithArm(1.5, -.3, 1.5, .25, Constants.GEARING_MIN); //back away from gear thing
		// TODO Auto-generated method stub
	}
}
