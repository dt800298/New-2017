package org.usfirst.frc.team548.robot.AutoCommands;

import org.usfirst.frc.team548.robot.DriveTrain;

public class WideTurnCommand extends AutoCommandBase {

	private double right, left;
	public WideTurnCommand(double timeOut, double powerLeft, double powerRight) {
		super(timeOut);
		right = powerRight;
		left = powerLeft;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		DriveTrain.drive(left, right);
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub
		DriveTrain.stop();
	}

	@Override
	protected String getCommandName() {
		// TODO Auto-generated method stub
		return "Wide Turn";
	}

}
