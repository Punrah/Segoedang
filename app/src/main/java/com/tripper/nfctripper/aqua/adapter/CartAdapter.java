package com.tripper.nfctripper.aqua.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tripper.nfctripper.aqua.R;
import com.tripper.nfctripper.aqua.model.productModel.Product;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    private ArrayList<Product> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        @BindView(R.id.image)
        TextView image;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.qty)
        TextView qty;
        @BindView(R.id.price)
        TextView price;
        @BindView(R.id.subtotal)
        TextView subtotal;
        public MyViewHolder(CardView v) {
            super(v);
            ButterKnife.bind(this,v);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CartAdapter(ArrayList<Product> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CartAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        // create a new view
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.image.setText(mDataset.get(position).toString());




    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
