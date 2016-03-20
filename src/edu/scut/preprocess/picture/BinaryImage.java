package edu.scut.preprocess.picture;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class BinaryImage {
	private File oldFile;
	private File newFile;
	
	public BinaryImage(File oldFile,File newFile){
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
	 * 二值化图片
	 */
	public GrayMatrix binaryImage(GrayMatrix grayMatrix) throws Exception{
		
		BufferedImage image=ImageIO.read(this.oldFile);//读取源图片
		
		int width=image.getWidth();//图片的宽度
		int height=image.getHeight();//图片的高度
		
		BufferedImage binaryImage=new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);//二值化图片
		
		int[][] gray=grayMatrix.getGray();
		
		for(int i=0;i<grayMatrix.getRow();i++){
			for(int j=0;j<grayMatrix.getColumn();j++){
				if(gray[i][j]>=180){
					gray[i][j] |=0x00FFFF;
				}else{
					gray[i][j] &=0xFF0000;
				}
				binaryImage.setRGB(i, j, gray[i][j]);
			}
		}
		
		grayMatrix.setGray(gray);
		
		ImageIO.write(binaryImage,"jpg",this.newFile);//存储灰度化之后的图片
		
		return grayMatrix;
	}
}
