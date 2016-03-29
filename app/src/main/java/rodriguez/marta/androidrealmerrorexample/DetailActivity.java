package rodriguez.marta.androidrealmerrorexample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;
import rodriguez.marta.realm.ddbb.PersonsDBHelper;
import rodriguez.marta.realm.models.User;

public class DetailActivity extends AppCompatActivity {
    private static final String USER_ID_KEY = "user_id_key";

    public static Intent getCallingIntent(Context context, int userId) {
        Intent callingIntent = new Intent(context, DetailActivity.class);
        Bundle b = new Bundle();
        b.putInt(USER_ID_KEY, userId);
        callingIntent.putExtras(b);
        return callingIntent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(toolbar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        int userId = getIntent().getIntExtra(USER_ID_KEY, 0);
//        User user = PersonsDBHelper.getUser(userId);
//        setTitle(user.getName());
        GetRealmRunnable getRealmRunnable = new GetRealmRunnable(userId);
        getRealmRunnable.run();
    }

    public class GetRealmRunnable implements Runnable {
        private int userId;

        public GetRealmRunnable(int userId) {
            this.userId = userId;
        }

        @Override
        public void run() {
            final User user = PersonsDBHelper.getUser(userId);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setTitle(user.getName());
                }
            });
        }
    }

}
