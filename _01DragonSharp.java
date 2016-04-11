import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.List;

@SuppressWarnings("ALL")
public class _01DragonSharp {
    public static int errorLine = 0;

    public static void main(String[] args) {
        Scanner get = new Scanner(System.in);

        int numOfLines = get.nextInt();
        boolean needsElse = false;

        List<String> instructions = new ArrayList<>();

        for (int i = 0; i <= numOfLines; i++)
            instructions.add(get.nextLine());

        String myRegex = "((if|else)\\s*\\((\\d+[<=>]=?\\d+)\\)\\s*(loop\\s\\d+\\s?out|out)\\s*\"(.*?)\";)";
        String elseRegex = "else (out|loop\\s*\\d+\\s*out)\\s*\"(.*?)\";";

        Pattern magic = Pattern.compile(myRegex);
        Pattern secondMagic = Pattern.compile(elseRegex);


        if (validateCompile(instructions, magic, secondMagic)) {
            for (int i = 0; i < instructions.size(); i++) {
                Matcher match = magic.matcher(instructions.get(i));
                Matcher elseMatch = secondMagic.matcher(instructions.get(i));

                if (i < (instructions.size() - 1)) {
                    elseMatch = secondMagic.matcher(instructions.get(i + 1));
                }

                while (match.find()) {
                    if (checkCondition(match.group(3))) {
                        execute(match.group(4), match.group(5));
                    } else {
                        while (elseMatch.find()) {
                            execute(elseMatch.group(1), elseMatch.group(2));
                        }
                    }
                }
            }
        }
        else {
            System.out.println("Compile time error @ line " + errorLine);
        }
    }

    public static boolean checkCondition(String conditionString) {

        String[] conditionElements = conditionString.split("[<>=]+"); // Get only the digits (from 5==5 - 5 and 5, from 11<8 - 11 and 8)
        String sign = conditionString.replaceAll("\\d", ""); // get rid of the digits

        int left = Integer.parseInt(conditionElements[0]); // get left digit
        int right = Integer.parseInt(conditionElements[1]); // get right digit
        boolean result = false; // result of the condition

        switch (sign) {
            case "<":  result = (left < right) ? true : false; break;
            case ">":  result = (left > right) ? true : false; break;
            case "==": result = (left == right) ? true : false; break;
        }
        return result;
    }

    public static void execute(String command, String text) {
        if (command.equals("out")) {
            System.out.println(text);
        } else {
            int loopAmount = Integer.parseInt(command.replaceAll("[a-zA-Z ]+", ""));
            for (int i = 0; i < loopAmount; i++) {
                System.out.println(text);
            }
        }
    }


    public static boolean validateCompile(List<String> text, Pattern ifPattern, Pattern elsePattern) {
        for (int i = 1; i < text.size(); i++) {
            Matcher a = ifPattern.matcher(text.get(i));
            Matcher b = elsePattern.matcher(text.get(i));

            boolean hasIf = false;
            boolean hasElse = false;
            while (a.find()) {
                hasIf = true;
            }
            while (b.find()) {
                hasElse = true;
            }

            if (!hasIf && !hasElse){
                errorLine = i;
                return false;
            }
        }
        return true;
    }
}


