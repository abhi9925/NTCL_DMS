package com.example.abhishek.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


public class frag_search_order extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public frag_search_order() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frag_search_order.
     */
    // TODO: Rename and change types and number of parameters
    public static frag_search_order newInstance(String param1, String param2) {
        frag_search_order fragment = new frag_search_order();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v= inflater.inflate(R.layout.fragment_frag_search_order, container, false);
        Bundle arguments = getArguments();
        if (arguments != null) {
            String myValue  = arguments.getString("Try");
        }
        Spinner dropdown = v.findViewById(R.id.spinnerFilter);
        String[] filters = new String[]{"Select Filter","Subject", "Type", "Department","RefNo"};
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,filters);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);

        final Button srch_bttn=v.findViewById(R.id.search_bttn);

        //Filter by Subject/RefNo
        final EditText sub=v.findViewById(R.id.sub_filter);
        final EditText ref=v.findViewById(R.id.refno_filter);

        //Type Filter Options
        final Spinner type_filter = v.findViewById(R.id.spinnerType);
        String[] types = new String[]{"Select Type","Internal Notes", "Type2", "Type3"};
        ArrayAdapter<String>adapter_type = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,types);
        adapter_type.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type_filter.setAdapter(adapter_type);

        //Department Filter options
        final Spinner dept_filter = v.findViewById(R.id.spinnerDept);
        String[] depts = new String[]{"Select Department","Information Technology Notes", "Type2", "Type3"};
        ArrayAdapter<String>adapter_dept = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,depts);
        adapter_dept.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dept_filter.setAdapter(adapter_dept);

        type_filter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                srch_bttn.setVisibility(View.INVISIBLE);

                switch(position)
                {
                    case 0: //No item selected
                        break;
                    case 1: //Filter by Type1
                        srch_bttn.setVisibility(View.VISIBLE);
                        break;
                    case 2: //Filter by Type2
                        srch_bttn.setVisibility(View.VISIBLE);
                        break;
                    case 3: //Filter by Type3
                        srch_bttn.setVisibility(View.VISIBLE);
                        break;
                    case 4: //Filter by Type4
                        srch_bttn.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        dept_filter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                srch_bttn.setVisibility(View.INVISIBLE);

                switch(position)
                {
                    case 0: //No item selected
                        break;
                    case 1: //Filter by Dept1
                        srch_bttn.setVisibility(View.VISIBLE);
                        break;
                    case 2: //Filter by Dept2
                        srch_bttn.setVisibility(View.VISIBLE);
                        break;
                    case 3: //Filter by Dept3
                        srch_bttn.setVisibility(View.VISIBLE);
                        break;
                    case 4: //Filter by Dept4
                        srch_bttn.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });


        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                srch_bttn.setVisibility(View.INVISIBLE);
                sub.setVisibility(View.INVISIBLE);
                ref.setVisibility(View.INVISIBLE);
                dept_filter.setVisibility(View.INVISIBLE);
                type_filter.setVisibility(View.INVISIBLE);

                switch(position)
                {
                    case 0: //No item selected
                            break;
                    case 1: //Filter by Subject
                        sub.setVisibility(View.VISIBLE);
                        srch_bttn.setVisibility(View.VISIBLE);
                        break;
                    case 2: //Filter by Type
                        type_filter.setVisibility(View.VISIBLE);

                        break;
                    case 3:
                        dept_filter.setVisibility(View.VISIBLE);
                        break;
                    case 4: //Filter by RefNo
                        srch_bttn.setVisibility(View.VISIBLE);
                        ref.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        return v;
    }

}
