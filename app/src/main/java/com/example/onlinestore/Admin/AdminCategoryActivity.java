package com.example.onlinestore.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.onlinestore.HomeActivity;
import com.example.onlinestore.MainActivity;
import com.example.onlinestore.R;


public class AdminCategoryActivity extends AppCompatActivity
{
    private ImageView cream,powder;
    private ImageView brush, foundation;
    private ImageView eyeshadow, lipstick;
    private Button LogoutBtn, CheckOrdersBtn, maintainProductBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);

        LogoutBtn =(Button)findViewById(R.id.admin_logout_btn) ;
        CheckOrdersBtn =(Button)findViewById(R.id.check_orders_btn) ;
        maintainProductBtn =(Button)findViewById(R.id.maintain_btn) ;

        maintainProductBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminCategoryActivity.this, HomeActivity.class);
                intent.putExtra("Admin", "Admin");
                startActivity(intent);


            }
        });


        LogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        CheckOrdersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewOrderActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

                startActivity(intent);

            }
        });



        cream = (ImageView) findViewById(R.id.cream);
        powder = (ImageView) findViewById(R.id.powder);

        brush = (ImageView) findViewById(R.id.brush);
        foundation = (ImageView) findViewById(R.id.foundation);


        eyeshadow = (ImageView) findViewById(R.id.eyeshadow);
        lipstick = (ImageView) findViewById(R.id.lipstick);


        cream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewproductActivity.class);
                intent.putExtra("category", "Creme");
                startActivity(intent);
            }
        });


        powder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewproductActivity.class);
                intent.putExtra("category", "Pudra de fata");
                startActivity(intent);
            }
        });


        brush.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewproductActivity.class);
                intent.putExtra("category", "Pensule Make up");
                startActivity(intent);
            }
        });


        foundation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewproductActivity.class);
                intent.putExtra("category", "Fond de ten");
                startActivity(intent);
            }
        });


        eyeshadow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewproductActivity.class);
                intent.putExtra("category", "Palete de farduri");
                startActivity(intent);
            }
        });


        lipstick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewproductActivity.class);
                intent.putExtra("category", "Rujuri");
                startActivity(intent);
            }
        });




    }
}