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
            Elements elementsMainArticle=website.getElementsByClass("with-roof");
            Element elementMainArticle=elementsMainArticle.get(0);
            Elements h2=elementMainArticle.getElementsByTag("h2");
            Element linkElementMain=h2.get(0).parent();
            String linkMainArticle=linkElementMain.attr("href");

            Document mainArticle=Jsoup.connect(linkMainArticle).get();
            String textMainArticle=mainArticle.getElementsByClass("item-main-content").text();
            System.out.println(textMainArticle);
            System.out.println("----------------------");
            Elements elementsSubArticles = website.getElementsByClass("main-taste");
            Element subArticle=elementsSubArticles.get(0);
            Elements article=subArticle.getElementsByTag("article");

            for (int i=0;i<article.size();i++) {
                Element mainArticleTitle = article.get(i);
                Element linkElement = mainArticleTitle.parent();
                String link = linkElement.attr("href");
                Document news=Jsoup.connect(link).get();
                String text=news.getElementsByClass("item-main-content").text();
                System.out.println(text);
                System.out.println("----------------------");

            }


        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}