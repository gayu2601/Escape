package edu.cmu.pocketsphinx;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import edu.cmu.pocketsphinx.demo.R;

import static android.widget.Toast.makeText;
import static edu.cmu.pocketsphinx.SpeechRecognizerSetup.defaultSetup;

public class ListActivity extends AppCompatActivity implements RecognitionListener{
    ListView listView;
    private static Intent intent;
    private static final String KWS_SEARCH = intent.getStringExtra(Change1.EXTRA_MESSAGE1);
    private static final String FORECAST = intent.getStringExtra(Change2.EXTRA_MESSAGE1);;
    //private static final String DIGITS_SEARCH = "";


    /* Keyword we are looking for to activate menu */
    private static final String KEYPHRASE = intent.getStringExtra(list1.EXTRA_MESSAGE1);

    private SpeechRecognizer recognizer;
    private HashMap<String, Integer> captions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = (ListView) findViewById(R.id.list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        captions = new HashMap<>();
        captions.put(KWS_SEARCH, R.string.kws_caption);

        String[] values = new String[] { "change the recognizer", "change the keywords"
        };
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);

        listView.setAdapter(adapter1);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                if (position == 0) {
                    Intent newActivity = new Intent(ListActivity.this, ListActivity.class);
                    startActivity(newActivity);
                    Intent newActivity1 = new Intent(ListActivity.this, list1.class);
                    startActivity(newActivity1);
                } else if (position == 1) {
                    Intent newActivity1 = new Intent(ListActivity.this, ChangelistActivity.class);
                    startActivity(newActivity1);
                } else if (position == 2) {
                    Intent newActivity1 = new Intent(ListActivity.this, Change1.class);
                    startActivity(newActivity1);
                } else if (position == 3) {
                    Intent newActivity1 = new Intent(ListActivity.this, Change2.class);
                    startActivity(newActivity1);
                }
            }

        });


        new AsyncTask<Void, Void, Exception>() {
            @Override
            protected Exception doInBackground(Void... params) {
                try {
                    Assets assets = new Assets(ListActivity.this);
                    File assetDir = assets.syncAssets();
                    setupRecognizer(assetDir);
                } catch (IOException e) {
                    return e;
                }
                return null;
            }

            @Override
            protected void onPostExecute(Exception result) {
                if (result != null) {

                } else {
                    switchSearch(KWS_SEARCH);
                }
            }
        }.execute();

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        recognizer.cancel();
        recognizer.shutdown();
    }


    @Override
    public void onPartialResult(Hypothesis hypothesis) {
        if (hypothesis == null)
            return;

        String text = hypothesis.getHypstr();
        if (text.equals(KEYPHRASE)) {
            switchSearch(KWS_SEARCH);

        } else if (text.equals(FORECAST)) {
            switchSearch(FORECAST);

        } else {


        }
    }

    @Override
    public void onResult(Hypothesis hypothesis) {

        if (hypothesis != null) {
            String text = hypothesis.getHypstr();
            makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBeginningOfSpeech() {
    }


    @Override
    public void onEndOfSpeech() {
        if (!recognizer.getSearchName().equals(KWS_SEARCH))
            switchSearch(KWS_SEARCH);
    }

    private void switchSearch(String searchName) {
        recognizer.stop();

        // If we are not spotting, start listening with timeout (10000 ms or 10 seconds).
        if (searchName.equals(KWS_SEARCH))
            recognizer.startListening(searchName);
        else
            recognizer.startListening(searchName);

        String caption = getResources().getString(captions.get(searchName));

    }

    private void setupRecognizer(File assetsDir) throws IOException {
        // The recognizer can be configured to perform multiple searches
        // of different kind and switch between them

        recognizer = defaultSetup()
                .setAcousticModel(new File(assetsDir, "en-us-ptm"))
                .setDictionary(new File(assetsDir, "cmudict-en-us.dict"))


                .setRawLogDir(assetsDir)


                .setKeywordThreshold(1e-45f)


                .setBoolean("-allphone_ci", true)

                .getRecognizer();
        recognizer.addListener(this);


        recognizer.addKeyphraseSearch(KWS_SEARCH, KEYPHRASE);


        File languageModel = new File(assetsDir, "weather.dmp");
        recognizer.addNgramSearch(FORECAST, languageModel);



    }

    @Override
    public void onError(Exception error) {
        //((TextView) findViewById(R.id.caption_text)).setText(error.getMessage());
    }

    @Override
    public void onTimeout() {
        switchSearch(KWS_SEARCH);
    }


}
