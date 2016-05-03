import java.util.*;

@SuppressWarnings("ALL")
public class _04DragonArmy {
    public static String dragonType,dragonName;
    public static int dragonDamage,dragonHealth,dragonArmor;
    public static LinkedHashMap<String, TreeSet<Dragon>> dragonList = new LinkedHashMap<>();

    public static void main(String[] args) {
        Scanner get = new Scanner(System.in);
        int n = get.nextInt();
        get.nextLine();

        String input = get.nextLine();
        for (int i = 0; i < n; i++) {
            initDragonStats(input);

            if(!(dragonList.containsKey(dragonType))) { // if there is no such dragon type in the map
                dragonList.put(dragonType, new TreeSet<>(Comparator.naturalOrder()));
                dragonList.get(dragonType).add(new Dragon(dragonName, dragonDamage, dragonHealth, dragonArmor));
            }
            else{
                for (Dragon dragon : dragonList.get(dragonType)) {
                    if (dragonName.equals(dragon.getName())) {
                        dragonList.get(dragonType).remove(dragon);
                        break;
                    }
                }
                dragonList.get(dragonType).add(new Dragon(dragonName, dragonDamage, dragonHealth, dragonArmor)); //will this actually overwrite a previous dragon?

            }
            if(i != (n - 1))
                input = get.nextLine();
        }

        for (Map.Entry<String, TreeSet<Dragon>> type :dragonList.entrySet()){
            System.out.print(type.getKey() + "::");

            int totalDamage = 0;
            int totalHealth = 0;
            int totalArmor = 0;
            for (Dragon currentDragon : type.getValue()) {
                totalDamage += currentDragon.getDamage();
                totalHealth += currentDragon.getHealth();
                totalArmor += currentDragon.getArmor();
            }
            System.out.printf("(%.2f/%.2f/%.2f)\n", (double)totalDamage / (double)type.getValue().size(), (double)totalHealth / (double)type.getValue().size(), (double)totalArmor / (double)type.getValue().size());

            for(Dragon currentDragon : type.getValue()){
                System.out.println("-" + currentDragon.getName() + " -> damage: " + currentDragon.getDamage() + ", health: " + currentDragon.getHealth() + ", armor: " + currentDragon.getArmor());
            }
        }

    }


    public static void initDragonStats(String input){
        String[] inData = input.split(" ");
        dragonType = inData[0];
        dragonName = inData[1];

        if(!(inData[2].equals("null")))
            dragonDamage = Integer.parseInt(inData[2]);
        else
            dragonDamage = 45;


        if(!(inData[3].equals("null")))
            dragonHealth = Integer.parseInt(inData[3]);
        else
            dragonHealth = 250;

        if(!(inData[4].equals("null")))
            dragonArmor = Integer.parseInt(inData[4]);
        else
            dragonArmor = 10;
    }
}


 class Dragon implements Comparable<Dragon>{
    private String name;
    private int damage;
    private int health;
    private int armor;

    public  Dragon(String name, int damage, int health, int armor){
        this.name = name;
        this.damage = damage;
        this.health = health;
        this.armor = armor;
    }

    public String getName(){
        return  this.name;
    }
    public int getDamage(){
        return  this.damage;
    }
    public int getHealth(){
        return  this.health;
    }
    public int getArmor(){
        return  this.armor;
    }

    @Override
    public int compareTo(Dragon other) {
        return this.getName().compareTo(other.getName());
    }
}

