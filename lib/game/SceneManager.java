

public class SceneManager{
	
	private IScene mActive;
	
	//Should already be linked with child scenes
	public SceneManager(IScene topScene){
		mActive = topScene;
	}
	
	public IScene getCurrentScene(){
		if(mActive.done()){
			mActive.finish();
			mActive = mActive.getParent();
		}
		else if(mActive.childReady()){
			mActive.finish();
			mActive = mActive.getChild();
		}
		return mActive;
	}
	
	
}