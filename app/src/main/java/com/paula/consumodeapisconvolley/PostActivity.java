package com.paula.consumodeapisconvolley;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PostActivity extends AppCompatActivity {

    private EditText editTextTitle;
    private EditText editTextBody;
    private Button buttonSubmit;
    private TextView textViewApiResult;

    private RequestQueue requestQueue;

    private static final String API_URL = "https://jsonplaceholder.typicode.com/posts";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post); // Usar el nuevo layout para POST

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextBody = findViewById(R.id.editTextBody);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        textViewApiResult = findViewById(R.id.textViewApiResult);

        requestQueue = Volley.newRequestQueue(this);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTextTitle.getText().toString().trim();
                String body = editTextBody.getText().toString().trim();

                if (title.isEmpty() || body.isEmpty()) {
                    Toast.makeText(PostActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    createPost(title, body);
                }
            }
        });
    }

    private void createPost(String title, String body) {
        JSONObject postData = new JSONObject();
        try {
            postData.put("title", title);
            postData.put("body", body);
            postData.put("userId", 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                API_URL,
                postData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String id = response.getString("id");
                            String responseTitle = response.getString("title");
                            String responseBody = response.getString("body");

                            String result = "Post creado exitosamente:\n" +
                                    "ID: " + id + "\n" +
                                    "Título: " + responseTitle + "\n" +
                                    "Cuerpo: " + responseBody;
                            textViewApiResult.setText(result);
                            Toast.makeText(PostActivity.this, "Post creado con ID: " + id, Toast.LENGTH_LONG).show();

                            editTextTitle.setText("");
                            editTextBody.setText("");

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(PostActivity.this, "Error al parsear la respuesta JSON: " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(PostActivity.this, "Error al crear post: " + error.getMessage(), Toast.LENGTH_LONG).show();
                        textViewApiResult.setText("Error al conectar con la API: " + error.getMessage());
                        error.printStackTrace();
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        requestQueue.add(jsonObjectRequest);
    }
}