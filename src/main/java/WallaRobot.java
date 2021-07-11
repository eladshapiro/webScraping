import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WallaRobot extends BaseRobot
{
    private ArrayList<String> sitesUrl;
    private Document site;
    private Map<String, Integer> map;

    public WallaRobot() throws IOException
    {
        super("https://www.walla.co.il/");
        site = Jsoup.connect(super.getRootWebsiteUrl()).get();
        sitesUrl = new ArrayList<>();
        map = new HashMap<>();
        String words;
        String[] wordsSplit;

        for (Element teasers : site.getElementsByClass("with-roof "))
        {
            sitesUrl.add(teasers.child(0).attributes().get("href"));
        }

        Element secondPart = site.getElementsByClass("css-1ugpt00 css-a9zu5q css-rrcue5 ").get(0);
        for (Element smallTeasers : secondPart.getElementsByTag("a"))
        {
            sitesUrl.add(smallTeasers.attributes().get("href"));
        }

        for (String url : sitesUrl) {
            site = Jsoup.connect(url).get();
            Element titleSection = site.getElementsByClass("item-main-content").get(0);
            words =titleSection.getElementsByTag("h1").get(0).text()+" ";
            for (Element subTitle : site.getElementsByClass("css-onxvt4")) {
                words+=(subTitle.text()+" ");
            }
            // צריכים למצוא גם את גוף הטקסט

            wordsSplit = words.split(" ");

            for (String word : wordsSplit)
            {
                if (map.containsKey(word))
                {
                    map.put(word, map.get(word) + 1);
                } else
                {
                    map.put(word, 1);
                }
            }
        }
        System.out.println(map);


    }

    @Override
    public Map<String, Integer> getWordsStatistics() {
        return null;
    }

    @Override
    public int countInArticlesTitles(String text) {
        return 0;
    }

    @Override
    public String getLongestArticleTitle() {
        return null;
    }
}
