package rodriguez.marta.realm.models;

import java.util.ArrayList;

/**
 * Created by martarodriguez on 29/3/16.
 */
public class User {

    private int userId;
    private String name;
    private String photoURL;

    private ArrayList<Car> cars;

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
