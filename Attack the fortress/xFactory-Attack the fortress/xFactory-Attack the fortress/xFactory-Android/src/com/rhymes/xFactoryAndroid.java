package com.rhymes;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.rhymes.ge.pw.GameApplication;
//import com.rhymes.adventureofpiku.R;

public class xFactoryAndroid extends AndroidApplication {

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        config.useGL20 = true;
        config.useWakelock = true;
        initialize(new GameApplication(), config);
    }
}