package com.example.smartflowerpot;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class water extends Fragment {

    private ScrollView scroll;
    private Button btnLogout, btnWatering;
    private ImageView imgPlant;
    private Uri imageUri;
    private final FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageRef = storage.getReference();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_water, container, false);

        btnLogout = (Button) view.findViewById(R.id.BtnLogout);
        btnWatering = (Button) view.findViewById(R.id.BtnWatering);
        imgPlant = (ImageView) view.findViewById(R.id.ImgPlant);
        scroll = (ScrollView) view.findViewById(R.id.Scroll);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        scroll.setVerticalScrollBarEnabled(true);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "로그아웃되었습니다.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        FirebaseStorage storage = FirebaseStorage.getInstance("gs://fir-test-5e529.appspot.com/");
        storageRef = storage.getReference();
        storageRef.child("1654756448290.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(getActivity().getApplicationContext()).load(uri).into(imgPlant);
                Toast.makeText(getActivity().getApplicationContext(), "이미지 로드 성공", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity().getApplicationContext(), "이미지 로드 실패", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
}