package com.nicoleblumhorst.stateofemergenz.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nicoleblumhorst.stateofemergenz.R;
import com.nicoleblumhorst.stateofemergenz.models.NavDrawerItem;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nicoleblumhorst on 1/23/16.
 */
public class NavDrawerAdapter extends ArrayAdapter<NavDrawerItem> {

    public NavDrawerAdapter(Context mContext, int layoutResourceId, ArrayList<NavDrawerItem> data) {
        super(mContext, layoutResourceId, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        NavDrawerItem menuItem = (NavDrawerItem) getItem(position);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        ViewHolder holder;

        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.drawer_list_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }

        holder.textView.setText(menuItem.getName());

        if (menuItem.getIcon() != null) {
            holder.iconImageView.setVisibility(View.VISIBLE);
            holder.iconImageView.setImageResource(menuItem.getIcon());
        } else {
            holder.iconImageView.setVisibility(View.INVISIBLE);
        }

        return convertView;
    }

    static class ViewHolder {

        @Bind(R.id.ndi_text_view)
        TextView textView;

        @Bind(R.id.ndi_image_view)
        ImageView iconImageView;

        public ViewHolder(View view) {
            ButterKnife.bind(ViewHolder.this, view);
        }

    }

}
