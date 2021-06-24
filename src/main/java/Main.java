import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sun.awt.geom.AreaOp;

import java.io.IOException;

public class Main
{
    public static void main(String[] args) {
        try
        {
            Document website = Jsoup.connect("https://www.walla.co.il/").get();
            Elements elements = website.getElementsByClass("main-taste");
            System.out.println("found " + elements.size());
            Element mainArticleElement=elements.get(0);
            Elements h2s=mainArticleElement.getElementsByTag("article");

            for (int i=0;i<h2s.size();i++) {
                Element mainArticleTitle = h2s.get(i);
                Element linkElement = mainArticleTitle.parent();
                String link = linkElement.attr("href");
                Document news=Jsoup.connect(link).get();
                System.out.println(link);
            }


        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}