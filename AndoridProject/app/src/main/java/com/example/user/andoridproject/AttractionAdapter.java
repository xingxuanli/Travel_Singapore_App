package com.example.user.andoridproject;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by trying on 27/11/2017.
 */
//TODO 4.1 (in a separate XML File) Design your list item layout

//TODO 4.2 go back to activity_main.xml and put in the recycler view widget

public class AttractionAdapter extends RecyclerView.Adapter<AttractionAdapter.AttractionViewHolder> {

    com.example.user.andoridproject.GuideActivity.AttractionJsonData[] mDataset;
    private static int viewHolderCount = 0; //
    Context parentContext;
    String intro;
    String name;
    ContentValues cv;

    private SpendingDbHelper spendingDbHelper;
    private SQLiteDatabase spendingDb;


    // Provide a suitable constructor (depends on the kind of dataset)
    public AttractionAdapter(Context context,
                             com.example.user.andoridproject.GuideActivity.AttractionJsonData[] myDataset) {
        mDataset = myDataset;
        parentContext = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AttractionAdapter.AttractionViewHolder onCreateViewHolder(ViewGroup parent,
                                                                     int viewType) {
        int layoutIDForListItem = R.layout.recycler_item;
        LayoutInflater inflater = LayoutInflater.from(parentContext);
        boolean shouldAttachToParentImmediately = false; // not attaching the view holder until its ready, on bind attach data

        View view = inflater.inflate(layoutIDForListItem, parent, shouldAttachToParentImmediately);//inflater --take xml and return java object

        AttractionViewHolder attractionViewHolder = new AttractionViewHolder(view);
        viewHolderCount++;
        Log.i("User", "onCreateViewHolder is called " + viewHolderCount + "times");

        return attractionViewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(AttractionViewHolder holder, int position) {
        holder.bind(position);
        Log.i("User", "onBindViewHolder position " + position);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }


    class AttractionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView attractionName;
        ImageView picture;
        TextView introduction;
        String filename;

        String intro = "";
        int signal = 0;

        AttractionViewHolder(View v) {
            //TODO 4.3 Invoke the superclass constructor
            // and get references to the various widgets in the List Item Layout
            super(v);
            picture = (ImageView) v.findViewById(R.id.item_image);
            attractionName = (TextView) v.findViewById(R.id.item_text);
            introduction = (TextView) v.findViewById(R.id.item_introduction);
            v.setOnClickListener(this);
        }

        //TODO 4.6 - write a bind method to attach content
        //            to the respective widgets
        public void bind(int position) {

            filename = mDataset[position].file;

            String packageName = parentContext.getPackageName();
            String typeOfResource = "drawable";

            int resID = parentContext.getResources().getIdentifier(filename, typeOfResource, packageName);

            picture.setImageResource(resID);

            attractionName.setText(mDataset[position].name);

            intro = mDataset[position].introduction;

            introduction.setText("Address: " + intro);

        }

        @Override
        public void onClick(View v){
            openWebPage(filename,parentContext);
        }
    }

    public void openWebPage(String object, Context context) {
        Uri uri = Uri.parse("http://www.google.com/#q="+object);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        context.startActivity(intent);
    }

}