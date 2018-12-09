package com.example.fileworker;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import static android.provider.Telephony.Mms.Part.FILENAME;

public class MainActivity extends Activity {
    EditText editText;
    private final static String name = "safefile.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.edit);
        ImageButton save = (ImageButton) findViewById(R.id.save);
        ImageButton open = (ImageButton) findViewById(R.id.open);
        ImageButton exit = (ImageButton) findViewById(R.id.exit);
    }


    public void openFile(View v) {
        try {
            InputStream inStream = openFileInput(name);

            if (inStream != null) {
                InputStreamReader tmp = new InputStreamReader(inStream);
                BufferedReader reader = new BufferedReader(tmp);
                String str;
                StringBuffer buffer = new StringBuffer();

                while ((str = reader.readLine()) != null) {
                    buffer.append(str + "\n");
                }
                inStream.close();
                editText.setText(buffer.toString());
            }
        } catch (Throwable t) {
            Toast.makeText(getApplicationContext(),
                    "Exception: " + t.toString(), Toast.LENGTH_LONG)
                    .show();
        }
    }

    public void saveFile(View v) {
        try {
            OutputStreamWriter outStream =
                    new OutputStreamWriter(openFileOutput(name, 0));

            outStream.write(editText.getText().toString());
            outStream.close();
        } catch (Throwable t) {
            Toast.makeText(getApplicationContext(),
                    "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
        }

    }
    public void exitApp(){
        finish();
        System.exit(0);
    }
}

