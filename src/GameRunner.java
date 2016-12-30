import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

public class GameRunner implements Runner, Runnable {

	private Thread gameThread = null;
	private volatile boolean running;
	private Canvas c = new Canvas();
	private BufferStrategy bs = null;
	private Graphics2D g = null;

	// Game Stuff
	long beginLoopTime;
	long endLoopTime;
	long currentUpdateTime = System.nanoTime();
	long lastUpdateTime;
	long deltaLoop;
	long desiredDeltaLoop = (1000 * 1000 * 1000) / 60;
	Player player1 = new Player(50,50);
	Player player2 = new Player(70, 70);
	Player player3 = new Player (200,200);
	

	@Override
	public void update() {
		beginLoopTime = System.nanoTime();

		render();

		lastUpdateTime = currentUpdateTime;
		currentUpdateTime = System.nanoTime();
		gameUpdate((int) ((currentUpdateTime - lastUpdateTime) / (1000 * 1000)));

		endLoopTime = System.nanoTime();
		deltaLoop = endLoopTime - beginLoopTime;

		if (deltaLoop > desiredDeltaLoop) {
			// Do nothing. We are already late.
		} else {
			try {
				Thread.sleep((desiredDeltaLoop - deltaLoop) / (1000 * 1000));
			} catch (InterruptedException e) {
				// Do nothing
			}
		}

	}

	@Override
	public void run() {
		while (running) {
			update();
		}

	}

	@Override
	public void gameStart() {
		initGameVariables();
		gameThread = new Thread(this);
		running = true;
		gameThread.start();
		System.out.println("Game Started");
	}

	private void initGameVariables() {

	}

	@Override
	public void gameStop() {

		if (gameThread != null) {
			try {
				System.out.println("Game stopping...");
				running = false;
				gameThread.interrupt();
				gameThread.join();
				gameThread = null;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Game Stopped");

	}

	public Canvas getC() {
		return c;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public void setC(Canvas c) {
		this.c = c;
	}

	public BufferStrategy getBs() {
		return bs;
	}

	public void setBs(BufferStrategy bs) {
		this.bs = bs;
	}

	public Graphics2D getG() {
		return g;
	}

	public void setG(Graphics2D g) {
		this.g = g;
	}

	@Override
	public void gameUpdate(int time) {
		player1.update();
		player2.update();
		player3.update();
		collisionDetection(player1.getRectPlayer(), player2.getRectPlayer(), player3.getRectPlayer());

	}
 
	private void collisionDetection(GameRectangle...e) {	
		for(int i =0 ; i<e.length; i++){
			for(int j = i +1; j<e.length; j++){
					
				
					//Upper left to x
					double d1 =  e[i].getPoint1().distance(e[j].getPoint1());
					double d2 =  e[i].getPoint1().distance(e[j].getPoint2());
					double d3  = e [i].getPoint1().distance(e[j].getPoint3());
					double  d4  =  e[i].getPoint1().distance(e[j].getPoint4());
		
					//Upper Right x 
					double d5 =  e[i].getPoint2().distance(e[j].getPoint1());
					double d6 =  e[i].getPoint2().distance(e[j].getPoint2());
					double d7  = e [i].getPoint2().distance(e[j].getPoint3());
					double  d8  =  e[i].getPoint2().distance(e[j].getPoint4());
					
					//lower right
					double d9 =  e[i].getPoint3().distance(e[j].getPoint1());
					double d10 =  e[i].getPoint3().distance(e[j].getPoint2());
					double d11  = e [i].getPoint3().distance(e[j].getPoint3());
					double  d12  =  e[i].getPoint3().distance(e[j].getPoint4());
					
					//lower left
					double d13 =  e[i].getPoint4().distance(e[j].getPoint1());
					double d14 =  e[i].getPoint4().distance(e[j].getPoint2());
					double d15  = e [i].getPoint4().distance(e[j].getPoint3());
					double  d16  =  e[i].getPoint4().distance(e[j].getPoint4());
					
					double [] distances  =   {d1,d2,d3,d4,d5,d6,d7,d8,d9,d10,d11,d12,d13,d14,d15,d16};
					int count = 16;
					for(int k = 0; k <distances.length; k++){
						if(distances[k] <= 10){
							//System.out.println(k+" true");
							count++;
						}
						else if (distances[k] > 10){
							count--;
							//System.out.println(k+" not true");
						}
					
					}
		
					if(count >0 || e[i].intersects(e[j])){
						System.out.println("hit");
					}
					
					
			}
		}
		
		
	}

	public void createBS() {
		bs = c.getBufferStrategy();

		if (bs == null) {
			c.createBufferStrategy(2);

			return;
		} else {
			return;
		}

	}

	public void KeysPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_Q) {
			gameStop();
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			gameStart();
		}

		// Player move
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			player1.moveUP();

		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			player1.moveDown();

		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			player1.moveLeft();

		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player1.moveRight();

		}

	}

	public void KeysReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {

			player1.playerStop();

		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {

			player1.playerStop();
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {

			player1.playerStop();
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

			player1.playerStop();
		}
	}

	@Override
	public void render() {
		createBS();
		if (bs != null) {
			g = (Graphics2D) bs.getDrawGraphics();
		} else {
			return;
		}
		if (g != null) {

			g.setColor(Color.WHITE);
			g.fillRect(0, 0, 500, 500);
			draw(g);
			bs.show();
			g.dispose();
			bs.dispose();
		}

	}

	private void draw(Graphics2D g2) {
		g2.setColor(Color.BLACK);
		player1.draw(g2);
		g2.setColor(Color.RED);
		player2.draw(g2);
		g2.setColor(Color.BLUE);
		player3.draw(g2);
	}

}
