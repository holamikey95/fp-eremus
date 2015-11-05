package state;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import handle.B2DVars;
import handle.GameStateManager;
import handle.MyContactListener;
import handle.UserInput;

import static main.Game.V_HEIGHT;
import static main.Game.V_WIDTH;
import static handle.B2DVars.ppm;

import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Play extends GameState{
	
	public float gravity = -9.81f;	
	public float speed;
	
	private World world;
	private Box2DDebugRenderer b2dr;
	
	private OrthographicCamera cam;
	
	public Body player;
	private MyContactListener contact;
	
	private TiledMap map;
	private float tileSize;
	private OrthogonalTiledMapRenderer tmr;
	
	private Vector2 vel;


	public Play(GameStateManager gsm){
		
		super(gsm);
		contact = new MyContactListener();
		world = new World(new Vector2(0, gravity), true);
		world.setContactListener(contact);
		b2dr = new Box2DDebugRenderer();
		
/*		//platforms or ground objects
		BodyDef bdef = new BodyDef();
//		bdef.position.set(160/ ppm, 120/ppm);
		bdef.position.set(150 / ppm, 0 / ppm);
		bdef.type = BodyType.StaticBody;
		Body body = world.createBody(bdef);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(200/ppm, 5/ppm);
		
		FixtureDef fdef = new FixtureDef();
		fdef.shape = shape;
		fdef.filter.categoryBits = B2DVars.BIT_GROUND;
		fdef.filter.maskBits = B2DVars.BIT_PLAYER;		
		body.createFixture(fdef).setUserData("ground");*/
		
		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		
		// make character
		bdef.position.set(160/ppm, 200/ppm);
		bdef.type = BodyType.DynamicBody;
		player = world.createBody(bdef);
		
		shape.setAsBox(5/ppm, 5/ppm);
		fdef.shape = shape;
		fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
		fdef.filter.maskBits = B2DVars.BIT_PLATFORM;
		player.createFixture(fdef).setUserData("player");
		
		//make foot
		shape.setAsBox(2 / ppm, 2 / ppm, new Vector2(0, -5 / ppm), 0);
		fdef.shape = shape;
		fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
		fdef.filter.maskBits = B2DVars.BIT_PLATFORM;
		fdef.isSensor = true;
		player.createFixture(fdef).setUserData("foot");
		
		
		
		//setup box2d cam
		cam = new OrthographicCamera();
		cam.setToOrtho(false, V_WIDTH / ppm, V_HEIGHT / ppm);
		
		
	//-----------------------------------------------------------------
		
		//load map
		map = new TmxMapLoader().load("resources/res/maps/testmap.tmx");
		tmr = new OrthogonalTiledMapRenderer(map);
		
		TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get("playforms");
		
		tileSize = layer.getTileWidth();
		
		for (int row = 0; row < layer.getHeight(); row++){
			for(int col = 0; col < layer.getWidth(); col++){
				
				Cell cell = layer.getCell(col,  row);
				
				if(cell == null) continue;
				if(cell.getTile() == null) continue;

				//create fixture for cell
				bdef.type = BodyType.StaticBody;
				bdef.position.set((col + 0.5f)*tileSize/ppm, (row +0.5f)*tileSize / ppm);
				
				ChainShape cs = new ChainShape();
				Vector2[] vect = new Vector2[3];
				vect[0] = new Vector2(-tileSize / 2 / ppm, -tileSize / 2 / ppm);
				vect[1] = new Vector2(-tileSize / 2 / ppm, tileSize / 2 / ppm);
				vect[2] = new Vector2(tileSize / 2 / ppm, tileSize / 2 / ppm);
				cs.createChain(vect);
				fdef.friction = 0;
				fdef.shape = cs;
				fdef.filter.categoryBits = B2DVars.BIT_PLATFORM;
				fdef.filter.maskBits = -1;
				fdef.isSensor = false;
				world.createBody(bdef).createFixture(fdef);
			}
		}
	}
	
	public void handleInput(){
		
		//jumping
		if(UserInput.isPressed(UserInput.JUMP_BUTTON)){
			if(contact.playersOnGround()){
				player.applyForceToCenter(0, 200, true);
			}
/*			if(UserInput.down(UserInput.RIGHT_BUTTON) && !contact.playersOnGround()){
				player.app
			}*/
		}
		
		//moving
		if(UserInput.down(UserInput.RIGHT_BUTTON)){
//			if(contact.playersOnGround()){
				player.applyForceToCenter(2, 0, true);
//				player.setLinearVelocity(new Vector2(1, 0));
			}
//			}
//		}
		if(UserInput.down(UserInput.LEFT_BUTTON)){
//			if(contact.playersOnGround()){
//				player.setLinearVelocity(new Vector2(-1, 0));
				player.applyForceToCenter(-2, 0, true);
		}
//		if(!(UserInput.isPressed))
//		updateCamera();
		
//	}	
	}

	public void update(float dt) {
		
		handleInput();
		world.step(dt, 6, 2);
	}

	public void render() {
		
		Gdx.gl.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
		tmr.setView(cam);
		tmr.render();
		
//		cam.position.set
		
		b2dr.render(world, cam.combined);
		
	}

	
	public void dispose(){
		
	}
	
/*	private void updateCamera() {
		b2dCam.position.x = player.getHitBox().x;
		}*/
}
