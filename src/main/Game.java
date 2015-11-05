package main;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import handle.Content;
import handle.GameStateManager;
import handle.UserInput;
import handle.UserInputProcessor;

public class Game implements ApplicationListener {
	
	public static final String title = "Eremus";
	public static final int V_WIDTH = 320;
	public static final int V_HEIGHT = 240;
	public static final int scale = 2;
	
	public static final float STEP = 1/60f;
	private float time;
	
	private SpriteBatch sb;
	private OrthographicCamera cam;
	private OrthographicCamera hudCam;
	
	private GameStateManager gsm;

	public static Content resource;

	public void create(){
			
		Gdx.input.setInputProcessor(new UserInputProcessor());
		
/*		resource = new Content();
		resource.loadTexture("resources/res/images/bunny.png", "bunny");*/
		sb = new SpriteBatch();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, V_WIDTH, V_HEIGHT);
		hudCam = new OrthographicCamera(); 
		hudCam.setToOrtho(false, V_WIDTH, V_HEIGHT);
		gsm = new GameStateManager(this);
	}
	
	public void render(){
		time += Gdx.graphics.getDeltaTime();
		while(time>= STEP){
			time -= STEP;
			gsm.update(STEP);
			gsm.render();
			UserInput.update();
		}
/*		sb.setProjectionMatrix(hudCam.combined);
		sb.begin();
		sb.draw(resource.getTexture("bunny"),0, 0);
		sb.end();*/
	}
	public void dispose(){
		
	}
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	
	public SpriteBatch getSpriteBatch() {
		return sb;
	}
	public OrthographicCamera getCam() {
		return cam;
	}
	public OrthographicCamera getHubCam() {
		return hudCam;
	}	
	public static int getViewwidth() {
		return V_WIDTH;
	}
	public static int getViewheight() {
		return V_HEIGHT;
	}
}
