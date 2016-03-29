package rodriguez.marta.realm.ddbb;

import android.util.Log;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import rodriguez.marta.realm.ddbb.entities.CarEntity;
import rodriguez.marta.realm.ddbb.entities.UserEntity;
import rodriguez.marta.realm.models.Car;
import rodriguez.marta.realm.models.User;

/**
 * Created by martarodriguez on 29/3/16.
 */
public class PersonsDBHelper {
    private static final String TAG = PersonsDBHelper.class.getSimpleName();

    public static ArrayList<User> getUsers() {
        // Obtain a Realm instance
        Realm realm = Realm.getDefaultInstance();
        // Or alternatively do the same all at once (the "Fluent interface"):
        RealmResults<UserEntity> result = realm.where(UserEntity.class)
                .findAll();

        ArrayList<User> users = new ArrayList<>();
        for(UserEntity userEntity : result) {
            users.add(UserEntity.getUserFromEntity(userEntity));
        }
        realm.close();
        return users;
    }

    public static void saveUser(User user) {
        // Obtain a Realm instance
        Realm realm = Realm.getDefaultInstance();
        try {
            realm.beginTransaction();
            UserEntity userEntity = new UserEntity();

            userEntity.setUserId(user.getUserId());
            userEntity.setName(user.getName());
            userEntity.setPhotoURL(user.getPhotoURL());

            if(user.getCars() != null) {
                for (Car car : user.getCars()) {
                    CarEntity carEntity = new CarEntity();
                    carEntity.setName(car.getName());
                    carEntity.setCarId(car.getCarId());
                    carEntity.setHp(car.getHp());
                    userEntity.getCars().add(carEntity);
                }
            }

            Log.e("saveUser", "saving ---------> " + userEntity.getName());
            realm.copyToRealmOrUpdate(userEntity);

            realm.commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            realm.cancelTransaction();
        } finally {
            if (realm != null) {
                realm.close();
            }
        }
    }

    public static User getUser(int userId) {
        Realm realm = Realm.getDefaultInstance();
        UserEntity savedUserEntity = realm.where(UserEntity.class)
                .equalTo(UserEntity.PRIMARY_KEY, userId)
                .findFirst();
        if(savedUserEntity != null) {
            Log.e(TAG, "getConversation ------> from realm ---------> " + savedUserEntity.getName());
            User savedUser = UserEntity.getUserFromEntity(savedUserEntity);
            Log.e(TAG, "getConversation --> SAVED CONVERSATION = "+ savedUser.getName());
            realm.close();
            return savedUser;
        } else {
            return null;
        }
    }
}
