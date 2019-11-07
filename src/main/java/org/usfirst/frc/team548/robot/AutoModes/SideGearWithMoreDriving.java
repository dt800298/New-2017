package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class SideGearWithMoreDriving extends AutoMode{
private int chooser;
	
	public SideGearWithMoreDriving(int chooser) {
		super("Do not use");
		this.chooser = chooser;
		
		
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void run() {
		if(chooser == 1){
			driveDistanceWithArm(2.3, .9, Constants.AUTO_SIDE_DRIVE_DISTANCE, 1.5, Constants.GEARING_PEGHEIGHT);
			turnToAngleInTime(2, 57, false);
			driveDistanceWithArm(1.5, .5, 1.6, .25,  Constants.GEARING_PEGHEIGHT); //drive toward gear spring
			GearIngestorExgeset(.7);
			driveDistanceWithArm(2, -.3, 2, .25, Constants.GEARING_MIN); //back away from gear thing
			//what was added
			turnToAngleInTime(1, -55, false);
			driveDistance(2, .8, 7, 1.0, false);
			turnToAngleInTime(.5, 40, false);
			driveDistance(100, .9, 40.00, 2.00, false);
		}
		else if (chooser == 2){
			driveDistanceWithArm(2.3, .9, Constants.AUTO_SIDE_DRIVE_DISTANCE, 1.5, Constants.GEARING_PEGHEIGHT);
			turnToAngleInTime(2, -57, false); //turn to gear spring (-) is red
			driveDistanceWithArm(1.5, .5, 1.6, .25,  Constants.GEARING_PEGHEIGHT); //drive toward gear spring
			GearIngestorExgeset(.7);
			driveDistanceWithArm(2, -.3, 2, .25, Constants.GEARING_MIN); //back away from gear thing
			//what was added
			turnToAngleInTime(1, 55, false);		
			driveDistance(100, .9, 40.00, 2, false);
		}
		else{
			driveDistanceWithArm(1.6, .9, 5.925, 1.7, Constants.GEARING_PEGHEIGHT);
			GearIngestorExgeset(.7);
			driveDistanceWithArm(.63, -.2, .5, 0, Constants.GEARING_MIN);
			driveDistanceWithArm(.6, -.5, 1.5, .1, Constants.GEARING_MIN);
			turnToAngleInTime(2, 90, false);
			driveDistance(2, .5, 4.5, 0, false);
			turnToAngleInTime(2, -90, false);
			driveDistance(3, .9, 17, 1, false);
		}
	}
}
