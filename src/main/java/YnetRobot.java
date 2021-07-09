import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class YnetRobot extends BaseRobot
{
    private Map<String, Integer> map = new HashMap<>();
    private  ArrayList<String> sitesUrl;

    public YnetRobot() throws IOException
    {
        super("https://www.ynet.co.il/home/0,7340,L-8,00.html");
        String url;
        sitesUrl = new ArrayList<>();
        Document ynet = Jsoup.connect(getRootWebsiteUrl()).get();
        //teasers section
        for (Element slotTitle : ynet.getElementsByClass("TopStory1280Componenta basic")) {
            Element element = slotTitle.getElementsByClass("slotTitle").get(0);
            url = element.child(0).attributes().get("href");
            sitesUrl.add(url);
        }
        for (Element teasers : ynet.getElementsByClass("YnetMultiStripComponenta oneRow multiRows")) {
            for (Element textDiv : teasers.getElementsByClass("textDiv")) {
                url = textDiv.child(1).attributes().get("href");
                sitesUrl.add(url);
            }
        }
        //news section
        for (Element slotsContent : ynet.getElementsByClass("MultiArticleRowsManualComponenta").get(0).getElementsByClass("slotsContent")) {
            for (Element slotTitle_medium : slotsContent.getElementsByClass("slotTitle medium")) {
                url = slotTitle_medium.child(0).attributes().get("href");
                sitesUrl.add(url);
            }
            for (Element slotTitle_small : slotsContent.getElementsByClass("slotTitle small")) {
                url = slotTitle_small.child(0).attributes().get("href");
                sitesUrl.add(url);
            }
        }
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
