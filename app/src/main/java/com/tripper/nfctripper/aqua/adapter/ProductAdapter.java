package com.tripper.nfctripper.aqua.adapter;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tripper.nfctripper.aqua.LoginActivity;
import com.tripper.nfctripper.aqua.MyApplication;
import com.tripper.nfctripper.aqua.R;
import com.tripper.nfctripper.aqua.apihelper.ProductService;
import com.tripper.nfctripper.aqua.apihelper.RetrofitClient;
import com.tripper.nfctripper.aqua.apihelper.UserService;
import com.tripper.nfctripper.aqua.interface2.CustomItemClickListener;
import com.tripper.nfctripper.aqua.model.productModel.Product;
import com.tripper.nfctripper.aqua.model.productModel.ResultProduct;
import com.tripper.nfctripper.aqua.model.userModel.ResultUser;
import com.tripper.nfctripper.aqua.model.userModel.User;

import java.util.ArrayList;

import javax.xml.transform.Result;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    private ArrayList<Product> mDataset;
    private Context context;
    User user;
    private Realm realm;
    CustomItemClickListener listener;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        @BindView(R.id.product)
        CardView product;
        @BindView(R.id.image)
        TextView image;
        @BindView(R.id.name)
        TextView name;
        public MyViewHolder(CardView v) {
            super(v);
            ButterKnife.bind(this,v);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ProductAdapter(ArrayList<Product> myDataset, Context context, CustomItemClickListener listener) {

        mDataset = myDataset;
        this.context=context;
        realm = Realm.getDefaultInstance();
        user =realm.where(User.class).findFirst();
        this.listener = listener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ProductAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product, parent, false);

        MyViewHolder vh = new MyViewHolder(v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, vh.getAdapterPosition());
            }
        });
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
