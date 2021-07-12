import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WallaRobot extends BaseRobot {
    private ArrayList<String> sitesUrl;
    private Document site;
    private Map<String, Integer> map;
    private ArrayList<Article> text;

    public WallaRobot() throws IOException {
        super("https://www.walla.co.il/");
        this.site = Jsoup.connect(super.getRootWebsiteUrl()).get();
        this.sitesUrl = new ArrayList<>();
        this.map = new HashMap<>();
        this.text=new ArrayList<>();
        for (Element teasers : site.getElementsByClass("with-roof ")) {
            sitesUrl.add(teasers.child(0).attributes().get("href"));
        }

        Element secondPart = site.getElementsByClass("css-1ugpt00 css-a9zu5q css-rrcue5 ").get(0);
        for (Element smallTeasers : secondPart.getElementsByTag("a")) {
            sitesUrl.add(smallTeasers.attributes().get("href"));
        }
        Element titleSection;
        for (String url : sitesUrl)
        {
            Article article = new Article("","","");
            site = Jsoup.connect(url).get();

            titleSection = site.getElementsByClass("item-main-content").get(0);
            article.setMainTitle(titleSection.getElementsByTag("h1").get(0).text());
            article.setSubTitle(titleSection.getElementsByTag("p").get(0).text());
            for (Element subTitle : site.getElementsByClass("css-onxvt4")) {
                article.setText(article.getText() + " " + subTitle.text());
            }
            text.add(article);
        }
    }


    @Override
    public Map<String, Integer> getWordsStatistics() {
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
        public int countInArticlesTitles (String text){
            return 0;
        }

        @Override
        public String getLongestArticleTitle () {
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

