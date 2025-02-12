package gh2;

import deque.ArrayDeque;
import deque.Deque;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

public class GuitarHero {
    /** The frequency constant. */
    public static final double CONSTANT = 440.0;
    public static final int STRING_NUM = 37;


    public static void main(String[] args) {
        //  the keyboard that generates keys.
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

        //  create an array with guitarStrings.
        Deque<GuitarString> guitarStrings = new ArrayDeque<>();
        for (int i = 0; i < STRING_NUM; i++) {
            double frequency = CONSTANT * Math.pow(2, ((double) i - 24.0) / 12.0);
            GuitarString newString = new GuitarString(frequency);
            guitarStrings.addLast(newString);
        }

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                GuitarString ringString = guitarStrings.get(index);
                ringString.pluck();
            }

            /* compute the superposition of samples */
            double sample = 0;
            for (int i = 0; i < STRING_NUM; i++) {
                GuitarString string = guitarStrings.get(i);
                sample += string.sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (int i = 0; i < STRING_NUM; i++) {
                GuitarString string = guitarStrings.get(i);
                string.tic();
            }
        }
    }
}
