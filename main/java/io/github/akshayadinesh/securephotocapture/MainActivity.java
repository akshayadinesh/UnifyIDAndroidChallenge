package io.github.akshayadinesh.securephotocapture;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.VideoView;

import java.io.File;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_VIDEO_CAPTURE = 1;
    VideoView videoView;
    File videoFile;
    int[] imageViewIDs = {R.id.image1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = (VideoView) findViewById(R.id.videoView);
    }

    public void startRecording(View view) {
        dispatchTakeVideoIntent();
    }

    private void dispatchTakeVideoIntent() {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        takeVideoIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 6);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Uri videoUri = intent.getData();
            String uri = videoUri.toString();
            videoFile = new File(uri);
            videoView.setVideoURI(videoUri);
            videoView.start();
            extractImages(uri);
        }
    }

    public void extractImages(String uri) {

        ImageView imageView = (ImageView) findViewById(R.id.image1);

        try {

            imageView.setImageBitmap(retrieveVideoFrameFromVideo(uri.toString()));

        } catch(Exception e) {
            e.printStackTrace();
        }


//        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
//
//        Log.d("msg", "retriever initialized");
//
//        Log.d("msg", "absolute file path: " + videoFile.getAbsolutePath());
//
//        Log.d("msg", uri);
//
//        try {
//
//            retriever.setDataSource(videoFile.getAbsolutePath());
//
//            Log.d("msg", "file path: " + videoFile.getAbsolutePath());
//
//            ImageView view = (ImageView)findViewById(R.id.image1);
//
//            view.setImageBitmap(retriever.getFrameAtTime(500000, MediaMetadataRetriever.OPTION_CLOSEST));
//
//        } catch (IllegalArgumentException ex) {
//
//            ex.printStackTrace();
//
//        }

    }

    public static Bitmap retrieveVideoFrameFromVideo(String videoPath)
    {
        Bitmap bitmap = null;
        MediaMetadataRetriever mediaMetadataRetriever = null;
        try
        {
            mediaMetadataRetriever = new MediaMetadataRetriever();
            if (Build.VERSION.SDK_INT >= 14)
                mediaMetadataRetriever.setDataSource(videoPath, new HashMap<String, String>());
            else
                mediaMetadataRetriever.setDataSource(videoPath);
            //   mediaMetadataRetriever.setDataSource(videoPath);
            bitmap = mediaMetadataRetriever.getFrameAtTime();
        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
        finally
        {
            if (mediaMetadataRetriever != null)
            {
                mediaMetadataRetriever.release();
            }
        }
        return bitmap;
    }
}
