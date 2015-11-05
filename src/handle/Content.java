package handle;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Content {
	
	private HashMap <String, Texture>textures;
	
	public Content(){
		textures = new HashMap<String, Texture>();
	}
	
	public void loadTexture(String path, String key){
		Texture t = new Texture(Gdx.files.internal(path));
		textures.put(key, t);
	}
	public Texture getTexture(String key){
		return textures.get(key);
	}
	public void disposeTexture(String key){
		Texture t = textures.get(key);
		if(t != null){
			t.dispose();
		}
	}

}
