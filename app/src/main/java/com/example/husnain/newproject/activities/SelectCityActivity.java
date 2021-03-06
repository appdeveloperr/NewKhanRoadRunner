package com.example.husnain.newproject.activities;

import androidx.lifecycle.ViewModelProviders;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.husnain.newproject.Global;
import com.example.husnain.newproject.R;
import com.example.husnain.newproject.adapters.CityAdapter;
import com.example.husnain.newproject.entities.InnerJoinRoute;
import com.example.husnain.newproject.entities.Terminal;
import com.example.husnain.newproject.viewmodels.RouteDetailViewModel;
import com.example.husnain.newproject.viewmodels.TerminalViewModel;

import java.util.ArrayList;

public class SelectCityActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CityAdapter adapter;

    private ArrayList<InnerJoinRoute> cities = new ArrayList<InnerJoinRoute>();
    private RouteDetailViewModel routeDetailViewModel;
    private TerminalViewModel terminalViewModel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Select City");

        recyclerView = (RecyclerView) findViewById(R.id.rv_serch_city);

        routeDetailViewModel = ViewModelProviders.of(this).get(RouteDetailViewModel.class);
        terminalViewModel = ViewModelProviders.of(this).get(TerminalViewModel.class);



        /*cities.add(new City(1,"Lahore","sdfsd",true,2,"",
                "",21,"white"));

        cities.add(new City(1,"Karachi","sdfsd",true,2,"",
                "",21,"white"));*/




        Terminal terminal = terminalViewModel.getTerminalById(  Integer.parseInt(removeLastChar( ("" + Global.VoucherNo), 9))); ;
        ArrayList<InnerJoinRoute> innerJoinRoute = new ArrayList<InnerJoinRoute>(routeDetailViewModel.getRouteDetailsById(getIntent().getExtras().getInt("RouteId"))); ;

        int SrNo = innerJoinRoute.size();

        for (int count = 0; count < innerJoinRoute.size(); count++){
            if(innerJoinRoute.get(count).getCity_ID() == terminal.getCity_ID()){
                SrNo = innerJoinRoute.get(count).getSr_No();
            }
            if (innerJoinRoute.get(count).getSr_No() >= SrNo){
                cities.add(innerJoinRoute.get(count));
            }
        }

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


    public String removeLastChar(String s, int no_of_chars) {

        return s.substring(0, s.length() - no_of_chars);

    }

}
