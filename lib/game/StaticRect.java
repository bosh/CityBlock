package game;

// A RECTANGLE SHAPED THING

public class StaticRect extends Thing
{
   public StaticRect(double x, double y, int width, int height) {
     this.width = width;
	this.height = height;
 		this.x = x + width / 2;
      this.y = y + height / 2;
      X[0] = x;
      Y[0] = y;
      X[1] = x + width;
      Y[1] = y;
      X[2] = x + width;
      Y[2] = y + height;
      X[3] = x;
      Y[3] = y + height;
      setShape(X, Y, 4);
   }
}

