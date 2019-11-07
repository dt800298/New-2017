package org.usfirst.frc.team548.robot.AutoCommands;


/**
 *
 * @author Alex
 */
public class Wait extends AutoCommandBase {

    public Wait(double timeOut) {
        super(timeOut);
    }

    @Override
    protected void run() {
        
    }     

    @Override
    public void end() {

    }

    @Override
    protected String getCommandName() {
        return "Wait";
    }

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
}
