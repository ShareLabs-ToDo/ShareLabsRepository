package com.example.sharedto_doapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.example.sharedto_doapp.AllTasksActivity;
import com.example.sharedto_doapp.DetailedTaskActivity;
import com.example.sharedto_doapp.LoginActivity;
import com.example.sharedto_doapp.R;
import com.parse.ParseUser;

import static com.example.sharedto_doapp.LoginActivity.TAG;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Toolbar tbToolbar;
    private ImageView ivPostImage;
    private TextView tvYourTasks;
    private TextView tvAllTasks;
    private TextView tvProjectName;
    private ImageView ivSettingsIcon;
    private ProgressBar pbProgressBar;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tbToolbar = view.findViewById(R.id.tbToolbar);
        ivPostImage = view.findViewById(R.id.ivPostImage);
        tvProjectName = view.findViewById(R.id.tvProjectName);
        tvYourTasks = view.findViewById(R.id.tvYourTasks);
        tvAllTasks = view.findViewById(R.id.tvAllTasks);
        ivSettingsIcon = view.findViewById(R.id.ivSettingsIcon);

        tvYourTasks.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent startDetailedTaskActivityIntent = new Intent(getContext(), /*DetailedTaskActivity*/YourTasksFragment.class);
                startActivity(startDetailedTaskActivityIntent);
            }
        });


        tvAllTasks.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent startAllTasksActivityIntent = new Intent(getContext(), AllTasksActivity.class);
                startActivity(startAllTasksActivityIntent);
            }
        });

        ivSettingsIcon.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                Intent startLoginActivityIntent = new Intent(getContext(), LoginActivity.class);
                startActivity(startLoginActivityIntent);
            }
        });

    }
}