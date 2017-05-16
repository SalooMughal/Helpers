package com.example.salman.uol_helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by SALMAN on 5/16/2017.
 */

public class Timetable_Adapter extends ArrayAdapter<Timetable>
{private Context _context;
    int _layoutId;

    public Timetable_Adapter(Context context,
                          int layoutId,
                          List<Timetable> items) {
        super(context, layoutId, items);
        _context = context;
        _layoutId = layoutId;
    }

    public class viewholder {
        TextView day;
        TextView time_slot;
        TextView room_no;
        TextView course_name;
        TextView status;
    }

    public View getView(int position, View convertview, ViewGroup parent) {

        Timetable item = getItem(position);
        viewholder holder;
        String day = item.getDay();
        String time_slot = item.getTime_slot();
        String room_no = item.getRoom_no();
        String course_name = item.getCourse_name();
        String status = item.getStatus();
        holder = new viewholder();
        View view = convertview;
        if (convertview == null) {
            LayoutInflater inflater = (LayoutInflater) _context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(_layoutId, parent, false);
            holder.day = (TextView) view.findViewById(R.id.Id);
            holder.time_slot = (TextView) view.findViewById(R.id.time_slot);
            holder.room_no = (TextView) view.findViewById(R.id.email);
            holder.course_name = (TextView) view.findViewById(R.id.name);
            holder.status = (TextView) view.findViewById(R.id.status);
            view.setTag(holder);
        } else {
            holder = (viewholder) view.getTag();
        }
        holder.day.setText(day);
        holder.time_slot.setText(time_slot);
        holder.room_no.setText(room_no);
        holder.course_name.setText(course_name);
        holder.status.setText(status);

        return view;
    }

}
