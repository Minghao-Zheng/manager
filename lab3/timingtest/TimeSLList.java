package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
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
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        AList<Integer> ns = new AList<>();
        AList<Integer> fre = new AList<>();
        AList<Double> time = new AList<>();
        int m = 10000;
        int count, i, j;
        for(count = 1000; count <= 128000; count *= 2) {
            ns.addLast(count);
            fre.addLast(m);
            SLList<Integer> t = new SLList<>();
            for(i = 0; i < count; i++) {
                t.addLast(i);
            }
            Stopwatch sw = new Stopwatch();
            for(j = 0; j < m; j++) {
                t.getLast();
            }
            Double timeinsec = sw.elapsedTime();
            time.addLast(timeinsec);
        }
        printTimingTable(ns, time, fre);
    }
}
