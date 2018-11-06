package com.example.robin.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.wang.avi.AVLoadingIndicatorView;
import java.util.HashMap;
import java.util.Map;

public class LoginAdmin extends AppCompatActivity {

    String Server_url = "https://beholden-effects.000webhostapp.com/DomPowCom/login.php";
    public Button logg;
    EditText username,password;
    String idofcus,ps;
    TextView signup;
    AVLoadingIndicatorView avi;
    ImageView imageView;
    android.support.v4.app.FragmentTransaction fragmentTransaction;
    public ProgressBar p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);
        avi = findViewById(R.id.avi);
        username = findViewById(R.id.username1);
        password = findViewById(R.id.password1);
        signup = findViewById(R.id.signup);
        logg = findViewById(R.id.login);
        logg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                avi.show();
                post();
            }
        });
    }

    void post()
    {
        StringRequest request = new StringRequest(Request.Method.POST, Server_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String res=response.toString().trim();
                        if(!res.equals("0"))
                        {
                           /* SharedPreferences sharedPreferences=getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.putString("status",res);
                            editor.apply();*/


                            Toast.makeText(LoginAdmin.this,res,Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(LoginAdmin.this,MainActivity.class);
                            startActivity(intent);
//                            fragmentTransaction.remove(Login.this).commit();
                        }
                        else
                        {
                            avi.hide();
                            Toast.makeText(LoginAdmin.this, "invalid user name or password",Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String voll = error.toString();
                Toast.makeText(LoginAdmin.this,voll,Toast.LENGTH_LONG).show();
                avi.hide();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                idofcus = username.getText().toString();
                ps = password.getText().toString();
                params.put("cus",idofcus);
                params.put("ps",ps);
                return params;
            }
        };
        MySingleton.getInstance(LoginAdmin.this).addToRequestQue(request);
    }
}