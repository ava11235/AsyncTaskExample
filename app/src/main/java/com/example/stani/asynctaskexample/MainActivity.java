package com.example.stani.asynctaskexample;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {

    //instance vars
    ProgressBar progressBar;
    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get reference to widgets
        progressBar = findViewById(R.id.progressBar);
        startButton = findViewById(R.id.button);

        //handle on click event
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //create an instance of Downloader class and exectue
                new Downloader().execute();
            }
        });

    }

    //inner class Downloader
    //AsyncTask runs in its own thread in the background
    class Downloader extends AsyncTask<Void, Integer, Integer>{

       @Override
       protected void onPreExecute(){
           super.onPreExecute();
           //set max value to 100
           progressBar.setMax(100);
       }


        @Override
        protected void onProgressUpdate(Integer[] values){
            super.onProgressUpdate(values);

            //update progress bar
            progressBar.setProgress(values[0]);
        }

        @Override
        protected Integer doInBackground(Void... voids) {

            //simulate a time consuming task
            for(int i = 0; i <100; i++){
                publishProgress(i);

                try{
                    Thread.sleep(100);
                }catch (InterruptedException ie){
                    ie.printStackTrace();
                }
            }

            return null;
        }


        protected void onPostExecute(Integer result){
            super.onPostExecute(result);
            Toast.makeText(getApplicationContext(), "Task Completed", Toast.LENGTH_LONG).show();

        }
    }
}
