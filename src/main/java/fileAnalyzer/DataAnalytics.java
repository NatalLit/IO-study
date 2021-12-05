package fileAnalyzer;

import java.util.List;

public class DataAnalytics {
    private List<String> sentences;
    private int count;

    public DataAnalytics(List<String> sentences, int count) {
        this.sentences = sentences;
        this.count = count;
    }

    public List<String> getSentences() {
        return sentences;
    }

    public int getCount() {
        return count;
    }

  public static void print (DataAnalytics dataAnalytics){
      System.out.println("The search word occurs " + dataAnalytics.count +" times in the text.");

      if(dataAnalytics.count >0){
          System.out.println("The search word is contained in such sentences:");
      }
      for (String sentence : dataAnalytics.sentences){
          System.out.println(sentence);
      }
  }
}
