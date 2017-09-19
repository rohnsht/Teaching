package np.com.rohanshrestha.firstproject;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class seventhActivity extends AppCompatActivity {

    private MyAdapter adapter;

    //private String mobile;
    private ArrayList<String> selectedList;
    private ActionMode actionMode;
    private boolean isActionMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seventh);

        selectedList = new ArrayList<>();

        ArrayList<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Samsung");
        list.add("Sony");
        list.add("Nokia");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new MyAdapter(this, list);
        recyclerView.setAdapter(adapter);
    }

    ActionMode.Callback callback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            MenuInflater inflater = mode.getMenuInflater();
            inflater.inflate(R.menu.action_mode_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            int id = item.getItemId();

            if (id == R.id.action_edit) {
                for (int i = 0; i < selectedList.size(); i++) {
                    Log.d("legend", selectedList.get(i));
                }
                //Toast.makeText(seventhActivity.this, "Edit " + mobile, Toast.LENGTH_SHORT).show();
            } else if (id == R.id.action_delete) {
                adapter.deleteItems(selectedList);
            }

            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            isActionMode = false;
            selectedList.clear();
            adapter.notifyDataSetChanged();
        }
    };

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        private Context context;
        private ArrayList<String> mobileList;
        private LayoutInflater inflater;

        public MyAdapter(Context context, ArrayList<String> mobileList) {
            this.context = context;
            this.mobileList = mobileList;
            inflater = LayoutInflater.from(context);
        }

        private void deleteItems(ArrayList<String> nameList) {
            for (int i = 0; i < nameList.size(); i++) {
                mobileList.remove(nameList.get(i));
            }
            notifyDataSetChanged();
            //actionMode.finish();
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup container, int viewType) {
            View view = inflater.inflate(R.layout.list_item_row, container, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            String s = mobileList.get(position);
            holder.tv_first.setText(s);
            holder.tv_first.setTextColor(ContextCompat.getColor(context, R.color.black));

            if (isActionMode) {
                if (selectedList.contains(s)) {
                    holder.tv_first.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
                }
            }
        }

        @Override
        public int getItemCount() {
            return mobileList.size();
        }

        /* View holder class */
        public class MyViewHolder extends RecyclerView.ViewHolder {

            private TextView tv_first;

            public MyViewHolder(View itemView) {
                super(itemView);
                tv_first = (TextView) itemView.findViewById(R.id.tv_first);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int selectedPos = getAdapterPosition();
                        String mobile = mobileList.get(selectedPos);

                        if (isActionMode) {
                            if (selectedList.contains(mobile)) {
                                selectedList.remove(mobile);
                            } else {
                                selectedList.add(mobile);
                            }

                            notifyItemChanged(selectedPos);
                        } else {
                            Toast.makeText(context, mobile, Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {

                        int selectedPos = getAdapterPosition();
                        String mobile = mobileList.get(selectedPos);

                        if (isActionMode) {
                            if (selectedList.contains(mobile)) {
                                selectedList.remove(mobile);
                            } else {
                                selectedList.add(mobile);
                            }
                        } else {
                            actionMode = startSupportActionMode(callback);
                            isActionMode = true;
                            selectedList.add(mobile);
                        }

                        notifyItemChanged(selectedPos);

                        return true;
                    }
                });
            }
        }
    }
}
