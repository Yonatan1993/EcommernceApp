package Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import android.widget.LinearLayout;

import com.example.e_commerceapp.R;

import java.util.ArrayList;

import Adapter.PopularListAdapter;
import Domain.PopularDomain;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapterPupolar;
    private RecyclerView recyclerViewPupolar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initRecycleview();
        bottom_navigation();
        
    }

    private void bottom_navigation() {
        LinearLayout homebtn = findViewById(R.id.homeBtn);
        LinearLayout cartBtn = findViewById(R.id.cartBtn);

        homebtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,MainActivity.class)));

        cartBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,CartActivity.class)));
    }

    private void initRecycleview() {
        ArrayList<PopularDomain> items = new ArrayList<>();
        items.add(new PopularDomain("MacBook Pro 13 M2 Chip", getResources().getString(R.string.macBook_details), "pic1", 15, 4,  500));
        items.add(new PopularDomain("PS-5 Digital", getResources().getString(R.string.macBook_details),"pic2" ,10,4.5,450 ));
        items.add(new PopularDomain("IPhone 14",getResources().getString(R.string.macBook_details) ,"pic3" ,13,4.2,800 ));


        recyclerViewPupolar = findViewById(R.id.view1);
        recyclerViewPupolar.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        adapterPupolar = new PopularListAdapter(items);
        recyclerViewPupolar.setAdapter(adapterPupolar);

    }
}