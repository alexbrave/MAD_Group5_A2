/*
*	PROJECT: PROG3150 - ASSIGNMENT 2
*	FILE: ASyncDownload.java
*	PROGRAMMER: Nghia Nguyen, Alex Braverman, Andrey Takhtamirov, Leon Vong
*	FIRST VERSION: 2020/04/08
*	DESCRIPTION:
		This file is the loading screen that downloads needed files for our app to run using threads and internet protocols.
*/
package com.example.group5_a2;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/*
 *  NAME : ASyncDownload
 *  PURPOSE : The purpose of this class is to display our loading screen and load files to our MAD directory.
 */
public class ASyncDownload extends AppCompatActivity {
    //Files to load
    private String[] downloadables =
            {
                    "https://1.bp.blogspot.com/-NxPvnk5ituI/W3aRKOKoXfI/AAAAAAAAPX8/36CvGPzhDNIYIPtizgymKMvzwNsUIiaWgCLcBGAs/s640/509921-nep.jpg",
                    "https://cdn.discordapp.com/attachments/822191686058115113/822191866279624704/img1.jpg",
                    "https://cdn.discordapp.com/attachments/822191686058115113/822191890623234098/img2.jpg",
                    "https://cdn.discordapp.com/attachments/822191686058115113/822191902576214074/img3.jpg"
            };

    private Context mContext=ASyncDownload.this;
    private ProgressBar progressBar;
    private static final int REQUEST = 112;

    private int currentDownloadIterator;


    /*
     *	Function: onCreate(Bundle savedInstanceState)
     *	Description:
     *       The purpose of this function is to create an instance of the ASyncDownload class.
     *	Parameter: Bundle savedInstanceState: The state of the instance
     *	Return: void: Not return anything
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Set to the loading view
        setContentView(R.layout.loading);

        //Set default values
        currentDownloadIterator = 0;
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        //Request for permissions to use storage
        if (Build.VERSION.SDK_INT >= 23) {
            String[] PERMISSIONS = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
            if (!hasPermissions(mContext, PERMISSIONS)) {
                ActivityCompat.requestPermissions((Activity) mContext, PERMISSIONS, REQUEST );
            } else {
                new DownloadFileFromURL().execute(downloadables[currentDownloadIterator]);
            }
        } else {
            //Toast that the version of android is not supported
            Toast.makeText(mContext, "Version of Android is Invalid", Toast.LENGTH_LONG).show();
        }
    }


    /*
     *	Function: onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
     *	Description:
     *       This function validates the request permission and its results
     *	Parameter:
     * int requestCode                  : Request Code To Listen For
     * @NonNull String[] permissions    : Permission Check
     * @NonNull int[] grantResults      : Results
     *	Return: void: Not return anything
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    new DownloadFileFromURL().execute(downloadables[currentDownloadIterator]);
                } else {
                    Toast.makeText(mContext, "The app was not allowed to write in your storage", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    /*
     *	Function: hasPermissions(Context context, String... permissions)
     *	Description:
     *       The purpose of this function is to obtain permissions from the system for our application
     *	Parameter:  Context context         :  Activity Context
     *              String... permissions   :  Permission
     *	Return: boolean: true if successful, false otherwise
     */
    private static boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    /*
     *  NAME : DownloadFileFromURL
     *  PURPOSE : ASync class that downloads files from a URL
     */
    class DownloadFileFromURL extends AsyncTask<String, String, String> {


        /*
         *	Function: doInBackground(String... f_url)
         *	Description:
         *       The purpose of this function is to start downloading the file
         *	Parameter:  f_url   :   List of arguments for download string
         *	Return: boolean: true if successful, false otherwise
         */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {

                //Get root and create directory
                String root = Environment.getExternalStorageDirectory().toString();
                File directory = new File(root+"/MAD/");
                if(!directory.exists()){
                    directory.mkdir();
                }

                //Get URL and make connection
                URL url = new URL(f_url[0]);
                URLConnection connection = url.openConnection();
                connection.connect();

                // Input stream to read file
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                //Name of new file
                String fileName = f_url[0].substring(f_url[0].lastIndexOf("/"));

                // Output stream to write file
                OutputStream output = new FileOutputStream(root+"/MAD/"+ fileName);


                //Save file
                byte data[] = new byte[1024];
                long total = 0;
                while ((count = input.read(data)) != -1) {
                    total += count;
                    // Writing
                    output.write(data, 0, count);
                }

                //Flushing output
                output.flush();

                //Closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return null;
        }


        /*
         *	Function: onPostExecute(String file_url)
         *	Description:
         *       Post result of our download
         *	Parameter:  file_url   :  URL of our download
         *	Return: Void : None
         */
        @Override
        protected void onPostExecute(String file_url) {
            currentDownloadIterator++;

            if (currentDownloadIterator < downloadables.length)
            {
                //Wait a second before starting next download
                try {
                    Thread.sleep(1 * 1000);
                } catch(InterruptedException e) {
                    System.out.println("Sleeping has stopped");
                }

                double progression = 100 *  ((double)currentDownloadIterator/downloadables.length);
                progressBar.setProgress((int)progression);

                //Start next download
                new DownloadFileFromURL().execute(downloadables[currentDownloadIterator]);
            }
            else
            {
                //Update Progress Bar to Max
                double progression = 100 *  ((double)currentDownloadIterator/downloadables.length);
                progressBar.setProgress((int)progression);

                //Wait 3 second before starting next download
                try {
                    Thread.sleep(3 * 1000);
                } catch(InterruptedException e) {
                    System.out.println("Sleeping has stopped");
                }

                //Open next screen
                Intent intent = new Intent(ASyncDownload.this, ChooseHotelActivity.class);
                startActivity(intent);
                Toast.makeText(ASyncDownload.this,
                        R.string.choose_hotel_layout_label,
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}
