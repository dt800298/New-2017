package org.usfirst.frc.team548.robot.AutoCommands;

import org.usfirst.frc.team548.robot.Constants;
import org.usfirst.frc.team548.robot.DriveTrain;
import org.usfirst.frc.team548.robot.GearIngestor;

public class DriveDistanceWithArm extends AutoCommandBase {

	private double power, distance, threashold, armPos;
	public DriveDistanceWithArm(double timeOut, double power, double distance, double threshhold, double armPos) {
		super(timeOut);
		this.power = power;
		this.distance = distance;
		this.threashold = threshhold;
		this.armPos = armPos;
	}

	@Override
	public void init() {
		DriveTrain.restEncoders();
		DriveTrain.breakMode(true);
		DriveTrain.restHyro();
	}

	@Override
	protected void run() {
		GearIngestor.setArmPos(armPos);
		if(Math.abs(DriveTrain.averageDistance()) < distance) {
			if(Math.abs(distance)- Math.abs(DriveTrain.averageDistance()) < threashold) DriveTrain.driveStraight(.3 * (Math.abs(power)/power)); 
			else DriveTrain.driveStraight(power);
			
		} else {
			DriveTrain.stop();
			//setDone(true);
		}

	}

	@Override
	public void end() {
		DriveTrain.stop();
		//GearIngestor.stopArm();
	}

	@Override
	protected String getCommandName() {
		// TODO Auto-generated method stub
		return "Drive Straight Distance With Arm";
	}
	
}
