public class Timer implements Runnable {

	private static double seconds = 0;
	private boolean running;
	private Thread timer;
	
	
	
	

	@Override
	public void run() {
		
		while (running) {
			
			seconds++;
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				
			}
		}
			
	}
	
	public void startTimer(){
		running =true;
		timer = new Thread(this);
		timer.start();
	}

	public void reset() {
		seconds = 0;
		running = false;
		try {
			timer.interrupt();
			timer.join();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public double getMilliseconds(){
		return  (seconds);
	}

	public double getMintues() {
		return (seconds / 60000.0);
	}

	public double getSeconds() {
		return (seconds/1000.0);
	}

	public void setSeconds(double secondsa) {
		seconds = secondsa;
	}
	
	
	
	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public Thread getTimer() {
		return timer;
	}

	public void setTimer(Thread timer) {
		this.timer = timer;
	}


}
