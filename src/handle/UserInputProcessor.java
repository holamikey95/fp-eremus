package handle;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

public class UserInputProcessor extends InputAdapter{
	
	public boolean keyDown(int k){
		if(k == Keys.UP){
			UserInput.setKEy(UserInput.JUMP_BUTTON, true);
		}
		if (k == Keys.RIGHT){
			UserInput.setKEy(UserInput.RIGHT_BUTTON, true);
		}
		if(k == Keys.LEFT){
			UserInput.setKEy(UserInput.LEFT_BUTTON, true);
		}
		return true;
	}
	public boolean keyUp(int k){
		if(k == Keys.UP){
			UserInput.setKEy(UserInput.JUMP_BUTTON, false);
		}
		if (k == Keys.RIGHT){
			UserInput.setKEy(UserInput.RIGHT_BUTTON, false);
		}
		if(k == Keys.LEFT){
			UserInput.setKEy(UserInput.LEFT_BUTTON, false);
		}
		return true;
	}

}
