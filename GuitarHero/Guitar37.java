/**
 * This class allows for creating a multi string Guitar using the
 * GuitarString class.
 *
 * @author Ameya Singh, CSE143 A, TA: Soham P.
 */
public class Guitar37 implements Guitar {
    /**
     * Holds the String of acceptable inputs
     */
    public static final String KEYBOARD =
            "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";  // keyboard layout
    /**
     * Holds the total number of GuitarStrings in the class
     */
    public static final int NUM_STRINGS = 37;

    /**
     * Array of all GuitarStrings
     */
    private GuitarString[] guitarStrings = new GuitarString[NUM_STRINGS];
    /**
     * Holds the count of number of tic() preformed
     */
    private int tics;

    /**
     * Constructs a new Guitar37 with all 37 GuitarStrings
     */
    public Guitar37() {
        for (int i = 0; i < NUM_STRINGS; i++) {
            double frequency = 440.0 * Math.pow(2, (i - 24.0) / 12.0);
            guitarStrings[i] = new GuitarString(frequency);
        }
        tics = 0;
    }

    /**
     * Plucks the GuitarString of specified pitch
     *
     * @param pitch Pitch value of the GuitarString to play
     *              Valid values are between -24 and 12, inclusive
     */
    public void playNote(int pitch) {
        if (pitch >= -24 && pitch <= 12) {
            guitarStrings[pitch + 24].pluck();
        }
    }

    /**
     * Returns if the specified char key is valid and corresponds to an
     * existing GuitarString
     *
     * @param key Char to be compared against valid input keys
     * @return Returns true if the char is valid
     * Returns false if the char is not valid
     */
    public boolean hasString(char key) {
        return KEYBOARD.indexOf(key) != -1;
    }

    /**
     * Plucks the GuitarString that corresponds to the specified character
     *
     * @param key char representing GuitarString to be played
     * @throws IllegalArgumentException thrown if the specified key to be
     *                                  played is not a valid input
     */
    public void pluck(char key) {
        int index = KEYBOARD.indexOf(key);

        if (index == -1) {
            throw new IllegalArgumentException();
        }

        guitarStrings[index].pluck();
    }

    /**
     * Samples all the GuitarStrings present
     *
     * @return Sum of sample() of all contained GuitarStrings
     */
    public double sample() {
        double sample = 0;
        for (GuitarString string : guitarStrings) {
            sample += string.sample();
        }
        return sample;
    }

    /**
     * Calls tic() on all contained GuitarStrings
     */
    public void tic() {
        for (GuitarString string : guitarStrings) {
            string.tic();
        }
        tics++;
    }

    /**
     * Returns the current time
     *
     * @return Returns the number of times tic() has been called
     */
    public int time() {
        return tics;
    }
}