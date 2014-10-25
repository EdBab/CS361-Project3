import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Steganography {
	static int totalBits = 0;
	static int bitCount = 0;
	static int imageRow = 0;
	static int imageCol = 0;
	BufferedImage in = null;
	BufferedImage out = null;

	int startAt = 1;
	static int r = 0;
	int b = 0;
	int g = 0;
	int a = 0;
	boolean tooFull = false;
	static String build = "";
	static int checker = 0;

	public Steganography(BufferedImage img) {
		this.in = img;
		this.out = new BufferedImage(img.getWidth(), img.getHeight(),
				BufferedImage.TYPE_INT_RGB);
	}

	public void addZeroByte() {
	
		if (startAt == 1) {
			
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

				if (imageCol == this.in.getWidth()-1) {
					imageCol = 0;
					imageRow++;
				} else
					imageCol++;
			}
	
			Color c = new Color(this.in.getRGB(imageCol, imageRow));
			r = c.getRed();
			g = c.getGreen();
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
			if (imageCol == this.in.getWidth()-1) {
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
					red -= 1;
				if (green % 2 != 0)
					green += 1;
				if (blue % 2 != 0)
					blue += 1;
				int co = (d.getAlpha() << 24) | (red << 16) | (green << 8)
						| blue;
				this.out.setRGB(imageCol, imageRow, co);

				if (imageCol == this.in.getWidth()-1) {
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
			int co = (d.getAlpha() << 24) | (r << 16) | (g << 8) | blu;
			this.out.setRGB(imageCol, imageRow, co);

			if (imageCol == this.in.getWidth()-1) {

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

				if (imageCol == this.in.getWidth()-1) {
					imageCol = 0;
					imageRow++;
				} else
					imageCol++;
			}
		
			Color c = new Color(this.in.getRGB(imageCol, imageRow));
			 r = c.getRed();
			if (r % 2 != 0) {
				r -= 1;
				
			}
			startAt = 2;
		}

	}

	public void fillOut() {
		if (startAt == 1) {

			for (; imageRow < this.in.getHeight(); imageRow++) {
				for (; imageCol < this.in.getWidth(); imageCol++) {
					this.out.setRGB(imageCol, imageRow,
							this.in.getRGB(imageCol, imageRow));
				}
				imageCol = 0;
			}
		} else if (startAt == 2) {
			if (r % 2 != 0)
				r -= 1;
			Color c = new Color(this.in.getRGB(imageCol, imageRow));
			int green = c.getGreen();
			int blue = c.getBlue();
			int col = (c.getAlpha() << 24) | (r << 16) | (green << 8) | blue;
			this.out.setRGB(imageCol, imageRow, col);
			if (this.in.getHeight() == imageRow
					&& this.in.getWidth() == imageCol) {

			} else {

				if (imageCol == this.in.getWidth()-1) {
					imageCol = 0;
					imageRow++;
				} else
					imageCol++;

				for (; imageRow < this.in.getHeight(); imageRow++) {
					for (; imageCol < this.in.getWidth(); imageCol++) {
						this.out.setRGB(imageCol, imageRow,
								this.in.getRGB(imageCol, imageRow));
					}
					imageCol = 0;
				}
			}
		} else if (startAt == 3) {
		
			if (r % 2 != 0)
				r -= 1;
			if (g % 2 != 0)
				g += 1;
			Color c = new Color(this.in.getRGB(imageCol, imageRow));

			int blue = c.getBlue();
			int col = (c.getAlpha() << 24) | (r << 16) | (g << 8) | blue;
			this.out.setRGB(imageCol, imageRow, col);
			if (this.in.getHeight() == imageRow
					&& this.in.getWidth() == imageCol) {

			} else {
				if (imageCol == this.in.getWidth()-1) {
					imageCol = 0;
					imageRow++;
				} else {

					imageCol++;
				}

				int hold = imageCol;
				
				for (; imageRow < this.in.getHeight(); imageRow++) {
					for (; imageCol < this.in.getWidth(); imageCol++) {
						// System.out.println(imageRow + "   " + imageCol);
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
			//System.out.println("woah");
			checker += 3;
			
		
			
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
			
			if(checker >= 8){
				if(imageRow == this.in.getHeight()- 1 && imageCol >= this.in.getWidth() - 4 ){
					if(stop2||checker == 10){
						
						checker = 0;
						r = red;
						startAt = 2;
						
					}
					else if(stop3||checker == 9){
						checker = checker % 8;
						r = red;
						g = green;
						startAt = 3;
					}
					else{
						int col = (c.getAlpha() << 24) | (red << 16) | (green << 8)
								| blue;
						checker = checker % 8;

						this.out.setRGB(imageCol, imageRow, col);
						imageCol++;
					}
					return false;
					
				}
				if(stop2)
					checker -=2;
				else if(stop3)
					checker -= 1;
				checker = checker % 8;
				
			}
			
			if(stop2)
				bitCount++;
			else if(stop3)
				bitCount+=2;
			else bitCount+=3;
			if (stop3) {
				a = alpha;
				r = red;
				if (!stop2) {
			
					startAt = 3;
					g = green;
					
				} else {
					startAt = 2;
					

				}
			} else {

				int col = (c.getAlpha() << 24) | (red << 16) | (green << 8)
						| blue;
				

				this.out.setRGB(imageCol, imageRow, col);

				
				
				
				if (imageCol == this.in.getWidth()-1) {
					imageCol = 0;
					imageRow++;
				} else
					imageCol++;

			}
		} else if (startAt == 2) {
			
			checker += 3;
			
			Color c = new Color(this.in.getRGB(imageCol, imageRow));
			int green = c.getGreen();
			int blue = c.getBlue();
			if(stop2)
				bitCount++;
			else if(stop3)
				bitCount+=2;
			else bitCount+=3;
			if ((Integer.valueOf(three.substring(0, 1)) % 2) != (green % 2))
				green += 1;

			if ((Integer.valueOf(three.substring(1, 2)) % 2) != (blue % 2))
				blue += 1;
			if(checker >= 8){
				if(imageRow == this.in.getHeight()- 1 && imageCol >= this.in.getWidth() - 4 ){
					if(stop2 || checker ==10){
						checker = 0;
						g = green;
						
						startAt = 3;
					}
					else if(stop3||checker==9){
						
						checker = checker % 8;
						b = blue;
						g = green;
						startAt = 1;
						int col = (c.getAlpha() << 24) | (r << 16) | (g << 8)
								| blue;
						

						this.out.setRGB(imageCol, imageRow, col);
						imageCol++;
					}
					else{
						int col = (c.getAlpha() << 24) | (r << 16) | (green << 8)
								| blue;
						checker = checker % 8;

						this.out.setRGB(imageCol, imageRow, col);
						imageCol++;
						Color d = new Color(this.in.getRGB(imageCol, imageRow));
						if ((Integer.valueOf(three.substring(2, 3)) % 2) != (d
								.getRed() % 2))
							r = d.getRed() - 1;
						else
							r = d.getRed();
						
					}
					return false;
					
				}
				if(stop2)
					checker -=2;
				else if(stop3)
					checker -= 1;
				checker = checker % 8;
				
			}
			
			if (stop3) {

				g = green;
				b = blue;
				startAt = 1;

			}
		
			if(stop2){
				g = green;
				startAt = 3;
			}
			else {

				int col = (c.getAlpha() << 24) | (r << 16) | (green << 8)
						| blue;
				this.out.setRGB(imageCol, imageRow, col);
				
				if (imageCol == this.in.getWidth()-1) {
					imageCol = 0;
					imageRow++;
				} else
					imageCol++;
				if (!stop3) {
					Color d = new Color(this.in.getRGB(imageCol, imageRow));
					if ((Integer.valueOf(three.substring(2, 3)) % 2) != (d
							.getRed() % 2))
						r = d.getRed() - 1;
					else
						r = d.getRed();
					
					
				}
				
			}
		}

		else if (startAt == 3) {
		
			checker += 3;
		
			if(stop2)
				bitCount++;
			else if(stop3)
				bitCount+=2;
			else bitCount+=3;
			Color c = new Color(this.in.getRGB(imageCol, imageRow));
			int blue = c.getBlue();
			if ((Integer.valueOf(three.substring(0, 1)) % 2) != blue % 2)
				blue += 1;
			int col = (c.getAlpha() << 24) | (r << 16) | (g << 8) | blue;
			
			this.out.setRGB(imageCol, imageRow, col);
			
			if(checker >= 8){
				if(imageRow == this.in.getHeight()- 1 && imageCol >= this.in.getWidth() - 5 ){
					imageCol++;
					if(stop2 || checker == 10){
						checker = 0;
						
						startAt = 1;
					}
					else if(stop3 || checker == 9){
						checker = checker % 8;
						Color d = new Color(this.in.getRGB(imageCol, imageRow));
						if ((Integer.valueOf(three.substring(1, 2)) % 2) != (d.getRed() % 2)
								&& !stop2)
							r = d.getRed() - 1;
						else if (!stop2)
							r = d.getRed();

						
						
						
						startAt = 2;
						
					}
					else{
						Color d = new Color(this.in.getRGB(imageCol, imageRow));
						if ((Integer.valueOf(three.substring(1, 2)) % 2) != (d.getRed() % 2)
								&& !stop2)
							r = d.getRed() - 1;
						else if (!stop2)
							r = d.getRed();

						
						if ((Integer.valueOf(three.substring(2, 3)) % 2) != (d.getGreen() % 2)
								&& !stop3)
							g = d.getGreen() + 1;
						else if (!stop3)
							g = d.getGreen();
						
						startAt = 3;
						checker = checker % 8;

						
					
						
					}
					return false;
					
				}
				if(stop2)
					checker -=2;
				else if(stop3)
					checker -= 1;
				checker = checker % 8;
				
			}
			
			if (imageCol == this.in.getWidth()-1) {
				imageCol = 0;
				imageRow++;
			} else
				imageCol++;

			Color d = new Color(this.in.getRGB(imageCol, imageRow));
			if ((Integer.valueOf(three.substring(1, 2)) % 2) != (d.getRed() % 2)
					&& !stop2)
				r = d.getRed() - 1;
			else if (!stop2)
				r = d.getRed();

			
			if ((Integer.valueOf(three.substring(2, 3)) % 2) != (d.getGreen() % 2)
					&& !stop3)
				g = d.getGreen() + 1;
			else if (!stop3)
				g = d.getGreen();

			if (stop2) {
				startAt = 1;
			} 

			else if (stop3)
				startAt = 2;

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
			
		} else if (binary.length() % 3 == 2) {
			binary += "5";
			
		}
		
		while (i < binary.length()) {
			/*
			 * if (startAt == 3) { build=binary.substring(i,i+8); int x =
			 * Integer.parseInt(build, 2); System.out.println("here   " +
			 * (char)x + "  " + build); }
			 */
			pad = transitionToNew(binary.substring(i, i + 3));
			//System.out.println(binary.substring(i, i + 3));
			i += 3;

			if (!pad) {
				this.tooFull = true;
				return false;
			}
		}
		return true;
	}
	public boolean nextLine(){
		//10
		if(startAt == 1){
			if(imageRow == this.in.getHeight() - 1 && imageCol > this.in.getWidth() - 6){
				return false;
			}
	
			String check = "";
			
		
			int count  = 0;
			for (int i = 0; i < 2; i++) {
				Color c = new Color(this.in.getRGB(imageCol, imageRow));
				int red = c.getRed();
				int green = c.getGreen();
				int blue = c.getBlue();
				if (red % 2 != 0)
					red -= 1;
				if (green % 2 != 0 && count == 0)
					green += 1;
				else if(count == 1 && green %2 != 1)
					green += 1;
				if (blue % 2 != 0)
					blue += 1;
				int col = (c.getAlpha() << 24) | (red << 16) | (green << 8)
						| blue;
				this.out.setRGB(imageCol, imageRow, col);

				
				
				 if (imageCol == this.in.getWidth()-1) {
					imageCol = 0;
					imageRow++;
				} else
					imageCol++;
				count++;
				check += Integer.toString(red%2) + Integer.toString(green%2) + Integer.toString(blue%2);
			}
			Color c = new Color(this.in.getRGB(imageCol, imageRow));
			r = c.getRed();
			 g = c.getGreen();
			if (r % 2 != 1)
				r -= 1;
			if (g % 2 != 0)
				g += 1;
check += Integer.toString(r%2) + Integer.toString(g%2);
		bitCount += 8;
			startAt = 3;
		}
		
			else if (startAt == 2) {
				if(imageRow == this.in.getHeight() - 1 && imageCol > this.in.getWidth() - 6 ){
					
					return false;
				}
				
				bitCount += 8;
				
			
				Color c = new Color(this.in.getRGB(imageCol, imageRow));
				int gree = c.getGreen();
				int blu = c.getBlue();
				if (gree % 2 != 0)
					gree += 1;
				if (blu % 2 != 0)
					blu += 1;
				int col = (c.getAlpha() << 24) | (r << 16) | (gree << 8) | blu;
				this.out.setRGB(imageCol, imageRow, col);
				
				
				if (imageCol == this.in.getWidth()-1) {
					imageCol = 0;
					imageRow++;
				} else
					imageCol++;
				int count = 0;
				for (int i = 0; i < 2; i++) {
					Color d = new Color(this.in.getRGB(imageCol, imageRow));
					int red = d.getRed();
					int green = d.getGreen();
					int blue = d.getBlue();
					if (red % 2 != 0)
						red -= 1;
					if (green % 2 != 0 && count  ==0)
						green += 1;
					if (green % 2 != 1 && count  ==1)
						green += 1;
					if (blue % 2 != 0 && count ==1)
						blue += 1;
					if(blue%2 != 1 && count == 0)
						blue += 1;
					int co = (d.getAlpha() << 24) | (red << 16) | (green << 8)
							| blue;
					this.out.setRGB(imageCol, imageRow, co);

					
				
					
					if (imageCol == this.in.getWidth()-1) {
						imageCol = 0;
						imageRow++;
					} else if (imageCol != this.in.getWidth())
						imageCol++;
					count++;
				}
				startAt = 1;
			
		}
			 
			 else if (startAt == 3) {
				 String look = "";
				 if(imageRow == this.in.getHeight() - 1 && imageCol > this.in.getWidth() - 6)
					 return false;
			
				 bitCount += 8;
				
					
					

					Color d = new Color(this.in.getRGB(imageCol, imageRow));
					int blu = d.getBlue();
					if (blu % 2 != 0)
						blu += 1;
					int co = (d.getAlpha() << 24) | (r << 16) | (g << 8) | blu;
					this.out.setRGB(imageCol, imageRow, co);
					
					look += String.valueOf(blu%2);
				
					
					if (imageCol == this.in.getWidth()-1) {

						imageCol = 0;
						imageRow++;
					} else
						imageCol++;
					
					int count  = 0;
					
					for (int i = 0; i < 2; i++) {

						Color c = new Color(this.in.getRGB(imageCol, imageRow));
						int red = c.getRed();
						int green = c.getGreen();
						int blue = c.getBlue();
						if (red % 2 != 0 && count == 0)
							red -= 1;
						else if(red % 2 != 1 && count == 1)
							red -= 1;
						if (green % 2 != 0)
							green += 1;
						if (blue % 2 != 0 && count == 0)
							blue += 1;
						else if(blue % 2 != 1 && count == 1)
							blue += 1;
						int col = (c.getAlpha() << 24) | (red << 16) | (green << 8)
								| blue;
						this.out.setRGB(imageCol, imageRow, col);

						
						
						if (imageCol == this.in.getWidth()-1) {
							imageCol = 0;
							imageRow++;
						} else
							imageCol++;
						count++;
						look += String.valueOf(red%2) + String.valueOf(green%2) +String.valueOf(blue%2);
						
					}
					Color c = new Color(this.in.getRGB(imageCol, imageRow));
					 r = c.getRed();
					if (r % 2 != 0) {
						r -= 1;
						
					}
					look += String.valueOf(r%2);
					
					startAt = 2;
				}

		return true;
	}

	public static void main(String[] args) throws IOException {
		// hey 00000010
		
		  String[] imageName = args[1].split("\\.");
		 
		String fileName =  args[2];
		String ext = imageName[1];
		// System.out.println(ext);
		String imageF = imageName[0];
		String eOrD =  args[0];
		String outName = fileName;
		
		boolean check = true;
		BufferedImage img = null;
		File test = new File(fileName);
		Scanner sc = new Scanner(test);
		

		
		try {
			img = ImageIO.read(new File( args[1]));
			// img = ImageIO.read(new File("inputImage.bmp"));
		} catch (IOException e) {

		}
		totalBits = 3 * (1+img.getHeight()) * (1+img.getWidth());
		Steganography ours = new Steganography(img);
		if (eOrD.equals("-D")) {
			PrintWriter outWriter = new PrintWriter(outName, "UTF-8");
			while (build != "00000000") {
				Color c = new Color(img.getRGB(imageCol, imageRow));
				int red = c.getRed();
				int green = c.getGreen();
				int blue = c.getBlue();
				if (red % 2 == 0) {
					build += "0";
				} else
					build += "1";
				if (build.length() == 8) {
					
					if (build.equals("00000000"))
						break;
					int x = Integer.parseInt(build, 2);
					build = "";
					outWriter.print((char) x);
				}
				if (green % 2 == 0) {
					build += "0";
				} else
					build += "1";
				if (build.length() == 8) {
				
					if (build.equals("00000000"))
						break;
					int x = Integer.parseInt(build, 2);
					build = "";
					outWriter.print((char) x);
				}

				if (blue % 2 == 0) {
					build += "0";
				} else
					build += "1";
				if (build.length() == 8) {
				
					if (build.equals("00000000"))
						break;
					int x = Integer.parseInt(build, 2);
					build = "";
					outWriter.print((char) x);
				}
				 if (imageCol == img.getWidth() - 1) {
					imageCol = 0;
					imageRow++;
				} else
					imageCol++;

				

			}
			outWriter.close();

		} else if (eOrD.equals("-E")) {
			System.out.println("Filename: " + fileName + " # of Pixels: " + 
					(img.getWidth() * img.getHeight()) + " Image Width: " + img.getWidth() +
					" Image Height: " + img.getHeight());
			int k = 0;
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
					System.out.println("Message too large. It has been truncated.");
					
					ours.addZeroByte();
					break;
				}
				
			
				k++;
				if (sc.hasNextLine()) {
				
					boolean maybe = ours.nextLine();
					
					
					if(maybe == false){
						System.out.println("Message too large. It has been truncated.");
						break;
					}
				}
			}
			sc.close();

			if (!ours.tooFull) {

				ours.addZeroByte();
				
				if (!ours.tooFull) {
					ours.fillOut();
				}

			}

			/*
			 * for(int i = 0; i < ours.in.getHeight(); i++){ for(int k = 0; k <
			 * ours.in.getWidth(); k++){ Color c = new
			 * Color(ours.in.getRGB(k,i)); int red = c.getRed() -10; int blue =
			 * c.getBlue()-10; int green = c.getGreen()-30; int alpha =
			 * c.getAlpha()-30; int col = (alpha << 24) |(red << 16) | (green <<
			 * 8) | blue; ours.out.setRGB(k, i, col); } }
			 */

			File f = new File(imageF + "-steg." +ext);
			ImageIO.write(ours.out, ext.toUpperCase(), f);
		}
		
		// System.out.println("'" + s + "' to binary: " + binary);
	}
}
