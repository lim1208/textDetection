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
	 * �ҶȻ�ͼƬ
	 */
	public GrayMatrix grayImage() throws Exception{
		
		BufferedImage image=ImageIO.read(this.oldFile);//��ȡԴͼƬ
		
		int width=image.getWidth();//ͼƬ�Ŀ��
		int height=image.getHeight();//ͼƬ�ĸ߶�
		
		GrayMatrix grayMatrix=new GrayMatrix(width,height);
		
		int[][] gray=grayMatrix.getGray();
		
		BufferedImage grayImage=new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);//�ҶȻ�ͼƬ
		
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
		
		ImageIO.write(grayImage,"jpg",this.newFile);//�洢�ҶȻ�֮���ͼƬ
		
		return grayMatrix;
	}
}
