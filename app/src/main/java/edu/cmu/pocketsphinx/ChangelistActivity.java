package edu.cmu.pocketsphinx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import edu.cmu.Change3;
import edu.cmu.pocketsphinx.demo.R;

public class ChangelistActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changelist);
        listView = (ListView) findViewById(R.id.list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent = null;
        assert intent != null;
        String[] values = new String[] {intent.getStringExtra(Change1.EXTRA_MESSAGE1),intent.getStringExtra(Change2.EXTRA_MESSAGE1)
        };
        ArrayAdapter<String> adapter1= new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_2, android.R.id.text2, values);
        /*ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_3, android.R.id.text3, values);*/

        listView.setAdapter(adapter1);
        listView.setAdapter(adapter2);
        //listView.setAdapter(adapter3);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent intent = new Intent(ChangelistActivity.this, Change1.class);
                startActivity(intent);
            }

        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent intent = new Intent(ChangelistActivity.this, Change2.class);
                startActivity(intent);
            }

        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent intent = new Intent(ChangelistActivity.this, Change3.class);
                startActivity(intent);
            }

        });
    }
}
