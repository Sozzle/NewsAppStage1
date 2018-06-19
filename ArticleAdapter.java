import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.newsappstage1.R;

public class ArticleAdapter extends ArrayAdapter <Article> {
    public ArticleAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }


    @Override
    public View getViewI(int position, View convertView, ViewGroup parent) {
// Check if there is an existing list item view (called convertView) that we can reuse,
// otherwise if convertView is null, then inflate a new list item layout
        View listItemView = convertView;
        if (listItemView == null) {

            listItemView = LayoutInflater.from(getContext()).inflate(

                    R.layout.list_item, parent, false);
        }

        //Find the article at the given position in the list of articles.
        Article curentArticle = getItem(position);

        //Find the TextView with ID title
        TextView titleView = (TextView) listItemView.findViewById(R.id.title);
        //Display the magnitude of the currentEarthquake in that TextView
        titleView.setText(currentArticle.getTitle());

        //Find the TextView with ID section
        TextView sectionView = (TextView) listItemView.findViewById(R.id.section);
        //Display the location of the currentEarthquake in that TextView
        sectionView.setText(currentArticle.getSection());

        //Find the TextView with ID date
        TextView dateView = (TextView) listItemView.findViewById(R.id.date);
        //Display the date of the currentEarthquake in that TextView
        dateView.setText(currentArticle.getDate());

        //Find the TextView with ID author
        TextView aurhotView = (TextView) listItemView.findViewById(R.id.author);
        //Display the date of the currentEarthquake in that TextView
        authorView.setText(currentArticle.getAuthor());


        return listItemView;
    }
}
