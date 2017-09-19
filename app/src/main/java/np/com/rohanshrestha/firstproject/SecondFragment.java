package np.com.rohanshrestha.firstproject;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SecondFragment extends Fragment {

    private TextView tv_first;
    private String result;

    public SecondFragment() {
        // Required empty public constructor
    }

    public static SecondFragment newInstance(String s) {
        SecondFragment fragment = new SecondFragment();

        Bundle b = new Bundle();
        b.putString("Param1", s);

        fragment.setArguments(b);

        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null) {
            result = getArguments().getString("Param1");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_second, container, false);

        tv_first = (TextView) layout.findViewById(R.id.tv_first);

        tv_first.  setText(result);

        return layout;
    }

    public void setData(String s) {
        tv_first.setText(s);
    }

}
