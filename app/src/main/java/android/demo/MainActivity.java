package android.demo;

import android.os.Bundle;
import android.popwindow.ViewUtil;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


public class MainActivity extends AppCompatActivity {

    RecyclerView recycler;
    private PopAdapter adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewUtil.init(this);
        recycler = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager orderManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(orderManager);
        adapter = new PopAdapter();
        recycler.setHasFixedSize(true);
        recycler.setAdapter(adapter);
    }
}
