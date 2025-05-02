package gameEntities;

abstract class BaseWeapon implements WeaponBehavior {
    public final String name;
    public final int damage;
    public final String useMessage;
    public BaseWeapon(String name, int damage, String useMessage) {
        this.name = name;
        this.damage = damage;
        this.useMessage = useMessage;
    }
    public int useWeapon() {
        System.out.println(useMessage);
        return damage;
    }
}
