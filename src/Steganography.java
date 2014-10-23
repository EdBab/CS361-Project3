import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
	boolean tooFull = false;

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
					red += 1;
				if (green % 2 != 0)
					green += 1;
				if (blue % 2 != 0)
					blue += 1;
				int col = (red << 16) | (green << 8) | blue;
				this.out.setRGB(imageCol, imageRow, col);

				if (imageCol == this.in.getWidth()) {
					imageCol = 0;
					imageRow++;
				} else
					imageCol++;
			}
			Color c = new Color(this.in.getRGB(imageCol, imageRow));
			int red = c.getRed();
			int green = c.getGreen();
			if (red % 2 != 0)
				r += 1;
			if (green % 2 != 0)
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
			int col = (r << 16) | (gree << 8) | blu;
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
				int co = (red << 16) | (green << 8) | blue;
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
			int co = (r << 16) | (g << 8) | blu;
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
				int col = (red << 16) | (green << 8) | blue;
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
			for (; imageRow < this.in.getHeight(); imageRow++) {
				for (; imageCol < this.in.getWidth(); imageCol++) {
					this.out.setRGB(imageCol, imageRow,
							this.in.getRGB(imageCol, imageRow));
				}
			}
		} else if (startAt == 2) {
			Color c = new Color(this.in.getRGB(imageCol, imageRow));
			int green = c.getGreen();
			int blue = c.getBlue();
			int col = (r << 16) | (green << 8) | blue;
			this.out.setRGB(imageCol, imageRow, col);
			if (this.in.getHeight() == imageRow
					&& this.in.getWidth() == imageCol) {

			} else {
				if (imageCol == this.in.getWidth()) {
					imageCol = 0;
					imageRow++;
				} else
					imageCol++;
				for (; imageRow < this.in.getHeight(); imageRow++) {
					for (; imageCol < this.in.getWidth(); imageCol++) {
						this.out.setRGB(imageCol, imageRow,
								this.in.getRGB(imageCol, imageRow));
					}
				}
			}
		}
		else if(startAt == 3){
			Color c = new Color(this.in.getRGB(imageCol, imageRow));
			
			int blue = c.getBlue();
			int col = (r << 16) | (g << 8) | blue;
			this.out.setRGB(imageCol, imageRow, col);
			if (this.in.getHeight() == imageRow
					&& this.in.getWidth() == imageCol) {

			} else {
				if (imageCol == this.in.getWidth()) {
					imageCol = 0;
					imageRow++;
				} else
					imageCol++;
				for (; imageRow < this.in.getHeight(); imageRow++) {
					for (; imageCol < this.in.getWidth(); imageCol++) {
						this.out.setRGB(imageCol, imageRow,
								this.in.getRGB(imageCol, imageRow));
					}
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
			int red = c.getRed();
			int green = c.getGreen();
			int blue = c.getBlue();
			System.out.println(red + "  " + green + "  " + blue);
			if ((Integer.valueOf(three.substring(0, 1)) % 2) != (red % 2)){
				if(red < 255)
					red += 1;
				else
					red -= 1;
			}
				
			if ((Integer.valueOf(three.substring(1, 2)) % 2) != (green % 2) && !stop2){
				if(green < 255)
					green += 1;
				else
					green -= 1;
			}
			if ((Integer.valueOf(three.substring(2, 3)) % 2) != (blue % 2)
					&& !stop3)
				blue += 1;
			System.out.println(red + "  " + green + "  " + blue);
			if (stop3) {
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
				int col = (red << 16) | (green << 8) | blue;
				System.out.println(col + "  " + this.in.getRGB(imageCol, imageRow) + "  " + imageRow + imageCol) ;
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
				int col = (r << 16) | (green << 8) | blue;
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

			int col = (r << 16) | (g << 8) | blue;
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
		System.out.println(binary + "  " + binary.length());
		while (i < 1){//binary.length()) {
			
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
		String fileName = "test";
		boolean check = true;
		BufferedImage img = null;
		File test = new File(fileName);
		Scanner sc = new Scanner(test);

		try {
			img = ImageIO.read(new File("inputImage.bmp"));
		} catch (IOException e) {

		}
		Steganography ours = new Steganography(img);

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
				// binary.append(' ');
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
		File f = new File("outputImage.bmp");
		ImageIO.write(ours.out, "BMP", f);

		String x = "jfj%%";
		Color c = new Color(ours.in.getRGB(222,43));
		Color d = new Color(ours.out.getRGB(222,43));
		System.out.println(d.getBlue() + "  " + c.getBlue());

		// System.out.println("'" + s + "' to binary: " + binary);
	}
}
