package ipeer.lwjgl.engine;

import org.lwjgl.input.Keyboard;

public class KeyListener {

	public static void parseKeys() {
		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			Engine.running = false;
		}
	}

}
