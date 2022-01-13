package com.example.mobproglabquiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mobproglabquiz1.models.UserModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RegisterPage extends AppCompatActivity {

    Button registerButton;
    ArrayList<UserModel> userModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        registerButton = findViewById(R.id.activity_register_page_register_button);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://103.150.98.211:3000/user";
        
        //send data to database
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
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
                                        userDataJSONObject.getString("fullname"), //R.id.fullNameInput
                                        userDataJSONObject.getString("email"),
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
        queue.add(stringRequest);

        registerButton.setOnClickListener((view) -> {
            Intent intent = new Intent(this, LoginPage.class);
            startActivity(intent);
        });
    }

    private void registerPageForm(String firstName, String lastName, String email, String password) {
        Intent intent = new Intent();

        //store data in database
    }
}