package edu.cmu.pocketsphinx.demo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.user.alarmapp.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import edu.cmu.pocketsphinx.demo.R;
//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.Statement;

//import java.sql.DriverManager;
//import java.sql.ResultSet;

public class MainActivity extends AppCompatActivity {
    private TextView info;
    private LoginButton loginButton;
    private  CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //AppLockManager.getInstance().enableDefaultAppLockIfAvailable(this);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.main);
        callbackManager = CallbackManager.Factory.create();
        info = (TextView)findViewById(R.id.info);

        /*LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });*/
        loginButton = (LoginButton) findViewById(R.id.login_button);
        //loginButton.setReadPermissions("user_friends");

        // If using in a fragment
        //loginButton.setFragment(this);
        // Other app specific specialization
        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
               //Intent mainintent = new Intent(PocketSphinxActivity.this, ListActivity.class);
                //startActivity(mainintent);
            }

            @Override
            public void onCancel() {
                info.setText("Login attempt canceled.");
            }
            @Override
            public void onError(FacebookException exception) {
                //info.setText("Login attempt failed.");
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        /*try{
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        }catch(Exception e){
            Toast.makeText(MainActivity.this, "Connection not established", Toast.LENGTH_SHORT).show();
        }
        try{

            Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/spamzombies", "root", "");
            Statement statement = (Statement) connection.createStatement();

            String query;
            query = "Select * from mailid";
            ResultSet result = statement.executeQuery(query);
            Toast.makeText(MainActivity.this, "query executed", Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
        }*/
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu_main, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}

