package sourabh.androdev.inlass07;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class Detail_Activity extends AppCompatActivity {
    TextView textView_title;
    TextView textView_time;
    TextView textView_description;
    ImageView imageView_icon;
    News newsObj=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_);
        textView_title=findViewById(R.id.textView_title);
        textView_time=findViewById(R.id.textView_time);
        textView_description=findViewById(R.id.textView_description);
        imageView_icon=findViewById(R.id.imageView_icon);
        if(getIntent() !=null && getIntent().getExtras()!=null){
            newsObj= (News) getIntent().getExtras().getSerializable(NewsActivity.NEWOBJ_KEY);
            Log.d("demoac",newsObj.toString());

            textView_title.setText(newsObj.getTitle());
            textView_time.setText(newsObj.getTime());
            textView_description.setText(newsObj.getDescription());
            Picasso.get().load(newsObj.getUrl()).into(imageView_icon);
        }else{
            Toast.makeText(this, "null values", Toast.LENGTH_SHORT).show();
        }




    }
}
