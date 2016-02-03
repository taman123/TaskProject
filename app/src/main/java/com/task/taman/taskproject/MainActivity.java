package com.task.taman.taskproject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData();
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //         create an Object for Adapter
//        for (int i = 1; i <= 15; i++) {
//            FeedDetailClass st = new FeedDetailClass("property " + i);
//            studentList.add(st);
//        }


    }

    public void getData() {
        class GetData extends AsyncTask<List<FeedDetailClass>, String, List<FeedDetailClass>> {



            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog= new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Downloading Feed");
                progressDialog.setCancelable(false);
                progressDialog.show();
            }


            @Override
            protected List<FeedDetailClass> doInBackground(List<FeedDetailClass>... params) {
               List<FeedDetailClass> response=new ArrayList<FeedDetailClass>();
                String userResponse=null;
                ServiceHandler serviceHandler= new ServiceHandler();
                response=serviceHandler.makeServiceCall("http://52.68.64.112:1337/feed/getFeedByCreatorId/56810d610ff0351c5d9e262f");
//                UserFetcher userFetcher= new UserFetcher();
//                userResponse=userFetcher.makeServiceCall(usersUrl,response);
                return response;
            }

            @Override
            protected void onPostExecute(List<FeedDetailClass> feedDetailClasses) {
                progressDialog.dismiss();
//                TextView jsonResponse= (TextView) findViewById(R.id.json_response);
//                jsonResponse.setText(feedDetailClasses.size()+"");
                mAdapter = new CardAdapter(feedDetailClasses);


//               set the adapter object to the Recyclerview
                mRecyclerView.setAdapter(mAdapter);
            }
        }
        GetData getData= new GetData();
        getData.execute();

    }

}
