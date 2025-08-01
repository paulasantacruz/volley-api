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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Delete extends AppCompatActivity {
    private EditText editTextPostId;
    private Button buttonDelete;
    private TextView textViewApiResult;

    private RequestQueue requestQueue;

    private static final String BASE_API_URL = "https://jsonplaceholder.typicode.com/posts/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        editTextPostId = findViewById(R.id.editTextPostId);
        buttonDelete = findViewById(R.id.buttonDelete);
        textViewApiResult = findViewById(R.id.textViewApiResult);

        requestQueue = Volley.newRequestQueue(this);

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String postId = editTextPostId.getText().toString().trim();

                if (postId.isEmpty()) {
                    Toast.makeText(Delete.this, "Por favor, ingrese el ID del post", Toast.LENGTH_SHORT).show();
                } else {
                    deletePost(postId);
                }
            }
        });
    }

    private void deletePost(String postId) {
        String apiUrl = BASE_API_URL + postId;

        StringRequest stringRequest = new StringRequest(
                Request.Method.DELETE,
                apiUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String result = "Post con ID " + postId + " eliminado exitosamente.\n" +
                                "Respuesta de la API: " + (response.isEmpty() ? "Objeto vacío" : response);
                        textViewApiResult.setText(result);
                        Toast.makeText(Delete.this, "Post " + postId + " eliminado", Toast.LENGTH_LONG).show();
                        editTextPostId.setText("");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Delete.this, "Error al eliminar post: " + error.getMessage(), Toast.LENGTH_LONG).show();
                        textViewApiResult.setText("Error al conectar con la API: " + error.getMessage());
                        error.printStackTrace();
                    }
                }
        );
        requestQueue.add(stringRequest);
    }
}