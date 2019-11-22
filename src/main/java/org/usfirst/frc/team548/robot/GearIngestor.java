package org.usfirst.frc.team548.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANPIDController;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;

public class GearIngestor {

	private static GearIngestor instance;
	private static PIDController IngestorPIDController;
	private static TalonSRX arm, roller, arm2;
	private static boolean currentLimiting = false, startedTimer = false;
	private static Timer currentTimer;
	
	public static GearIngestor getInstance() {
		if(instance == null) instance= new GearIngestor();
		return instance;
	}
	
	private GearIngestor() {
		
		arm = new TalonSRX(Constants.GEARING_TALONID_ARM); //encoder
		//arm2 = new TalonSRX(12);
		
		arm.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,10);

	
		//arm.reverseOutput(true);
		arm.config_kP(0, 1, 0);
		arm.config_kI(0, .0001, 0);
		arm.config_kD(0, 0, 0);

	


		//arm.enableLimitSwitch(false, false);
		roller = new TalonSRX(Constants.GEARING_TALONID_ROLLER);
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
			roller.set(ControlMode.PercentOutput,-.7);
		} else {
			roller.set(ControlMode.PercentOutput,-.25);
			if(Robot.PDP.getCurrent(4) < 1) 
				currentLimiting = false;
		}
	}
	
	public static void setRollerBarPower(double p) {
		roller.set(ControlMode.PercentOutput,p);
	}
	
	public static void stopRoller() {
		setRollerBarPower(0);
		
	}
	
	public static void stopArm() {
		setArmPower(0);
	}
	
	public static void setArmPos(double pos) {
		arm.set(ControlMode.Position, pos);
	}

	
	/*public static boolean isEncConnected() {
		return arm.isSensorPresent(FeedbackDevice.CTRE_MagEncoder_Relative) == FeedbackDevice.FeedbackStatusPresent;
	}*/
	
	public static void setArmPower(double power) {
		arm.set(ControlMode.PercentOutput,power);
	}
	public static double getArmPos() {
		return arm.getSelectedSensorPosition();
	}
	
	/*public static double getAbsPos() {
		return (arm.pulse & 0xFFF)/4095d;
	}*/
	
	public static boolean isGearInIngestor() {
		return currentLimiting;
	}
	
	public static void setArmEncPos(int pos) {
		arm.setSelectedSensorPosition(pos, 0, 10);;
	}
	
	/*public static void setArmOffSet() {
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
	}*/
	
	private static double locSub(double v, double c) {
		if (v - c > 0) {
			return v - c;
		} else {
			return (1 - c) + v;
		}
	}
}