package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class ShootAndMiddleGear extends AutoMode {
	boolean isRed;
	public ShootAndMiddleGear(boolean isRed) {
		super("Middle Gear And Shoot red "+isRed);
		// TODO Auto-generated constructor stub
		this.isRed = isRed;
	}

	@Override
	protected void run() {
		
		if(isRed){
			shootAtSpeed(8.5, Constants.SHOOT_MIDDLE_AUTON_SPEED);
			driveDistanceWithArm(.3, .3, .5, 0, Constants.GEARING_PEGHEIGHT);
			driveDistanceWithArm(2, -.5, 2.9635, .5, Constants.GEARING_PEGHEIGHT);
			turnToAngleInTime(1.5, 77, false);
			driveDistanceWithArm(1.6, .9, 2.925, 1.7, Constants.GEARING_PEGHEIGHT);
			GearIngestorExgeset(.7);
			driveDistanceWithArm(.63, -.2, .5, 0, Constants.GEARING_MIN);
			driveDistanceWithArm(.6, -.5, 2, .1, Constants.GEARING_MIN);;	
		}
		else{
			shootAtSpeed(8.5, Constants.SHOOT_MIDDLE_AUTON_SPEED);
			driveDistanceWithArm(.3, -.3, .5, 0, Constants.GEARING_PEGHEIGHT);
			driveDistanceWithArm(1.6, .9, 5.925, 1.7, Constants.GEARING_PEGHEIGHT);
			GearIngestorExgeset(.7);
			driveDistanceWithArm(.63, -.2, .5, 0, Constants.GEARING_MIN);
			driveDistanceWithArm(.6, -.5, 2, .1, Constants.GEARING_MIN);
		} 
	}
}
