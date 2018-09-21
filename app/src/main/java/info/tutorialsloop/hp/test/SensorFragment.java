package info.tutorialsloop.hp.test;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;
//import android.widget.ImageView;
//import android.widget.TextView;

public class SensorFragment extends Fragment
{
    //private double arr[];
    private TextView gas, co2, pm25, pm10;
    private double totalgas = 0, totalco2 = 0, totalpm25 = 0, totalpm10 = 0;
    double[][] arr1 = null;

    public SensorFragment(){

    }
    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fl_sensor, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = this.getArguments();
        if(bundle!=null){
            arr1 = (double[][]) bundle.getSerializable("array");
            Log.d("abcdef", String.valueOf(arr1[1][2]));
        }
    }
}
