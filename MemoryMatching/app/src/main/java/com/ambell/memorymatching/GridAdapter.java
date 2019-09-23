package com.ambell.memorymatching;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class GridAdapter extends BaseAdapter {
    private Context context;
    private View view;
    private LayoutInflater layoutInflater;


    public GridAdapter(Context context) {
        this.context = context;

    }

    @Override
    public int getCount() {

        return 20;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            view = new View(context);
            view = layoutInflater.inflate(R.layout.single_item, null);
            final ImageView imageView = view.findViewById(R.id.image_view);
            imageView.setImageResource(R.drawable.blank);//setBackgroundResource(R.drawable.thumbs_down);

        }
        return view;

    }
}