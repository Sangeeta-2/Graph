import java.util.LinkedList;
import java.util.Queue;

/*
Find minimum dice throw to reach the destination.
Take the value of N=30
//TC&SC->Same as BFS
 */
public class snakeLadderProblem {
    static class qentry{
        int v;//vertex number reached
        int steps;// at what number of steps
    }
     static int getMinDiceThrows(int[] moves,int N){
        int visited[]=new int[N];
         Queue<qentry> queue=new LinkedList<>();
         qentry qe=new qentry();
         qe.v=0;//starting from vertex 0
         qe.steps=0;
         visited[0]=1;
         queue.add(qe);
         while(!queue.isEmpty()){
             qe=queue.poll();
             int vertex=qe.v;
             if(vertex==N-1){// as vertex starts from 0
                 break;
             }
             for(int i=vertex+1;i<=(vertex+6) && i<N;i++){
                 if(visited[i]==0){
                     qentry a=new qentry();
                     a.steps=qe.steps +1;
                     visited[i]=1;
                     if(moves[i]!=-1){
                         a.v=moves[i];
                     }
                     else{
                         a.v=i;
                     }
                     queue.add(a);
                 }
             }

         }
         return qe.steps;
     }

     public static void main(String[] args){
        int N=30;
        int[] moves=new int[N];
        for(int i=0;i<N;i++){
            moves[i]=-1;
        }
        //For Ladder
         moves[2]=21;
         moves[4]=7;
         moves[10]=25;
         moves[19]=28;

         //forSnakes
         moves[26]=0;
         moves[20]=8;
         moves[16]=3;
         moves[18]=6;

         System.out.println("Minimum Dice throw required is "+getMinDiceThrows(moves,N));
     }
}
