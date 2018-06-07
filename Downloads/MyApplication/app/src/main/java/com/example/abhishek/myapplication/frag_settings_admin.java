package com.example.abhishek.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.view.View.OnClickListener;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class frag_settings_admin extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public frag_settings_admin() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment frag_settings.
     */
    // TODO: Rename and change types and number of parameters
    public static frag_settings_admin newInstance(String param1, String param2) {
        frag_settings_admin fragment = new frag_settings_admin();
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

    String username_password_change;
    public void setUser(String s)
    {
        username_password_change=s;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_frag_settings_admin, container, false);
        final Button lo=v.findViewById(R.id.lgout_bttn);
        final ImageButton addUsersButton=v.findViewById(R.id.imageButtonAddU);
        final ImageButton changePwdButton=v.findViewById(R.id.imageButtonPwd);

        final TextView ta=v.findViewById(R.id.textViewAdd);
        final TextView tp=v.findViewById(R.id.textViewPwd);

        final EditText OldPwd=v.findViewById(R.id.oldPwd);
        final EditText NewPwd=v.findViewById(R.id.newPwd);
        final EditText CnfPwd=v.findViewById(R.id.cnfPwd);
        final Button CP=v.findViewById(R.id.changePwd);

        final EditText Name=v.findViewById(R.id.editTextName);
        final EditText Des=v.findViewById(R.id.editTextDes);
        final EditText userName=v.findViewById(R.id.editTextUsername);
        final EditText Password=v.findViewById(R.id.editTextPassword);
        final EditText email=v.findViewById(R.id.editTextemail);
        final EditText Phno=v.findViewById(R.id.editTextPhno);
        final Button addUserButton=v.findViewById(R.id.add_button);


        lo.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), MainActivity.class);
                startActivity(myIntent);
                //your Code Goes Here
            }
        });


        final ArrayList<String> Users_List= new ArrayList<>();
        Users_List.add("Select User");
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Login/");
        Query query = reference.orderByChild("username");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot creds: dataSnapshot.getChildren()) {
                        Users_List.add(creds.child("username").getValue().toString());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });


        final Spinner Users = v.findViewById(R.id.usersList);

        final Spinner Types = v.findViewById(R.id.typeSpinner);
        String[] TypesList = new String[]{"Select Type","type1", "Type2", "Type3"};
        ArrayAdapter<String>adapter_types = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,TypesList);
        adapter_types.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Types.setAdapter(adapter_types);

        final Spinner Dept = v.findViewById(R.id.deptSpinner);
        String[] DeptList = new String[]{"Select Dept","dept1", "dept2", "dept3"};
        ArrayAdapter<String>adapter_dept = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,DeptList);
        adapter_dept.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Dept.setAdapter(adapter_dept);

        Users.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                setUser(Users_List.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }

        });

        addUsersButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                addUsersButton.setVisibility(View.INVISIBLE);
                changePwdButton.setVisibility(View.INVISIBLE);
                ta.setVisibility(View.INVISIBLE);
                tp.setVisibility(View.INVISIBLE);
                Types.setVisibility(View.VISIBLE);
                Dept.setVisibility(View.VISIBLE);
                Name.setVisibility(View.VISIBLE);
                Des.setVisibility(View.VISIBLE);
                userName.setVisibility(View.VISIBLE);
                Password.setVisibility(View.VISIBLE);
                email.setVisibility(View.VISIBLE);
                Phno.setVisibility(View.VISIBLE);
                addUserButton.setVisibility(View.VISIBLE);
                lo.setVisibility(View.INVISIBLE);
            }
        });


        changePwdButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                addUsersButton.setVisibility(View.INVISIBLE);
                changePwdButton.setVisibility(View.INVISIBLE);
                Users.setVisibility(View.VISIBLE);
                OldPwd.setVisibility(View.VISIBLE);
                NewPwd.setVisibility(View.VISIBLE);
                CnfPwd.setVisibility(View.VISIBLE);
                CP.setVisibility(View.VISIBLE);
                ta.setVisibility(View.INVISIBLE);
                tp.setVisibility(View.INVISIBLE);
                String [] UserNames= new String[Users_List.size()];
                for (int i=0; i<Users_List.size(); i++) {
                    UserNames[i] = Users_List.get(i);
                }
                final ArrayAdapter<String>adapter_users = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,UserNames);
                adapter_users.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                Users.setAdapter(adapter_users);
            }
        });

        CP.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                final String op=OldPwd.getText().toString();
                final String np=NewPwd.getText().toString();
                String cp=CnfPwd.getText().toString();
                if (username_password_change.equals("Select User"))
                    Toast.makeText(getContext(),"Please Select a User",Toast.LENGTH_SHORT).show();
                else if (op.equals(""))
                    Toast.makeText(getContext(),"Please Enter Old Password",Toast.LENGTH_SHORT).show();
                else if (np.equals(""))
                    Toast.makeText(getContext(),"Please Enter New Password",Toast.LENGTH_SHORT).show();
                else if (cp.equals(np)) {
                    Query query = reference.orderByChild("username").equalTo(username_password_change);
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                for (DataSnapshot creds: dataSnapshot.getChildren()) {
                                    String old_key=creds.child("password").getValue().toString();
                                    if (old_key.equals(op)){
                                        if (op.equals(np))
                                            Toast.makeText(getContext(),"New Password matches Old Password",Toast.LENGTH_SHORT).show();
                                        else {
                                            //Toast.makeText(getContext(),username_password_change,Toast.LENGTH_SHORT).show();
                                            //Make Changes to DB
                                            String cc=creds.getKey().toString();
                                            DatabaseReference ref=FirebaseDatabase.getInstance().getReference("Login/"+cc+"/password");
                                            ref.setValue(np);
                                            Toast.makeText(getContext(),"Password Changed Successfully",Toast.LENGTH_SHORT).show();
                                            OldPwd.setText("");
                                            NewPwd.setText("");
                                            CnfPwd.setText("");
                                        }
                                    }
                                    else
                                        Toast.makeText(getContext(),"Incorrect Old Password",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            throw databaseError.toException();
                        }
                    });

                }
                else
                    Toast.makeText(getContext(),"New Password does not match with Confirm Passord",Toast.LENGTH_SHORT).show();


            }
        });

        return v;
    }
}
