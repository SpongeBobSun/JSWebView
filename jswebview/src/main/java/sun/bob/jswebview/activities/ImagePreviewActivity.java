package sun.bob.jswebview.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import dpl.bobsun.dummypicloader.DummyPicLoader;
import sun.bob.jswebview.R;

/**
 * Created by bob.sun on 15/7/28.
 */
public class ImagePreviewActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagepreviewactivity);
        DummyPicLoader.getInstance(this.getApplicationContext()).loadImageFromFile(getIntent().getStringExtra("image_file_path"), (ImageView) findViewById(R.id.id_imageview));
    }
}
