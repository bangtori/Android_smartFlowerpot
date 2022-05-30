package com.example.smartflowerpot;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;


public class image extends Fragment {

    private View view;
    private Button btnLogout, btnUploadPic;
    private ImageView imgPlant;
    private FirebaseAuth mAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_image, container, false);

        btnLogout = (Button) view.findViewById(R.id.BtnLogout);
        btnUploadPic = (Button) view.findViewById(R.id.BtnUploadPic);
        imgPlant = (ImageView) view.findViewById(R.id.ImgPlant);
        mAuth = FirebaseAuth.getInstance();

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

        btnUploadPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                mStartForResult.launch(intent);
            }
        });




        return view;
    }
    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode() == Activity.RESULT_OK){
                    try {
                        Uri uri = result.getData().getData();
                        Glide.with(getActivity().getApplicationContext()).load(uri).into(imgPlant);
                        Toast.makeText(getActivity(), "사진 불러오기에 성공하였습니다.",
                                Toast.LENGTH_SHORT).show();
                    }catch (Exception e) {
                        Toast.makeText(getActivity(), "사진을 불러오지 못했습니다.",
                                Toast.LENGTH_SHORT).show();
                    }
                }
                else if(result.getResultCode() == Activity.RESULT_CANCELED){
                    Toast.makeText(getActivity(), "뇨.",
                            Toast.LENGTH_SHORT).show();
                }
            }
    );
}