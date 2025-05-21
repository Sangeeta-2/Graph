import java.util.*;

/*
This is an extension of word ladder 1. Here we have to return all possible sequence.
 */
public class wordLadderII {
    static ArrayList<ArrayList<String>> wordLengthII(String startWord, String targetWord, String[] wordList){
        ArrayList<ArrayList<String>> ans=new ArrayList<>();
        ArrayList<String> ls=new ArrayList<>();
        ArrayList<String> wordsUsedOnLevel=new ArrayList<>();
        Queue<ArrayList<String>> queue=new LinkedList<>();
        Set<String> wordSet=new HashSet<>();
        ArrayList<String> vec=new ArrayList<>();
        int level=0;
        for(int i=0;i<wordList.length;i++){
            wordSet.add(wordList[i]);
        }
        ls.add(startWord);
        wordsUsedOnLevel.add(startWord);
        queue.add(ls);
        while(!queue.isEmpty()){
           vec=queue.peek();
           queue.poll();
           if(vec.size()>level){
               level++;
               //remove all the words used in previous level
               for(int i=0;i<wordsUsedOnLevel.size();i++){
                   wordSet.remove(wordsUsedOnLevel.get(i));
               }
           }
           String word=vec.get(vec.size()-1);
           if(word.equals(targetWord)){
               if(ans.size()==0){
                   ans.add(vec);
               }
               else if(ans.get(0).size()==vec.size()){
                   ans.add(vec);
               }
           }
           for(int i=0;i<word.length();i++){
               for(char ch='a';ch<='z';ch++){
                   char[] replacedCharArray=word.toCharArray();
                   replacedCharArray[i]=ch;
                   String replacedWord=new String(replacedCharArray);
                   if(wordSet.contains(replacedWord)){
                       vec.add(replacedWord);
                       ArrayList<String> temp=new ArrayList<>(vec);
                       queue.add(temp);
                       wordsUsedOnLevel.add(replacedWord);
                       vec.remove(replacedWord);
                   }
               }
           }
        }
        return ans;
    }
    public static void main(String[] args){
        String startWord="der";
        String targetWord="dfs";
        String[] wordList={"des","der","dfr","dgt","dfs"};
        ArrayList<ArrayList<String>> resultArray=wordLengthII(startWord,targetWord,wordList);
        for(ArrayList<String> list:resultArray){
            for(String word:list){
                System.out.print(word+" ");
            }
            System.out.println();
        }
    }
}
