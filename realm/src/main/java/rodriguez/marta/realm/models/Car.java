package rodriguez.marta.realm.models;

/**
 * Created by martarodriguez on 29/3/16.
 */
public class Car {

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
}
