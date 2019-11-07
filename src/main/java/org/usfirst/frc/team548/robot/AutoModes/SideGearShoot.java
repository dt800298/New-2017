package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class SideGearShoot extends AutoMode{

private boolean isRed;
	
	public SideGearShoot(boolean isRed) {
		super("Side Gear then shoot");
		this.isRed = isRed;
		
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run() {
		//line up correctly
		if(isRed){
			shootAtSpeed(5.5, Constants.SHOOT_CLOSE_SIDE_AUTON_SPEED);
			driveInTime(.5, .5);
			driveDistanceWithArm(1.15, -.9, (Constants.AUTO_SIDE_DRIVE_DISTANCE / 2), 1.5, Constants.GEARING_MAX);
			turnToAngleInTime(.75, 90, false);
			turnToAngleInTime(.75, 90, false);
			driveDistanceWithArm(1.15, .9, (Constants.AUTO_SIDE_DRIVE_DISTANCE / 2), 1.5, Constants.GEARING_PEGHEIGHT);
			turnToAngleInTime(1.7, -57, false);
			driveDistanceWithArm(1.5, .5, 1.6, .25,  Constants.GEARING_PEGHEIGHT);
			GearIngestorExgeset(.7);
		}
		else{
			shootAtSpeed(5.25, Constants.SHOOT_CLOSE_SIDE_AUTON_SPEED);
			driveInTime(.5, -.5);
			driveDistanceWithArm(2.3, .9, Constants.AUTO_SIDE_DRIVE_DISTANCE, 1.5, Constants.GEARING_MAX);
			turnToAngleInTime(1.5, 57, false);
			driveDistanceWithArm(1.5, .5, 1.6, .25,  Constants.GEARING_PEGHEIGHT); //drive toward gear spring
			GearIngestorExgeset(.7);
		}
	}
}

//