package dynamicImages;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



public class DynamicImage {
	private String text;
	private Font font;
	private Point textLocation;
	private String orginalImageDir;
	private Color fontColor;
	
	public static class DynamicImageBuilder{
		private String text;
		private Font font;
		private Point textLocation;
		private String orginalImageDir;
		private Color fontColor;
		
		public DynamicImageBuilder(String orginalImageDir) {
			this.orginalImageDir = orginalImageDir;
			this.text = "Default";
			this.textLocation= new Point(0,0);
			this.font = new Font("Arial", Font.PLAIN, 13);
			this.fontColor = new Color(0,0,0);
		}
		
		public DynamicImageBuilder text(String text) {
			this.text=text;
			return this;
		}
		
		public DynamicImageBuilder textLocation(Point textLocation) {
			this.textLocation=textLocation;
			return this;
		}
		
		public DynamicImageBuilder font(Font font) {
			this.font = font;
			return this;
		}
		
		public DynamicImageBuilder fontColor(Color fontColor) {
			this.fontColor = fontColor;
			return this;
		}
		
		public DynamicImage build() {
			return new DynamicImage(this);
		} 
	}
	
	private DynamicImage(DynamicImageBuilder builder) {
		this.font=builder.font;
		this.text=builder.text;
		this.textLocation = builder.textLocation;
		this.orginalImageDir=builder.orginalImageDir;
		this.fontColor=builder.fontColor;
	}
	
	public BufferedImage generateImage() {	
		File imageFile = new File(this.orginalImageDir);
		try {
			BufferedImage orginalImage = ImageIO.read(imageFile);
			BufferedImage newImage = new BufferedImage(orginalImage.getWidth(), orginalImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
			Graphics2D graphics2d = (Graphics2D) newImage.getGraphics();
			graphics2d.drawImage(orginalImage, 0,0, null);
			graphics2d.setFont(this.font);
			graphics2d.setColor(this.fontColor);
			graphics2d.drawString(this.text, this.textLocation.x, this.textLocation.y);
			return newImage;
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("No image was found in the given directory. Returning blank image");
			return new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
		}
	}
	
	public File writeImage(BufferedImage bufferedImage, String outputDir) {
		File image = new File(outputDir);
		try {
			ImageIO.write(bufferedImage, "png", image);
			System.out.println("File Written to: "+image.getAbsolutePath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return image;
	}
}
