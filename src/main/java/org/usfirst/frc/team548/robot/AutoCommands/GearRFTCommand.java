package org.usfirst.frc.team548.robot.AutoCommands;

import org.usfirst.frc.team548.robot.Climber;
import org.usfirst.frc.team548.robot.Constants;
import org.usfirst.frc.team548.robot.DriveTrain;

public class GearRFTCommand extends AutoCommandBase {

	public GearRFTCommand(double timeOut) {
		super(timeOut);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}
	
	int wiggle = 0;

	@Override
	protected void run() {
		
		if(timer.get() > .75 && timer.get() < 1.5) {
			Climber.setClimbOpen(true);
			if(wiggle < 4){
				DriveTrain.drive(-Constants.DT_WIGGLE_POWER, -Constants.DT_WIGGLE_POWER);
			}
			else if(wiggle >= 4){
				DriveTrain.drive(Constants.DT_WIGGLE_POWER, Constants.DT_WIGGLE_POWER);
			}	
			wiggle ++;
			if (wiggle > 8) wiggle = 0;
			
			
		} else if(timer.get() > 1.5) {
			Climber.setClimbOpen(false);
		}
		
		
		//TopGear.setOpen(true);
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub
		DriveTrain.stop();
	}

	@Override
	protected String getCommandName() {
		return "Gear With RFT";
	}

}
