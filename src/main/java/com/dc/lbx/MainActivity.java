package com.dc.lbx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements HexagonViewYC.MySelectListener {
    HexagonView h1;
    HexagonView h2;
    HexagonView h3;
    HexagonView h4;
    HexagonView h5;
    HexagonView h6;
    HexagonView h7;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        h1 = (HexagonView) findViewById(R.id.h1);
//        h2 = (HexagonView) findViewById(R.id.h2);
//        h3 = (HexagonView) findViewById(R.id.h3);
//        h4 = (HexagonView) findViewById(R.id.h4);
//        h5 = (HexagonView) findViewById(R.id.h5);
//        h6 = (HexagonView) findViewById(R.id.h6);
//        h7 = (HexagonView) findViewById(R.id.h7);
//
//        initConfig();

        HexagonLayout hly = (HexagonLayout) findViewById(R.id.hly);

        for (int i = 0 ; i < hly.getChildCount(); i ++){
            HexagonViewYC hv = (HexagonViewYC) hly.getChildAt(i);
            hv.setMySelectListener(this);
        }
    }


    void initConfig(){
        double a = Math.PI/6;
        double width = Utils.getDeviceWidth(this);
        double x = width/2;
        double y = width/2;
        double radius = width/6;

        double x1 = x ;
        double y1 = y - 2* radius * Math.cos(a);

        double x2 = x + radius + radius * Math.sin(a);
        double y2 = y - radius * Math.cos(a);

        double x3 = x2;
        double y3 = y + radius * Math.cos(a);

        double x4 = x;
        double y4= y + 2* radius* Math.cos(a);

        double x5 = x - radius - radius * Math.sin(a);
        double y5 = y + radius * Math.cos(a);

        double x6 = x5;
        double y6 = y - radius * Math.cos(a);

        h1.setData(x, y , radius);
        h2.setData(x1, y1 , radius);
        h3.setData(x2, y2 , radius);
        h4.setData(x3, y3 , radius);
        h5.setData(x4, y4 , radius);
        h6.setData(x5, y5 , radius);
        h7.setData(x6, y6 , radius);

    }

    @Override
    public void onSelected(HexagonViewYC view, int index) {
        Toast.makeText(this, "index="+ index, Toast.LENGTH_SHORT).show();
    }
}
