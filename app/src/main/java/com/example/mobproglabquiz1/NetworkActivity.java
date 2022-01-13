package com.example.mobproglabquiz1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
        String url = "http://103.150.98.211:3000/user";

        StringRequest stringGetRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("RESULT", response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            userModels = new ArrayList<>();
//                            ImageView ppImageView = findViewById(R.id.profile_picture_image_view);
//                            userModels.add(new UserModel(2, "user1@gmail.com", "Aiko Meiko", "pa55W0rd!"));
//                            userModels.add(new UserModel(3, "user2@gmail.com", "Nava Rone", "pAssw0rd!"));
//                            userModels.add(new UserModel(4, "user3@gmail.com", "Cia Gyu", "Gyu!123"));

                            JSONArray userDataJSONArray = jsonObject.getJSONArray("data");
                            for(int i = 0; i < userDataJSONArray.length(); i++){
                                JSONObject userDataJSONObject = userDataJSONArray.getJSONObject((i));
                                UserModel userModel = new UserModel(
                                        userDataJSONObject.getString("id"),
                                        userDataJSONObject.getString("email"),
                                        userDataJSONObject.getString("fullname"),
                                        userDataJSONObject.getString("password"),
                                        userDataJSONObject.getString("salt")
                                );
                                userModels.add(userModel);
                            }
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
        queue.add(stringGetRequest);
    }

    class UserModelViewHolder extends RecyclerView.ViewHolder {

        TextView emailTextView;
        TextView nameTextView;
        TextView idTextView;
        TextView passwordTextView;
        TextView saltTextView;

//    View rootView;

        public UserModelViewHolder(@NonNull View itemView) {
            super(itemView);
            emailTextView = itemView.findViewById(R.id.emailInput);
            nameTextView = itemView.findViewById(R.id.fullNameInput);
//            idTextView = itemView.findViewById(R.id.id);
            passwordTextView = itemView.findViewById(R.id.passwordInput);
//            saltTextView = itemView.findViewById(R.id.user_info_view_salt_text);
        }
    }
}