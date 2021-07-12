public class Article {
    private String mainTitle;
    private String subTitle;
    private String text;

    public Article(String mainTitle, String subTitle, String text) {
        this.mainTitle = mainTitle;
        this.subTitle = subTitle;
        this.text = text;
    }

    public String getMainTitle() {
        return mainTitle;
    }

    public void setMainTitle(String mainTitle) {
        this.mainTitle = mainTitle;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
