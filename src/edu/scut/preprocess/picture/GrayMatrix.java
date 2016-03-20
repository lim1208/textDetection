package edu.scut.preprocess.picture;

public class GrayMatrix {
	private int row;
	private int column;
	private int[][] gray;
	
	public GrayMatrix(int row,int column){
		this.row=row;
		this.column=column;
		
		this.gray=new int[row][column];
	}

	public int[][] getGray() {
		return gray;
	}

	public void setGray(int[][] gray) {
		this.gray = gray;
	}
	
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public void print(){
		for(int i=0;i<row;i++){
			for(int j=0;j<column;j++){
				
				if(this.gray[i][j]==65535){
					this.gray[i][j]=1;
				}
				
				System.out.print(this.gray[i][j]);
			}
			System.out.println();
		}
	}
}
