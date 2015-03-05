package victory.sandbox.mytestapp;


import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

public class ModelListAdapter extends ArrayAdapter<ListItemModel> {
    private View.OnTouchListener listener;
    public ModelListAdapter(
            Activity context, int resource, int textViewResourceId, List<ListItemModel> models, View.OnTouchListener listener) {
        super(context, resource, textViewResourceId, models);
        this.listener = listener;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        view.setOnTouchListener(listener);
        view.setTag(position);
        return view;
    }

}
