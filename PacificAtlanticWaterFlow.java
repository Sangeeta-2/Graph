import java.util.ArrayList;
import java.util.List;

/* Need to capture the indices where both the Atlantic and Pacific water flows
TC->O(N*M)
SC->O(N*M)
*/
public class PacificAtlanticWaterFlow {
    public static List<List<Integer>> pacificAtlantic(int[][] heights){
        boolean[][] pacific =new boolean[heights.length][heights[0].length];
        boolean[][] atlantic =new boolean[heights.length][heights[0].length];
        //Marking by varying row and keeping column constant
        for(int i=0;i<heights.length;i++){
            mark(i,0,pacific,-1,-1,heights);
            mark(i,heights[0].length -1,atlantic,-1,-1,heights);
        }
        //Marking by varying column and keeping row constant
        for(int i=0;i<heights.length;i++){
            mark(0,i,pacific,-1,-1,heights);
            mark(heights.length -1, i,atlantic,-1,-1,heights);
        }

        List<List<Integer>> ans=new ArrayList<>();
        for(int i=0;i< heights.length;i++){
            for(int j=0;j<heights[0].length;j++){
                if(pacific[i][j] && atlantic[i][j]){
                    List<Integer> indices=new ArrayList<>();
                    indices.add(i);
                    indices.add(j);
                    ans.add(indices);
                }
            }
        }
        return ans;
    }

    static void mark(int i, int j, boolean[][] arr, int oi, int oj, int[][] heights) {
        if(i<0 || j<0 ||i>=arr.length || j>=arr[0].length ||arr[i][j] ||
                (oi>=0 && oj>=0 && heights[oi][oj]>heights[i][j])){
            return;
        }
        arr[i][j]=true;
        //checking its neighbours if the water can flow
        mark(i+1,j,arr,i,j,heights);
        mark(i,j+1,arr,i,j,heights);
        mark(i-1,j,arr,i,j,heights);
        mark(i,j-1,arr,i,j,heights);
    }

    public static void main(String[] args){
        int[][] heights = {
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}
        };
        List<List<Integer>> output=pacificAtlantic(heights);
        for(List<Integer> row:output){
            System.out.print("[");
            for(int i:row){
                System.out.print(i+" ");
            }
            System.out.print("]");
            System.out.println();
        }
    }
}
