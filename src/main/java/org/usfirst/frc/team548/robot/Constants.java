package org.usfirst.frc.team548.robot;

public class Constants {
	//Xbox
	public static final int XB_POS_DRIVER = 0;
	public static final int XB_POS_MANIP = 1;
	//DriveTrain 
	public static final int DT_TALONID_RIGHTFRONT = 4;
	public static final int DT_TALONID_RIGHTBACK = 5;
	public static final int DT_TALONID_RIGHTMINI = 6;
	public static final int DT_TALONID_LEFTFRONT = 8;
	public static final int DT_TALONID_LEFTBACK = 7;
	public static final int DT_TALONID_LEFTMINI = 9;
	public static final int DT_SOLENOID_SHIFTER = 7;
	public static final boolean DT_SHIFT_HIGH = true;
	public static final double DT_DRIVE_STRAIGHT = 1.20;
	public static final double DT_WIGGLE_POWER = .25;
	
	//Climber
	public static final int CLIMB_TALONID_CLIMBTALON1 = 3;
	public static final int CLIMB_TALONID_CLIMBTALON2 = 10;
	public static final int CLIMB_SOL_PORT = 6;
	
	//Shooter
	public static final int SHOOT_TALONID_TALONRIGHT= 15;
	public static final int SHOOT_TALONID_TALONLEFT= 14;
	public static final int SHOOT_TALONID_TALONELEVATOR = 13;
	public static final int SHOOT_TALONID_TALONCONVA = 16;
	public static final double SHOOT_AUTON_SPEED = .3;//4270
	public static final int SHOOT_MIDDLE_AUTON_SPEED = 3850;
	public static final int SHOOT_FAR_SIDE_AUTON_SPEED = 2000;//
	public static final int SHOOT_CLOSE_SIDE_AUTON_SPEED= 3333;
	public static final double SHOOT_PID_P = .005;
	public static final double SHOOT_PID_I = 0.00005;
	public static final double SHOOT_PID_D = 0;
	public static final double SHOOT_PID_F = 0.264957;
	public static final int SHOOT_PID_IZONE = 200;
	
	//Gear Ingestor
	public static final int GEARING_TALONID_ARM = 1;
	public static final int GEARING_TALONID_ROLLER = 12;
	public static final int GEAR_SOL_PORT = 5;
	public static final double GEARING_MAX = 0;
	public static final double GEARING_MIN = -2350;
	public static final double GEARING_PEGHEIGHT  = -740;  //800
	public static final double GEARING_ZERO =  0.8915750915750916;
	
	
	//Auto
	public static final double AUTO_SIDE_DRIVE_DISTANCE = 7.1;
}