package cityblock;
import game.*;
import java.util.*;
import java.awt.*;
import java.io.*;
import java.net.*;

public class SelectorScene implements IScene{

	public String name = "";
	String mSpecFile;

	boolean moreSelectors = false;

	ArrayList mSelectors;
	GameScene mWorld = null;
	ImageThing mBackground;
	Image lockImage;
	HashMap mButtons;
	IScene mDaddy;
	MenuThing mBack;

	private SelectorScene(String name, String spec) throws Exception{
		this.name = name;
		this.mBack = new MenuThing(725, 560, 100, 50, "Back");
		this.mSpecFile = spec;
		mButtons = new HashMap();
		lockImage = Platform.platform.getImage(Platform.platform.getBase(), "lock.png");
		URL url = new URL(Platform.platform.getBase(), mSpecFile);
		loadResources(url);
	}

	private void loadResources(URL file) throws Exception{
		InputStream in = file.openStream();
		BufferedReader bf = new BufferedReader(new InputStreamReader(in));

		int i = 0;
		String line;
		while((line = bf.readLine()) != null){
			if(line.trim() == "") break;
			String[] tokens = line.split("\\s+");
			if(i == 0){
				mBackground = new ImageThing(tokens[1], 800, 600);	
				i++;
				continue;
			}
			Image mg = Platform.platform.getImage(Platform.platform.getBase(), tokens[1]);
			double x = Double.valueOf(tokens[2]).doubleValue();
			double y = Double.valueOf(tokens[3]).doubleValue();

			MenuThing m = new MenuThing(x, y, mg);
			m.alternateImage = lockImage;
			m.number = i - 1;
			mButtons.put(tokens[0], m);
			i++;
		}
	}

	public SelectorScene(String name, String spec, GameScene world) throws Exception{
		this(name, spec);
		mWorld = world;
	}

	public SelectorScene(String name, String spec, ArrayList worldSelectors) throws Exception{
		this(name, spec);
		this.moreSelectors = true;
		mSelectors = worldSelectors;
	}

	public void addChild(IScene child){
	}

	public void setup(){
		Platform p = Platform.platform;
		p.addThing(mBackground);

		p.addThing(mBack);
		Object[] buttons = mButtons.values().toArray();
		for(int i = 0; i < mButtons.size(); i++){
			MenuThing m = (MenuThing)buttons[i];
			p.addThing(m);

			if(mWorld != null){
				System.out.println("i vs firstLockedLevel: " + i + " " + mWorld.firstLockedLevel());
				if( m.number >= mWorld.firstLockedLevel() ) {
					m.useAlternateImage();
					m.allowClicks = false;
					System.out.println("disabling clicks");
				} else {
					m.usePrimaryImage();
					m.allowClicks = true;
				}
			} else if (m.number > 0) {
				m.useAlternateImage();
				m.allowClicks = false;
			}
		}
	}

	public void finish(){
		Platform p = Platform.platform;
		p.removeThing(mBackground);
		Object[] buttons = mButtons.values().toArray();
		for(int i = 0; i < mButtons.size(); i++){
			MenuThing m = (MenuThing)buttons[i];
			p.removeThing(m);
		}
	}

	public void update(){
	}

	public void updateOverlay(Graphics g){
		mBack.updateOverlay(g);
	}

	private String getDestination(){
		Object[] keys = mButtons.keySet().toArray();
		for(int i = 0; i < mButtons.size(); i++){
			MenuThing button = (MenuThing) mButtons.get(keys[i]);
			if(button.clicked()) {
				return (String)keys[i];}
		}
		return null;
	}

	public boolean childReady(){
		String s = getDestination();
		if(s != null) return true;
		return false;
	}

	private void unclickAll(){
		Object[] buttons = mButtons.values().toArray();
		for(int i = 0; i < buttons.length; i++){
			MenuThing button = (MenuThing) buttons[i];
			button.unClick();
		}
	}

	public IScene getChild(){
		String level = getDestination();
		System.out.println("level text = " + level);
		System.out.println("level to load is: " + level);
		if(level == null) return null; //take that!
		if(!moreSelectors) {
			mWorld.loadLevel(Integer.valueOf(level).intValue());
			unclickAll();
			return mWorld;
		} else {
			for(int i = 0; i < mSelectors.size(); i++){
				SelectorScene s = (SelectorScene)mSelectors.get(i);
				if(s.name.equalsIgnoreCase(level)) {
					unclickAll();
					return s;
				}
			}
		}
		return null;
	}

	public void addParent(IScene p){
		mDaddy = p;
	}

	public IScene getParent(){
		return mDaddy;
	}

	public boolean done(){
		if(mBack.clicked()){
			mBack.unClick();
			return true;
		}
		return false;
	}
}
