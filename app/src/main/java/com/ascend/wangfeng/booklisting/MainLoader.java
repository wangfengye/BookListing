package com.ascend.wangfeng.booklisting;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fengye on 2017/9/25.
 * email 1040441325@qq.com
 */

public class MainLoader extends AsyncTaskLoader<List<Book>> {
    private static final String URL = "https://api.douban.com/v2/book/search?q=python";
    public static final String BOOKS = "books";
    public static final String TITLE = "title";
    public static final String AUTHOR = "author";

    public MainLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public List<Book> loadInBackground() {
        HttpURLConnection conn = null;
        BufferedReader in =null;
        StringBuilder responseStr= new StringBuilder();
        try {
            URL url = new URL(URL);
            conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            if (conn.getResponseCode() == 200) {
                in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                while ((inputLine=in.readLine())!=null){
                    responseStr.append(inputLine);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (conn!=null){
                conn.disconnect();
            }
        }
        return convert(responseStr.toString());
    }
    private List<Book> convert(String response){
        if (response==null) return null;
        List<Book> books = new ArrayList<>();
        try {
            JSONObject object =new JSONObject(response);
            JSONArray array = object.getJSONArray(BOOKS);
            for (int i = 0; i < array.length(); i++) {
                JSONObject data = array.getJSONObject(i);

                String title = data.getString(TITLE);
                JSONArray authorArray = data.getJSONArray(AUTHOR);
                //只取第一作者
                String author=null;
                if (authorArray.length()>0){
                   author = authorArray.getString(0);
                }
                Book book =new Book(title,author);
                books.add(book);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return books;
    }
}
