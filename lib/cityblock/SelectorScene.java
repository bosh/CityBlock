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
	GameScene mWorld;
	ImageThing mBackground;
	HashMap mButtons;


	private SelectorScene(String name, String spec) throws Exception{
		this.name = name;
		this.mSpecFile = spec;
		mButtons = new HashMap();
		
		
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
			mButtons.put(tokens[0], m);
			
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
		Object[] buttons = mButtons.values().toArray();
		for(int i = 0; i < mButtons.size(); i++){
			MenuThing m = (MenuThing)buttons[i];
			p.addThing(m);
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
		
	}
	
	private String getDestination(){
		Object[] keys = mButtons.keySet().toArray();
		for(int i = 0; i < mButtons.size(); i++){
			MenuThing button = (MenuThing) mButtons.get(keys[i]);
			if(button.clicked()) return (String)keys[i];
		}
		return null;
	}
	
	
	public boolean childReady(){
		String s = getDestination();
		if(s != null) return true;
		return false;
	}
	public IScene getChild(){
		String level = getDestination();		
		System.out.println("level to load is: " + level);
		if(level == null) return null; //take that!
		if(!moreSelectors) {
			mWorld.loadLevel(Integer.valueOf(level).intValue());
			return mWorld;
		}else{
			for(int i = 0; i < mSelectors.size(); i++){
				SelectorScene s = (SelectorScene)mSelectors.get(i);
				if(s.name.equalsIgnoreCase(level)) {
					System.out.println("FOUND " + level);
					return s;
					}
			}
						
		}
		return null;
	}
	public IScene getParent(){
		return null;
	}
	public boolean done(){
		return false;
	}
	
	
	
	
	
}