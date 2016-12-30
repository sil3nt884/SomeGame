import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Player {
	
	int level;
	int dex;
	int Int;
	int con;
	int str;
	int hp;
	double currexp =1;
	double neededexp = 300000;
	double neededexpmax = 300000;
	private Timer timer = new Timer ();
	int statpoints = 0;
	private GameRectangle rectPlayer ;
	int x, y;
	
	
	
	public  Player(int x ,int y){
		level=1;
		dex = 0;
		Int =0;
		con = 0;
		this.x=x;
		this.y=y;
		rectPlayer = new GameRectangle (x,y, 50, 50);
	}
	
	
	
	public void draw(Graphics2D g){
		g.draw(rectPlayer);
	}
	
	
	public void update(){
		rectPlayer.x = x;
		rectPlayer.y = y;
		rectPlayer.update();
	}
	
	
	public GameRectangle getRectPlayer() {
		return rectPlayer;
	}



	public void setRectPlayer(GameRectangle rectPlayer) {
		this.rectPlayer = rectPlayer;
	}



	public double levelPercent (){
		return (currexp/neededexpmax)* 100;
	}
	
	
	public void xpGathering(double currexp2, double neededexp2 , double d){
		double total =   (level *d);
		int  gatheredexp = (int) (currexp+ total);
		neededexp -= gatheredexp;
		currexp += gatheredexp;
		
		if(neededexp <= 0){
			level++;
			neededexpmax = (neededexpmax * level);
			neededexp = neededexpmax;
			currexp =1;
			statpoints += 10;
		}
		
		
	}
	
	public int getX() {
		return x;
	}



	public void setX(int x) {
		this.x = x;
	}



	public int getY() {
		return y;
	}



	public void setY(int y) {
		this.y = y;
	}



	public Timer getTimer() {
		return timer;
	}



	public void setTimer(Timer timer) {
		this.timer = timer;
	}



	public void addSTR(int statpoint){
		str += statpoint;
	}
	
	public void addINT(int statpoint){
		Int += statpoint;
	}
	
	public void addCON(int statpoint){
		con += statpoint;
	}
	
	public void addDEX(int statpoint){
		dex += statpoint;
	}
	
	
	
	
	public void moveUP(){
		y-=10;
		if(!timer.isRunning()){
			timer.startTimer();
		}
	
		
	}
	
	public void moveDown(){
		y+=10;
		if(!timer.isRunning()){
			timer.startTimer();
		}
		//System.out.println(timer.getSeconds());
	}
	
	public void moveLeft(){
		x+=-10;
		if(!timer.isRunning()){
			timer.startTimer();
		}
		//System.out.println(timer.getSeconds());
	}
	
	public void moveRight(){
		x+=10;
		if(!timer.isRunning()){
			timer.startTimer();
		}
		//System.out.println(timer.getSeconds());
	}
	
	public void playerStop(){
		timer.reset();
	}
	
	
	
}
