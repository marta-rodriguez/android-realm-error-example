package rodriguez.marta.androidrealmerrorexample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rodriguez.marta.realm.ui.fragments.UsersFragment;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.fab)
    FloatingActionButton fab;

    private UsersFragment usersFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        usersFragment = UsersFragment.newInstance();
        getFragmentManager().beginTransaction()
                .add(R.id.main_content, usersFragment)
                .commit();

        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(DetailActivity.getCallingIntent(MainActivity.this, 1));
            }
        });
    }

    @OnClick(R.id.fab)
    public void onFabClicked() {
        usersFragment.editUser(1, "Carlos = " + (new Date()).toString());
        Toast.makeText(MainActivity.this, "Realm edited", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}