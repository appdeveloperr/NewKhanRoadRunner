package com.example.husnain.newproject.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.example.husnain.newproject.R;
import com.example.husnain.newproject.adapters.CityAdapter;
import com.example.husnain.newproject.entities.City;
import com.example.husnain.newproject.entities.InnerJoinRoute;
import com.example.husnain.newproject.viewmodels.RouteDetailViewModel;

import java.util.ArrayList;

public class SelectCityActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CityAdapter adapter;

    private ArrayList<InnerJoinRoute> cities;
    private RouteDetailViewModel routeDetailViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Select City");

        recyclerView = (RecyclerView) findViewById(R.id.rv_serch_city);

        routeDetailViewModel = ViewModelProviders.of(this).get(RouteDetailViewModel.class);



        /*cities.add(new City(1,"Lahore","sdfsd",true,2,"",
                "",21,"white"));

        cities.add(new City(1,"Karachi","sdfsd",true,2,"",
                "",21,"white"));*/



        cities = new ArrayList<InnerJoinRoute>(routeDetailViewModel.getRouteDetailsById(getIntent().getExtras().getInt("RouteId"))); ;


        /*cities.add(new InnerJoinRoute(54, "Lahore لاہور","LHR",1));
        cities.add(new InnerJoinRoute(179, "Sargodha","Sargodha",2));
        cities.add(new InnerJoinRoute(251, "Mianwali","Mianwali",3));
        cities.add(new InnerJoinRoute(248, "ڈیرہ اسماعیل خان","D I Khan",4));*/

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new CityAdapter(cities,SelectCityActivity.this);
        recyclerView.setAdapter(adapter);

        /*recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search,menu);
        MenuItem item = menu.findItem(R.id.menu_search);

        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                //Utile.DisplayShortToast(DonorListActivity.this,query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final ArrayList<InnerJoinRoute> filteredModelList = new ArrayList<InnerJoinRoute>();
                for (InnerJoinRoute model : cities) {
                    final String text = model.getCity_Name().toUpperCase();
                    if (text.contains(newText.toUpperCase())) {
                        filteredModelList.add(model);
                    }
                }

                if(filteredModelList.size()>0){
                    adapter = new CityAdapter(filteredModelList,SelectCityActivity.this);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    return true;
                } else{
                    return false;
                }
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            onBackPressed();
            this.finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
