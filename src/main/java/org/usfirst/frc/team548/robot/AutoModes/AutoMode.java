/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc.team548.robot.AutoModes;

import org.usfirst.frc.team548.robot.Constants;
import org.usfirst.frc.team548.robot.AutoCommands.*;


/**
 *
 * @author Alex
 */
public abstract class AutoMode {
    
    private String autoName;
    
    public AutoMode(String autoName) {
        this.autoName = autoName;
        System.out.println("AUTO: " + this.autoName + " has been initialized!");
    }
    
    public void start() {
        System.out.println("AUTO: " + this.autoName + " has started!");
        this.run();
        System.out.println("AUTO: " + this.autoName + " has ended!");
    }
    
    protected abstract void run();
    
    protected void waitTime(double seconds) {
        runCommand(new Wait(seconds));
    }
    
    protected void turnToAngleInTime(double seconds, double angle, boolean reset) {
        runCommand(new TurnToAngleInTime(seconds, angle, reset));
    }
    
    protected void driveInTime(double seconds, double power) {
        runCommand(new DriveInTime(seconds, power));
    }
    
    protected void driveDistance(double seconds, double power, double distance, double threshold, boolean gear) {
        runCommand(new DriveDistance(seconds, power, distance, threshold));
    }
    
    protected void driveDistanceWithArm(double seconds, double power, double distance, double threshold, double arm) {
        runCommand(new DriveDistanceWithArm(seconds, power, distance, threshold, arm));
    }
    
    protected void openGear(double seconds, boolean open) {
        runCommand(new GearCommand(seconds, open));
    }
    
    protected void shootAtSpeed(double seconds, double speed) {
        runCommand(new ShooterCommand(seconds, speed));
    }
    
    protected void wideTurn(double seconds, double left, double right) {
        runCommand(new WideTurnCommand(seconds, left, right));
    }
    /**
     * TIME MUST BE BIGGER THAN 1.5
     * @param sec
     */
    protected void GearRFT(double sec) {
    	runCommand(new GearRFTCommand(sec));
    }
    
    protected void GearIngestorExgeset(double sec) {
    	runCommand(new ArmGearExgest(sec));
    }
    
    protected void offsetPower(double seconds, double power, boolean isRed){
    	runCommand(new OffsetCommand(seconds, power, isRed));
    }
    
    private static void runCommand(AutoCommandBase command) {
        command.execute();
    }
    
    public String getName() {
    	return this.autoName;
    }
}