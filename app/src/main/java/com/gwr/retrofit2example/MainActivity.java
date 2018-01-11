package com.gwr.retrofit2example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.gwr.retrofit2example.adapter.CourseAdapter;
import com.gwr.retrofit2example.models.Course;
import com.gwr.retrofit2example.models.UdacityCatalog;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    public List<Course> listaCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.rvCardCourse);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        conectarAPIUdacity();
    }

    public void preencherCards() {
        recyclerView.setAdapter(new CourseAdapter(MainActivity.this, listaCourses));
    }

    public void conectarAPIUdacity() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UdacityService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UdacityService service = retrofit.create(UdacityService.class);
        Call<UdacityCatalog> catalogCall = service.listCatalog();

        catalogCall.enqueue(new Callback<UdacityCatalog>() {
            @Override
            public void onResponse(Call<UdacityCatalog> call, Response<UdacityCatalog> response) {

                if (!response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "ERRO NO RESPONSE", Toast.LENGTH_LONG).show();
                } else {
                    UdacityCatalog udacityCatalog = response.body();
                    listaCourses = udacityCatalog.getCourses();
                    preencherCards();
                }
            }

            @Override
            public void onFailure(Call<UdacityCatalog> call, Throwable t) {
                Log.i("LOGWILLIAM", t.getMessage());
                Toast.makeText(MainActivity.this, "ERRO NO REQUEST", Toast.LENGTH_LONG).show();
                conectarAPIUdacity();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
}
