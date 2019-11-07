package org.usfirst.frc.team548.robot;

import org.usfirst.frc.team548.robot.AutoModes.*;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {
	
	AutoMode autonCommand;
	SendableChooser<AutoMode> austinChooser;
	
	public static PowerDistributionPanel PDP;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		DriveTrain.getInstance();
		Climber.getInstance();
		Shooter.getInstance();
		GearIngestor.getInstance();
		TeleOperated.getInstance();
		PDP = new PowerDistributionPanel();
		
		austinChooser = new SendableChooser<AutoMode>();
		austinChooser.addDefault("Middle Gear", new MiddleGear());
		austinChooser.addObject("Red Side Gear no driving", new SideGear(true));
		austinChooser.addObject("Blue Side Gear  no driving", new SideGear(false));
		austinChooser. addObject("Red alliance right side gear", new SideGearRightSideRed());
		austinChooser.addObject("Blue alliance right side gear", new SideGearRightSideBlue());
		austinChooser.addObject("Red alliance left side gear", new SideGearLeftSideRed2());
		austinChooser.addObject("Blue alliance left side gear", new SideGearLeftSideBlue());
		austinChooser.addObject("Shoot side gear blue alliance", new SideGearShoot(false));
		austinChooser.addObject("Shoot side gear red alliance. Line this one up backwards with shooter aimed at boiler", new SideGearShoot(true));
		austinChooser.addObject("Middle gear shoot red alliance", new MiddleGearAndShoot(true));
		austinChooser.addObject("Middle gear shoot blue alliance", new MiddleGearAndShoot(false));
		austinChooser.addObject("Shoot far side gear red alliance", new FarSideGearShoot(true));
		austinChooser.addObject("Shoot far side gear blue alliance", new FarSideGearShoot(false));
		//austinChooser.addObject("Middle gear with driving to the feeder station  red", new HershIdealMiddleGear(true));
		//austinChooser.addObject("Middle gear with driving to the feeder station  blue", new HershIdealMiddleGear(false));
		austinChooser.addObject("Shoot middle gear blue", new ShootAndMiddleGear(false));
		austinChooser.addObject("Shoot and middle gear red. Line this up backwards with shooter aimed at boiler", new ShootAndMiddleGear(true));
		austinChooser.addObject("shoot only", new OnlyShoot());
		
		SmartDashboard.putData("AUTO MODE", austinChooser);
		GearIngestor.setArmOffSet();
		USBLED.getInstance();
	}

	
	//true is red, false is blue
	@Override
	public void autonomousInit() {
		austinChooser.getSelected().start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		
	}

	public void teleopInit() {
		TeleOperated.init();
	}
	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		TeleOperated.run();
		SmartDashboard.putNumber("Arm pos", GearIngestor.getArmPos());
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		System.out.println(GearIngestor.getArmPos() + " "+GearIngestor.getAbsPos()+ " "+GearIngestor.isEncConnected());
		if (TeleOperated.driver.getAButton()) {
			GearIngestor.setArmPos(Constants.GEARING_MIN);
		}  else if(TeleOperated.driver.getBButton()) {
			GearIngestor.setArmPos(Constants.GEARING_MAX);
		}else if(TeleOperated.driver.getXButton()) {
			GearIngestor.setArmPos(Constants.GEARING_PEGHEIGHT);
		} else {
			if(Math.abs(TeleOperated.driver.getRightStickYAxis()) > .2) {
				GearIngestor.setArmPower(TeleOperated.driver.getRightStickYAxis());
			} else {
				GearIngestor.stopArm();
			}
		}
	}
	@Override
	public void disabledPeriodic() {
		TeleOperated.manip.setRightRumble(0);
		TeleOperated.manip.setLeftRumble(0);
		TeleOperated.driver.setRightRumble(0);
		TeleOperated.driver.setLeftRumble(0);
	}
}
