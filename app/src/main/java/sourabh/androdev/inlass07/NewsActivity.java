package sourabh.androdev.inlass07;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity implements AsyncJson.TransferMethods {
ArrayList<News> newsActivityList=new ArrayList<News>();
ListView listView_newsActivity;
public static String NEWOBJ_KEY="NEWSOBJ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        listView_newsActivity=findViewById(R.id.listView_newsActivity);

        if(getIntent() !=null && getIntent().getExtras()!=null){
         String keword=getIntent().getExtras().getString(MainActivity.STRING_KEY);
         if (isConnected()){
             new AsyncJson(NewsActivity.this).execute("https://newsapi.org/v2/top-headlines?country=de&"+keword+"=business&apiKey=1998c3c1ff6241ffbcc218db206cc280");
         }else{
             Toast.makeText(this, "no internet connection", Toast.LENGTH_SHORT).show();
         }

        }
    }
    private boolean isConnected(){
        ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if (networkInfo==null || !networkInfo.isConnected()
                ||(networkInfo.getType() != ConnectivityManager.TYPE_WIFI
                && networkInfo.getType() != ConnectivityManager.TYPE_MOBILE)){
            return false;
        }
        return true;
    }

    @Override
    public void newsListTransfer(ArrayList<News> data) {
        if (data==null){
            Toast.makeText(this, "no images retrieved", Toast.LENGTH_SHORT).show();
        }else {
            this.newsActivityList=data;
            NewsAdapter adapter=new NewsAdapter(this,R.layout.news_adapter,newsActivityList);
            listView_newsActivity.setAdapter(adapter);
            listView_newsActivity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent=new Intent(NewsActivity.this,Detail_Activity.class);
                    News newsObjSend=newsActivityList.get(i);
                    intent.putExtra(NEWOBJ_KEY,newsObjSend);
                    startActivity(intent);
                    finish();

                }
            });
        }

    }
}
