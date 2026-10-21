package com.example.notatnikrzepa;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> listaNotatek;
    private ArrayAdapter<String> arrayAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        listaNotatek = new ArrayList<>();

        arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                listaNotatek
        );

        listView.setAdapter(arrayAdapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/Thismaccc/notatnik_json/main/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);

        Call<List<Notatka>> call = jsonPlaceHolder.getNotatka();

        call.enqueue(new Callback<List<Notatka>>() {
            @Override
            public void onResponse(Call<List<Notatka>> call, Response<List<Notatka>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    listaNotatek.clear();
                    for (Notatka notatka : response.body()) {
                        String tekst =
                                "Tytuł: " + notatka.getTytul()
                                        + "\nTreść: " + notatka.getTresc()
                                        + "\nData: " + notatka.getData();
                        listaNotatek.add(tekst);
                    }

                    arrayAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(MainActivity.this, "Błąd odpowiedzi serwera", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Notatka>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Błąd połączenia: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}