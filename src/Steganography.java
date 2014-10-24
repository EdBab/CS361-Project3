import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Steganography {

	static int imageRow = 0;
	static int imageCol = 0;
	BufferedImage in = null;
	BufferedImage out = null;
	static int fillCount = 0;
	int startAt = 1;
	int r = 0;
	int b = 0;
	int g = 0;
	int a = 0;
	boolean tooFull = false;
	static String build = "";

	public Steganography(BufferedImage img) {
		this.in = img;
		this.out = new BufferedImage(img.getWidth(), img.getHeight(),
				BufferedImage.TYPE_INT_RGB);
	}

	public void addZeroByte() {
		if (startAt == 1) {
			System.out.println("hello");
			for (int i = 0; i < 2; i++) {
				Color c = new Color(this.in.getRGB(imageCol, imageRow));
				int red = c.getRed();
				int green = c.getGreen();
				int blue = c.getBlue();
				if (red % 2 != 0)
					red -= 1;
				if (green % 2 != 0)
					green += 1;
				if (blue % 2 != 0)
					blue += 1;
				int col = (c.getAlpha() << 24) | (red << 16) | (green << 8)
						| blue;
				this.out.setRGB(imageCol, imageRow, col);

				if (imageCol == this.in.getWidth()) {
					imageCol = 0;
					imageRow++;
				} else
					imageCol++;
			}
			Color c = new Color(this.in.getRGB(imageCol, imageRow));
			int r = c.getRed();
			int g = c.getGreen();
			if (r % 2 != 0)
				r -= 1;
			if (g % 2 != 0)
				g += 1;

			startAt = 3;

		} else if (startAt == 2) {
			
			if (imageRow == this.in.getHeight()
					&& imageCol == this.in.getWidth() - 2) {
				this.tooFull = true;
			}
			Color c = new Color(this.in.getRGB(imageCol, imageRow));
			int gree = c.getGreen();
			int blu = c.getBlue();
			if (gree % 2 != 0)
				gree += 1;
			if (blu % 2 != 0)
				blu += 1;
			int col = (c.getAlpha() << 24) | (r << 16) | (gree << 8) | blu;
			this.out.setRGB(imageCol, imageRow, col);
			if (imageCol == this.in.getWidth()) {
				imageCol = 0;
				imageRow++;
			} else
				imageCol++;
			for (int i = 0; i < 2; i++) {
				Color d = new Color(this.in.getRGB(imageCol, imageRow));
				int red = d.getRed();
				int green = d.getGreen();
				int blue = d.getBlue();
				if (red % 2 != 0)
					red += 1;
				if (green % 2 != 0)
					green += 1;
				if (blue % 2 != 0)
					blue += 1;
				int co = (d.getAlpha() << 24) | (red << 16) | (green << 8)
						| blue;
				this.out.setRGB(imageCol, imageRow, co);

				if (imageCol == this.in.getWidth()) {
					imageCol = 0;
					imageRow++;
				} else if (imageCol != this.in.getWidth())
					imageCol++;
			}
			startAt = 1;
		} else if (startAt == 3) {
			
			Color d = new Color(this.in.getRGB(imageCol, imageRow));
			int blu = d.getBlue();
			if (blu % 2 != 0)
				blu += 1;
			int co = (d.getAlpha() << 24) | (d.getRed() << 16)
					| (d.getGreen() << 8) | blu;
			this.out.setRGB(imageCol, imageRow, co);

			if (imageCol == this.in.getWidth()) {
				imageCol = 0;
				imageRow++;
			} else
				imageCol++;
			for (int i = 0; i < 2; i++) {

				Color c = new Color(this.in.getRGB(imageCol, imageRow));
				int red = c.getRed();
				int green = c.getGreen();
				int blue = c.getBlue();
				if (red % 2 != 0)
					red += 1;
				if (green % 2 != 0)
					green += 1;
				if (blue % 2 != 0)
					blue += 1;
				int col = (c.getAlpha() << 24) | (red << 16) | (green << 8)
						| blue;
				this.out.setRGB(imageCol, imageRow, col);

				if (imageCol == this.in.getWidth()) {
					imageCol = 0;
					imageRow++;
				} else
					imageCol++;
			}
			Color c = new Color(this.in.getRGB(imageCol, imageRow));
			int r = c.getRed();
			if (r % 2 != 0)
				r += 1;
			startAt = 2;
		}

	}

	public void fillOut() {
		if (startAt == 1) {
			
			int hold = imageCol;
			for (; imageRow < this.in.getHeight(); imageRow++) {
				for (; imageCol < this.in.getWidth(); imageCol++) {
					this.out.setRGB(imageCol, imageRow,
							this.in.getRGB(imageCol, imageRow));
				}
				imageCol = 0;
			}
		} else if (startAt == 2) {
			
			Color c = new Color(this.in.getRGB(imageCol, imageRow));
			int green = c.getGreen();
			int blue = c.getBlue();
			int col = (c.getAlpha() << 24) | (r << 16) | (green << 8) | blue;
			this.out.setRGB(imageCol, imageRow, col);
			if (this.in.getHeight() == imageRow
					&& this.in.getWidth() == imageCol) {

			} else {

				if (imageCol == this.in.getWidth()) {
					imageCol = 0;
					imageRow++;
				} else
					imageCol++;
				int hold = imageCol;
				for (; imageRow < this.in.getHeight(); imageRow++) {
					for (; imageCol < this.in.getWidth(); imageCol++) {
						this.out.setRGB(imageCol, imageRow,
								this.in.getRGB(imageCol, imageRow));
					}
					imageCol = 0;
				}
			}
		} else if (startAt == 3) {
			System.out.println("hello   " + imageRow + "   " + imageCol);
			Color c = new Color(this.in.getRGB(imageCol, imageRow));
			
			int blue = c.getBlue();
			int col = (c.getAlpha() << 24) | (r << 16)
					| (g << 8) | blue;
			this.out.setRGB(imageCol, imageRow, col);
			if (this.in.getHeight() == imageRow
					&& this.in.getWidth() == imageCol) {

			} else {
				if (imageCol == this.in.getWidth()) {
					imageCol = 0;
					imageRow++;
				} else {
					
					imageCol++;
				}

				int hold = imageCol;
				System.out.println(imageRow + "  " + imageCol);
				for (; imageRow < this.in.getHeight(); imageRow++) {
					for (; imageCol < this.in.getWidth(); imageCol++) {
						//System.out.println(imageRow + "   " + imageCol);
						this.out.setRGB(imageCol, imageRow,
								this.in.getRGB(imageCol, imageRow));
						
					}
					imageCol = 0;
				}
			}
		}
	}

	public boolean transitionToNew(String three) {
		boolean stop2 = false;
		boolean stop3 = false;
		if (three.charAt(1) == '5') {
			stop2 = true;
			stop3 = true;
		} else if (three.charAt(2) == '5') {
			stop3 = true;
		}

		if (startAt == 1) {

			Color c = new Color(this.in.getRGB(imageCol, imageRow));
			int alpha = c.getAlpha();
			int red = c.getRed();
			int green = c.getGreen();
			int blue = c.getBlue();
			
			if ((Integer.valueOf(three.substring(0, 1)) % 2) != (red % 2)) {
				if (red < 255)
					red += 1;
				else
					red -= 1;
			}

			if ((Integer.valueOf(three.substring(1, 2)) % 2) != (green % 2)
					&& !stop2) {
				if (green < 255)
					green += 1;
				else
					green -= 1;
			}
			if ((Integer.valueOf(three.substring(2, 3)) % 2) != (blue % 2)
					&& !stop3)
				blue += 1;
			
			if (stop3) {
				a = alpha;
				r = red;
				if (!stop2) {
					startAt = 3;
					g = green;
					if (imageRow == this.in.getHeight()
							&& imageCol == this.in.getWidth() - 3) {

						return false;
					}
				} else {
					startAt = 2;
					if (imageRow == this.in.getHeight()
							&& imageCol == this.in.getWidth() - 2) {
						return false;
					}

				}
			} else {
			
				int col = (c.getAlpha() << 24) | (red << 16) | (green << 8)
						| blue;
				int col2 = (c.getAlpha() << 24) | (c.getRed() << 16) | (c.getGreen() << 8)
						| c.getBlue();
			
				this.out.setRGB(imageCol, imageRow, col);
		
				
				if (imageRow == this.in.getHeight()
						&& imageCol == this.in.getWidth() - 3) {
					imageCol++;
					return false;

				}

				if (imageCol == this.in.getWidth()) {
					imageCol = 0;
					imageRow++;
				} else
					imageCol++;

			}
		} else if (startAt == 2) {

			Color c = new Color(this.in.getRGB(imageCol, imageRow));
			int green = c.getGreen();
			int blue = c.getBlue();
			if ((Integer.valueOf(three.substring(0, 1)) % 2) != (green % 2))
				green += 1;

			if ((Integer.valueOf(three.substring(1, 2)) % 2) != (blue % 2))
				blue += 1;
			if (stop3) {

				g = green;
				if (!stop2) {

					b = blue;
					startAt = 1;

				} else
					startAt = 3;

			}
			if (stop2) {
				if (imageRow == this.in.getHeight()
						&& imageCol == this.in.getWidth() - 3) {
					return false;
				}
			}

			else {

				int col = (c.getAlpha() << 24) | (r << 16) | (green << 8)
						| blue;
				this.out.setRGB(imageCol, imageRow, col);

				if (imageCol == this.in.getWidth()) {
					imageCol = 0;
					imageRow++;
				} else
					imageCol++;
				if (!stop3) {
					Color d = new Color(this.in.getRGB(imageCol, imageRow));
					if ((Integer.valueOf(three.substring(2, 3)) % 2) != (d
							.getRed() % 2))
						r = d.getRed() + 1;
				}
				if (imageRow == this.in.getWidth()
						&& imageCol == this.in.getHeight() - 2) {
					return false;
				}
			}
		}

		else if (startAt == 3) {

			Color c = new Color(this.in.getRGB(imageCol, imageRow));
			int blue = c.getBlue();
			if ((Integer.valueOf(three.substring(0, 1)) % 2) != (blue % 2))
				blue += 1;

			int col = (c.getAlpha() << 24) | (r << 16) | (g << 8) | blue;
			this.out.setRGB(imageCol, imageRow, col);
			if (imageRow == this.in.getHeight()
					&& imageCol == this.in.getWidth() - 3 && stop2) {
				imageCol++;
				startAt = 1;
				return false;
			}

			if (imageCol == this.in.getWidth()) {
				imageCol = 0;
				imageRow++;
			} else
				imageCol++;

			Color d = new Color(this.in.getRGB(imageCol, imageRow));
			if ((Integer.valueOf(three.substring(1, 2)) % 2) != (d.getRed() % 2)
					&& !stop2)
				r = d.getRed() + 1;
			if ((Integer.valueOf(three.substring(2, 3)) % 2) != (d.getGreen() % 2)
					&& !stop3)
				g = d.getGreen() + 1;

			if (stop2) {
				startAt = 1;
			} else if (stop3
					&& (imageRow == this.in.getHeight() && imageCol == this.in
							.getWidth() - 3)) {
				startAt = 2;
				return false;
			}

		}

		/*
		 * if (imageRow == this.in.getHeight() && imageCol == this.in.getWidth()
		 * - 1) return false;
		 * 
		 * if (imageCol == this.in.getWidth()) { imageCol = 0; imageRow++; }
		 */

		return true;
	}

	public boolean imbedMess(String binary, boolean done) {
		int i = 0;
		boolean pad = true;
		if (binary.length() % 3 == 1) {
			binary += "55";
			fillCount = 2;
		} else if (binary.length() % 3 == 2) {
			binary += "5";
			fillCount = 1;
		}
	
		while (i < binary.length()) {

			pad = transitionToNew(binary.substring(i, i + 3));
			System.out.println(binary.substring(i, i + 3));
			i += 3;

			if (!pad) {
				this.tooFull = true;
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		
			String[] imageName = args[1].split("\\.");
			System.out.println(imageName[0]);
			if(args[1].contains(".")){
				System.out.println("kjfdk");
			}
		String fileName = args[2];
		String ext = imageName[1];
		System.out.println(ext);
		String imageF = imageName[0];
		String eOrD = args[0];
		String outName = fileName+"-out";
		boolean check = true;
		BufferedImage img = null;
		File test = new File(fileName);
		Scanner sc = new Scanner(test);
		PrintWriter outWriter = new PrintWriter(outName, "UTF-8");
		try {
			img = ImageIO.read(new File(args[1]));
			// img = ImageIO.read(new File("inputImage.bmp"));
		} catch (IOException e) {

		}
		Steganography ours = new Steganography(img);
		if(eOrD.equals("-D")){
		  while (build != "00000000") {
			  Color c = new Color(img.getRGB(imageCol,imageRow)); 
			  int red = c.getRed(); 
			  int green = c.getGreen();
			  int blue = c.getBlue();
			  if(red % 2 == 0){ 
				  build += "0";
				  }
			  else
				  build += "1"; 
			  if(build.length() == 8){
			  System.out.println("hey " + build);
				  if(build.equals("00000000"))
					  break;
				  int x = Integer.parseInt(build, 2);
				  build="";
		  outWriter.print((char)x); 
		  }
			  if(green % 2 == 0){
				  build += "0";
				  }
			  else
		  build += "1";
			  if(build.length() == 8){
			  System.out.println("hey " + build);
			  if(build.equals("00000000"))
					  break;
		  int x = Integer.parseInt(build, 2); 
		  build="";
		  outWriter.print((char)x);
		  }
		  
		  if(blue % 2 == 0){
			  build += "0";
			  }
		  else build += "1";
		  if(build.length() == 8){ 
		  System.out.println("hey " + build);
		  if(build.equals("00000000"))
				  break;
			  int x = Integer.parseInt(build, 2);
			  build="";
			  outWriter.print((char)x);
			  }
		  if(imageCol == img.getWidth() -1){
			  imageCol = 0;
			  imageRow++;
			  }
		  else
		  imageCol++;
		  
		  System.out.println(build);
		
		  }
		  outWriter.close();
		 
		}
		else if(eOrD.equals("-E")){
			while (sc.hasNextLine()) {
		
			String line = sc.nextLine();
		

		byte[] bytes = line.getBytes();
		StringBuilder binary = new StringBuilder();
		for (byte b : bytes) {
			int val = b;
			for (int i = 0; i < 8; i++) {
				binary.append((val & 128) == 0 ? 0 : 1);
				val <<= 1;
			}
			// binary.append(' '); }
		}
			check = ours.imbedMess(binary.toString(), false);
			if (!check) {

				ours.addZeroByte();
				break;
			}

		}
		sc.close();
		if (!ours.tooFull) {

			ours.addZeroByte();
			if (!ours.tooFull) {
				ours.fillOut();
			}

		}

		int gay = 0;
		 /* for(int i = 0; i < ours.in.getHeight(); i++){ for(int k = 0; k <
		 * ours.in.getWidth(); k++){ Color c = new Color(ours.in.getRGB(k,i));
		 * int red = c.getRed() -10; int blue = c.getBlue()-10; int green =
		 * c.getGreen()-30; int alpha = c.getAlpha()-30; int col = (alpha << 24)
		 * |(red << 16) | (green << 8) | blue; ours.out.setRGB(k, i, col); } }
		 */
		
		File f = new File(imageF + "-steg." + ext);
		ImageIO.write(ours.out, ext.toUpperCase(), f);
		}
		 Color r = new Color(ours.out.getRGB(8, 0)); 
		 Color s = new Color(ours.out.getRGB(9, 0)); 
		 Color t = new Color(ours.out.getRGB(10, 0));
       
		 int r1 = r.getRed();
		 int r2 = s.getRed();
		 int r3 = t.getRed(); 
		 int g1 = r.getGreen();
		 int g2 = s.getGreen();
		 int g3 = t.getGreen(); 
		 int b1 = r.getBlue();
		 int b2 = s.getBlue();
		 System.out.println("arf  " + r1 + " " + r2 + " " + r3 + " "+ g1 + " "+ g2 + " " + g3 + " "
				 + b1 + " " + b2);
		 
		String x = "jfj%%";
		Color c = new Color(ours.in.getRGB(0, 0));
		int red = c.getRed() - 1;
		int blue = c.getBlue() + 1;
		int green = c.getGreen();
		int rgb = red;
		int alpha = c.getAlpha();
		System.out.println(alpha + "  " + red + "  " + green + " " + blue);
		rgb = (rgb << 8) + green;
		rgb = (rgb << 8) + blue;
		int col = (alpha << 24) | (red << 16) | (green << 8) | blue;
		System.out.println(ours.in.getRGB(0, 100) + "  " + ours.out.getRGB(0, 100));

		// System.out.println("'" + s + "' to binary: " + binary);
	}
}
