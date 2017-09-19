package np.com.rohanshrestha.firstproject;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class SearcchActivity extends AppCompatActivity {

    RecyclerView rv_list;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searcch);

        ArrayList<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Samsung");
        list.add("Sony");
        list.add("Nokia");

        /*SearchView searchView = (SearchView) findViewById(R.id.search);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return true;
            }
        });*/

        rv_list = (RecyclerView) findViewById(R.id.rv_list);
        rv_list.setLayoutManager(new LinearLayoutManager(this));
        //rv_list.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));

        adapter = new MyAdapter(this, list);
        rv_list.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search_activity, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return true;
            }
        });
        return true;
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        private Context context;
        private ArrayList<String> list;
        private ArrayList<String> filterList;
        private LayoutInflater inflater;

        private MyAdapter(Context context, ArrayList<String> list) {
            this.context = context;
            this.list = list;
            inflater = LayoutInflater.from(context);

            filterList = new ArrayList<>();
            filterList.addAll(list);
        }

        public void clearList() {
            filterList.clear();
            notifyDataSetChanged();
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.list_item_row, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            String s = filterList.get(position);
            holder.tv_first.setText(s);
        }

        @Override
        public int getItemCount() {
            return filterList.size();
        }

        public void filter(String query) {

            filterList.clear();

            if (TextUtils.isEmpty(query)) {
                filterList.addAll(list);
            } else {
                /*for (String s : list) {
                    if (s.equals(query)) {
                        filterList.add(s);
                    }
                }*/

                for (int i = 0; i < list.size(); i++) {
                    String s = list.get(i);
                    if (s.toLowerCase().contains(query.toLowerCase())) {
                        filterList.add(s);
                    }
                }
            }

            notifyDataSetChanged();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {

            private TextView tv_first;

            public MyViewHolder(View itemView) {
                super(itemView);
                tv_first = (TextView) itemView.findViewById(R.id.tv_first);
            }
        }
    }
}
