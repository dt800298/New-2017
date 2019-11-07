package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;

public class OnlyShoot extends AutoMode {

	public OnlyShoot() {
		super("Only Shoot");
	}

	@Override
	protected void run() {
		turnToAngleInTime(1.5,  77, false);
	}

}
