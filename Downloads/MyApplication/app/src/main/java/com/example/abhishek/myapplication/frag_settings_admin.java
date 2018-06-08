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

import com.firebase.client.Firebase;
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
    String newType;
    String newDept;
    String username_password_change;
    private void setUser(String s)
    {
        username_password_change=s;
    }

    Button lo;
    ImageButton addUsersButton;
    ImageButton changePwdButton;

    TextView ta;
    TextView tp;

    EditText OldPwd;
    EditText NewPwd;
    EditText CnfPwd;
    Button CP;

    EditText Name;
    EditText Des;
    EditText userName;
    EditText Password;
    EditText email;
    EditText Phno;
    Button addUserButton;

    Spinner Users;
    Spinner Types;
    Spinner Dept;

    private void initialise(View v)
    {
        lo=v.findViewById(R.id.lgout_bttn);
        addUsersButton=v.findViewById(R.id.imageButtonAddU);
        changePwdButton=v.findViewById(R.id.imageButtonPwd);

        ta=v.findViewById(R.id.textViewAdd);
        tp=v.findViewById(R.id.textViewPwd);
        OldPwd=v.findViewById(R.id.oldPwd);
        NewPwd=v.findViewById(R.id.newPwd);
        CnfPwd=v.findViewById(R.id.cnfPwd);
        CP=v.findViewById(R.id.changePwd);

        Name=v.findViewById(R.id.editTextName);
        Des=v.findViewById(R.id.editTextDes);
        userName=v.findViewById(R.id.editTextUsername);
        Password=v.findViewById(R.id.editTextPassword);
        email=v.findViewById(R.id.editTextemail);
        Phno=v.findViewById(R.id.editTextPhno);
        addUserButton=v.findViewById(R.id.add_button);

        Users = v.findViewById(R.id.usersList);
        Types = v.findViewById(R.id.typeSpinner);
        Dept = v.findViewById(R.id.deptSpinner);

    }

    private void setAddUserView()
    {
        addUsersButton.setVisibility(View.GONE);
        changePwdButton.setVisibility(View.GONE);
        ta.setVisibility(View.GONE);
        tp.setVisibility(View.GONE);
        Types.setVisibility(View.VISIBLE);
        Dept.setVisibility(View.VISIBLE);
        Name.setVisibility(View.VISIBLE);
        Des.setVisibility(View.VISIBLE);
        userName.setVisibility(View.VISIBLE);
        Password.setVisibility(View.VISIBLE);
        email.setVisibility(View.VISIBLE);
        Phno.setVisibility(View.VISIBLE);
        addUserButton.setVisibility(View.VISIBLE);
        lo.setVisibility(View.GONE);
    }

    private Person getDetails()
    {
        String newUserName=userName.getText().toString();
        String newPassword=Password.getText().toString();
        String newName=Name.getText().toString();
        String newDesignation=Des.getText().toString();
        String newEmail=email.getText().toString();
        String newPhno=Phno.getText().toString();
        Person newPerson= new Person(newName,newDesignation,newType,newDept,newUserName,newPassword,newEmail,newPhno);
        return newPerson;
    }

    public void back()
    {
        addUsersButton.setVisibility(View.VISIBLE);
        changePwdButton.setVisibility(View.VISIBLE);
        ta.setVisibility(View.VISIBLE);
        tp.setVisibility(View.VISIBLE);

        Types.setVisibility(View.GONE);
        Dept.setVisibility(View.GONE);
        Name.setVisibility(View.GONE);
        Des.setVisibility(View.GONE);
        userName.setVisibility(View.GONE);
        Password.setVisibility(View.GONE);
        email.setVisibility(View.GONE);
        Phno.setVisibility(View.GONE);
        addUserButton.setVisibility(View.GONE);

        Users.setVisibility(View.GONE);
        OldPwd.setVisibility(View.GONE);
        NewPwd.setVisibility(View.GONE);
        CnfPwd.setVisibility(View.GONE);
        CP.setVisibility(View.GONE);

        lo.setVisibility(View.VISIBLE);

    }

    private void setChangePwdButtonView()
    {
        addUsersButton.setVisibility(View.GONE);
        changePwdButton.setVisibility(View.GONE);
        Users.setVisibility(View.VISIBLE);
        OldPwd.setVisibility(View.VISIBLE);
        NewPwd.setVisibility(View.VISIBLE);
        CnfPwd.setVisibility(View.VISIBLE);
        CP.setVisibility(View.VISIBLE);
        ta.setVisibility(View.GONE);
        tp.setVisibility(View.GONE);
    }

    private void changePassword(String key, String newPassword)
    {
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference("Login/"+key+"/password");
        ref.setValue(newPassword);
        Toast.makeText(getContext(),"Password Changed Successfully",Toast.LENGTH_SHORT).show();
        OldPwd.setText("");
        NewPwd.setText("");
        CnfPwd.setText("");
        Users.setSelection(0);
        back();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_frag_settings_admin, container, false);
        initialise(v);

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


        final String[] TypesList = new String[]{"Select Type","Super Admin", "Admin", "User"};
        ArrayAdapter<String>adapter_types = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,TypesList);
        adapter_types.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Types.setAdapter(adapter_types);

        final String[] DeptList = new String[]{"Select Dept","dept1", "dept2", "dept3"};
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

        Types.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                newType=TypesList[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }

        });
        Dept.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                newDept=DeptList[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }

        });


        addUsersButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setAddUserView();
            }
        });

        addUserButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Person newUser = getDetails();;
                if (newUser.getParity()==1)
                {
                    Toast.makeText(getContext(),"Please Enter Valid Data",Toast.LENGTH_SHORT).show();
                    return;
                }
                DatabaseReference newRef = FirebaseDatabase.getInstance().getReference("Login").push();
                newRef.child("username").setValue(newUser.getUsername());
                newRef.child("password").setValue(newUser.getPassword());
                newRef.child("name").setValue(newUser.getName());
                newRef.child("type").setValue(newUser.getType());
                newRef.child("designation").setValue(newUser.getDesignation());
                newRef.child("department").setValue(newUser.getDepartment());
                newRef.child("phno").setValue(newUser.getPhNo());
                newRef.child("email").setValue(newUser.getEmail());
                Toast.makeText(getContext(),"User added successfully",Toast.LENGTH_SHORT).show();
                back();
                userName.setText("");
                Des.setText("");
                Name.setText("");
                Password.setText("");
                email.setText("");
                Phno.setText("");
                Types.setSelection(0);
                Dept.setSelection(0);
            }
        });

        changePwdButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                setChangePwdButtonView();
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
                final String cp=CnfPwd.getText().toString();
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
                                            //Make Changes to DB
                                            String credentials_key=creds.getKey().toString();
                                            changePassword(credentials_key,np);
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
                    Toast.makeText(getContext(),"New Password does not match with Confirm Password",Toast.LENGTH_SHORT).show();


            }
        });

        return v;
    }
}
