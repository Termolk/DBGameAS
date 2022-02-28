package ru.samsung.itschool.dbgame;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StatActivity extends Activity {

    private DBManager dbManager;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);

        dbManager = DBManager.getInstance(this);

        textView = findViewById(R.id.textView2);
        textView.setText("Games played: " + dbManager.allGames());



        ArrayList<Result> results = dbManager.getGroupByUserResults();

        AdapterRecyclerViewForResults adapter = new AdapterRecyclerViewForResults(results);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewStat);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
}
