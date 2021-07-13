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
    private ArrayList<Article> text;
    private final String openUrl = "https://www.mako.co.il/";

    public MakoRobot() throws IOException {
        super("https://www.mako.co.il/");
        String currentUrl;
        this.site = Jsoup.connect(super.getRootWebsiteUrl()).get();
        this.sitesUrl = new ArrayList<>();
        this.map = new HashMap<>();
        this.text = new ArrayList<>();
        String words;
        String[] wordsSplit;

        for (Element teasers : site.getElementsByClass("teasers")) {
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
            Article article = new Article("","","");
            site = Jsoup.connect(url).get();
            article.setMainTitle(site.getElementsByTag("h1").get(0).text());
            article.setSubTitle (site.getElementsByTag("h2").text());
            Element element = site.getElementsByClass("article-body").get(0);
           article.setText(element.getElementsByTag("p").text());
           text.add(article);

        }
    }

    @Override
    public Map<String, Integer> getWordsStatistics()throws IOException {
        for (Article article :this.text) {
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
    public int countInArticlesTitles(String text) throws IOException
    {
        Document makoText = Jsoup.connect(getRootWebsiteUrl()).get();
        int countHowMany = 0;

        for (Element element : makoText.getElementsByTag("span"))
        {
            for (Element makoTitle : element.getElementsByAttributeValue("data-type", "title"))
            {
                if (makoTitle.text().contains(text))
                {
                    countHowMany++;
                }
            }
        }
        return countHowMany;
    }



    @Override
    public String getLongestArticleTitle() throws IOException {
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
