package com.ascend.wangfeng.booklisting;

/**
 * Created by fengye on 2017/9/25.
 * email 1040441325@qq.com
 */

public class Book {
    private String title;
    private String author;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Book(String title, String author) {

        this.title = title;
        this.author = author;
    }
}
