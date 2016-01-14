package insa.roomfinder;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import insa.roomfinder.data.Data;
import insa.roomfinder.data.ExtendedRoom;

public class ResultActivity extends Activity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ExtendedRoom> extendedRooms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_salle);
        this.extendedRooms = Data.getInstance().getExtendedRooms().getExtendedRooms();
        mRecyclerView = (RecyclerView) findViewById(R.id.Recycler_View);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RoomAdapter(extendedRooms);
        mRecyclerView.setAdapter(mAdapter);
    }
}