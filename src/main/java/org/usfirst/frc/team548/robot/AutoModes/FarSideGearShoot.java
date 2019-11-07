package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class FarSideGearShoot extends AutoMode{
private boolean isRed;
	
	public FarSideGearShoot(boolean isRed) {
		super("Far side gear then shoot");
		this.isRed = isRed;
		
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run() {
		if(isRed){
			shootAtSpeed(6.25, Constants.SHOOT_FAR_SIDE_AUTON_SPEED);
			driveInTime(.5, .5);
			driveDistanceWithArm(1.15, -.9, (Constants.AUTO_SIDE_DRIVE_DISTANCE / 2), 1.5, Constants.GEARING_PEGHEIGHT);
			turnToAngleInTime(1.7, 180, false);
			driveDistanceWithArm(1.15, .9, (Constants.AUTO_SIDE_DRIVE_DISTANCE / 2), 1.5, Constants.GEARING_PEGHEIGHT);
			turnToAngleInTime(1.7, 57, false);
			driveDistanceWithArm(1.5, .5, 1.5, .25,  Constants.GEARING_PEGHEIGHT);
			GearIngestorExgeset(.7);
		}
		else{
			shootAtSpeed(6.25, Constants.SHOOT_FAR_SIDE_AUTON_SPEED);
			driveInTime(.5, -.5);
			driveDistanceWithArm(2.3, .9, Constants.AUTO_SIDE_DRIVE_DISTANCE, 1.5, Constants.GEARING_PEGHEIGHT);
			turnToAngleInTime(1.5, -57, false);
			driveDistanceWithArm(1.5, .5, 1.5, .25,  Constants.GEARING_PEGHEIGHT); //drive toward gear spring
			GearIngestorExgeset(.7);
		}	
	}
}
