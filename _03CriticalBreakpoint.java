import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("ALL")
public class _03CriticalBreakpoint {
    public static void main(String[] args) {
        Scanner get = new Scanner(System.in);

        String line = get.nextLine();

        boolean critRatioAvaiable = true;
        boolean critRatioFormed = true;
        Long lines = 0L;
        int intLines = 0;
        Long actualCritRatio = 0L;
        ArrayList<String> lineList = new ArrayList<>();
        while(!line.equals("Break it.")){
            lineList.add(line.replaceAll(" ", ", "));
            String[] coordinates = line.split(" ");
            int startPointX = Integer.parseInt(coordinates[0]);
            int startPointY =  Integer.parseInt(coordinates[1]);
            int endPointX = Integer.parseInt(coordinates[2]);
            int endPointY=  Integer.parseInt(coordinates[3]);

            Long criticalRatio = Math.abs(((long)endPointX + (long)endPointY) - ((long)startPointX + (long)startPointY));
            if(!criticalRatio.equals(0L) && critRatioAvaiable){
                actualCritRatio = criticalRatio;
                critRatioAvaiable = false;
            }

            if(!critRatioAvaiable && !criticalRatio.equals(0L) && !criticalRatio.equals(actualCritRatio)){
                System.out.println("Critical breakpoint does not exist.");
                critRatioFormed = false;
                break;
            }

            lines++;
            intLines++;
            line = get.nextLine();
        }


        if(critRatioFormed){
            BigInteger result = new BigInteger(actualCritRatio.toString());
            result = result.pow(intLines);
            result = result.divideAndRemainder(new BigInteger(lines.toString()))[1];

            //long criticalBreakpoint = ((long)Math.pow(actualCritRatio, lines) % 4);
            for (String t :lineList) {
                System.out.println("Line: [" + t + "]");
            }
            System.out.println("Critical Breakpoint: " + result);
        }

    }
}

