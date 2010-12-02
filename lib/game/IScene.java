


public interface IScene {
	
	public void addChild(IScene child);
	public void finish();
	public void update();
	public void updateOverlay(Graphics g);
	public boolean childReady();
	public IScene getChild();
	public IScene getParent();
	public boolean done();
	
	
}