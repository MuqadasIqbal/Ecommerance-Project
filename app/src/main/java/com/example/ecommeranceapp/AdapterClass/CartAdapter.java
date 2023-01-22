package com.example.ecommeranceapp.AdapterClass;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ecommeranceapp.Activity.CheckoutActivity;
import com.example.ecommeranceapp.ModelClass.Product;
import com.example.ecommeranceapp.R;
import com.example.ecommeranceapp.databinding.ItemCartBinding;
import com.example.ecommeranceapp.databinding.QuantityDialogBinding;
import com.hishd.tinycart.model.Cart;
import com.hishd.tinycart.util.TinyCartHelper;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.viewHolder> {
Context context;
ArrayList<Product>products;
CartListener cartListener;
Cart cart;
public interface CartListener{
    public void onQuantityChanged();


}

    public CartAdapter(Context context, ArrayList<Product> products, CartListener cartListener) {
        this.context=context;
        this.products=products;
        this.cartListener=cartListener;
        cart= TinyCartHelper.getCart();
    }


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(context).inflate(R.layout.item_cart,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Product product=products.get(position);
        holder.binding.name.setText(product.getName());
        holder.binding.price.setText("PKR"+product.getPrice());
        holder.binding.quantity.setText(product.getQuantity()+"item(s)");
        Glide.with(context)
                .load(product.getImage())
                .into(holder.binding.imageView2);
        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                QuantityDialogBinding quantityDialogBinding=QuantityDialogBinding.inflate(LayoutInflater.from(context));
                AlertDialog dialog=new AlertDialog.Builder(context)
                        .setView(quantityDialogBinding.getRoot())
                        .create();
               /* dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.R.color.transparent));*/
                quantityDialogBinding.productName.setText(product.getName());
                quantityDialogBinding.stock.setText("Stock:"+product.getStock());
                int stock=product.getStock();

                quantityDialogBinding.quantity.setText(String.valueOf(product.getQuantity()));
                quantityDialogBinding.plusbutton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int quantity=product.getQuantity();
                        quantity++;

                      if (quantity>product.getStock()){
                          Toast.makeText(context, "Max stock available"+product.getStock(), Toast.LENGTH_SHORT).show();
                      }
                      else {
                          product.setQuantity(quantity);
                          quantityDialogBinding.quantity.setText(String.valueOf(quantity));
                      }
                        notifyDataSetChanged();
                        cart.updateItem(product,product.getQuantity());
                        cartListener.onQuantityChanged();



                    }
                });
                quantityDialogBinding.minusBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int quantity=product.getQuantity();
                        if (quantity>1)
                        quantity--;
                        product.setQuantity(quantity);
                        quantityDialogBinding.quantity.setText(String.valueOf(quantity));

                        notifyDataSetChanged();
                        cart.updateItem(product,product.getQuantity());
                        cartListener.onQuantityChanged();

                    }

                });
                quantityDialogBinding.saveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                     dialog.dismiss();
                     /*notifyDataSetChanged();
                     cart.updateItem(product,product.getQuantity());
                     cartListener.onQuantityChanged();*/

                    }
                });
                dialog.show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
          ItemCartBinding binding;
         public viewHolder(@NonNull View itemView) {
             super(itemView);
             binding=ItemCartBinding.bind(itemView);
         }
     }
}
