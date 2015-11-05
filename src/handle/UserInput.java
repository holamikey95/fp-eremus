package handle;

public class UserInput {
	
	public static boolean[] keys;
	public static boolean[] pkeys;
	
	public static final int NUM_KEYS = 3;
	public static final int JUMP_BUTTON = 0;
	public static final int RIGHT_BUTTON = 1;
	public static final int LEFT_BUTTON = 2;
	
	static{
		keys = new boolean[NUM_KEYS];
		pkeys = new boolean[NUM_KEYS];
	}
	
	public static void update(){
		for (int i = 0; i < NUM_KEYS; i++){
			pkeys[i] = keys[i];
		}
	}
	
	public static void setKEy(int i, boolean b){
		keys[i] = b;
	}
	public static boolean down(int i){
		return keys[i];
	}
	public static boolean isPressed(int i){
		return keys[i] && !pkeys[i];
	}

}
