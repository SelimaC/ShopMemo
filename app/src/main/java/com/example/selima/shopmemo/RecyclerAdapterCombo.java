package com.example.selima.shopmemo;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.selima.shopmemo.model.Combo;
import com.example.selima.shopmemo.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by selim on 13/01/2017.
 */

public class RecyclerAdapterCombo extends RecyclerView.Adapter<RecyclerAdapterCombo.ViewHolder> {

    private List<Combo> mItemsList;
    private PageFragmentCombo.OnListItemClickListener mListItemClickListener;

    public RecyclerAdapterCombo(List<Combo> itemsList) {
        mItemsList = (itemsList == null) ? new ArrayList<Combo>() : itemsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new view by inflating the row item xml.
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.combocard_view, parent, false);

        // Set the view to the ViewHolder
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nome.setText(mItemsList.get(position).getNome());

        float tot = 0;
        int n = 0;
        List<Product> p = new ArrayList<>();
        p = mItemsList.get(position).getListaProdotti();
        for(Product e : p){
            tot += e.getPrezzo();
            n++;
        }
        holder.prezzo.setText(tot + " €");
        holder.numOgg.setText(n + "");

        if(n!=0) {
            int id = Home_Activity.context().getResources().getIdentifier(p.get(0).getPathFoto(),
                    "drawable", Home_Activity.context().getPackageName());

            holder.photo.setImageResource(id);
        }

    }

    @Override
    public int getItemCount() {
        return mItemsList.size();
    }

    // Create the ViewHolder class to keep references to your views
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cardView;
        TextView numOgg;
        TextView nome;
        TextView prezzo;
        ImageView photo;

        /**
         * Constructor
         * @param v The container view which holds the elements from the row item xml
         */
        public ViewHolder(View v) {
            super(v);

            cardView = (CardView)v.findViewById(R.id.card_view);
            numOgg = (TextView)v.findViewById(R.id.numOgg);
            nome = (TextView)v.findViewById(R.id.nome);
            prezzo = (TextView)v.findViewById(R.id.prezzo);
            photo = (ImageView) v.findViewById(R.id.photos);
            v.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            if (null != mListItemClickListener) {
                // Notify the active callbacks interface (the activity, if the
                // fragment is attached to one) that an item has been selected.
                mListItemClickListener.onListItemClick(mItemsList.get(getAdapterPosition()).getId());
            }
        }
    }

    public void setOnItemTapListener(PageFragmentCombo.OnListItemClickListener itemClickListener) {
        mListItemClickListener = itemClickListener;
    }
}