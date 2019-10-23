package com.example.jooyoung.db_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class BaseExpandableAdater extends BaseExpandableListAdapter {
    private Context context;
    private int groupLayout = 0;
    private int childLayout = 0;
    private ArrayList<MyGroup> DataList;
    private LayoutInflater myinf = null;

    public BaseExpandableAdater(Context c ,int groupLay, int childLay,ArrayList<MyGroup> DataList){
        this.DataList = DataList;
        this.groupLayout = groupLay;
        this.childLayout = childLay;
        this.context = c;
        this.myinf = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return DataList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
       return DataList.get(groupPosition).child.size();
    }

    @Override
    public MyGroup getGroup(int groupPosition) {
        return DataList.get(groupPosition);
    }

    @Override
    public String getChild(int groupPosition, int childPosition) {
        return DataList.get(groupPosition).child.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = myinf.inflate(this.groupLayout,parent,false);
        }
        TextView groupName = (TextView)convertView.findViewById(R.id.parenttext);
        groupName.setText(DataList.get(groupPosition).groupName);
        return convertView;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = myinf.inflate(this.childLayout,parent,false);
        }
        TextView childText = (TextView)convertView.findViewById(R.id.child_text);
        childText.setText(DataList.get(groupPosition).child.get(childPosition));
        return convertView;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}

