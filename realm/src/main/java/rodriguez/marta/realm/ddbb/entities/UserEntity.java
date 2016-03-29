package rodriguez.marta.realm.ddbb.entities;

import java.util.ArrayList;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import rodriguez.marta.realm.models.Car;
import rodriguez.marta.realm.models.User;

/**
 * Created by martarodriguez on 29/3/16.
 */
public class UserEntity extends RealmObject{

    public static final String PRIMARY_KEY = "userId";

    @PrimaryKey
    private int userId;

    private String name;
    private String photoURL;

    private RealmList<CarEntity> cars = new RealmList<>();

    public UserEntity() {
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

    public RealmList<CarEntity> getCars() {
        return cars;
    }

    public void setCars(RealmList<CarEntity> cars) {
        this.cars = cars;
    }

    public static User getUserFromEntity(UserEntity userEntity) {
        User user = new User();
        user.setUserId(userEntity.getUserId());
        user.setName(userEntity.getName());
        user.setPhotoURL(userEntity.getPhotoURL());

        ArrayList<Car> cars = new ArrayList<>();
        if(userEntity.getCars() != null) {
            for (CarEntity carEntity : userEntity.getCars()) {
                cars.add(CarEntity.getCarFromEntity(carEntity));
            }
        }
        user.setCars(cars);

        return user;
    }
}
