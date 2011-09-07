package com.fuzeez;

import java.io.IOException;
import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class ImageGridActivity extends Activity {
		
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
               // Toast.makeText(ImageGridActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                try {
                	String filename="sound_" + Integer.toString(position) + ".mp3";
                	AssetFileDescriptor afd = getAssets().openFd(filename); 
                	if(afd != null) {
                		MediaPlayer mp = new MediaPlayer();
                		mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(), afd.getLength());
                		mp.prepare();
                		mp.start();
                	}
                } catch (IOException e) {
                	e.printStackTrace();
                }
            }
        });
    }
}