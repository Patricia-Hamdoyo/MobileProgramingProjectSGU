package com.example.mobproglabquiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.mobproglabquiz1.models.UserModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NetworkActivity extends AppCompatActivity {

    ArrayList<UserModel> userModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://reqres.in/api/users";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("RESULT", response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            userModels = new ArrayList<>();
                            ImageView ppImageView = findViewById(R.id.profile_picture_image_view);

                            JSONArray userDataJSONArray = jsonObject.getJSONArray("data");
                            for(int i = 0; i < userDataJSONArray.length(); i++){
                                JSONObject userDataJSONObject = userDataJSONArray.getJSONObject((i));
                                UserModel userModel = new UserModel(
                                        userDataJSONObject.getString("id"),
                                        userDataJSONObject.getString("email"),
                                        userDataJSONObject.getString("first_name"),
                                        userDataJSONObject.getString("last_name"),
                                        userDataJSONObject.getString("avatar")
                                );
                                userModels.add(userModel);
                            }

                            for(UserModel um : userModels){
                                Log.d("USER MODEL", um.getFirstName());
                                Log.d("USER MODEL", um.getAvatar());
                            }

//                            Glide
//                                    .with(NetworkActivity.this)
//                                    .load("https://reqres.in/img/faces/4-image.jpg")
//                                    .circleCrop()
////                                    .placeholder(R.drawable.ic_baseline_lock_24)
//                                    .into(ppImageView);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("FETCH_ERROR", error.networkResponse.toString());
                    }
                }
        );
        queue.add(stringRequest);
    }
}