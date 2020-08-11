package com.example.rinkb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class login extends AppCompatActivity {

    EditText editEmail,editPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final Button btnSignUp = (Button) findViewById(R.id.btnSignUp);
        final RadioGroup rg = (RadioGroup) findViewById(R.id.rg);
        final RadioButton rp = (RadioButton) findViewById(R.id.radioPrivate);
        final RadioButton rc = (RadioButton) findViewById(R.id.radiCcorporate);
        final Button btnBack = (Button) findViewById(R.id.btnBack);
        final Button btnLogin = (Button) findViewById(R.id.btnLogin);
        editEmail = (EditText) findViewById(R.id.edit_login_email);
        editPwd = (EditText) findViewById(R.id.edit_login_pwd);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idEdit = editEmail.getText().toString();
                String pwdEdit = editPwd.getText().toString();
                new RestAPITaskLogin("http://101.101.161.189/api/index.php/linkb_member/login_member", idEdit, pwdEdit).execute();

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int privCorp = rg.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton) findViewById(privCorp);
                if (rb.getText().toString() == rp.getText().toString()) {
                    Intent intent = new Intent(getApplicationContext(), privateSignUp.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getApplicationContext(), coprateSignup.class);
                    startActivity(intent);
                }
            }
        });
    }

    public class RestAPITaskLogin extends AsyncTask<Integer, Void, String> {
        protected String mURL;
        protected String id, pwd;

        public RestAPITaskLogin(String url, String id, String pwd) {
            this.mURL = url;
            this.id = id;
            this.pwd = pwd;
        }

        @Override
        protected String doInBackground(Integer... params) {
            try {
                URL url = new URL(mURL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDefaultUseCaches(false);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
                conn.addRequestProperty("apikey", "starthub");

                StringBuffer buffer = new StringBuffer();
                buffer.append("email").append("=").append(id).append("&");
                buffer.append("password").append("=").append(pwd);

                OutputStreamWriter outputStream = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");

                PrintWriter writer = new PrintWriter(outputStream);
                writer.write(buffer.toString());
                System.out.println(buffer.toString());
                writer.flush();
                writer.close();

                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuilder builder = new StringBuilder();
                String str;
                while ((str = reader.readLine()) != null) {
                    builder.append(str + "\n");
                }
                String result = builder.toString();
                System.out.println(result);

                JSONObject jsonObject = new JSONObject(result);

                JSONObject postObject = jsonObject.getJSONObject("code");
                System.out.println(postObject.toString());
                System.out.println();
                String code = postObject.getString("code");
                String message = postObject.getString("message");
                System.out.println("code:" + code);
                System.out.println("message:" + message);

                return code;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(String code) {

            if (code.equals("207")) {
                Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_SHORT).show();
            } else if (code.equals("200")) {
                Toast.makeText(getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), main.class);
                startActivity(intent);

            } else if (code.equals("206")) {
                Toast.makeText(getApplicationContext(), "API Key가 틀렸습니다.", Toast.LENGTH_SHORT).show();
            } else if (code.equals("500")) {
                Toast.makeText(getApplicationContext(), "API 실행 중 시스템에서 발생한 에러입니다.", Toast.LENGTH_SHORT).show();
            }

        }
    }
}