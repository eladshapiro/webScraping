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
    switch (choice){
        case 1:
            MakoRobot makoRobot=new MakoRobot();
            System.out.println(makoRobot.getLongestArticleTitle());
            for(int i=0;i<5;i++){
                String word=scanner.nextLine();
                score+=makoRobot.getWordsStatistics().get(word);
            }
            break;
        case 2:
            WallaRobot wallaRobot=new WallaRobot();
            System.out.println(wallaRobot.getLongestArticleTitle());
            for (int i=0;i<5;i++) {
                String word=scanner.next();
                score+=wallaRobot.getWordsStatistics().get(word);
            }
            break;
        case 3:
            YnetRobot ynetRobot=new YnetRobot();
            System.out.println(ynetRobot.getLongestArticleTitle());
            for (int i=0;i<5;i++) {
                String word=scanner.next();
                score+=ynetRobot.getWordsStatistics().get(word);
            }
            break;

    }
    System.out.println(score);


    }
}
