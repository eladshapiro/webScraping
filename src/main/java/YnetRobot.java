import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class YnetRobot extends BaseRobot
{
    private ArrayList<String> sitesUrl;
    private Document site;
    private Map<String, Integer> map;

    public YnetRobot() throws IOException
    {
        super("https://www.ynet.co.il/home/0,7340,L-8,00.html");
        site = Jsoup.connect(super.getRootWebsiteUrl()).get();
        sitesUrl = new ArrayList<>();
        map = new HashMap<>();
        String words;
        String[] wordsSplit;

        sitesUrl.add(site.getElementsByClass("slotTitle").get(0).child(0).attributes().get("href"));
        Element teasers = site.getElementsByClass("YnetMultiStripComponenta oneRow multiRows").get(0);
        for (Element mediaItems : teasers.getElementsByClass("mediaItems")) {
            sitesUrl.add(mediaItems.child(0).child(0).attributes().get("href"));
        }
        Element news = site.getElementsByClass("MultiArticleRowsManualComponenta").get(0);
        for (Element mediaItems : news.getElementsByClass("mediaItems")) {
            sitesUrl.add(mediaItems.child(0).child(0).attributes().get("href"));
        }
        for (Element slotTitle_small : news.getElementsByClass("slotTitle small"))
        {
            sitesUrl.add(slotTitle_small.child(0).attributes().get("href"));
        }

        for (String url : sitesUrl) {
            site = Jsoup.connect(url).get();
            words = (site.getElementsByClass("mainTitle").text()) + " ";
            words += (site.getElementsByClass("subTitle").text()) + " ";
            words += (site.getElementsByClass("text_editor_paragraph rtl").text());
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
