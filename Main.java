

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner in = new Scanner(new java.io.FileReader("src/mazes.txt"));
        StringBuilder sb = new StringBuilder();
        while (in.hasNext()) {
            sb.append("\n" + in.next());
        }
        in.close();

        String data[] = sb.toString().split("]");

        for (int i = 0; i <data.length ; i++) {

            int height = Integer.parseInt(data[i].substring(data[i].indexOf("(") + 1, data[i].indexOf(",")));
            int width = Integer.parseInt(data[i].substring(data[i].indexOf(",") + 1, data[i].indexOf(")")));
            String t[] = data[i].substring(data[i].indexOf("[") + 1).split(",");

            System.out.println("Mine Disabled "+PathCalculator.calculate(height,width,t,false));
            System.out.println("Mine Enabled "+PathCalculator.calculate(height,width,t,true));
        }
    }
}
