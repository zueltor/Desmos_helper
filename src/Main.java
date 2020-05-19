import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("values.txt");
        Scanner sc = new Scanner(file);

        ArrayList<Double> values = new ArrayList<>();

        while (sc.hasNext()) {
            values.add(sc.nextDouble());
        }

        values.sort(Comparator.comparingDouble(a -> a));

        File out = new File("result.txt");
        PrintWriter pw = new PrintWriter(out);

        double max_delta = 0;
        double delta;
        double val;
        double point=0;

        pw.print("-0.1,0\n");
        double height = 0.0;
        double incr = 1.0 / values.size();
        for (int i = 0; i < values.size(); i++) {
            val=values.get(i);
            delta=Math.abs(val-height);
            if (max_delta<delta){
                point=val;
                max_delta=delta;
            }
            pw.print(val + "," + height + "\n");
            height = (i + 1) * incr;
            if (max_delta<delta){
                point=val;
                max_delta=delta;
            }
            pw.print(val + "," + height + "\n");
        }
        pw.print("1.1,1\n");
        pw.print(String.format("\nMax difference %f in point %f",max_delta,point));
        pw.close();
        sc.close();
    }
}
