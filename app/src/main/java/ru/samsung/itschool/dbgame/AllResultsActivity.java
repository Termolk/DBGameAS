package ru.samsung.itschool.dbgame;

import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AllResultsActivity extends Activity {

	private DBManager dbManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_results);

		dbManager = DBManager.getInstance(this);

		ArrayList<Result> results = dbManager.getAllResults();

		RecyclerView recyclerView = findViewById(R.id.recyclerView);
		AdapterRecyclerViewForResults adapterRecyclerViewForResults = new AdapterRecyclerViewForResults(results);
		recyclerView.setAdapter(adapterRecyclerViewForResults);
		recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
	}
}
