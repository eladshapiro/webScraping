import java.util.Map;

public abstract class BaseRobot {
    private String rootWebsiteUrl;

    public BaseRobot(String rootWebsiteUrl) {

        this.rootWebsiteUrl = rootWebsiteUrl;
    }

    public String getRootWebsiteUrl() {

        return rootWebsiteUrl;
    }

    public void setRootWebsiteUrl(String rootWebsiteUrl) {

        this.rootWebsiteUrl = rootWebsiteUrl;
    }
    public String[]textToWord(String text){
        String[]word=new String[text.length()];
        int index=0;
        word[index]="";
        for (int i=0; i<text.length();i++){
            if(text.charAt(i)!=' '){
               word[index]+=text.charAt(i);
            }
            else {
                index++;
                word[index]="";
            }
        }
        return word;
    }

    public abstract Map<String, Integer> getWordsStatistics();

    public abstract int countInArticlesTitles(String text);

    public abstract String getLongestArticleTitle();


}
