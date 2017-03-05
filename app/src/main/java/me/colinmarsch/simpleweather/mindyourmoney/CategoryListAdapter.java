package me.colinmarsch.simpleweather.mindyourmoney;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by colinmarsch on 2017-03-04.
 */

public class CategoryListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] categories;
    private final Integer[] balances;

    public CategoryListAdapter (Activity context, String[] categories, Integer[] balances) {
        super(context, R.layout.list, categories);

        this.context = context;
        this.categories = categories;
        this.balances = balances;
    }

    public View getView (int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list, null, true);

        TextView title = (TextView) rowView.findViewById(R.id.category);
        TextView remaining_bal = (TextView) rowView.findViewById(R.id.balance);

        title.setText(categories[position]);
        remaining_bal.setText("$" + balances[position].toString());
        return rowView;
    }
}
