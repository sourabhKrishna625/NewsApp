package sourabh.androdev.inlass07;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends ArrayAdapter<News> {
    public NewsAdapter(@NonNull Context context, int resource, @NonNull List<News> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        News news=getItem(position);
        ViewHodler viewHodler;
        if (convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.news_adapter,parent,false);
            viewHodler=new ViewHodler();
            viewHodler.textViewTitle=convertView.findViewById(R.id.textView_Title);
            viewHodler.textViewTime=convertView.findViewById(R.id.textView_Time);
            viewHodler.imageViewIcon=convertView.findViewById(R.id.imageView_icon);
            convertView.setTag(viewHodler);
        }else{
            viewHodler= (ViewHodler) convertView.getTag();
        }
        viewHodler.textViewTitle.setText(news.getTitle());
        viewHodler.textViewTime.setText(news.getTime());
        Picasso.get().load(news.getUrl()).into(viewHodler.imageViewIcon);

        return convertView;
    }
    private static class ViewHodler{
        TextView textViewTitle;
        TextView textViewTime;
        ImageView imageViewIcon;

    }
}
