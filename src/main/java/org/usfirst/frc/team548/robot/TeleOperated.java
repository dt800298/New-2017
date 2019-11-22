package org.usfirst.frc.team548.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TeleOperated {

	private static TeleOperated instance;
	private static Timer timer;
	private static int wiggle = 0;
	private static boolean yPressed = false, dPressed = false, timerStart = false, povPressed = false,
			gearArmOffsetPress = false;

	public static XBoxController driver;
	public static XBoxController manip;
	private static double gearOffset = 0;

	public static TeleOperated getInstance() {
		if (instance == null)
			instance = new TeleOperated();
		return instance;
	}
//test
	private TeleOperated() {
		driver = new XBoxController(Constants.XB_POS_DRIVER);
		manip = new XBoxController(Constants.XB_POS_MANIP);
		timer = new Timer();
	}

	public static void run() {
		/**
		 * Driver
		 */

		

		// change codde here

		if (driver.getAButton()) {
			if (wiggle < 4) {
				DriveTrain.drive(-Constants.DT_WIGGLE_POWER, -Constants.DT_WIGGLE_POWER);
			} else if (wiggle >= 4) {
				DriveTrain.drive(Constants.DT_WIGGLE_POWER, Constants.DT_WIGGLE_POWER);
			}
			wiggle++;
			if (wiggle > 8)
				wiggle = 0;
		} else if (driver.getXButton()) {
			DriveTrain.drive(.15, .15);
		} else if (driver.getYButton()) {

			if (!dPressed) {
				DriveTrain.restEncoders();
				dPressed = true;
			}
			DriveTrain.moveDistance(.14, .12, 0);

		} else {
			dPressed = false;
			DriveTrain.arcadeDrice(driver.getRightStickYAxis(), Utils.negPowTwo(driver.getLeftStickXAxis()));
		}
		if (DriverStation.getInstance().isBrownedOut())
			driver.setRightRumble(1);
		else
			driver.setRightRumble(0);

		DriveTrain.shiftHigh(driver.getRightBumper());

		/**
		 * Manip
		 */
		
		
		if (Math.abs(manip.getRightStickYAxis()) > .2)
			Climber.setPower(-Math.abs(manip.getRightStickYAxis()));
		else
			Climber.setPower(0);
		if (!manip.getBButton()) {
			if (manip.getRightBumper()) {
				manip.setLeftRumble(0);
				driver.setLeftRumble(0);
				GearIngestor.setRollerBarPower(.7d);
			} else if (GearIngestor.getArmPos() < Constants.GEARING_MIN + 300) {
				GearIngestor.rollerIngestCurrentLimiting();
				if (GearIngestor.isGearInIngestor()) {
					manip.setLeftRumble(1);
					driver.setLeftRumble(1);

				} else {
					manip.setLeftRumble(0);
					driver.setLeftRumble(0);
				}
				USBLED.isHasGear(GearIngestor.isGearInIngestor());
			} else if (manip.getPOV() == 90) {
				GearIngestor.rollerIngestCurrentLimiting();
				if (GearIngestor.isGearInIngestor()) {
					manip.setLeftRumble(1);
					driver.setLeftRumble(1);
				}
			} else {
				USBLED.isHasGear(false);
				manip.setLeftRumble(0);
				driver.setLeftRumble(0);
				GearIngestor.setRollerBarPower(0);
			}

			// ARM
			if (Math.abs(manip.getLeftStickYAxis()) > .2) {
				GearIngestor.setArmPower(-manip.getLeftStickYAxis() * .3);
			} else if (manip.getYButton()) {
				GearIngestor.setArmPos(Constants.GEARING_MAX - 200);
			} else if (manip.getRightTriggerButton()) {
				//GearIngestor.setRollerBarPower(.7d); //I ADDED THIS
				GearIngestor.setArmPos(Constants.GEARING_MIN);
			}
			
			else if (manip.getXButton()) {
				if (!timerStart) {
					timerStart = true;
					timer.reset();
					timer.start();
				}

				GearIngestor.setArmPos(Constants.GEARING_MIN);// Go down
				GearIngestor.setRollerBarPower(-.7d);// Spit out gear
				if (timer.get() > .5) {
					DriveTrain.drive(-.620, -.620);
				} else {
					DriveTrain.stop();
				}
			}
			
			else if (DriveTrain.isHigh()) {
				GearIngestor.setArmPos(Constants.GEARING_MAX);
			} else if (manip.getStartButton()) {
				if (wiggle < 3) {
					GearIngestor.setArmPower(-.5);
				} else if (wiggle >= 3) {
					GearIngestor.setArmPower(.5);
				}
				wiggle++;
				if (wiggle > 6)
					wiggle = 0;
			} else {
				GearIngestor.setArmPos(Constants.GEARING_PEGHEIGHT + gearOffset);
			}
		} else {
			GearIngestor.setArmPos(Constants.GEARING_MIN);// Go down
			GearIngestor.setRollerBarPower(-.7d);// Spit out gear
		}

		if (Climber.isOpen())
			manip.setRightRumble(1);
		else
			manip.setRightRumble(0);

		if (manip.getRightJoystickButton()) {
			Climber.setClimbOpen(true);
		} else if (manip.getPOV() == 270) {
			Climber.setClimbOpen(false);
		}

		if (manip.getBackButton())
			gearOffset = 0;

		if (manip.getLeftTriggerButton()) {
			Shooter.injectAfterSpeed(Constants.SHOOT_AUTON_SPEED);
		} else if (manip.getLeftBumper()) {
			Shooter.setConvPower(-.5);
			Shooter.setElevator(-.7);
		}

		else {
			Shooter.stop();
		}

		if (!gearArmOffsetPress && manip.getPOV() == 180) {
			gearOffset -= 20;
			gearArmOffsetPress = true;

		} else if (!gearArmOffsetPress && manip.getPOV() == 0) {
			gearOffset += 20;
			gearArmOffsetPress = true;
		} else {
			gearArmOffsetPress = false;
		}

		/**
		 * LEDS
		 */

		USBLED.isRftOut(Climber.isOpen());
		USBLED.isWantGear(manip.getAButton());
		// USBLED.isWombo(manip.getAButton());
		USBLED.isWombo(driver.getYButton());

		SmartDashboard.putData("HYRO", DriveTrain.hyro);
		if (driver.getAButton())
			DriveTrain.restHyro();
		SmartDashboard.putNumber("Hyro", DriveTrain.getAngle());
		SmartDashboard.putNumber("Speed", Shooter.getSpeed());
	}

	public static void init() {
		DriveTrain.breakMode(false);
	}

}