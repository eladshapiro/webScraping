import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MakoRobot extends BaseRobot
{
    private ArrayList<String> sitesUrl;
    private Document site;
    private Map<String, Integer> map;
    private final String openUrl = "https://www.mako.co.il/";

    public MakoRobot() throws IOException
    {
        super("https://www.mako.co.il/");
        String currentUrl;
        site = Jsoup.connect(super.getRootWebsiteUrl()).get();
        sitesUrl = new ArrayList<>();
        map = new HashMap<>();
        String words;
        String[] wordsSplit;

        for (Element teasers : site.getElementsByClass("teasers"))
        {
            for (Element child : teasers.children()) {
                currentUrl = child.child(0).child(0).attributes().get("href");
                if (currentUrl.contains(openUrl)) {
                    sitesUrl.add(currentUrl);
                } else {
                    sitesUrl.add(openUrl + currentUrl);
                }
            }

            for (Element news : site.getElementsByClass("neo_ordering scale_image horizontal news")) {
                for (Element h5 : news.getElementsByTag("h5")) {
                    currentUrl = h5.child(0).attributes().get("href");
                    if (currentUrl.contains(openUrl)) {
                        sitesUrl.add(currentUrl);
                    } else {
                        sitesUrl.add(openUrl + currentUrl);
                    }
                }
            }
        }



        for (String url : sitesUrl) {
            site = Jsoup.connect(url).get();
            words = (site.getElementsByTag("h1").get(0).text()) + " ";
            words += (site.getElementsByTag("h2").text()) + " ";
            Element article = site.getElementsByClass("article-body").get(0);
            for (Element p : article.getElementsByTag("p"))
            {
                words += p.text()+ " ";
            }

            wordsSplit = words.split(" ");
            for (String word : wordsSplit) {
                if (map.containsKey(word)) {
                    map.put(word, map.get(word) + 1);
                } else {
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
