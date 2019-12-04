package org.usfirst.frc.team548.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
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

	private static TalonSRX rightFront, rightBack, rightMini, leftFront, leftBack, leftMini;
	private static Solenoid shifter;
	public static AHRS hyro;
	private static AnalogInput pressure;
	private static PIDController pid;

	private DriveTrain() {
		rightFront = new TalonSRX(Constants.DT_TALONID_RIGHTFRONT);
		rightBack = new TalonSRX(Constants.DT_TALONID_RIGHTBACK);
		rightMini = new TalonSRX(Constants.DT_TALONID_RIGHTMINI);
		leftFront = new TalonSRX(Constants.DT_TALONID_LEFTFRONT);
		leftBack = new TalonSRX(Constants.DT_TALONID_LEFTBACK);
		leftMini = new TalonSRX(Constants.DT_TALONID_LEFTMINI);
		shifter = new Solenoid(Constants.DT_SOLENOID_SHIFTER);
		leftFront.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,10);
		rightMini.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,10);
		hyro = new AHRS(SerialPort.Port.kMXP);
		pressure = new AnalogInput(0);
		pid = new PIDController(0.017d, 0, 0, hyro, this);
		LiveWindow.addActuator("Turning", "pid", pid);
		pid.setOutputRange(-.7, 0.7);

	}

	public static void drive(double leftPower, double rightPower) {
		rightFront.set(ControlMode.PercentOutput, -rightPower);
		rightBack.set(ControlMode.PercentOutput, -rightPower);
		rightMini.set(ControlMode.PercentOutput, -rightPower);
		leftFront.set(ControlMode.PercentOutput, leftPower);
		leftBack.set(ControlMode.PercentOutput, leftPower);
		leftMini.set(ControlMode.PercentOutput, leftPower);
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
		rightFront.setNeutralMode(NeutralMode.Brake);
		rightBack.setNeutralMode(NeutralMode.Brake);
		rightMini.setNeutralMode(NeutralMode.Brake);
		leftFront.setNeutralMode(NeutralMode.Brake);
		leftBack.setNeutralMode(NeutralMode.Brake);
		leftMini.setNeutralMode(NeutralMode.Brake);
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
		return rightMini.getSelectedSensorPosition();
	}

	public static double getLeftBackMotor() {
		return leftBack.getTemperature();
	}

	public static double getRightBackMotor() {
		return rightBack.getTemperature();
	}
	
	public static double getLeftFrontMotor() {
		return leftFront.getTemperature();
	}
	
	public static double getRightFrontMotor() {
		return rightFront.getTemperature();
	}

	public static double getRightFrontMotorC() {
		return rightFront.getOutputCurrent();
	}

	public static double getLeftFrontMotorC() {
		return leftFront.getOutputCurrent();
	}

	public static double getRightBackMotorC() {
		return rightBack.getOutputCurrent();
	}

	public static double getLeftBackMotorC() {
		return leftBack.getOutputCurrent();
	}

	public static double getRightFrontMotorV() {
		return rightFront.getMotorOutputVoltage();
	}

	public static double getLeftFrontMotorV() {
		return leftFront.getMotorOutputVoltage();
	}
	public static double getRightBackMotorV() {
		return rightBack.getMotorOutputVoltage();
	}
	public static double getLeftBackMotorV() {
		return leftBack.getMotorOutputVoltage();
	}





	public static double getLeftEncoderDistance() {
		return -leftFront.getSelectedSensorPosition();
	}

	public static double averageDistance() {
		return (getLeftEncoderDistance() + getRightEncoderDistance()) / 2d;
	}

	public static double getLeftSpeed() {
		return -leftFront.getSelectedSensorVelocity();
	}

	public static double getRightSpeed() {
		return rightMini.getSelectedSensorVelocity();
	}
	
	
	public static void restEncoders() {
		leftFront.setSelectedSensorPosition(0);
		rightMini.setSelectedSensorPosition(0);
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