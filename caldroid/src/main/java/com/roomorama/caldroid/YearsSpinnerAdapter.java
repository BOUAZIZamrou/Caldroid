package com.roomorama.caldroid;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;


/**
 * Created by amr on 12-Jan-17.
 * Spinner adapter that shows 3 previous years and 99 future years
 */

public class YearsSpinnerAdapter extends ArrayAdapter<CharSequence> {
    private static final String TAG = "YearsSpinnerAdapter";
    private final int mResource;
    private int previousYearsCount = 5;
    int commingYearsCount = 99;
    private int thisYear = -1;
    private Context context;

    public YearsSpinnerAdapter(Context context, @LayoutRes int resource) {
        super(context, resource);
        mResource = resource;
        this.context = context;
        thisYear = Calendar.getInstance().get(Calendar.YEAR);
    }

    @Override
    public long getItemId(int position) {
        Log.d(TAG, "getItemId() called with: position = [" + position + "]");

        return getYearByPosition(position);
    }

    private int getYearByPosition(int position) {
        Log.d(TAG, "getYearByPosition() called with: position = [" + position + "]");
        return thisYear - previousYearsCount + position;
    }

    @NonNull
    public TextView getView(int position, View convertView, @NonNull ViewGroup parent) {
        Log.d(TAG, "getView() called with: position = [" + position + "], convertView = [" + convertView + "], parent = [" + parent + "]");
        TextView view = (TextView) convertView;
        if (view == null) {
            view = (TextView) ((LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                    .inflate(mResource, null, false);
        }
//        Typeface myTypeFace = Typeface.createFromAsset(context.getAssets(),
//                "fonts/gilsanslight.otf");
//        v.setTypeface(myTypeFace);
        view.setText(getYearByPosition(position));
        return view;
    }

    @Override
    public int getCount() {
        Log.d(TAG, "getCount() called");
        Log.d(TAG, "getCount() returned: " + (previousYearsCount + commingYearsCount + 1));
        return previousYearsCount + commingYearsCount + 1;
//        return 0  ;
    }

    public TextView getDropDownView(int position, View convertView,
                                    @NonNull ViewGroup parent) {

        return getView(position, convertView, parent);
    }
}
