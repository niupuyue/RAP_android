package net.example.paul.rapapp.domain;

import java.io.Serializable;

/**
 * Created by 牛谱乐
 * Timer 2017/2/13 17:45
 * E-mail niupuyue@togogo.net
 * 新闻类实体
 */
public class NewsInfo implements Serializable {
    private String uniquekey;

    private String title;

    private String date;

    private String category;

    private String author_name;

    private String url;

    private String thumbnail_pic_s;

    private String thumbnail_pic_s02;

    private String thumbnail_pic_s03;

    public void setUniquekey(String uniquekey) {
        this.uniquekey = uniquekey;
    }

    public String getUniquekey() {
        return this.uniquekey;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return this.category;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getAuthor_name() {
        return this.author_name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public void setThumbnail_pic_s(String thumbnail_pic_s) {
        this.thumbnail_pic_s = thumbnail_pic_s;
    }

    public String getThumbnail_pic_s() {
        return this.thumbnail_pic_s;
    }

    public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
        this.thumbnail_pic_s02 = thumbnail_pic_s02;
    }

    public String getThumbnail_pic_s02() {
        return this.thumbnail_pic_s02;
    }

    public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
        this.thumbnail_pic_s03 = thumbnail_pic_s03;
    }

    public String getThumbnail_pic_s03() {
        return this.thumbnail_pic_s03;
    }

}
