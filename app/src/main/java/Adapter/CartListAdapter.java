package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.e_commerceapp.R;

import java.util.ArrayList;

import Domain.PopularDomain;
import Helper.ChangeNumberItemsListener;
import Helper.ManagmentCart;

/**
 * Created by Yonatan Shkolsky on 4/6/2024.
 */
public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.viewHolder> {

    ArrayList<PopularDomain> listItemSelected;
    private ManagmentCart managmentCart;
    ChangeNumberItemsListener changeNumberItemsListener;

    public CartListAdapter(ArrayList<PopularDomain> listItemSelected,Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.listItemSelected = listItemSelected;
        managmentCart = new ManagmentCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @NonNull
    @Override
    public CartListAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent, false);
        return new viewHolder(inflate);

    }

    @Override
    public void onBindViewHolder(@NonNull CartListAdapter.viewHolder holder, int position) {

        holder.title.setText(listItemSelected.get(position).getTitle());
        holder.feeEachItem.setText("$"+listItemSelected.get(position).getPrice());
        holder.totalEachItem.setText("$"+Math.round(listItemSelected.get(position).getNumberInCart())*listItemSelected.get(position).getPrice());
        holder.num.setText(String.valueOf(listItemSelected.get(position).getNumberInCart()));

        int drawableResourceId = holder.itemView.getContext().getResources()
                .getIdentifier(listItemSelected.get(position).getPicUrl(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .transform(new GranularRoundedCorners(30, 30, 30, 30))
                .into(holder.pic);

        holder.plusItem.setOnClickListener(v -> {
          managmentCart.plusNumberItem(listItemSelected, position, new ChangeNumberItemsListener() {
              @Override
              public void change() {
                  notifyDataSetChanged();
                  changeNumberItemsListener.change();
              }
          });
        });

        holder.minusItem.setOnClickListener(v -> {
           managmentCart.minusNumberItem(listItemSelected, position, new ChangeNumberItemsListener() {
               @Override
               public void change() {
                   notifyDataSetChanged();
                   changeNumberItemsListener.change();
               }
           });
        });

    }

    @Override
    public int getItemCount() {
        return listItemSelected.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView title,feeEachItem,plusItem, minusItem;
        ImageView pic;
        TextView totalEachItem, num;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTxt);
            pic = itemView.findViewById(R.id.pic);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            plusItem = itemView.findViewById(R.id.plusCartButton);
            totalEachItem = itemView.findViewById(R.id.totalEachItems);
            minusItem = itemView.findViewById(R.id.minusCartButton);
            num = itemView.findViewById(R.id.numbetOfItemsTxt);



        }
    }




}
