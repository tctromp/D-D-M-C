package org.trompgames.ddmc;

public class DDMCThread extends Thread{
	
	private DDMCHandler handler;
	private double fps;
	private double fpms;
	private long time;
	private long lastTime;

	
	public DDMCThread(DDMCHandler handler, double fps) {
		this.handler = handler;
		this.fps = fps;
		this.fpms = 1000.0 / fps;
	}
	
	@Override
	public void run() {
		
		System.out.println("FPMS: " + fpms);
		
		time = System.currentTimeMillis();
		
		while(true) {
			if(1.0 * System.currentTimeMillis() < time + fpms || handler.getFrame().getPanel().isUpdating()) continue;

			lastTime = time;

			handler.update();
			
			handler.getFrame().getPanel().repaint();

			
			
			//System.out.println(1.0 * (System.currentTimeMillis() - time));
			time = System.currentTimeMillis();
			
		}
		
	}
	
	public long getLastTime() {
		return lastTime;
	}
	
}
