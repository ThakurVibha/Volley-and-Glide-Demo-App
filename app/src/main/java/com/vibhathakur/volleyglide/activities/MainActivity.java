package com.vibhathakur.volleyglide.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.vibhathakur.volleyglide.R;
import com.vibhathakur.volleyglide.adapters.RecyclerViewAdapter;
import com.vibhathakur.volleyglide.model.Anim;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String URL_JSON = "https://gist.githubusercontent.com/aws1994/f583d54e5af8e56173492d3f60dd5ebf/raw/c7796ba51d5a0d37fc756cf0fd14e54434c547bc/anime.json";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<Anim> lstAnime = new ArrayList<>();
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        lstAnime =new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        jsonrequest();
    }

    private void jsonrequest() {
        request = new JsonArrayRequest(URL_JSON, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                Log.d("test","Warning!!");
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        Anim anim = new Anim();
                        anim.setName(jsonObject.getString("name"));
                        anim.setDescription(jsonObject.getString("description"));
                        anim.setRating(jsonObject.getString("Rating"));
                        anim.setCategorie(jsonObject.getString("categorie"));
                        anim.setNb_episode(jsonObject.getInt("episode"));
                        anim.setStudio(jsonObject.getString("studio"));
                        anim.setImage_url(jsonObject.getString("img"));
                        lstAnime.add(anim);
                        Log.d("test","Warning!!");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                setprecyclerview(lstAnime);
                Log.d("test","Warning!!");

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

requestQueue= Volley.newRequestQueue(MainActivity.this);
requestQueue.add(request);
    }

    private void setprecyclerview(List<Anim> lstAnime) {
        RecyclerViewAdapter myAdapter=new RecyclerViewAdapter(this,lstAnime);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myAdapter);
    }
}
