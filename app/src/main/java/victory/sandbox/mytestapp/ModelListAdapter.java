package victory.sandbox.mytestapp;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class ModelListAdapter extends ArrayAdapter<ListItemModel> {
    private List<ListItemModel> allModels;
    private LayoutInflater inflator;
    private View.OnTouchListener listener;
    public ModelListAdapter(
            Activity context, int resource, int textViewResourceId, List<ListItemModel> models, View.OnTouchListener listener) {
        super(context, resource, textViewResourceId, models);

        inflator = context.getLayoutInflater();
        allModels = new ArrayList<ListItemModel>();
        this.listener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        view.setOnTouchListener(listener);
        return view;
    }

}
