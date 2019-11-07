package org.usfirst.frc.team548.robot.AutoCommands;

import org.usfirst.frc.team548.robot.Constants;
import org.usfirst.frc.team548.robot.GearIngestor;

public class ArmGearExgest extends AutoCommandBase {

	public ArmGearExgest(double timeOut) {
		super(timeOut);
		
	}

	@Override
	public void init() {
		
	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		GearIngestor.setArmPos(Constants.GEARING_MIN);
		GearIngestor.setRollerBarPower(-.7);
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub
		GearIngestor.stopArm();
		GearIngestor.stopRoller();

	}

	@Override
	protected String getCommandName() {
		// TODO Auto-generated method stub
		return null;
	}

}
