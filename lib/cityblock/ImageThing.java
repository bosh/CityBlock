package cityblock;

import game.*;
import java.awt.*;
import java.awt.image.*;

public class ImageThing extends Thing implements ImageObserver{
	
	int mWidth, mHeight;
	Image mImage;
	public ImageThing(String path, int width, int height){
		this.mPolygon = false;
		mImage = Platform.platform.getImage(Platform.platform.getBase(), path);
		mWidth = width;
		mHeight = height;
	}
	public void update(Graphics g){
		g.drawImage(mImage, 0, 0, mWidth, mHeight, this);
		super.update(g);
	}
	public boolean imageUpdate(Image img, int a, int b, int c, int d, int e){
		return true;
	}
}