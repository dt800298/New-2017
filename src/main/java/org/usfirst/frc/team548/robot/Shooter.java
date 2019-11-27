package org.usfirst.frc.team548.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

public class Shooter {
	
private static Shooter instance;
	
	public static Shooter getInstance() {
		if(instance == null) instance = new Shooter();
		return instance;
	}
	
	private static TalonSRX talonLeft, talonRight, elvevator, conv;

	
	private Shooter() {
		talonLeft = new TalonSRX(Constants.SHOOT_TALONID_TALONLEFT); //Encoder
		talonRight = new TalonSRX(Constants.SHOOT_TALONID_TALONRIGHT);
		talonLeft.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,10);
		
		talonLeft.config_kI(0, Constants.SHOOT_PID_I, 0);
		talonLeft.config_kD(0, Constants.SHOOT_PID_D, 0);
		talonLeft.config_kF(0, Constants.SHOOT_PID_F, 0);
		conv = new TalonSRX(Constants.SHOOT_TALONID_TALONCONVA);
		talonRight.follow(talonLeft);
		elvevator = new TalonSRX(Constants.SHOOT_TALONID_TALONELEVATOR);
	}
	
	public static void setShooterPower(double p) {

		talonLeft.set(ControlMode.PercentOutput,p);
	}
	
	public static void setElevator(double p) {
		elvevator.set(ControlMode.PercentOutput,p);
	}
	
	public static void setShooterSpeed(double speed) {

		talonLeft.set(ControlMode.PercentOutput,speed);
	}
	
	public static void stop() {
		setShooterPower(0);
		setConvPower(0);
		setElevator(0);
	}
	
	public static void setConvPower(double p) {
		conv.set(ControlMode.PercentOutput,-p);
	}
	
	public static double getSpeed() {
		return talonLeft.getSelectedSensorVelocity();
	}
	
	public static void injectAfterSpeed(double speed) {
		setShooterSpeed(speed);
		if(Math.abs((int)(speed-getSpeed())) < .5){
			setElevator(1);
		} else {
			setElevator(0);
			setConvPower(.7);
		}
	}
	
	public static void addF(double a){

		talonLeft.config_kF(0, Constants.SHOOT_PID_F+a);
	}
	
}

