package sourabh.androdev.inlass07;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView_Categories;
    String[] categories={"business","entertainment","general","health","science" ,"sports","technology"};
    ArrayAdapter<String> adapter;
   public static String STRING_KEY="STRINGKEY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         listView_Categories=findViewById(R.id.listView_Categories);
          adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
                             android.R.id.text1,categories);
          listView_Categories.setAdapter(adapter);
          listView_Categories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                  Intent intent=new Intent(MainActivity.this,NewsActivity.class);
                  String position=categories[i];
                  intent.putExtra(STRING_KEY,position);
                  startActivity(intent);
                  finish();
              }
          });




    }
}
