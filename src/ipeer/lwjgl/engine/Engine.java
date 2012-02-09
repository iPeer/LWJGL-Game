package ipeer.lwjgl.engine;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Frame;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public class Engine {

	public Engine() { }

	public static void main(String[] args0) {
		//boolean fullscreen = (args0.length > 0 && args0[0].equals("-fullscreen"));
		Frame f = new Frame(title);
		gameCanvas = new Canvas();
		f.setLayout(new BorderLayout());
		f.add(gameCanvas, "Center");
		gameCanvas.setMinimumSize(new Dimension(width, height));
		gameCanvas.setPreferredSize(new Dimension(width, height));
		f.pack();
		f.setLocationRelativeTo(null);
		f.addWindowListener(new iWindowListener());
		f.setVisible(true);
		f.setResizable(true);
		f.requestFocus();
		try {
			init(false);
			running = true;
			loop();
		}
		catch (Exception e) {
			System.out.println("Unable to launch!");
			e.printStackTrace();
		}
		finally {
			cleanUpAndClose();
		}
	}

	public static void init(boolean f) {
		try {
			Display.setParent(gameCanvas);
			Display.setTitle(title);
			if (f)
				Display.setFullscreen(f);
			Display.setResizable(true);
			//Display.setVSyncEnabled(true);
			Display.create();
			GL11.glViewport(0, 0, width, height);
			//Display.s
			//Display.resize(width, height);
		}
		catch (Exception e) {
			System.out.println("unable to finish init!");
			e.printStackTrace();
		}
	}

	public static void loop() {
		long lastTime = System.nanoTime();
		double toprocess = 0.0;
		/*
		 * 
		 * This will limit the number of renders per second.
		 * 
		 * Dividing by 60, will limit the game to 60 renders/sec 30 will limit
		 * it to 30, 100 to 100 and so on.
		 */
		double nsPerTick = 1000000000 / 60.0;
		int frames = 0;
		int ticks = 0;
		boolean shouldRender = true;
		while (running) {
			Display.update();
			if (Display.isCloseRequested())
				running = false;

			long now = System.nanoTime();
			toprocess += (double) (now - lastTime) / nsPerTick;
			lastTime = now;
			
			//if (Display.isActive()) {
				for (Display.isActive(); toprocess >= 1.0; shouldRender = true) {
						ticks++; // count this tick
						tick(); // run the tick.
						toprocess--; // remove 1 from the toprocess queue.
				}
				KeyListener.parseKeys();
				//Display.sync(fps);
			//}
			if (shouldRender) {
				render();
				frames++;
			}
			if (System.currentTimeMillis() - lastUpdate >= 1000L) {
				System.out.println(frames+" fps, "+ticks+" ticks");
				lastUpdate = System.currentTimeMillis();
				lastFrames = frames;
				lastTicks = ticks;
				frames = ticks = 0;
			}
		}
	}

	private static void tick() {
		logic++;
	}

	private static void render() {
		// clear the screen
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_STENCIL_BUFFER_BIT);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		GL11.glLoadIdentity();
	}
	
	public static void closeGame() {
		running = false;
		cleanUpAndClose();
	}
	
	public static void cleanUpAndClose() {
		System.out.println("Closed!");
		Display.destroy();
		System.exit(0);
	}

	public static final String title = "LWJGL Stuff";
	public static final int fps = 60;
	public static boolean running;
	@SuppressWarnings("unused")
	private static boolean paused = false;
	public static final int width = 854;
	public static final int height = 480;
	@SuppressWarnings("unused")
	private static int frames = 0, logic = 0;
	static long lastUpdate = 0L;
	private static Canvas gameCanvas;
	@SuppressWarnings("unused")
	private static int lastFrames = 0, lastTicks = 0;


}
