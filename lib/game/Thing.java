package game;

// GENERIC THING
import java.awt.*;

public class Thing {
   public boolean held = false;
   protected boolean movable = false; //dont change in this class.
   Color color = Color.black;
   Color lineColor = Color.black;
   protected double X[] = new double[100];
   protected double Y[] = new double[100];
   int n = 0;
	public int width;
	public int height;
   int IX[] = new int[100];
   int IY[] = new int[100];
   protected double x = 0, y = 0, mx = 0, my = 0;
   double moveX = 0, moveY = 0;

   Polygon polygon = null;
   boolean needToUpdateShape = false;
   Platform platform;
      
   public void setPlatform(Platform platform) {
      this.platform = platform;
   }
   
   public Platform getPlatform() {
      return platform;
   }

   public boolean isInStagingArea(){
      return platform.stagingArea.contains(this);
   }

   public boolean isInPlayArea(){
      return platform.playArea.contains(this);
   }
   
   public void update(Graphics g) {
      updateShape();
      g.setColor(color);
      g.fillPolygon(polygon);
      g.setColor(lineColor);
      g.drawPolygon(polygon);
   }

	public double distance(Thing other){
		double result = 0;
		
		//if(other.getX() < this.getX()) result += this.getX() - width - other.getX() + other.width;
		//else result += other.getX() - other.width - this.getX()+width;
		if(other.getY() < this.getY()) result += this.getTopY() - other.getBottomY();
		if(other.getY() > this.getY()) result += other.getTopY() - this.getBottomY();
		
		return result;
	}


   public LineSegment[] getLineSegments() {
      LineSegment[] segments = new LineSegment[n];
      for(int i = 0; i < n-1; i++){
         double[] point1 = new double[] {X[i],Y[i]};
         double[] point2 = new double[] {X[i+1],Y[i+1]};
         segments[i] = new LineSegment(point1, point2);
      }
      segments[n-1] = new LineSegment(new double[]{X[n-1],Y[n-1]}, new double[] {X[0],Y[0]}); //The wrap around case
      return segments;
   }

   public boolean contains(int x, int y) {
      updateShape();
      return polygon.contains(x, y);
   }

   public boolean contains(Thing contained) {
      updateShape();
      for(int i = 0; i < n; i++) {
         System.out.println(polygon.contains(X[i], Y[i]));
         if (!polygon.contains(X[i], Y[i])) return false;
      }
      return true;
   }

   public boolean isContainedBy(Thing container) {
      return container.contains(this);
   }

   public boolean mouseDown(int x, int y) {
      mx = x;
      my = y;
      this.held = true;
      return false;
   }

   public boolean mouseDrag(int x, int y) {
      setX(this.x + x - mx);
      setY(this.y + y - my);
      mx = x;
      my = y;
      return false;
   }

   public boolean mouseUp(int x, int y) { //TODO: May need to expand functionality
      this.held = false;
      return false;
   }

   public boolean keyUp(int key) {
      return false;
   }

   public boolean keyDown(int key) {
      return false;
   }

   public void setColor(Color color) {
      this.color = color;
   }
   public void setLineColor(Color color){
     this.lineColor = color;
   }

	public double getLeftX(){return x - width/2;}
	public double getRightX(){return x + width/2;}
	public double getTopY(){return y - height/2;}
	public double getBottomY(){return y + height/2;}

   public double getX() { return x; }
   public double getY() { return y; }

   public void setX(double x) {
      moveX += x - this.x;
      this.x = x;
      needToUpdateShape = true;
   }

   public void setY(double y) {
      moveY += y - this.y;
      this.y = y;
      needToUpdateShape = true;
   }

   public void setShape(double X[], double Y[], int n) {
      this.n = n;
      for (int i = 0 ; i < n ; i++) {
         this.X[i] = X[i];
         this.Y[i] = Y[i];
      }
      needToUpdateShape = true;
   }

   void updateShape() {
      if (needToUpdateShape) {
         for (int i = 0 ; i < n ; i++) {
            X[i] += moveX;
            Y[i] += moveY;
            IX[i] = (int)X[i];
            IY[i] = (int)Y[i];
         }
         moveX = moveY = 0;
         polygon = new Polygon(IX, IY, n);
         needToUpdateShape = false;
      }
   }

}
