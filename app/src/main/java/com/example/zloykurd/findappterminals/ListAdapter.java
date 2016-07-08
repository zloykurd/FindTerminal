package com.example.zloykurd.findappterminals;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZloykurD on 18.06.2016.
 */
public class ListAdapter extends BaseAdapter implements View.OnClickListener {
    int flag;
    List<Point> rData = new ArrayList<Point>();
    private Activity activity;

    private LayoutInflater inflater;

    Context mContext;

    public ListAdapter(Activity a, ArrayList<Point> rD,
                       Context context) {
        this.mContext = context;
        activity = a;
        rData = rD;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    @Override
    public int getCount() {
        return rData.size();
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
    public void onClick(View v) {

    }

    public static class ViewHolder {

        public ImageView image;
        public TextView text;
        public TextView text2;
    }

    @Override
    public boolean hasStableIds() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        ViewHolder holder;
        if (convertView == null) {
            vi = inflater.inflate(R.layout.listadapter, null);
            holder = new ViewHolder();
            holder.text = (TextView) vi.findViewById(R.id.text1);
            holder.text2 = (TextView) vi.findViewById(R.id.textListIDDate);
            holder.image = (ImageView) vi.findViewById(R.id.imagelist);
            vi.setTag(holder);
        } else {
            holder = (ViewHolder) vi.getTag();
        }
        Point item = rData.get(position);
        Context context = parent.getContext();
        holder.text.setText(item.getPoint_name());
        holder.text2.setText(item.getDate_point());
        vi.setOnClickListener(new OnItemClickListener(position, item));
        // vi.setOnLongClickListener(new View.OnLongClickListener(position, item));
        return vi;
    }

    private class OnItemClickListener implements View.OnClickListener {
        private int mPosition;
        private Point p;

        OnItemClickListener(int position, Point point) {
            mPosition = position;
            p = point;

        }

        public void onClick(View arg0) {
            Intent qIntent = new Intent(mContext, SingleMapsActivity.class);
            flag = 0;
            qIntent.putExtra("point_name", p.getPoint_name());
            qIntent.putExtra("point_lat", String.valueOf(p.getLat()));
            qIntent.putExtra("point_long", String.valueOf(p.getLongt()));
            qIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(qIntent);


        }

        public void onClickidLocation(View arg0) {
            Intent qIntent = new Intent(mContext, SingleMapsActivity.class);

            qIntent.putExtra("point_nameid", p.getPoint_name());
            qIntent.putExtra("point_latid", String.valueOf(p.getLat()));
            qIntent.putExtra("point_longid", String.valueOf(p.getLongt()));

            Log.d(" ferfrefer", "p " + p.toString());
            qIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(qIntent);


        }
    }
}
