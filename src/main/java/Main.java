import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sun.awt.geom.AreaOp;

import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner=new Scanner(System.in);
        int score=0;
        System.out.println("Which site would you like to crawl?");
        System.out.println("1.mako");
        System.out.println("2.walla");
        System.out.println("3.ynet");
    int choice = scanner.nextInt();
        try {
            switch (choice){
                case 1:
                    MakoRobot makoRobot=new MakoRobot();
                    System.out.println(makoRobot.getLongestArticleTitle());
                    for(int i=0;i<5;i++){
                        String word=scanner.next();
                        if(makoRobot.getWordsStatistics().get(word)==null){
                            score+=0;
                        }
                        else score+=makoRobot.getWordsStatistics().get(word);
                    }
                    break;
                case 2:
                    WallaRobot wallaRobot=new WallaRobot();
                    System.out.println(wallaRobot.getLongestArticleTitle());
                    for (int i=0;i<5;i++) {
                        String word=scanner.next();
                        if(wallaRobot.getWordsStatistics().get(word)==null){
                            score+=0;
                        }
                        else score+=wallaRobot.getWordsStatistics().get(word);
                    }
                    break;
                case 3:
                    YnetRobot ynetRobot=new YnetRobot();
                    System.out.println(ynetRobot.getLongestArticleTitle());
                    for (int i=0;i<5;i++) {
                        String word=scanner.next();
                        if(ynetRobot.getWordsStatistics().get(word)==null){
                            score+=0;
                        }
                        else score+=ynetRobot.getWordsStatistics().get(word);
                    }
                    break;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(score);


    }
}
