package np.com.rohanshrestha.firstproject;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import np.com.rohanshrestha.firstproject.models.City;

public class SixthActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sixth);

        City c1 = new City("Pokhara", "Nepal");
        City c2 = new City("London", "UK");

        ArrayList<City> list = new ArrayList<>();
        list.add(c1);
        list.add(c2);

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
                if (swipeRefreshLayout.isRefreshing())
                    swipeRefreshLayout.setRefreshing(false);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        recyclerView.setHasFixedSize(true);

        adapter = new CustomAdapter(this, list);
        recyclerView.setAdapter(adapter);

    }

    private void getData() {
        City c1 = new City("Pokhara", "Nepal");
        City c2 = new City("London", "UK");
        City c3 = new City("New York", "USA");
        City c4 = new City("Vienna", "Austria");

        ArrayList<City> list = new ArrayList<>();
        list.add(c1);
        list.add(c2);
        list.add(c3);
        list.add(c4);

        adapter.addItems(list);
    }


    private class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

        private Context context;
        private ArrayList<City> list;
        private LayoutInflater inflater;

        public CustomAdapter(Context context, ArrayList<City> list) {
            this.context = context;
            this.list = list;
            inflater = LayoutInflater.from(context);
        }

        public void addItems(ArrayList<City> cities) {
            this.list = cities;
            notifyDataSetChanged();
        }

        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.list_item_row, parent, false);
            CustomViewHolder holder = new CustomViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(CustomViewHolder holder, int position) {
            City city = list.get(position);

            holder.tv_first.setText(city.getName());
            holder.tv_second.setText(city.getCountry());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private TextView tv_first, tv_second;
            private Button btn_first;

            public CustomViewHolder(View itemView) {
                super(itemView);
                tv_first = (TextView) itemView.findViewById(R.id.tv_first);
                tv_second = (TextView) itemView.findViewById(R.id.tv_second);
                btn_first = (Button) itemView.findViewById(R.id.btn_first);

                itemView.setOnClickListener(this);
                btn_first.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                int id = view.getId();
                if (id == R.id.main_content) {
                    int pos = getAdapterPosition();
                    City city = list.get(pos);
                    Toast.makeText(context, "" + city.getName(), Toast.LENGTH_SHORT).show();
                } else if (id == R.id.btn_first) {
                    Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
