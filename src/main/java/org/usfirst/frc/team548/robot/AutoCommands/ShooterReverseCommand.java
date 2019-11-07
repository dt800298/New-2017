package org.usfirst.frc.team548.robot.AutoCommands;

import org.usfirst.frc.team548.robot.Shooter;

public class ShooterReverseCommand extends AutoCommandBase {
	private double speed;

	public ShooterReverseCommand(double timeOut) {
		super(timeOut);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void run() {
		// TODO Auto-generated method stub
		Shooter.setConvPower(-.5);
		Shooter.setElevator(-.7);
	}
	
	@Override
	public void end() {
		// TODO Auto-generated method stub
		Shooter.stop();
		Shooter.setElevator(0);
		
	}
	
	@Override
	protected String getCommandName() {
		// TODO Auto-generated method stub
		return "somename";
	}
}
