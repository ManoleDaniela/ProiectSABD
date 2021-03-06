package com.example.onlinestore;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlinestore.Admin.AdminMaintainProductsActivity;
import com.example.onlinestore.Model.ProductViewHolder;
import com.example.onlinestore.Model.Products;
import com.example.onlinestore.Prevalent.Prevalent;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;
import io.paperdb.Paper;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DatabaseReference ProductsRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    private String type ="";


    private AppBarConfiguration mAppBarConfiguration;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Intent intent =getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle !=null)
        {
            type = getIntent().getExtras().get("Admin").toString();
        }





        Toolbar toolbar = findViewById(R.id.toolbar);
        if(type.equals("Admin")) {


            toolbar.setTitle(" ");
        }
        toolbar.setTitle("Home");

        setSupportActionBar(toolbar);





        ProductsRef = FirebaseDatabase.getInstance().getReference().child("Products");
        Paper.init(this);
       // Toast.makeText(this, "111!", Toast.LENGTH_SHORT).show();
        FloatingActionButton fab = findViewById(R.id.fab);

        if(type.equals("Admin")) {

            fab.setVisibility(View.GONE);
        }
        else
        {
            fab.setVisibility(View.VISIBLE);

        }
               // fab.setVisibility(View.VISIBLE);



        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                 //       .setAction("Action", null).show();

                if(!type.equals("Admin"))
                {
                    Intent intent = new Intent(HomeActivity.this, CartActivity.class);
                    startActivity(intent);


                }



            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);


        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView= navigationView.getHeaderView(0);
        TextView userNameTextView = headerView.findViewById(R.id.user_profile_name);
        CircleImageView profileImageView = headerView.findViewById(R.id.user_profile_image);

        //Toast.makeText(this, "33333!", Toast.LENGTH_SHORT).show();

        if(!type.equals("Admin"))
        {
            userNameTextView.setText(Prevalent.currentOnlineUser.getName());
            Picasso.get().load(Prevalent.currentOnlineUser.getImage()).placeholder(R.drawable.profile).into(profileImageView);

        }



        recyclerView = findViewById(R.id.recycler_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);

    }

    @Override
    protected void onStart()
    {
        super.onStart();


        //Toast.makeText(this, "44444!", Toast.LENGTH_SHORT).show();
        FirebaseRecyclerOptions<Products> options =
                new FirebaseRecyclerOptions.Builder<Products>()
                        .setQuery(ProductsRef, Products.class)
                        .build();


        FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter =
                new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position,
                                                    @NonNull Products model)
                    {
                        holder.txtProductName.setText(model.getPname());
                        holder.txtProductDescription.setText(model.getDescription());
                        holder.txtProductPrice.setText("Pret = " + model.getPrice() + " lei");
                        Picasso.get().load(model.getImage()).into(holder.imageView);

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(type.equals("Admin"))
                                {
                                    Intent intent = new Intent(HomeActivity.this, AdminMaintainProductsActivity.class);
                                    intent.putExtra("pid",model.getPid());
                                    startActivity(intent);

                                }
                                else
                                {
                                    Intent intent = new Intent(HomeActivity.this,ProductDetailsActivity.class);
                                    intent.putExtra("pid",model.getPid());
                                    startActivity(intent);
                                }

                            }
                        });
                    }

                    @NonNull
                    @Override
                    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                    {
                        View view = LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.product_items_layout, parent, false);
                        ProductViewHolder holder = new ProductViewHolder(view);
                        return holder;
                    }
                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
      //  Toast.makeText(this, "6666!", Toast.LENGTH_SHORT).show();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        //Toast.makeText(this, "6666!", Toast.LENGTH_SHORT).show();
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Toast.makeText(this, "77777!", Toast.LENGTH_SHORT).show();
        int id = item.getItemId();


        return super.onOptionsItemSelected(item);


    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {//Toast.makeText(this, "888888!", Toast.LENGTH_SHORT).show();
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        //Toast.makeText(this, "aaaaaaaaaaaaaa!", Toast.LENGTH_SHORT).show();
        if (id == R.id.nav_cart)
        {   if(!type.equals("Admin")) {
            Intent intent = new Intent(HomeActivity.this, CartActivity.class);
            startActivity(intent);
        }
        }
        else if (id == R.id.nav_search)
        {
            if(!type.equals("Admin"))
            {
                Intent intent = new Intent(HomeActivity.this, SearchProducsActivity.class);
                startActivity(intent);
            }

        }
//        else if (id == R.id.nav_categories)
//        {
//
//        }
        else if (id == R.id.nav_settings)
        {
            if(!type.equals("Admin"))
            {
                Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
                startActivity(intent);
            }



        }
        else if (id == R.id.nav_logout)
        {
            if(!type.equals("Admin"))
            {
                Paper.book().destroy();

                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}




















