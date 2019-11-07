package org.usfirst.frc.team548.robot.AutoCommands;

import org.usfirst.frc.team548.robot.DriveTrain;

public class OffsetCommand extends AutoCommandBase {
	private double power, distance, threashold;
	private boolean side;
	public OffsetCommand(double timeOut, double power, boolean isRed) {
		super(timeOut);
		this.power = power;
		this.side = isRed;
		
	}
	//true = red, false = blue
	//when alliance = true: 
	
	
	@Override
	public void init() {
		DriveTrain.restEncoders();
		DriveTrain.breakMode(true);
		DriveTrain.restHyro();
	}

	@Override
	protected void run() {
		if(side){
			DriveTrain.drive(power, 0);
		}
		else if (side == false){
			DriveTrain.drive(0, power);
		}
	}

	@Override
	public void end() {
		DriveTrain.stop();
	}

	@Override
	protected String getCommandName() {
		// TODO Auto-generated method stub
		return "only move one side of robot";
	}
}
