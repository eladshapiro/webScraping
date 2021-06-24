import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Main
{
    public static void main(String[] args) {
        try
        {
            Document website = Jsoup.connect("https://www.mako.co.il/").get();
            Elements elements = website.getElementsByClass("mako_main_portlet_container");
            System.out.println("found " + elements.size());
            for (Element element : elements)
            {
                Element container = element.parent().parent();
                String classValue = container.attr(("class"));
                System.out.println(element.text());
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
