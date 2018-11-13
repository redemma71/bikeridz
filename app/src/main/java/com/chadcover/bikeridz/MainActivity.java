package com.chadcover.bikeridz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position) {
                    case 0:
                        Intent intentFindBikeShop = new Intent(MainActivity.this, FindBikeShopActivity.class);
                        startActivity(intentFindBikeShop);
                        break;
                    case 1:
                        Intent intentBikeInventory = new Intent(MainActivity.this, WishlistActivity.class);
                        startActivity(intentBikeInventory);
                        break;
                    case 2:
                        Intent intentMapMyRide = new Intent(MainActivity.this, MapMyRideActivity.class);
                        startActivity(intentMapMyRide);
                        break;
                    case 3:
                        Intent intentTurnByTurn = new Intent(MainActivity.this, TurnByTurnActivity.class);
                        startActivity(intentTurnByTurn);
                        break;
                    case 4:
                        Intent intentMySelfieMarkers = new Intent(MainActivity.this, MySelfieMarkersActivity.class);
                        startActivity(intentMySelfieMarkers);
                        break;
                }
            }
        };

        ListView listView = (ListView) findViewById(R.id.bikeRidzOptions);
        listView.setOnItemClickListener(itemClickListener);
    }
}
