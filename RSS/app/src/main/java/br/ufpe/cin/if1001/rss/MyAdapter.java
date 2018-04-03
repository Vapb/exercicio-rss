package br.ufpe.cin.if1001.rss;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter {

    Context c;
    List<ItemRSS> listaRss;

    public MyAdapter(Context c){
        this.c = c;
        this.listaRss = new ArrayList<ItemRSS>();
    }

    public int getCount(){
        return listaRss.size();
    }

    public Object getItem( int pos){
        return listaRss.get(pos);
    }

    public long getItemId(int pos){
        return pos;
    }


    public View getView(int pos, View convertView, ViewGroup parent){

        /// Utilizar Itemlista passo 6
        View v = LayoutInflater.from(c).inflate(R.layout.itemlista,parent,false);

        // IDs
        TextView tituloTv = (TextView) v.findViewById(R.id.item_titulo);
        TextView dataTv = (TextView) v.findViewById(R.id.item_data);


        String titulo = ((ItemRSS) getItem(pos)).getTitle();
        String data = ((ItemRSS) getItem(pos)).getDescription();

        tituloTv.setText(titulo);
        dataTv.setText(data);

        return v;
    }

}
