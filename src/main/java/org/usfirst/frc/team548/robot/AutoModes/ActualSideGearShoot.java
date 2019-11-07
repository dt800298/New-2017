package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class ActualSideGearShoot extends AutoMode{
	public ActualSideGearShoot() {
		super("Side Gear then shoot");
		//this.isRed = isRed;
		
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run() {
		//line up correctly
		
		
			
			
			driveDistanceWithArm(2.3, .9, Constants.AUTO_SIDE_DRIVE_DISTANCE, 1.5, Constants.GEARING_MAX);
			turnToAngleInTime(1.5, 57, false);
			driveDistanceWithArm(1.5, .5, 1.6, .25,  Constants.GEARING_PEGHEIGHT); //drive toward gear spring
			GearIngestorExgeset(.7);
			driveDistanceWithArm(1.5, .5, 1.6, .25,  Constants.GEARING_MIN); //drive toward gear spring
			
			
			shootAtSpeed(5.25, Constants.SHOOT_CLOSE_SIDE_AUTON_SPEED);
		
	
	}
}

