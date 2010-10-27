package game;

// GENERIC THING
import java.awt.*;

public class Thing {
   public boolean held = false;
   protected boolean movable = false; //dont change in this class.
   protected Color color = Color.black;
   protected Color lineColor = Color.black;
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
      
   public void setPlatform(Platform platform) {
      this.platform = platform;
   }
   
   public Platform getPlatform() {
      return platform;
   }

   public boolean isInStagingArea(){
      double areaDivider = platform.getWidth() * 2.0 / 3.0;
      for(int i = 0; i < n; i++){
         if (X[i] < areaDivider) { return false; }
      }
      return true;
   }

   public boolean isInPlayArea(){
      double areaDivider = platform.getWidth() * 2.0 / 3.0;
      for(int i = 0; i < n; i++){
         if (X[i] > areaDivider) { return false; }
      }
      return true;
   }
   
   public void update(Graphics g) {
      updateShape();
      g.setColor(color);
      g.fillPolygon(polygon);
      g.setColor(lineColor);
      g.drawPolygon(polygon);
   }

   public double[] distanceTo(Thing other){ //Assumes that there will always be parallel lines between things
      double left    = getLeftEdge().distanceTo(   other.getRightEdge())   [0];
      double right   = getRightEdge().distanceTo(  other.getLeftEdge())    [0];
      double top     = getTopEdge().distanceTo(    other.getBottomEdge())  [1];
      double bottom  = getBottomEdge().distanceTo( other.getTopEdge())     [1];

      double[] minimumDistance = new double[] {left, top};
      if (Math.abs(right) < Math.abs(left)){
         minimumDistance[0] = right;
      }
      if (Math.abs(bottom) < Math.abs(top)){
         minimumDistance[1] = bottom;
      }
      return minimumDistance;
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

   public LineSegment getSingleEdge(String location) {
      LineSegment[] segments = getLineSegments();
      LineSegment result = null;
      for(int i = 0; i < segments.length; i++){
         LineSegment seg = segments[i];
         if(location == "left" && seg.isVertical()){
            if(result == null || seg.x1 < result.x1) { result = seg; }
         } else if(location == "right" && seg.isVertical()){
            if(result == null || seg.x1 > result.x1) { result = seg; }
         } else if(location == "bottom" && seg.isHorizontal()){
            if(result == null || seg.y1 > result.y1) { result = seg; }
         } else if(location == "top" && seg.isHorizontal()){
            if(result == null || seg.y1 < result.y1) { result = seg; }
         }
      }
      return result;
   }

   public LineSegment getLeftEdge(){ return getSingleEdge("left"); }
   public LineSegment getRightEdge(){ return getSingleEdge("right"); }
   public LineSegment getBottomEdge(){ return getSingleEdge("bottom"); }
   public LineSegment getTopEdge(){ return getSingleEdge("top"); }

   public boolean contains(int x, int y) {
      updateShape();
      return polygon.contains(x, y);
   }

   public boolean contains(Thing contained) {
      updateShape();
      for(int i = 0; i < n; i++) {
         if (!polygon.contains(X[i], Y[i])) return false;
      }
      return true;
   }

   public boolean isContainedBy(Thing container) {
      return container.contains(this);
   }

   public boolean mouseDown(int x, int y) {
			System.out.println("click");
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

   public void updateShape() {
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
