package eknow.com.eknow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                sendMessage();
                Toast.makeText(MainActivity.this, id + "  " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /** Called when the user clicks the Send button */
    public void sendMessage() {
        Intent intent = new Intent(this,  CategoryListActivity.class);
        startActivity(intent);
    }
}
