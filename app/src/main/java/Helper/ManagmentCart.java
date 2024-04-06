package Helper;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

import Domain.PopularDomain;

/**
 * Created by Yonatan Shkolsky on 4/6/2024.
 */
public class ManagmentCart {

    private Context context;
    private TinyDB tinyDB;
    public static final String KEY = "CartList";

    public ManagmentCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertItem(PopularDomain item){
        ArrayList<PopularDomain> listPop = getListCart();
        boolean existAlready = false;
        int n = 0;
        for (int i = 0; i < listPop.size(); i++) {
            if (listPop.get(i).getTitle().equals(item.getTitle())) {
                existAlready = true;
                n = i;
                break;
            }
        }

        if(existAlready){
            listPop.get(n).setNumberInCart(item.getNumberInCart());
        }else{
            listPop.add(item);
        }
        
        tinyDB.putListObject(KEY,listPop);
        Toast.makeText(context, "Added to your cart", Toast.LENGTH_SHORT).show();

    }

    public ArrayList<PopularDomain> getListCart() {
        
        return tinyDB.getListObject(KEY);
    }

    public void minusNumberItem(ArrayList<PopularDomain>listItem,int position,ChangeNumberItemsListener changeNumberItemsListener){

        if(listItem.get(position).getNumberInCart() == 1){
            listItem.remove(position);
        }else{
            listItem.get(position).setNumberInCart(listItem.get(position).getNumberInCart()-1);
        }

        tinyDB.putListObject(KEY,listItem);
        changeNumberItemsListener.change();
    }

    public void plusNumberItem(ArrayList<PopularDomain>listItem,int position, ChangeNumberItemsListener changeNumberItemsListener){

        listItem.get(position).setNumberInCart(listItem.get(position).getNumberInCart()+1);
        tinyDB.putListObject(KEY,listItem);
        changeNumberItemsListener.change();
    }

    public Double getTotalFee(){
        double totalFee = 0;
        ArrayList<PopularDomain> list = getListCart();
        for (PopularDomain item: list) {
            totalFee += (item.getPrice() * item.getNumberInCart());
        }
        return totalFee;
    }
}
