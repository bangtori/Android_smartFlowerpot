package com.example.smartflowerpot;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class Write extends AppCompatActivity {

    private ScrollView scroll;
    private Button btnRegister;
    private EditText edtTitle, edtTextMultiLine;
    community communityFrag;
    water waterFrag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_item);

        communityFrag = new community();
        waterFrag = new water();
        scroll = (ScrollView) findViewById(R.id.Scroll);
        btnRegister = (Button) findViewById(R.id.BtnRegister);
        edtTitle = (EditText) findViewById(R.id.EdtTitle);
        edtTextMultiLine = (EditText) findViewById(R.id.EdtTextMultiLine);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference();


        scroll.setVerticalScrollBarEnabled(true);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Date date = new Date();
                String title = edtTitle.getText().toString();
                String content = edtTextMultiLine.getText().toString();
                Data data = new Data(title, content);

                DatabaseReference dataRef = ref.child("Data");
                dataRef.push().setValue(data);


                finish();
            }
        });
    }

}