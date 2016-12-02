package eknow.com.eknow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import pl.polidea.webimageview.WebImageView;

public class testActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        WebImageView sampleWebImageView = (WebImageView) findViewById(R.id.id_sample);
        sampleWebImageView.setImageURL("http://upload.wikimedia.org/wikipedia/en/b/bc/Wiki.png");

    }

    /** Called when the user clicks the Send button */
    public void sendMessage() {
        Intent intent = new Intent(this,  CategoryListActivity.class);
        startActivity(intent);
    }
}
