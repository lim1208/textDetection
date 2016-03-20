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
	 * ��ֵ��ͼƬ
	 */
	public GrayMatrix binaryImage(GrayMatrix grayMatrix) throws Exception{
		
		BufferedImage image=ImageIO.read(this.oldFile);//��ȡԴͼƬ
		
		int width=image.getWidth();//ͼƬ�Ŀ��
		int height=image.getHeight();//ͼƬ�ĸ߶�
		
		BufferedImage binaryImage=new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);//��ֵ��ͼƬ
		
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
		
		ImageIO.write(binaryImage,"jpg",this.newFile);//�洢�ҶȻ�֮���ͼƬ
		
		return grayMatrix;
	}
}
