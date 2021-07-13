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

        sitesUrl.add(site.getElementsByClass("with-roof").get(0).child(0).attr("href"));
        Elements elements=site.getElementsByClass("main-taste").get(0).getElementsByTag("a");
        for (Element element : elements) {
            sitesUrl.add(element.attr("href"));
        }
        Element titleSection = null;
            for (String url : sitesUrl)
            {
                Article article = new Article("","","");
                site = Jsoup.connect(url).get();
                try {                                                        // walla was a strange thing that gives you another website, so that prevents it
                    titleSection = site.getElementsByClass("item-main-content").get(0);
                } catch (Exception e) {
                  //  e.printStackTrace();
                }
                article.setMainTitle(titleSection.getElementsByTag("h1").get(0).text());
                article.setSubTitle(titleSection.getElementsByTag("p").get(0).text());
                article.setText(site.getElementsByClass("css-onxvt4").text());
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
        public int countInArticlesTitles(String text)
        {
            int countHowMany = 0;

            for (Article article : this.text)
            {
                if (article.getMainTitle().contains(text))
                {
                    countHowMany++;
                }
                if (article.getSubTitle().contains(text))
                {
                    countHowMany++;
                }
            }
            return countHowMany;
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

