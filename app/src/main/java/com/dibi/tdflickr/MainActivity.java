package com.dibi.tdflickr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

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
                doQuery(tag.getText().toString());
            }
        });


    }

    private void doQuery(String tag)
    {
        Toast.makeText(this, "getting " + tag, Toast.LENGTH_SHORT).show();
    }

}
