package rodriguez.marta.realm.ui.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import rodriguez.marta.realm.R;
import rodriguez.marta.realm.ddbb.PersonsDBHelper;
import rodriguez.marta.realm.models.Car;
import rodriguez.marta.realm.models.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UsersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UsersFragment extends Fragment {

    private TextView usersInfo;

    public UsersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment UsersFragment.
     */
    public static UsersFragment newInstance() {
        UsersFragment fragment = new UsersFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_users, container, false);
        usersInfo = (TextView) view.findViewById(R.id.usersInfo);

        ArrayList<User> users = PersonsDBHelper.getUsers();
        if(users.size() == 0) {
            ArrayList<Car> cars1 = new ArrayList<>();
            cars1.add(new Car(1, "Ford Focus", 110));
            cars1.add(new Car(2, "Citroen C4", 110));
            cars1.add(new Car(3, "Audi A3", 90));
            PersonsDBHelper.saveUser(new User(1, "Carlos", "http://www.kjadlfkjasdf.com", cars1));

            ArrayList<Car> cars2 = new ArrayList<>();
            cars2.add(new Car(4, "Alfa Romeo", 220));
            cars2.add(new Car(5, "Fiat Panda", 90));
            PersonsDBHelper.saveUser(new User(2, "Klaus", "http://www.32r32.com", cars2));

            ArrayList<Car> cars3 = new ArrayList<>();
            cars3.add(new Car(6, "Citroen C3", 90));
            cars3.add(new Car(7, "Fiat Punto", 110));
            cars3.add(new Car(8, "Seat Leon", 160));
            PersonsDBHelper.saveUser(new User(3, "Gonzalo", "http://www.sdfaew.com", cars3));

            users = PersonsDBHelper.getUsers();
        }
        usersInfo.setText(users.toString());

        return view;
    }

    public void editUser(int userId, String newName) {
        EditRealmRunnable editRealmRunnable = new EditRealmRunnable(userId, newName);
        editRealmRunnable.run();
    }

    public class EditRealmRunnable implements Runnable {
        private int userId;
        private String newName;

        public EditRealmRunnable(int userId, String newName) {
            this.userId = userId;
            this.newName = newName;
        }

        @Override
        public void run() {
            User user = PersonsDBHelper.getUser(userId);
            user.setName(newName);
            PersonsDBHelper.saveUser(user);

            final ArrayList<User> users = PersonsDBHelper.getUsers();
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    usersInfo.setText(users.toString());
                }
            });
        }

    }
}
