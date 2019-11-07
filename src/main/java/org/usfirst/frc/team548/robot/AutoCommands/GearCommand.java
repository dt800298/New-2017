package org.usfirst.frc.team548.robot.AutoCommands;


public class GearCommand extends AutoCommandBase {
	
	private boolean open;
	
	public GearCommand(double timeOut, boolean b) {
		super(timeOut);
		open = b;
	}

	@Override
	public void init() {
		//TopGear.setOpen(open);

	}

	@Override
	protected void run() {
		// TODO Auto-generated method stub
		//TopGear.setOpen(open);
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub

	}

	@Override
	protected String getCommandName() {
		// TODO Auto-generated method stub
		return "Gear Command";
	}

}
