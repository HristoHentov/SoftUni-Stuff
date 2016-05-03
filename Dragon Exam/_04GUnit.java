import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("ALL")
public class _04GUnit {
    public static void main(String[] args) {
        Scanner get = new Scanner(System.in);
        String regex = "^(([A-Z][a-zA-Z0-9]+) \\| ([A-Z][a-zA-Z0-9]+) \\| ([A-Z][a-zA-Z0-9]+))$";
        Pattern myPattern = Pattern.compile(regex);

        TreeMap<String, TreeMap <String, ArrayList<String> >> database = new TreeMap<>();
        String line = get.nextLine();

        while (!(line.equals("It's testing time!"))){
            Matcher myMatcher = myPattern.matcher(line);


            if(myMatcher.find()){
                String className = myMatcher.group(2);
                String methodName = myMatcher.group(3);
                String unitTestName = myMatcher.group(4);

                if(!(database.containsKey(className))){ // if there is no such class
                    database.put(className, new TreeMap<>());
                    database.get(className).put(methodName, new ArrayList<>());
                    database.get(className).get(methodName).add(unitTestName);
                }
                else{ // if it has class
                    if(!database.get(className).containsKey(methodName)) { // if there is no such method
                        database.get(className).put(methodName, new ArrayList<>());
                        database.get(className).get(methodName).add(unitTestName);
                    }
                    else{ // if it has method
                        if(!(database.get(className).get(methodName).contains(unitTestName))){ // if there is no such unit test
                            database.get(className).get(methodName).add(unitTestName);
                        }
                    }
                }
            }
            line = get.nextLine();
        }


        database.entrySet().stream()
                .sorted((classA, classB) -> { // for every 2 class entries
                return Integer.compare(classA.getValue().size(), classB.getValue().size()); // getValue.size() returns the size of the second tree map which means it returns the numbers of methods it has.
        })
                .sorted((classA, classB) -> { // again, for every 2 class entries
                    int[] classAUnitTests = {0};
                    int[] classBUnitTests = {0};
                    classA.getValue() // gets the current classes, TreeMap of <Method -> UnitTests>
                            .entrySet() // gets all entries <Method Name -> UnitTests>
                            .forEach(unitTest -> classAUnitTests[0] += Integer.valueOf(unitTest.getValue().size())); // goes through all entries and and adds the size of their value(the arraylist<string>) to the counter.
                    classB.getValue()
                            .entrySet()
                            .forEach(unitTest -> classBUnitTests[0] += Integer.valueOf(unitTest.getValue().size())); // same as classA stuff, but for the other class entriy

                    return Integer.compare(classBUnitTests[0],classAUnitTests[0]); // compares both variables ( the amounts of unit test each class has, and sorts accordingly). With this, to class sorting is finished.
                })
                .forEach(classEntry -> {
                    System.out.println(classEntry.getKey() + ":"); // prints the class name.

                    classEntry.getValue().entrySet().stream() // a stream to all of the methods of the current classEntry Stream<Entry<String, ArrayList<String>>>
                            .sorted((methodA, methodB) -> {
                                return Integer.compare(methodB.getValue().size(), methodA.getValue().size()); // compares the unit tests of method b, to the unit tests of method A
                            })
                            .forEach(methodEntry -> { // for every method (now sorted)
                                System.out.println("##" + methodEntry.getKey()); // prints the method name

                                methodEntry.getValue().stream() // a stream to all of the unit tests of a method Stream<String>
                                        .sorted(Comparator.naturalOrder()) // sort string alphabetically
                                        .sorted((unitTestA, unitTestB) -> {
                                            return Integer.compare(unitTestA.length(), unitTestB.length());
                                        })
                                        .forEach(unitTest -> {
                                            System.out.println("####" + unitTest);
                                        });
                            });


                });
    }
}
