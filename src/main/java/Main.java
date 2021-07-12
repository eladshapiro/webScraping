import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sun.awt.geom.AreaOp;

import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static final Scanner scanner = new Scanner(System.in);
    public static final int mako=1;
    public static final int walla=2;
    public static final int ynet=3;

    public static void main(String[] args) throws IOException{
        Scanner scanner = new Scanner(System.in);
        int score = 0;
        System.out.println("Which site would you like to crawl?-Eמter it's number:");
        System.out.println("1.Mako");
        System.out.println("2.Walla");
        System.out.println("3.Ynet");
            int choice = scanner.nextInt();
            switch (choice) {
                case mako:
                    MakoRobot makoRobot = new MakoRobot();
                    levelOne(makoRobot);
                    break;
                case walla:
                    WallaRobot wallaRobot = new WallaRobot();
                   levelOne(wallaRobot);
                    break;
                case ynet:
                    YnetRobot ynetRobot = new YnetRobot();
                    levelOne(ynetRobot);
                    break;


            }
    }


    public static void levelOne(BaseRobot site){
    int score=0;
        String word;
        System.out.println("Hint: The title of the longest article is: "+site.getLongestArticleTitle());
        System.out.println();
        for (int i=0;i<5;i++)
        {
            if (i==0)
            {
                System.out.println("Enter a word that you think appears on the site  (you have 5 tries):");
                 word = scanner.next();
            }
            else
            {
                System.out.println("Enter another word:");
                 word = scanner.next();
                if (site.getWordsStatistics().get(word) == null)
                {
                    score += 0;
                }
                else
                    score += site.getWordsStatistics().get(word);
            }
        }
        System.out.println("\nyou'r score is:"+score);
    }
}
