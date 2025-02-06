package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        // TODO: YOUR CODE HERE
        AList<Integer> ns = new AList<>();
        AList<Double> t = new AList<>();
        int count, i;
        for(count = 1000; count <= 128000; count *= 2) {
            AList<Integer> table = new AList<>();
            ns.addLast(count);
            Stopwatch sw = new Stopwatch();
            for(i = 0; i < count; i++) {
                table.addLast(i);
            }
            double timeinsec = sw.elapsedTime();
            t.addLast(timeinsec);
        }
        printTimingTable(ns, t, ns);
    }
}
