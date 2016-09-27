package com.dibi.tdflickr;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity
{

    String responseStr;

    private class GetItTask extends AsyncTask<String, Integer, Double>
    {
        @Override
        protected Double doInBackground(String... params)
        {
            String dataUrl = "http://www.flickr.com/services/feeds/photos_public.gne" + "?tags=" + params[0] + "&format=json";
            URL url;
            HttpURLConnection connection = null;
            try
            {
                url = new URL(dataUrl);
                connection = (HttpURLConnection) url.openConnection();
                InputStream is = connection.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                String line;
                StringBuffer response = new StringBuffer();
                while ((line = rd.readLine()) != null)
                {
                    response.append(line);
                    response.append('\r');
                }
                rd.close();
                responseStr = response.toString();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                if (connection != null)
                {
                    connection.disconnect();
                }
            }
            return null;
        }

        protected void onPostExecute(Double result)
        {
            Toast.makeText(MainActivity.this, responseStr, Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button getIt = (Button) findViewById(R.id.GetItButton);
        getIt.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                final EditText tag = (EditText) findViewById(R.id.TagInput);
                Toast.makeText(MainActivity.this, "getting " + tag.getText().toString(), Toast.LENGTH_SHORT).show();
                new GetItTask().execute(tag.getText().toString());
            }
        });


    }

}

// https://www.airpair.com/android/fragments-android-studio