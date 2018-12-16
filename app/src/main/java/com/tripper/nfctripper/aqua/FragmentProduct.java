package com.tripper.nfctripper.aqua;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.tripper.nfctripper.aqua.adapter.CartAdapter;
import com.tripper.nfctripper.aqua.adapter.ProductAdapter;
import com.tripper.nfctripper.aqua.apihelper.ProductService;
import com.tripper.nfctripper.aqua.apihelper.RetrofitClient;
import com.tripper.nfctripper.aqua.interface2.CustomItemClickListener;
import com.tripper.nfctripper.aqua.model.productModel.Product;
import com.tripper.nfctripper.aqua.model.productModel.ResultProduct;
import com.tripper.nfctripper.aqua.model.userModel.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentProduct extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Product> testArrayList;

    Realm realm;
    User user;


    public FragmentProduct() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment FragmentProduct.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentProduct newInstance(String param1) {
        FragmentProduct fragment = new FragmentProduct();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myInflater = inflater.inflate(R.layout.fragment_product, container, false);

        mRecyclerView = (RecyclerView) myInflater.findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        realm = Realm.getDefaultInstance();
        user = realm.where(User.class).findFirst();


        populate();




        return myInflater;
    }

    private void populate() {

        final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Load data");
        progressDialog.show();

        ProductService service = RetrofitClient.getClient().create(ProductService.class);



        Call<ResultProduct> userCall = service.getIndex("Bearer "+user.getToken());

        userCall.enqueue(new Callback<ResultProduct>() {
            @Override
            public void onResponse(Call<ResultProduct> call, Response<ResultProduct> response) {
                progressDialog.dismiss();
                //onSignupSuccess();
                Log.d("onResponse", "" + response.body().getMessage());


                if(response.body().getSuccess()== true ) {

                    Toast.makeText(getActivity(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    testArrayList = response.body().getDataProduct().getProductList();
                    // specify an adapter (see also next example)
                    mAdapter = new ProductAdapter(testArrayList, getActivity(), new CustomItemClickListener() {
                        @Override
                        public void onItemClick(View v, int position) {

                        }
                    });
                    mRecyclerView.setAdapter(mAdapter);




                    onLoginSuccess();


                }else {
                    Toast.makeText(getActivity(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    onLoginFailed();

                }
            }



            @Override
            public void onFailure(Call<ResultProduct> call, Throwable t) {
                progressDialog.dismiss();
                Log.d("onFailure", t.toString());
                onLoginFailed();
            }
        });
    }


    private void onLoginFailed() {
    }

    private void onLoginSuccess() {





    }


}
