package edu.scut.test;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import edu.scut.preprocess.matrix.Matrix;
import edu.scut.preprocess.matrix.Point;
import edu.scut.preprocess.picture.BinaryImage;
import edu.scut.preprocess.picture.GrayImage;
import edu.scut.preprocess.picture.GrayMatrix;

public class Test {
	public static void main(String[] args) throws Exception {
		File oldFile=new File("E:\\temp\\sh.jpg");
		File newFile=new File("E:\\temp\\sh.jpg");
		
		
		GrayMatrix grayMatrix=new GrayImage(oldFile,newFile).grayImage();
		new BinaryImage(oldFile,newFile).binaryImage(grayMatrix);
		
		grayMatrix.print();
		
		Matrix matrix=new Matrix();
		
		matrix.setGrayMatrix(grayMatrix);
		
		GrayMatrix grayM=matrix.getGrayMatrix();
		
		Map<Integer,Point> pointMap=matrix.getPointMap(grayM);//获取所有矩阵起始点
		
		for(Map.Entry<Integer, Point> entry : pointMap.entrySet()){
			System.out.println(entry.getKey()+"@"+"("+entry.getValue().getX()+","+entry.getValue().getY()+")");
		}
		
//		List<Integer> tagList=matrix.AllWordsTag(pointMap, grayM);
//		
//		System.out.println(tagList.size());
//		
//		Iterator<Integer> it=tagList.iterator();
//		
//		while(it.hasNext()){
//			System.out.print(it.next()+"&");
//		}
		
		List<Point> pointList=matrix.AllWordsPoint(pointMap, grayM);
		
		for(Point point : pointList){
			System.out.print("("+point.getX()+","+point.getY()+")");
		}
		
		BufferedImage image=ImageIO.read(oldFile);
		Graphics g=image.getGraphics();
		g.setColor(Color.red);
		
		for(Point point : pointList){
			g.drawRect(point.getX(), point.getY(),18, 6);
		}
		
		ImageIO.write(image,"jpg",newFile);
	}
}
