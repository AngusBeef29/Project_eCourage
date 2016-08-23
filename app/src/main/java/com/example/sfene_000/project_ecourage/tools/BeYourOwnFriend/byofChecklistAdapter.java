package com.example.sfene_000.project_ecourage.tools.BeYourOwnFriend;

/**
 * Created by geoffreyangus on 8/23/16.
 */

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.example.sfene_000.project_ecourage.R;

public class BYOFChecklistAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    ArrayList<BYOFCounterArgument> counterArguments;

    BYOFChecklistAdapter(Context context, ArrayList<BYOFCounterArgument> counterArguments) {
        ctx = context;
        this.counterArguments = counterArguments;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return counterArguments.size();
    }

    @Override
    public Object getItem(int position) {
        return counterArguments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.element_byof_confirm_thoughts, parent, false);
        }

        BYOFCounterArgument ca = getCounterArgument(position);

        ((TextView) view.findViewById(R.id.tvDescr)).setText(ca.counterArgument);

        CheckBox cbBuy = (CheckBox) view.findViewById(R.id.cbBox);
        cbBuy.setOnCheckedChangeListener(myCheckChangList);
        cbBuy.setTag(position);
        cbBuy.setChecked(ca.checkBox);
        return view;
    }

    BYOFCounterArgument getCounterArgument(int position) {
        return ((BYOFCounterArgument) getItem(position));
    }

    ArrayList<BYOFCounterArgument> getCheckedBoxes() {
        ArrayList<BYOFCounterArgument> checkedBoxes = new ArrayList<BYOFCounterArgument>();
        for (BYOFCounterArgument ca : counterArguments) {
            if (ca.checkBox)
                checkedBoxes.add(ca);
        }
        return checkedBoxes;
    }

    OnCheckedChangeListener myCheckChangList = new OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView,
                                     boolean isChecked) {
            getCounterArgument((Integer) buttonView.getTag()).checkBox = isChecked;
        }
    };
}
