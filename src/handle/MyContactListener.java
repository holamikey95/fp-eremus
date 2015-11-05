package handle;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

public class MyContactListener implements ContactListener {
	
//	private boolean canJump;
	private int footContacts;
	
	public void beginContact(Contact c){
		Fixture fa = c.getFixtureA();
		Fixture fb = c.getFixtureB();

		if(fa.getUserData()!= null && fa.getUserData().equals("foot")){
//			canJump = true;
			footContacts ++;
		}
		if(fb.getUserData()!= null && fb.getUserData().equals("foot")){
//			canJump = true;
			footContacts ++;
		}
	}
	public void endContact(Contact c){
		Fixture fa = c.getFixtureA();
		Fixture fb = c.getFixtureB();

		if(fa.getUserData()!= null && fa.getUserData().equals("foot")){
//			canJump = false;
			footContacts --;
		}
		if(fb.getUserData()!= null && fb.getUserData().equals("foot")){
//			canJump = false;
			footContacts --;
		}
	}
	public void preSolve(Contact c, Manifold m){

	}
	public void postSolve(Contact c, ContactImpulse ci){}
	
	public boolean playersOnGround(){
		return footContacts > 0;
	}

	

}
