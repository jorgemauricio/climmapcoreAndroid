package com.example.carol.a1;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by carol on 14/01/2018.
 */

public class DescargaArchivosTXT extends AppCompatActivity {
    public void descargar(final String fechas, final int i ) {
        new Thread(new Runnable() {
            public void run() {
                    Log.i("step0", "it starts here");
                    URLConnection urlConnection = null;
                    // TODO Auto-generated method stub
                    try {
                        //fetching the URL
                        Log.i("step 1.1", "getting the url");
                        URL url = new URL(fechas);
                        Log.i("step 1.2", "captured the url");
                        urlConnection = url.openConnection();
                        Log.i("step 1.3", "captured the url");

                        urlConnection.connect();
                        Log.i("step 1", "fetching the URL");

                        //specifying path and file name
                        File sdcard = Environment.getExternalStorageDirectory();
                        File file = new File(sdcard, "d"+i+".csv");
                        Log.i("step 2", "specifying path and file name");

                        //Preparing for download
                        FileOutputStream fileOutput = new FileOutputStream(file);
                        InputStream inputStream = urlConnection.getInputStream();

                        byte[] buffer = new byte[1024*4];
                        int bufferLength = 0;
                        Log.i("step 3", "Preparing for download");


                        //Downloading
                        while ((bufferLength = inputStream.read(buffer)) > 0) {
                            fileOutput.write(buffer, 0, bufferLength);
                        }
                        fileOutput.close();
                        Log.i("step 4", "Downloading");

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

        }).start();

    }


}
