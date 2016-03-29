package rodriguez.marta.realm.ddbb.entities;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import rodriguez.marta.realm.models.Car;

/**
 * Created by martarodriguez on 29/3/16.
 */
public class CarEntity extends RealmObject {

    @PrimaryKey
    private int carId;

    private String name;
    private int hp;

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CarEntity() {
    }

    public static Car getCarFromEntity(CarEntity carEntity) {
        Car car = new Car();
        car.setCarId(carEntity.getCarId());
        car.setName(carEntity.getName());
        car.setHp(carEntity.getHp());
        return car;
    }
}
