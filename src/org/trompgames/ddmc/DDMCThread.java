package org.trompgames.ddmc;

public class DDMCThread extends Thread{
	
	public DDMCHandler handler;
	public double fps;
	public double fpms;
	public long time;

	
	public DDMCThread(DDMCHandler handler, double fps) {
		this.handler = handler;
		this.fps = fps;
		this.fpms = 1000.0 / fps;
	}
	
	@Override
	public void run() {
		
		time = System.currentTimeMillis();
		while(true) {
			
			if(!(time + fpms < System.currentTimeMillis())) continue;
			
			handler.update();
			
			handler.getFrame().getPanel().repaint();

			time = System.currentTimeMillis();
			
		}
		
	}
	
	public long getLastTime() {
		return time;
	}
	
}
