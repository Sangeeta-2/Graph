/*
You will be given an 2d array in which you will be given a pixel which you need to color with the new color.
And also, you need to check the adjacents of the pixel and if it contain sthe same initial pixel color
you need to color it with new color
sr->starting row
sc-> starting column
TC->O(N*M)
SC->O(N*M)
 */
public class floodFillProblem {
    public int[][] floodFill(int sr,int sc,int[][] image,int newColor){
        int initialColor=image[sr][sc];
        int[][] ans=image;
        int[] delRow={-1,0,+1,0};
        int[] delCol={0,+1,0,-1};
        dfs(image,ans,sr,sc,delRow,delCol,newColor,initialColor);
        return ans;
    }
    private void dfs(int[][] image,int[][] ans,int row,int col,int[] delRow,int[] delCol,int newColor,int initialColor){
        ans[row][col]=newColor;
        for(int i=0;i<4;i++){// due to 4 adjacent we are considering
            int nRow=row+delRow[i];
            int nCol=col+delCol[i];
            if(nRow>=0 && nRow<image.length && nCol>=0 && nCol<image[0].length && image[nRow][nCol]==initialColor
            && ans[nRow][nCol]!=newColor){
                dfs(image,ans,nRow,nCol,delRow,delCol,newColor,initialColor);
            }
        }
    }

    public static void main(String[] args){
        int[][] image={
                {1,1,1},
                {1,1,0},
                {1,0,1}
        };
        int sr=1,sc=1,newColor=2;
        floodFillProblem obj = new floodFillProblem();
        int[][] result=obj.floodFill(sr,sc,image,newColor);
        //Printing the result
        for(int[] row:result){
            for(int pixel:row){
                System.out.print(pixel+" ");
            }
            System.out.println();
        }
    }
}
