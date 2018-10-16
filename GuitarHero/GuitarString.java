import java.util.*;

/**
 * GuitarString models the vibration of a guitar string at a given frequency
 *
 * @author Ameya Singh, CSE143 A, TA: Soham P.
 */
public class GuitarString {
    /**
     * Represents the current ring buffer of the string
     */
    private Queue<Double> ringBuffer = new LinkedList<>();

    /**
     * Holds the constant sampling rate
     */
    public static final int SAMPLE_RATE = StdAudio.SAMPLE_RATE;
    /**
     * Holds the constant energy decay factor
     */
    public static final double ENERGY_DECAY_FACTOR = 0.996;

    /**
     * Creates a new GuitarString whose ring buffer size is based the given
     * frequency
     *
     * @param frequency Frequency to create GuitarString of
     * @throws IllegalArgumentException Thrown if frequency is invalid as it
     *                                  would cause a ring buffer size of < 2
     */
    public GuitarString(double frequency) {
        int capacity = (int) Math.round(SAMPLE_RATE / frequency);

        if (capacity < 2 || frequency <= 0) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < capacity; i++) {
            ringBuffer.add((double) 0);
        }
    }

    /**
     * This constructor is used for testing
     * Creates a new GuitarString whose ring buffer represents the contents of
     * the passed array
     *
     * @param init Values to initialize ring buffer to
     * @throws IllegalArgumentException Thrown if ring buffer size would be less
     *                                  than 2
     */
    public GuitarString(double[] init) {
        if (init.length < 2) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < init.length; i++) {
            ringBuffer.add(init[i]);
        }
    }

    /**
     * Fills the ring buffer with white noise
     */
    public void pluck() {
        Random random = new Random();
        for (int i = 0; i < ringBuffer.size(); i++) {
            ringBuffer.remove();
            ringBuffer.add(random.nextDouble() - 0.5);
        }
    }

    /**
     * Applies one tic of the Karplus-Strong algorithm to the ring buffer
     */
    public void tic() {
        double avg = (ringBuffer.remove() + ringBuffer.peek()) / 2.0;
        ringBuffer.add(avg * ENERGY_DECAY_FACTOR);
    }

    /**
     * Samples the ring buffer
     *
     * @return Returns the first value in the ring buffer
     */
    public double sample() {
        return ringBuffer.peek();
    }
}
