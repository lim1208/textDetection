package edu.scut.preprocess.picture;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class GrayImage {
	private File oldFile;
	private File newFile;
	
	public GrayImage(File oldFile,File newFile){
		this.oldFile=oldFile;
		this.newFile=newFile;
	}
	
	public File getOldFile() {
		return oldFile;
	}


	public void setOldFile(File oldFile) {
		this.oldFile = oldFile;
	}


	public File getNewFile() {
		return newFile;
	}


	public void setNewFile(File newFile) {
		this.newFile = newFile;
	}


	/*
	 * 灰度化图片
	 */
	public GrayMatrix grayImage() throws Exception{
		
		BufferedImage image=ImageIO.read(this.oldFile);//读取源图片
		
		int width=image.getWidth();//图片的宽度
		int height=image.getHeight();//图片的高度
		
		GrayMatrix grayMatrix=new GrayMatrix(width,height);
		
		int[][] gray=grayMatrix.getGray();
		
		BufferedImage grayImage=new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);//灰度化图片
		
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				int rgb=image.getRGB(i, j);
				
				
				int r=(rgb>>16) & 0xFF;
				int g=(rgb>>8) & 0xFF;
				int b=(rgb>>0) & 0xFF;
				
				int grayPixel=(int)(77*r+151*g+28*b+128)/256;
				
				gray[i][j]=grayPixel;
				
				grayImage.setRGB(i, j, grayPixel);
			}
		}
		
		grayMatrix.setGray(gray);
		
		ImageIO.write(grayImage,"jpg",this.newFile);//存储灰度化之后的图片
		
		return grayMatrix;
	}
}
