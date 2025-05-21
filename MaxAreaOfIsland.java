/*
Given a M*N matrix we need to find the max area of the island.
Land represented with 1 and water with 0
TC->O(rows*col)
SC(rows+col)
 */
public class MaxAreaOfIsland {
    public int maxArea(int[][] grid){
        int maximumArea=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1){
                    int island_size=dfs(grid,i,j);
                    if(island_size>maximumArea){
                        maximumArea=island_size;
                    }
                }
            }
        }
        return maximumArea;
    }
    public int dfs(int[][] grid,int i,int j){
        if(i<0 || i>=grid.length || j<0 || j>=grid[0].length || grid[i][j]==0)
            return 0;
        int count=1;
        grid[i][j]=0;
        count+=dfs(grid,i-1,j);
        count+=dfs(grid,i,j+1);
        count+=dfs(grid,i+1,j);
        count+=dfs(grid,i,j-1);
        return count;
    }

    public static void main(String[] args){
        int[][] M={
                {1,0,0,0,1,0,0},
                {0,1,0,0,1,1,1},
                {1,1,0,0,0,0,0},
                {1,0,0,1,1,0,0},
                {1,0,0,1,0,1,1}
        };
        MaxAreaOfIsland obj = new MaxAreaOfIsland();
        System.out.println("Maximum area of the island is "+obj.maxArea(M));
    }
}
