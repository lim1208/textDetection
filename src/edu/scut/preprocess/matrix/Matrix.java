package edu.scut.preprocess.matrix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.scut.preprocess.picture.GrayMatrix;

public class Matrix {
	private static final int SPLIT_WIDTH=18;
	private static final int SPLIT_HEIGHT=6;
	
	private GrayMatrix grayMatrix;
	private Map<Integer,Point> pointMap=new HashMap<Integer,Point>();//存储分块矩阵的起始点
	
	private Point startPoint=new Point();//文字区域起始点
	private Point endPoint=new Point();//文字区域结束点
	
	public List<Integer> AllWordsTag(Map<Integer,Point> pointMap,GrayMatrix grayMatrix){
		List<Integer> tagList=new ArrayList<Integer>();
		
		for(Map.Entry<Integer, Point> entry : pointMap.entrySet()){
			if(this.isWords(entry.getValue(),grayMatrix)){
				tagList.add(entry.getKey());
			}
		}
		
		return tagList;
	}
	
	//根据tag获取pointMap的点
	public List<Point> AllWordsPoint(Map<Integer,Point> pointMap,GrayMatrix grayMatrix){
		List<Point> pointList=new ArrayList<Point>();
		
		for(Map.Entry<Integer, Point> entry : pointMap.entrySet()){
			if(this.isWords(entry.getValue(),grayMatrix)){
				pointList.add(entry.getValue());
			}
		}
		
		return pointList;
	}
	
	
	//通过起始点判断该区域是否有文字
	public boolean isWords(Point point,GrayMatrix grayMatrix){
		int[][] gray=grayMatrix.getGray();
		
		int column=point.getY();
		int row=point.getX();
		
		boolean flag=false;//用于标记区域是否有文字
		
		boolean startFlag=true;//用于标记起始点是否已经找到
		
		for(int i=column;i<column+18;i++){
			point.setY(i);
			for(int j=row;j<row+6;j++){
				point.setX(j);
				if(gray[j][i]==0 && startFlag){
					startPoint.setX(j);
					startPoint.setY(i);
					
					endPoint.setX(j);
					endPoint.setY(i);
					
					startFlag=false;
				}else if(gray[j][i]==0 && startFlag==false){
					endPoint.setX(j);
					endPoint.setY(i);
				}else{
					
				}
				
				//System.out.println("("+startPoint.getX()+","+endPoint.getY()+")");
			}
		}
		
		if(Math.abs(startPoint.getX()-endPoint.getX())>=4 && Math.abs(startPoint.getY()-endPoint.getY())>=8){
			flag=true;
		}else{
			flag=false;
		}
		
		return flag;
	}
	
	
	//对二值化矩阵进行处理
	public GrayMatrix getGrayMatrix() {
		int column=this.grayMatrix.getColumn()-(this.grayMatrix.getColumn()%SPLIT_WIDTH);
		int row=this.grayMatrix.getRow()-(this.grayMatrix.getRow()%SPLIT_HEIGHT);
		
		this.grayMatrix.setColumn(column);
		this.grayMatrix.setRow(row);
		
		return grayMatrix;
	}
	
	public void setGrayMatrix(GrayMatrix grayMatrix) {
		this.grayMatrix = grayMatrix;
	}

	//获取所有分块矩阵的起始点
	public Map<Integer, Point> getPointMap(GrayMatrix grayMatrix) {
		int flag=0;//用于标记矩阵的数目
		
		for(int i=0;i<grayMatrix.getRow();i=i+6){
			for(int j=0;j<grayMatrix.getColumn();j=j+18){
				flag++;
				Point point=new Point();
				
				point.setX(i);
				point.setY(j);
				
				this.pointMap.put(flag, point);
			}
		}
		
		return pointMap;
	}

	public void setPointMap(Map<Integer, Point> pointMap) {
		this.pointMap = pointMap;
	}
	
	public Point getStartPoint() {
		return startPoint;
	}
	
	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}
	
	public Point getEndPoint() {
		return endPoint;
	}
	
	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}
}
