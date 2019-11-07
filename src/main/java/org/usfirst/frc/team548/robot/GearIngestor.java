package org.usfirst.frc.team548.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.FeedbackDeviceStatus;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Timer;

public class GearIngestor {

	private static GearIngestor instance;
	private static CANTalon arm, roller, arm2;
	private static boolean currentLimiting = false, startedTimer = false;
	private static Timer currentTimer;
	
	public static GearIngestor getInstance() {
		if(instance == null) instance= new GearIngestor();
		return instance;
	}
	
	private GearIngestor() {
		
		arm = new CANTalon(Constants.GEARING_TALONID_ARM); //encoder
		//arm2 = new CANTalon(12);
		
		arm.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		arm.changeControlMode(TalonControlMode.Position);
		
		//arm.reverseOutput(true);
		arm.setPID(1, 0.0001, 0);
	
		arm.enableLimitSwitch(false, false);
		roller = new CANTalon(Constants.GEARING_TALONID_ROLLER);
	//	arm2.changeControlMode(TalonControlMode.Follower);
	//	arm2.set(arm.getDeviceID());
		currentTimer = new Timer();
	}
	
	
	public static void rollerIngestCurrentLimiting() {
		if(!currentLimiting) {
			if(!startedTimer && Robot.PDP.getCurrent(4) > 15) {
				startedTimer = true;
				currentTimer.reset();
				currentTimer.start();
			} else if(Robot.PDP.getCurrent(4) < 10 && startedTimer) {
				startedTimer = false;
				currentTimer.stop();
			} else if(currentTimer.get() > .25 && startedTimer) {
				currentLimiting = true;
				startedTimer = false;
				//roller.set(p*.25);
			} 
			roller.set(-.7);
		} else {
			roller.set(-.25);
			if(Robot.PDP.getCurrent(4) < 1) 
				currentLimiting = false;
		}
	}
	
	public static void setRollerBarPower(double p) {
		roller.set(p);
	}
	
	public static void stopRoller() {
		setRollerBarPower(0);
		
	}
	
	public static void stopArm() {
		setArmPower(0);}
	
	public static void setArmPos(double pos) {
		if(isEncConnected()) {
			arm.changeControlMode(TalonControlMode.Position);
			arm.set(pos);
		} else {
			stopArm();
		}
			
	}

	
	public static boolean isEncConnected() {
		return arm.isSensorPresent(FeedbackDevice.CtreMagEncoder_Relative) == FeedbackDeviceStatus.FeedbackStatusPresent;
	}
	
	public static void setArmPower(double power) {
		arm.changeControlMode(TalonControlMode.PercentVbus);
		arm.set(power);
	}
	public static double getArmPos() {
		return arm.getEncPosition();
	}
	
	public static double getAbsPos() {
		return (arm.getPulseWidthPosition() & 0xFFF)/4095d;
	}
	
	public static boolean isGearInIngestor() {
		return currentLimiting;
	}
	
	public static void setArmEncPos(int pos) {
		arm.setEncPosition(pos);
	}
	
	public static void setArmOffSet() {
		if(Constants.GEARING_ZERO >= .7) {
			setArmEncPos(-(int)((Constants.GEARING_ZERO-(getAbsPos()%1))*4095));
		} else {
			if(getAbsPos()%1 >= Constants.GEARING_ZERO && Math.abs(Constants.GEARING_ZERO-getAbsPos()%1) < .1) { //When the arm is too far back
				setArmEncPos((int)(((getAbsPos()%1)-Constants.GEARING_ZERO)*4095));
			} else if(getAbsPos()%1 < Constants.GEARING_ZERO) {
				setArmEncPos(-(int)((Constants.GEARING_ZERO-(getAbsPos()%1))*4095));//When arm is below zero(off) but has not passed zero
			} else {
				setArmEncPos(-(int)((Constants.GEARING_ZERO+(1-(getAbsPos()%1)))*4095));//When value has passed zero
			}
		}
		//setArmEncPos((int)((locSub((getAbsPos()%1), Constants.GEARING_ZERO))*4095));
		//setArmEncPos((int)(((getAbsPos()%1)-Constants.GEARING_ZERO)*4095));
	}
	
	private static double locSub(double v, double c) {
		if (v - c > 0) {
			return v - c;
		} else {
			return (1 - c) + v;
		}
	}
}