package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class MiddleGearAndShoot extends AutoMode {

	boolean isRed;
	public MiddleGearAndShoot(boolean red) {
		super("Middle Gear And Shoot. Red? "+red);
		// TODO Auto-generated constructor stub
		this.isRed = red;
	}

	@Override
	protected void run() {
		if(isRed){
			//System.out.print("****** I AM RUNNING *********");
			driveDistanceWithArm(1.6, .9, 5.925, 1.7, Constants.GEARING_PEGHEIGHT);
			GearIngestorExgeset(.7);
			driveDistanceWithArm(2, -.5, 2.9635, .5, Constants.GEARING_MAX);
			turnToAngleInTime(1.5, 77, false);
			
			driveDistanceWithArm(2, -.5, 3, .5, Constants.GEARING_ZERO);
			offsetPower(.1, -.5, true);
			shootAtSpeed(7.5, Constants.SHOOT_MIDDLE_AUTON_SPEED);
		} 
		else {
			driveDistanceWithArm(1.6, .9, 5.925, 1.7, Constants.GEARING_PEGHEIGHT);
			GearIngestorExgeset(.7);
			driveDistanceWithArm(1.9, -.8, 5.940, 1, Constants.GEARING_ZERO);
			offsetPower(.1, .5, false);
			shootAtSpeed(7.5, Constants.SHOOT_MIDDLE_AUTON_SPEED);
		}
		
	}

}