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
    private ArrayList<Article> text;

    public YnetRobot() throws IOException
    {
        super("https://www.ynet.co.il/home/0,7340,L-8,00.html");
        this.site = Jsoup.connect(super.getRootWebsiteUrl()).get();
        this.sitesUrl = new ArrayList<>();
        this.map = new HashMap<>();
        this.text=new ArrayList<>();

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
            Article article = new Article(site.getElementsByClass("mainTitle").text(),
                    site.getElementsByClass("subTitle").text(),
                    site.getElementsByClass("text_editor_paragraph rtl").text());
            text.add(article);
        }


    }

    @Override
    public Map<String, Integer> getWordsStatistics() {
        for (Article article : this.text) {
            String text = article.getText() + " " + article.getMainTitle() + " " + article.getSubTitle();

            String[] wordsSplit = new String[text.length()];
            wordsSplit = text.split(" ");

            for (String word : wordsSplit) {
                if (map.containsKey(word)) {
                    map.put(word, map.get(word) + 1);
                } else {
                    map.put(word, 1);
                }
            }
        }
        return map;
    }

    @Override
    public int countInArticlesTitles(String text) {
        return 0;
    }

    @Override
    public String getLongestArticleTitle() {
        String longestArticleTitle=new String();
        for (int i=0;i<this.text.size()-1;i++){
            if(text.get(i).getText().length()>text.get(i+1).getText().length()){
                longestArticleTitle=text.get(i).getMainTitle();
            }
            else longestArticleTitle=text.get(i+1).getMainTitle();
        }
        return longestArticleTitle;
    }
    }

