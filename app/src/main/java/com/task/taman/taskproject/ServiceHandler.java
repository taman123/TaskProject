package com.task.taman.taskproject;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xerric on 2/3/2016.
 */
public class ServiceHandler {
    static String response = null;
    private URL responseURL;

    public ServiceHandler() {

    }

    public List<FeedDetailClass> makeServiceCall(String url) {
        InputStream inputStream = null;
        HttpURLConnection urlConnection = null;
        List<FeedDetailClass> result = new ArrayList<FeedDetailClass>();
        try {
            responseURL = new URL(url);
            urlConnection = (HttpURLConnection) responseURL.openConnection();

            urlConnection.setRequestProperty("Authorization", "JWT eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjp7ImVtYWlsIjoibWFuaXNoYWFAZ21haWwuY29tIiwiZmlyc3ROYW1lIjoibWFuaXNoIiwibGFzdE5hbWUiOiJtYWhhcmphbiIsInVzZXJUeXBlIjoiR2VuZXJhbCIsIm5pY2tOYW1lIjoiIiwiaXNWZXJpZmllZCI6ZmFsc2UsInByb2ZpbGVQaG90byI6IiIsImNvdmVyUGhvdG8iOiIiLCJyUG9pbnRzIjowLCJmb2xsb3dpbmdzIjpbXSwiZm9sbG93ZXJzIjpbXSwiZm9sbG93ZWRQYWdlcyI6W3siaWQiOiI1NWZiZDM1NDc1YjlkODM5Njk0ZGM2NGQifV0sImNyZWF0ZWRBdCI6IjIwMTUtMTAtMDdUMTI6MTQ6NDEuNzg4WiIsInVwZGF0ZWRBdCI6IjIwMTUtMTAtMDdUMTI6MTQ6NDEuNzg4WiIsImlkIjoiNTYxNTBjYjFiYTRiMmQxODFkMWQxNjQ4In0sImlhdCI6MTQ0NDIyMDA4MX0.zzMYZXpHXKmtcG8HJuYU3KRxY0fKGHSTOPvgABKY4b8");

            urlConnection.setRequestProperty("Content-Type", "application/json");

            urlConnection.setRequestProperty("Accept", "application/json");

            urlConnection.setRequestMethod("GET");


            int statuscode = urlConnection.getResponseCode();

            if (statuscode == 200) {
                inputStream = new BufferedInputStream(urlConnection.getInputStream());
                response = convertInputStreamToString(inputStream);
                result = parseResult(response);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null) {
            result += line;
        }
        if (result != null) {
            inputStream.close();
        }
        return result;
    }

    public List<FeedDetailClass> parseResult(String result) {
        JSONArray jsonArray = null;
        JSONObject jsonObject = null;
        JSONObject createrId = null;
        int likes;
        int comments;

        String details=null;
        List<FeedDetailClass> idList=new ArrayList<FeedDetailClass>();
        try {
            JSONObject response = new JSONObject(result);
            jsonArray = response.optJSONArray("objects");
            for(int i=0;i<jsonArray.length();i++) {
                jsonObject = jsonArray.getJSONObject(i);
                createrId = jsonObject.getJSONObject("creatorId");
                String name = createrId.getString("name");
                String profilePhoto = createrId.getString("profilePhoto");
                String feedType= jsonObject.getString("feedType");
                String message=jsonObject.getString("message");
                likes=jsonObject.getJSONArray("likes").length();
                comments=jsonObject.getJSONArray("comments").length();
//                Log.i("Details from card",name +" "+ profilePhoto+" "+ message+" "+ likes+" "+ comments);
//                details = name + " " + profilePhoto+ " " + feedType + " " +message +" " + likes + " "+ comments;
//                Log.i("Details: ",details);
                FeedDetailClass feedDetailClass= new FeedDetailClass();
                feedDetailClass.setName(name);
                feedDetailClass.setComments(comments);
                feedDetailClass.setLikes(likes);
                feedDetailClass.setMessage(message);
                feedDetailClass.setPhoto(profilePhoto);
//                feedDetailClass= new FeedDetailClass(name,message,profilePhoto,likes,comments);

                idList.add(feedDetailClass);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return idList ;
    }

}
