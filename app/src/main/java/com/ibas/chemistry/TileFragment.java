package com.ibas.chemistry;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Ibas on 11/05/2017.
 */

public class TileFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView)inflater.inflate(
                R.layout.recycle_view, container, false
        );
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        // Set padding for Tiles
        int titlePadding = getResources().getDimensionPixelSize(R.dimen.tile_padding);
        recyclerView.setPadding(titlePadding, titlePadding, titlePadding, titlePadding);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        return recyclerView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView picture;
        public TextView name;
        public ViewHolder(LayoutInflater inflater, ViewGroup viewGroup){
            super(inflater.inflate(R.layout.item_tile, viewGroup, false));
            picture = (ImageView)itemView.findViewById(R.id.tile_picture);
            name = (TextView)itemView.findViewById(R.id.tile_text);
        }
    }

    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder>{
        private final String[] mTitle;
        private final Drawable[] mPicture;

        public ContentAdapter(Context context){
            Resources resources = context.getResources();
            mTitle = resources.getStringArray(R.array.nama_kal);
            TypedArray typedArray = resources.obtainTypedArray(R.array.picture_kal);
            mPicture = new Drawable[typedArray.length()];
            for (int i = 0; i < mPicture.length; i++){
                mPicture[i] = typedArray.getDrawable(i);
            }
            typedArray.recycle();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(
                    parent.getContext()
            ), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.picture.setImageDrawable(mPicture[position % mPicture.length]);
            holder.name.setText(mTitle[position % mTitle.length]);
        }

        @Override
        public int getItemCount() {
            return mPicture.length;
        }
    }
}
