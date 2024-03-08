package com.example.jdbc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    Button btn_back;
    ListView listView;
    List<UserData> userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        new DataAsync().execute();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void initView() {
        btn_back=findViewById(R.id.btn_back);
        listView = findViewById(R.id.listView);
        textView = findViewById(R.id.textView);
        userList = new ArrayList<>();
    }
    private class DataAsync extends AsyncTask<Void, Void, List<UserData>> {
        @Override
        protected void onPostExecute(List<UserData> data) {
            super.onPostExecute(data);
            if (userList != null) {
                DataAdapter adapter = new DataAdapter(MainActivity.this, R.layout.data_row_item, data);
                listView.setAdapter(adapter);
            }
        }
        @Override
        protected List<UserData> doInBackground(Void... voids) {
            try {
                // Load the MySQL JDBC driver
                Class.forName("com.mysql.jdbc.Driver");

                // Replace the placeholders with your MySQL server details
                String url = "jdbc:mysql://10.0.2.2:3306/login_schema";
                String username = "root";
                String password = "c3@@admin";

                // Establish the connection
                Connection connection = DriverManager.getConnection(url, username, password);

                // Execute a simple query
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM USERS");

                while (resultSet.next()) {
                    String us = resultSet.getString("username");
                    String pass = resultSet.getString("password");

                    userList.add(new UserData(us, pass));
                }
                // Close the connections
                resultSet.close();
                statement.close();
                connection.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return userList;
        }
    }
}