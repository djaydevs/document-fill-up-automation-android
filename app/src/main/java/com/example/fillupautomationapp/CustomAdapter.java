package com.example.fillupautomationapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class CustomAdapter extends ArrayAdapter<String>{
    Context c;
    String[] id;
    String[] ln;
    String[] fn;
    String[] mii;
    String[] hn;
    String[] st;
    String[] g;
    String[] a;
    String[] yos;
    String[] bd;
    String[] bp;
    String[] cn;
    LayoutInflater inflater;

    public CustomAdapter(Context context, String[] id, String[] ln, String[] fn , String[] mii, String[] hn
    ,String[] st, String[] g,  String[] a, String[] yos, String[] bd, String[] bp,
                         String[] cn) {
        super(context, R.layout.row_record,id);
        this.c = context;
        this.ln = ln;
        this.fn = fn;
        this.mii = mii;
        this.hn = hn;
        this.st = st;
        this.g = g;
        this.a = a;
        this.yos = yos;
        this.bd= bd;
        this.bp = bp;
        this.cn = cn;
    }

    public class ViewHolder{
        TextView Lname;
        TextView Fname;
        TextView Mi;
        TextView House_num;
        TextView Street;
        TextView Gender;
        TextView Age;
        TextView Year_of_stay;
        TextView Birthday;
        TextView Birthplace;
        TextView Contact_num;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            inflater=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_record,null);
        }
        ViewHolder vh = new ViewHolder();
        vh.Lname = (TextView) convertView.findViewById(R.id.rowLn);
        vh.Fname = (TextView) convertView.findViewById(R.id.rowFn);
        vh.Mi = (TextView) convertView.findViewById(R.id.rowMI);
        vh.House_num = (TextView) convertView.findViewById(R.id.rowHn);
        vh.Street = (TextView) convertView.findViewById(R.id.rowStreet);
        vh.Gender = (TextView) convertView.findViewById(R.id.rowGender);
        vh.Age = (TextView) convertView.findViewById(R.id.rowAge);
        vh.Year_of_stay = (TextView) convertView.findViewById(R.id.rowYstay);
        vh.Birthday = (TextView) convertView.findViewById(R.id.rowBd);
        vh.Birthplace = (TextView) convertView.findViewById(R.id.rowBp);
        vh.Contact_num = (TextView) convertView.findViewById(R.id.rowCn);

        vh.Lname.setText(ln[position]);
        vh.Fname.setText(fn[position]);
        vh.Mi.setText(mii[position]);
        vh.House_num.setText(hn[position]);
        vh.Street.setText(st[position]);
        vh.Gender.setText(g[position]);
        vh.Age.setText(a[position]);
        vh.Year_of_stay.setText(yos[position]);
        vh.Birthday.setText(bd[position]);
        vh.Birthplace.setText(bp[position]);
        vh.Contact_num.setText(cn[position]);
        return convertView;
    }
}
