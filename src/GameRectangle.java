import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;

public class GameRectangle extends Rectangle {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private double urx, ury, llx, lly, lrx, lry;
	private Point point1, point2, point3, point4;
	
	public GameRectangle(int x, int y, int width, int height){
		
		super( x,  y, width , height);
		
		
		urx = x + width;
		ury = y;
		
		lrx =  urx;
		lry = y + height;
		
		
		llx = lrx - width;
		lly =  lry;
	
		
	
		
		point1 = new Point();
		point2 = new Point();
		point3 = new Point();
		point4 = new Point();
		
		point1.setLocation(x, y);
		point2.setLocation(urx, ury);
		point3.setLocation(lrx, lry);
		point4.setLocation(llx, lly);
		
	}

	
	
	public void  update (){
		urx = x + width;
		ury = y;
		
		lrx =  urx;
		lry = y + height;
		
		
		llx = lrx - width;
		lly =  lry;
	
		
		point1.setLocation(this.x, this.y);
		point2.setLocation(urx, ury);
		point3.setLocation(lrx, lry);
		point4.setLocation(llx, lly);
		
		
	}
	
	

	public Point getPoint1() {
		return point1;
	}

	public void setPoint1(Point point1) {
		this.point1 = point1;
	}

	public Point getPoint2() {
		return point2;
	}

	public void setPoint2(Point point2) {
		this.point2 = point2;
	}

	public Point getPoint3() {
		return point3;
	}

	public void setPoint3(Point point3) {
		this.point3 = point3;
	}

	public Point getPoint4() {
		return point4;
	}

	public void setPoint4(Point point4) {
		this.point4 = point4;
	}

	public double getUrx() {
		return urx;
	}

	public void setUppeRightX(double urx) {
		this.urx = urx;
	}

	public double getUppeRightY() {
		return ury;
	}

	public void setUppeRightY(double ury) {
		this.ury = ury;
	}

	public double getLowerLeftX() {
		return llx;
	}

	public void setLowerleftX(double llx) {
		this.llx = llx;
	}

	public double getLowerLeftY() {
		return lly;
	}

	public void setLowerLeftY(double lly) {
		this.lly = lly;
	}

	public double getLowerRightX() {
		return lrx;
	}

	public void setLowerRightX(double lrx) {
		this.lrx = lrx;
	}

	public double getLowerRightY() {
		return lry;
	}

	public void setLowerRightY(double lry) {
		this.lry = lry;
	}

	public double distance(int x, int x2, int y, int y2) {
		double point1 = (Math.pow((x2 - x), 2));
		double point2 = (Math.pow((y2 - y), 2));
		return (Math.sqrt((point1 + point2)));
	}

	

}
