package org.usfirst.frc.team548.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Solenoid;

public class Climber {
	private static Climber instance;
	
	public static Climber getInstance() {
		if(instance == null) instance = new Climber();
		return instance;
	}
	
	private static CANTalon climbTalon1, climbTalon2;
	private static Solenoid sol;
	
	private Climber() {
		climbTalon1 = new CANTalon(Constants.CLIMB_TALONID_CLIMBTALON1);
		climbTalon2 = new CANTalon(Constants.CLIMB_TALONID_CLIMBTALON2);
		sol = new Solenoid(Constants.CLIMB_SOL_PORT);
	}
	
	public static void setPower(double power) {
		climbTalon1.set(power);
		climbTalon2.set(-power);
	}///
	
	public static void setClimbOpen(boolean b) {
		sol.set(b);
	}
	
	public static boolean isOpen() {
		return sol.get();
	}
	
}