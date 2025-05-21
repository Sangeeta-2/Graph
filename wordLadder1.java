import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
given 2 distinct words start word and target word, and a list denoting word list of unique words of equal length.
Find the shortest length of the shortest transformation sequence from start word to target word.
TC->O(word list length * word length *26)
SC->O(word list length)//storing in the set
 */
public class wordLadder1 {
    static class Pair{
        String word;
        int steps;
        Pair(String _word, int _steps){
            word=_word;
            steps=_steps;
        }
    }
    public static int wordLadderLength(String startWord,String targetWord,String[] wordList){
        Queue<Pair> queue=new LinkedList<>();
        Set<String> wordSet=new HashSet<>();
        for(int i=0;i<wordList.length;i++){
            wordSet.add(wordList[i]);
        }
        queue.add(new Pair(startWord,1));
        wordSet.remove(startWord);
        while(!queue.isEmpty()){
            String currentWord=queue.peek().word;
            int steps_to_reach=queue.peek().steps;
            queue.poll();
            if(currentWord.equals(targetWord)){
                return steps_to_reach;
            }
            for(int i=0;i<currentWord.length();i++){
                for(char ch='a';ch<='z';ch++){
                    char[] replacedCharArray=currentWord.toCharArray();
                    replacedCharArray[i]=ch;
                    String replacedWord=new String(replacedCharArray);
                    if(wordSet.contains(replacedWord)){
                        wordSet.remove(replacedWord);
                        queue.add(new Pair(replacedWord,steps_to_reach+1));
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args){
        String startWord="der";
        String targetWord="dfs";
        String[] wordList={"des","der","dfr","dgt","dfs"};
//        String[] wordList={"des","der","dfr","dgt"};
        int steps_to_reach=wordLadderLength(startWord,targetWord,wordList);
        if(steps_to_reach>0){
            System.out.println("The word ladder length to reach the target word "+targetWord+" from the startd word "+startWord+" is "+steps_to_reach);
        }
        else{
            System.out.println("The target word "+targetWord+" is not present in the word list provided");
        }
    }
}
