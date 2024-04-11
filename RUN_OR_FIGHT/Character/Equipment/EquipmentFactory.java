package Character.Equipment;

public class EquipmentFactory {
    // Método para crear un arma
    public static Weapon createWeapon(String name, int modAttack, int spaceHand) {
        // Valida que modAttack esté entre 1 y 3, y spaceHand entre 1 y 2
        if ((modAttack < 1 || modAttack > 3) || (spaceHand < 1 || spaceHand > 2)) {
            throw new IllegalArgumentException(
                    "El valor de modAttack debe estar entre 1 y 3, y spaceHand debe ser 1 o 2.");
        }
        return new Weapon(name, modAttack, 0, spaceHand); // modDefense por defecto a 0
    }

    // Método para crear un arma con modAttack y modDefense
    public static Weapon createWeapon(String name, int modAttack, int modDefense, int spaceHand) {
        // Valida que modAttack y modDefense estén entre 1 y 3, y spaceHand entre 1 y 2
        if ((modAttack < 1 || modAttack > 3) || (modDefense < 1 || modDefense > 3)
                || (spaceHand < 1 || spaceHand > 2)) {
            throw new IllegalArgumentException(
                    "Los valores de modAttack y modDefense deben estar entre 1 y 3, y spaceHand debe ser 1 o 2.");
        }
        return new Weapon(name, modAttack, modDefense, spaceHand);
    }

    // Método para crear una armadura solo con modDefense
    public static Armor createArmor(String name, int modDefense) {
        // Valida que modDefense esté entre 1 y 3
        if (modDefense < 1 || modDefense > 3) {
            throw new IllegalArgumentException("El valor de modDefense debe estar entre 1 y 3.");
        }
        return new Armor(name, 0, modDefense); // modAttack por defecto a 0
    }

    // Método para crear una armadura con modAttack y modDefense
    public static Armor createArmor(String name, int modAttack, int modDefense) {
        // Valida que modAttack y modDefense estén entre 1 y 3
        if ((modAttack < 1 || modAttack > 3) || (modDefense < 1 || modDefense > 3)) {
            throw new IllegalArgumentException("Los valores de modAttack y modDefense deben estar entre 1 y 3.");
        }
        return new Armor(name, modAttack, modDefense);
    }
}
