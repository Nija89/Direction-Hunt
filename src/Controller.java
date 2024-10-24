import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

class Controller {
    public static Random random = new Random();
    public static Scanner myScanner = new Scanner(System.in);

    private static final Map<Integer, Location> location = new HashMap<Integer, Location>();

    public Controller() {
        instruction();

        Map<String, String> fullDirectionName = new HashMap<>();
        fullDirectionName.put("UP", "U");
        fullDirectionName.put("DOWN", "D");
        fullDirectionName.put("LEFT", "L");
        fullDirectionName.put("RIGHT", "R");

        Map<String, Integer> locationExits = new HashMap<>();
        locationExits.put("U", 1);
        locationExits.put("D", 2);
        locationExits.put("L", 3);
        locationExits.put("R", 4);
        location.put(0, new Location("START", locationExits));

        locationExits = new HashMap<>();
        locationExits.put("D", 0);
        locationExits.put("R", 2);
        locationExits.put("L", 3);
        location.put(1, new Location( "Kathmandu.", locationExits));

        locationExits = new HashMap<>();
        locationExits.put("U", 0);
        locationExits.put("R", 4);
        locationExits.put("L", 3);
        location.put(2, new Location( "Lalitpur.", locationExits));

        locationExits = new HashMap<>();
        locationExits.put("R", 0);
        locationExits.put("U", 1);
        locationExits.put("D", 2);
        location.put(3, new Location( "Bhaktapur.", locationExits));

        locationExits = new HashMap<>();
        locationExits.put("L", 0);
        locationExits.put("R", 3);
        locationExits.put("U", 1);
        location.put(4, new Location("Kirtipur.", locationExits));

        boolean found = false;
        int chance = 2;
        int direction = 0;
        boolean isTrue = true;
        int randomNum = random.nextInt(1, 4);
        System.out.println("Answer = " + randomNum);


        while (isTrue) {
            int i = 0;
            System.out.println("You are currently at : " + location.get(direction).getLocationDescription());
            System.out.println("===========================");
            System.out.println("Remaining Chance : " + chance);
            System.out.println("===========================");

            Map<String, Integer> currentSelectedLocation = location.get(direction).getExits();
            System.out.println("Available Location You can go: ");
            for (String selectedKey : currentSelectedLocation.keySet()) {
                System.out.print("Press " + selectedKey + " to go to " +
                        location.get(currentSelectedLocation.get(selectedKey)).getLocationDescription()
                        + "\n"
                );
                i++;
            }


            System.out.print("\n Enter the next location you wanna go: ");
            String myString = myScanner.nextLine().toUpperCase();
            String[] myStringArray = myString.split(" ");


            for(String seperateString : myStringArray){
                if(fullDirectionName.containsKey(seperateString)){
                    myString = fullDirectionName.get(seperateString);
                    break;
                }else if(currentSelectedLocation.containsKey(seperateString)){
                    myString = seperateString;
                    break;
                }


            }


            for (String selectedKey : currentSelectedLocation.keySet()) {
                if (myString.equals(selectedKey)) {
                    found = true;
                    direction = currentSelectedLocation.get(selectedKey);
                    if (randomNum == direction) {
                        System.out.println("You Won. ");
                        isTrue = false;
                    } else {
                        chance = chance - 1;
                    }
                }
            }


            if (!found) {
                System.out.println("Only Enter Available Loaciton.");
            }
            if (chance < 1) {
                System.out.println("You Lose.");
                System.out.println("Correct Answer is " + location.get(randomNum).getLocationDescription());
                isTrue = false;
            }
        }
    }
    public void instruction(){
        System.out.println();
        System.out.println("** Instruction ** \n" +
                "To move, type the direction " +
                "(either its full name or the shorthand: U for UP, D for DOWN, L for LEFT, and R for RIGHT).\n" +
                "The goal is to find the hidden winning location by moving around the map.(2 chances at the start)\n" +
                "");
    }
}