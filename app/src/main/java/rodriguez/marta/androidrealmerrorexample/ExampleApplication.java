package rodriguez.marta.androidrealmerrorexample;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import rodriguez.marta.realm.ddbb.modules.AllRealmModules;

/**
 * Created by martarodriguez on 20/11/15.
 */
public class ExampleApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        // Realm initialization
        RealmConfiguration realmConfig = new RealmConfiguration.Builder(getApplicationContext())
                .name("library.realm")
                .setModules(new AllRealmModules())
                .build();
        Realm.setDefaultConfiguration(realmConfig);
    }
}
