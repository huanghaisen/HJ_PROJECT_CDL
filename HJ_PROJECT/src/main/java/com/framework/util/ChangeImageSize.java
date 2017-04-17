package com.framework.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 图片尺寸操作类
 * @author PC076
 *
 */
public class ChangeImageSize {

	/**
	 * 缩放图片
	 * @param srcImageFile  源图片地址
	 * @param result      缩放后图片地址
	 * @param scale       缩放比例
	 * @param flag        是否放大
	 */
	public static void scale(String srcImageFile, String result, int scale,
			boolean flag) {
		try {
			BufferedImage src = ImageIO.read(new File(srcImageFile)); // 读入文件
			int width = src.getWidth(); // 得到源图宽
			int height = src.getHeight(); // 得到源图长
			if (flag) {
				// 放大
				width = width * scale;
				height = height * scale;
			} else {
				// 缩小
				width = width / scale;
				height = height / scale;
			}
			Image image = src.getScaledInstance(width, height,
					Image.SCALE_DEFAULT);
			BufferedImage tag = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null); // 绘制缩小后的图
			g.dispose();
			File file = new File(result);
			if(!file.exists()){
				file.mkdirs();
			}
			ImageIO.write(tag, "JPEG", file);// 输出到文件流
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 缩放图片
	 * @param srcImageFile  源图片地址
	 * @param result      缩放后图片地址
	 * @param scale       缩放比例
	 * @param flag        是否放大
	 */
	public static void scale(File srcImageFile, String result, int scale,
			boolean flag) {
		try {
			BufferedImage src = ImageIO.read(srcImageFile); // 读入文件
			int width = src.getWidth(); // 得到源图宽
			int height = src.getHeight(); // 得到源图长
			if (flag) {
				// 放大
				width = width * scale;
				height = height * scale;
			} else {
				// 缩小
				width = width / scale;
				height = height / scale;
			}
			Image image = src.getScaledInstance(width, height,
					Image.SCALE_DEFAULT);
			BufferedImage tag = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null); // 绘制缩小后的图
			g.dispose();
			File file = new File(result);
			if(!file.exists()){
				file.mkdirs();
			}
			ImageIO.write(tag, "JPEG", file);// 输出到文件流
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 传入固定的size缩放图片
	 * @param srcImageFile
	 * @param result
	 * @param width    目标宽
	 * @param height   目标高
	 */
	public static void scale(String srcImageFile, String result, int width,int height) {
		try {
			BufferedImage src = ImageIO.read(new File(srcImageFile)); // 读入文件
			Image image = src.getScaledInstance(width, height,
					Image.SCALE_DEFAULT);
			BufferedImage tag = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null); // 绘制缩小后的图
			g.dispose();
			File file = new File(result);
			if(!file.exists()){
				file.mkdirs();
			}
			ImageIO.write(tag, "JPEG", file);// 输出到文件流
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void scale(File ImageFile, String result, int width,int height) {
		try {
			BufferedImage src = ImageIO.read(ImageFile); // 读入文件
			Image image = src.getScaledInstance(width, height,
					Image.SCALE_DEFAULT);
			BufferedImage tag = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null); // 绘制缩小后的图
			g.dispose();
			File file = new File(result);
			if(!file.exists()){
				file.mkdirs();
			}
			ImageIO.write(tag, "JPEG", file);// 输出到文件流
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 确定宽度 根据比例缩放图片
	 * @param srcImageFile
	 * @param result
	 * @param width
	 */
	public static void scale(String srcImageFile, String result, int width) {
		try {
			File src_file = new File(srcImageFile);
			System.out.println("fileName:"+src_file.getName());
			BufferedImage src = ImageIO.read(src_file); // 读入文件
			float src_width = src.getWidth(); // 得到源图宽
			int src_height = src.getHeight(); // 得到源图长
			float height_num = src_height*(width/src_width);
			int height = Float.valueOf(height_num).intValue();
			Image image = src.getScaledInstance(width, height,
					Image.SCALE_DEFAULT);
			BufferedImage tag = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null); // 绘制缩小后的图
			g.dispose();
			File file = new File(result);
			if(!file.exists()){
				file.mkdirs();
			}
			ImageIO.write(tag, "JPEG", file);// 输出到文件流
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] arg){
		//ChangeImageSize server = new ChangeImageSize();
		File file =new File("E:/20130126/20130126000054.jpg");
		ChangeImageSize.scale(file, "E:\\baby\\00.jpg_s.jpg", 3,false);
	}

}
