package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class TestAuto extends AutoMode {

	public TestAuto() {
		super("Test");
		// TODO Auto-generated constructor stub
	}
	//.869539 feet to rot
	@Override
	protected void run() {
		//Fail hopper
//		driveDistance(1.6, -.9, 8.25, 2.5, false);
//		turnToAngleInTime(.2, -10, false);
//		turnToAngleInTime(.5, 90, false);
//		driveDistance(.5, .7, .7, .3, false);
//		turnToAngleInTime(.5, 20, false);
//		driveDistance(2.4, .9, 7.6, 1.5, false);
		//Wide turn and back
//		wideTurn(1, 0, .75);
//		shootAtSpeed(4.0, Constants.SHOOT_AUTON_SPEED);
//		driveDistance(2.0, -.5, 5.0, 1);
		//turnToAngleInTime()
		
//		driveDistance(3, -.9, 7.7, 1.5, false);
// 		turnToAngleInTime(3, -62, false);
		
		driveDistanceWithArm(2, .6, 5.925, 2, Constants.GEARING_PEGHEIGHT);
		GearIngestorExgeset(.7);
		turnToAngleInTime(.5, -90, false);
		driveDistance(2.5, .7, 11.7, .5, false);
		shootAtSpeed(15, Constants.SHOOT_AUTON_SPEED);
		
	}

	

}
