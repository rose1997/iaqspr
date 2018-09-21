package info.tutorialsloop.hp.test;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
//import android.view.View;

//import android.widget.TextView;

public class Tab extends FragmentActivity
{
//    private View indicator = null;
    FirebaseDatabase mdatabase;
    DatabaseReference mRef;
    double[][] arr;
    private static FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        fragmentManager = getSupportFragmentManager();
        mdatabase = FirebaseDatabase.getInstance();
        mRef = mdatabase.getReference("sensor");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                SensorFragment fragment = new SensorFragment();
                Bundle bundle = new Bundle();
                arr = new double[(int) dataSnapshot.child("1").getChildrenCount()][(int) dataSnapshot.getChildrenCount()];
                for(int i=1;i<=dataSnapshot.getChildrenCount();i++) {
                    for (int k=0; k<4; k++) {
                        double date = Double.parseDouble(dataSnapshot.child(String.valueOf(i)).child("date").getValue() + "");
                        double humidity = Double.parseDouble(dataSnapshot.child(String.valueOf(i)).child("humidity").getValue() + "");
                        double pressure = Double.parseDouble(dataSnapshot.child(String.valueOf(i)).child("pressure").getValue() + "");
                        double temp = Double.parseDouble(dataSnapshot.child(String.valueOf(i)).child("temp").getValue() + "");
                        if (k==0) {
                            arr[k][i-1] = date;
                        } else if (k == 1) {
                            arr[k][i-1] = humidity;
                        } else if (k == 2) {
                            arr[k][i-1] = pressure;
                        } else {
                            arr[k][i-1] = temp;
                        }
                        Log.d("abcd", String.valueOf(arr[k][i-1]));
                    }
                }
                bundle.putSerializable("array", arr);
                fragment.setArguments(bundle);
                fragmentManager.beginTransaction().replace(R.id.fragmentContainer,fragment).commit();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        //獲取TabHost控制元件
        FragmentTabHost mTabHost =  findViewById(android.R.id.tabhost);
        //設定Tab頁面的顯示區域，帶入Context、FragmentManager、Container ID
        mTabHost.setup(this, getSupportFragmentManager(), R.id.container);

        mTabHost.addTab(mTabHost.newTabSpec("one")
                        .setIndicator("",getResources().getDrawable(R.drawable.ic_pie_chart_black_24dp))
                ,SensorFragment.class,null);


        //同上方Tab設定，不同處為帶入參數的差異

        mTabHost.addTab(mTabHost.newTabSpec("two")
                        .setIndicator("",getResources().getDrawable(R.drawable.ic_place_black_24dp))
                ,MapFragment.class,null);

        //同上方Tab設定，不同處為帶入參數的差異
        mTabHost.addTab(mTabHost.newTabSpec("three")
                        .setIndicator("",getResources().getDrawable(R.drawable.ic_person_black_24dp))
                ,UserFragment.class, null);

    }
}
