package org.usfirst.frc.team548.robot;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.SerialPort;

public class DriveTrain implements PIDOutput {
	private static DriveTrain instance;

	public static DriveTrain getInstance() {
		if (instance == null)
			instance = new DriveTrain();
		return instance;
	}

	private static CANTalon rightFront, rightBack, rightMini, leftFront, leftBack, leftMini;
	private static Solenoid shifter;
	public static AHRS hyro;
	private static AnalogInput pressure;
	private static PIDController pid;

	private DriveTrain() {
		rightFront = new CANTalon(Constants.DT_TALONID_RIGHTFRONT);
		rightBack = new CANTalon(Constants.DT_TALONID_RIGHTBACK);
		rightMini = new CANTalon(Constants.DT_TALONID_RIGHTMINI);
		leftFront = new CANTalon(Constants.DT_TALONID_LEFTFRONT);
		leftBack = new CANTalon(Constants.DT_TALONID_LEFTBACK);
		leftMini = new CANTalon(Constants.DT_TALONID_LEFTMINI);
		shifter = new Solenoid(Constants.DT_SOLENOID_SHIFTER);
		leftFront.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		rightMini.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		hyro = new AHRS(SerialPort.Port.kMXP);
		pressure = new AnalogInput(0);
		pid = new PIDController(0.017d, 0, 0, hyro, this);
		LiveWindow.addActuator("Turning", "pid", pid);
		pid.setOutputRange(-.7, 0.7);

	}

	public static void drive(double leftPower, double rightPower) {
		rightFront.set(-rightPower);
		rightBack.set(-rightPower);
		rightMini.set(-rightPower);
		leftFront.set(leftPower);
		leftBack.set(leftPower);
		leftMini.set(leftPower);
	}

	public static void stop() {
		drive(0, 0);
	}
	
	public static void arcadeDrice(double fwd, double tur) {
		if (Math.abs(tur) < .01)
			tur = 0;
		if (Math.abs(fwd) < .2)
			fwd = 0;
		drive(Utils.ensureRange(fwd + tur, -1d, 1d), Utils.ensureRange(fwd - tur, -1d, 1d));
	}
	
	
	
	public static void shiftHigh(boolean b) {
		shifter.set(b == Constants.DT_SHIFT_HIGH);
	}

	public static boolean isHigh() {
		return shifter.get() == Constants.DT_SHIFT_HIGH;
	}

	public static double getAngle() {
		return hyro.getAngle();
	}

	public static void restHyro() {
		hyro.reset();
	}

	public static void moveDistance(double distance, double power, double threshold){
		if(Math.abs(DriveTrain.averageDistance()) < distance) { 
			DriveTrain.drive(power, power);
		} else DriveTrain.drive(0, 0);
	}

	public static double getPressure() {
		return (250d * (pressure.getVoltage() / 5d)) - 25d;
	}

	public static void breakMode(boolean b) {
		rightFront.enableBrakeMode(b);
		rightBack.enableBrakeMode(b);
		rightMini.enableBrakeMode(b);
		leftFront.enableBrakeMode(b);
		leftBack.enableBrakeMode(b);
		leftMini.enableBrakeMode(b);
	}

	public void pidWrite(double output) {
		// . 0.006 P
		if (Math.abs(pid.getError()) < 5d) {
			pid.setPID(pid.getP(), .001, 0);
		} else {
			// I Zone
			pid.setPID(pid.getP(), 0, 0);
		}
		drive(output, -output);
	}

	public static void disablePID() {
		pid.reset();
	}

	public static double getRightEncoderDistance() {
		return rightMini.getPosition();
	}

	public static double getLeftEncoderDistance() {
		return -leftFront.getPosition();
	}

	public static double averageDistance() {
		return (getLeftEncoderDistance() + getRightEncoderDistance()) / 2d;
	}

	public static double getLeftSpeed() {
		return -leftFront.getSpeed();
	}

	public static double getRightSpeed() {
		return rightMini.getSpeed();
	}
	
	
	public static void restEncoders() {
		leftFront.setPosition(0);
		rightMini.setPosition(0);
	}

	public static void turnToAngle(double angle) {
		pid.setSetpoint(angle);
		if (!pid.isEnabled()) {
			pid.reset();
			pid.enable();
		}
	}
	
	public static void humanDrive(double left, double right){
		if(Math.abs(left) < .2 && Math.abs(right) < .2){
			left = 0;
			right = 0;
		}
		drive(left, right);
	}

	public static void driveStraight(double power) {
		if (power > 0) { //If the power is greater than zero. What this means is that if the robot is moving forward, it will run the block underneath it. http://letmegooglethat.com/?q=decision+making+structures+java
			if (getAngle() > Constants.DT_DRIVE_STRAIGHT) { //This is saying that if the angle that the gyroscope senses is greater than 1.2, then there are some changes that need to be made
				drive(power * .85, power * 1.15); //This makes it stop not going straight and makes it go straight
			} else if (getAngle() < -Constants.DT_DRIVE_STRAIGHT) {//This is what happens when the angle is on the other side of threshold; being -1.2, also known as left
				drive(power * 1.15, power * .85);//See, this is the same method being called as the one above, except the opposite sides are being reducded and added
			} else {//This is an else statement, please refer to this link once again: http://letmegooglethat.com/?q=decision+making+structures+java
				drive(power, power);//This is what happens if there is now nothing to change
			}//This is a bracket...
		} else { //This is the start of another decision making structure, please refer to the link above 
			if (getAngle() > Constants.DT_DRIVE_STRAIGHT) {////This is saying that if the angle that the gyroscope senses is greater than 1.2, then there are some changes that need to be made
				drive(power * 1.15, power * .85);//This makes it stop not going straight and makes it go straight
			} else if (getAngle() < -Constants.DT_DRIVE_STRAIGHT) {//This is what happens when the angle is on the other side of threshold; being -1.2, also known as left
				drive(power * .85, power * 1.15);////This is what happens when the angle is on the other side of threshold; being -1.2, also known as left
			} else {//This is an else statement 
				drive(power, power);//This is what happens if there is nothing to change because the robot is now moving in the correct direction
			}//bracket
		}//bracket
	}//bracket
	//space
}//bracket