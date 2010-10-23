package game;

// GENERIC THING
import java.awt.*;

public class Thing
{
	public boolean held = false;
	protected boolean movable = false; //dont change in this class.
   public void setPlatform(Platform platform) {
      this.platform = platform;
   }

   public void update(Graphics g) {
      updateShape();
      g.setColor(color);
      g.fillPolygon(polygon);
      g.setColor(lineColor);
      g.drawPolygon(polygon);
   }
	
	public int distanceTo(Thing other){
		//returns the distance between the edges of the two objects.
		return 999;
	}

   public boolean contains(int x, int y) {
      updateShape();
      return polygon.contains(x, y);
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

   public boolean mouseUp(int x, int y) {
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

   Color color = Color.black;
   Color lineColor = Color.black;
   protected double X[] = new double[100];
   protected double Y[] = new double[100];
   int n = 0;

   int IX[] = new int[100];
   int IY[] = new int[100];
   protected double x = 0, y = 0, mx = 0, my = 0;
   double moveX = 0, moveY = 0;

   Polygon polygon = null;
   boolean needToUpdateShape = false;
   Platform platform;
}

