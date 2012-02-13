package ipeer.lwjgl.gfx;

import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;

public class ImageSheet {

	public ImageSheet(String n, int w, int h) {
		this.w = w;
		this.h = h;
		this.n = n;
	}
	
	public int getWidth() {
		return w;
	}
	
	public int getHeight() {
		return h;
	}
	
	public BufferedImage bind() {
		return read(n);
	}

	public BufferedImage read(String file) {
		if (cache.get(file) != null)
			return cache.get(file);
		try {
			BufferedImage i;
			InputStream in;
			in = ImageSheet.class.getResourceAsStream(file);
			i = ImageIO.read(in);
			cache.put(file, i);
			return i;
		}
		catch (Exception e) {
			e.printStackTrace();
			return notfound.bind();
		}
	}

	private int w, h; 
	private String n;
	public Hashtable<String, BufferedImage> cache;
	public ImageSheet font = new ImageSheet("ipeer/lwjgl/gfx/font.png", 256, 256);
	public ImageSheet notfound = new ImageSheet("ipeer/lwjgl/gfx/notfound.png", 250, 100);

}
