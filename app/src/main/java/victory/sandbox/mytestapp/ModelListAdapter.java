package victory.sandbox.mytestapp;


import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

public class ModelListAdapter extends ArrayAdapter<ListItemModel> {
    public ModelListAdapter(
            Context context, int resource, int textViewResourceId, List<ListItemModel> objects) {
        super(context, resource, textViewResourceId, objects);
    }
}
