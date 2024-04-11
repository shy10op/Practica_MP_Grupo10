package Character.Equipment;

public class EquipmentFactory {

    // Weapon con ataque y defensa
    public static Weapon createWeapon(String name, int modAttack, int modDefense, int spaceHand) {
        return new Weapon(name, modAttack, modDefense, spaceHand);
    }

    // Weapon con solo ataque
    public static Weapon createWeapon(String name, int modAttack, int spaceHand) {
        return new Weapon(name, modAttack, spaceHand);
    }

    // Armor con solo ataque
    public static Armor createArmor(String name, int modDefense) {
        return new Armor(name, modDefense);
    }

    // Armor con ataque y defensa
    public static Armor createArmor(String name, int modAttack, int modDefense) {
        return new Armor(name, modAttack, modDefense);
    }
}
