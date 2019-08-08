package sourabh.androdev.inlass07;

import android.net.SSLCertificateSocketFactory;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.commons.io.IOUtils;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class AsyncJson extends AsyncTask<String,Void,ArrayList<News>> {
    TransferMethods transferMethods;

    public AsyncJson(TransferMethods transferMethods) {
        this.transferMethods = transferMethods;
    }

    @Override
    protected ArrayList<News> doInBackground(String... strings) {
        HttpURLConnection connection=null;
        String json=null;
        ArrayList<News> newsList=new ArrayList<News>();
        try {
            URL url=new URL(strings[0]);
            Log.d("demo",url.toString());
            connection= (HttpURLConnection) url.openConnection();
            Log.d("demao", "hello");

            if (connection instanceof HttpsURLConnection) {
                HttpsURLConnection httpsConn = (HttpsURLConnection) connection;
                httpsConn.setSSLSocketFactory(SSLCertificateSocketFactory.getInsecure(0, null));
                httpsConn.setHostnameVerifier(new AllowAllHostnameVerifier());
            }

            connection.setRequestMethod("GET");
            connection.connect();

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
                json= IOUtils.toString(connection.getInputStream(),"UTF8");
                Log.d("demo",json);
                JSONObject root=new JSONObject(json);
                JSONArray news=root.getJSONArray("articles");
                for (int i=0;i<news.length();i++){
                    JSONObject newsobj=news.getJSONObject(i);
                    News newsclass=new News();
                    newsclass.setTitle(newsobj.getString("title"));
                    newsclass.setTime(newsobj.getString("publishedAt"));
                    newsclass.setDescription(newsobj.getString("description"));
                    newsclass.setUrl(newsobj.getString("urlToImage"));

                    newsList.add(newsclass);
                }

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if(connection !=null){
                connection.disconnect();
            }
        }
        return newsList;
    }
    @Override
    protected void onPostExecute(ArrayList<News> newsArrayList) {
        if (newsArrayList.size()>0){
            transferMethods.newsListTransfer(newsArrayList);
        }
    }
    public static interface TransferMethods{
        public void newsListTransfer(ArrayList<News> data);
    }
}
