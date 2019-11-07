package org.usfirst.frc.team548.robot.AutoCommands;

import org.usfirst.frc.team548.robot.Constants;
import org.usfirst.frc.team548.robot.Shooter;

public class ShooterCommand extends AutoCommandBase {
	
	private double speed;

	public ShooterCommand(double timeOut, double speed) {
		super(timeOut);
		// TODO Auto-generated constructor stub
		
		this.speed  = speed;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		Shooter.injectAfterSpeed(speed);
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
