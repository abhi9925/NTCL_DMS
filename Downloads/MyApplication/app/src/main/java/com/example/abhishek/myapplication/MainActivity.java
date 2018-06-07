 package com.example.abhishek.myapplication;

         import android.content.SharedPreferences;
         import android.support.v7.app.AppCompatActivity;
         import android.os.Bundle;
         import android.widget.Button;
         import android.os.Bundle;
         import android.app.Activity;
         import android.content.Intent;
         import android.view.Menu;
         import android.view.View;
         import android.view.View.OnClickListener;
         import android.widget.EditText;
         import android.widget.Toast;

         import com.google.firebase.database.DataSnapshot;
         import com.google.firebase.database.DatabaseError;
         import com.google.firebase.database.DatabaseReference;
         import com.google.firebase.database.FirebaseDatabase;
         import com.google.firebase.database.Query;
         import com.google.firebase.database.ValueEventListener;

         import java.lang.String;
         import java.util.concurrent.TimeUnit;
         import java.util.jar.Attributes;

 public class MainActivity extends AppCompatActivity {


     private void login(String Name, final String key)
     {
         final Intent UserIntent = new Intent(MainActivity.this, HomePage.class);
         final Intent AdminIntent = new Intent(MainActivity.this, HomePageAdmin.class);

         final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Login/");
         Query query = reference.orderByChild("username").equalTo(Name);
         query.addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot dataSnapshot) {
                 if (dataSnapshot.exists()) {
                     for (DataSnapshot creds: dataSnapshot.getChildren()) {
                         String actual_password=creds.child("password").getValue().toString();
                         if (key.equals(actual_password))
                         {
                             String Type=creds.child("type").getValue().toString();
                             Toast.makeText(getBaseContext(), "Welcome "+creds.child("name").getValue().toString(), Toast.LENGTH_SHORT).show();

                             if (Type.equals("User"))
                                startActivity(UserIntent);
                             else
                                 startActivity(AdminIntent);
                         }
                         else {
                             Toast.makeText(getBaseContext(), "Wrong password", Toast.LENGTH_SHORT).show();
                         }
                     }
                 }
                 else
                     Toast.makeText(getBaseContext(), "No Such Username", Toast.LENGTH_SHORT).show();
             }

             @Override
             public void onCancelled(DatabaseError databaseError) {
                 throw databaseError.toException();
             }
         });
     }

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);
         Button btn1= findViewById(R.id.button);
         final EditText user_name=findViewById(R.id.username);
         final EditText password=findViewById(R.id.password);

         btn1.setOnClickListener(new OnClickListener() {
             public void onClick(View v) {
                 login(user_name.getText().toString(),password.getText().toString());
             }
         });
     }

 }
