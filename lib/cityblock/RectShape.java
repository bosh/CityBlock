package cityblock;
import java.awt.*;
import game.*;

public class RectShape extends cityblock.Shape{
	
	public RectShape(int w, int h){
		super(w, h);
		this.setColor(Color.blue);
		this.setLineColor(Color.blue);
	}
	
	public void setup(int x, int y){
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