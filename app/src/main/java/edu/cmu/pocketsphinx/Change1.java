package edu.cmu.pocketsphinx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import edu.cmu.pocketsphinx.demo.PocketSphinxActivity;
import edu.cmu.pocketsphinx.demo.R;

public class Change1 extends AppCompatActivity {
    public final static String EXTRA_MESSAGE1 = "com.example.user.myapp.MESSAGE1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change1);
    }
    public void sendMessage(View view) {
        Intent pocketintent = new Intent(this, PocketSphinxActivity.class);
        EditText editText1 = (EditText) findViewById(R.id.edit1);
        String message1 = editText1.getText().toString();
        pocketintent.putExtra(EXTRA_MESSAGE1, message1);
        startActivity(pocketintent);
    }
}
