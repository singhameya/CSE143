import java.util.*;

/**
 * 
 */
public class AssassinManager {
    private AssassinNode killRing;
    private AssassinNode graveyard;


    /**
     * Constructs a new AssassinManager and initializes the kill ring in the
     * same order of the passed List.
     *
     * @param names List of names in order of how kill ring should be
     *              constructed
     * @throws IllegalArgumentException Thrown if the passed list is empty
     */
    public AssassinManager(List<String> names) {
        if (names.isEmpty()) {
            throw new IllegalArgumentException();
        }

        killRing = new AssassinNode(names.get(0));

        AssassinNode current = killRing;
        for (int i = 1; i < names.size(); i++) {
            while (current.next != null) {
                current = current.next;
            }
            current.next = new AssassinNode(names.get(i));
        }
    }

    /**
     * Prints a formatted version of the current kill ring.
     */
    public void printKillRing() {
        AssassinNode current = killRing;

        while (current != null) {
            String next;
            if (current.next == null) {
                next = killRing.name;
            } else {
                next = current.next.name;
            }

            String s = "    " +
                    current.name +
                    " is stalking " +
                    next;
            System.out.println(s);

            current = current.next;
        }
    }

    /**
     * Prints a formatted version of the current graveyard in last to first
     * order.
     */
    public void printGraveyard() {
        AssassinNode current = graveyard;

        while (current != null) {
            String s = "    " +
                    current.name +
                    " was killed by " +
                    current.killer;
            System.out.println(s);

            current = current.next;
        }
    }

    /**
     * @param name
     * @return
     */
    public boolean killRingContains(String name) {
        return listContains(name, killRing);
    }

    public boolean graveyardContains(String name) {
        return listContains(name, graveyard);
    }

    private boolean listContains(String name, AssassinNode list) {
        AssassinNode current = list;
        while (current != null) {
            if (current.name.toLowerCase().equals(name.toLowerCase())) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean gameOver() {
        return killRing.next == null;
    }

    public String winner() {
        if (gameOver()) {
            return killRing.name;
        }
        return null;
    }

    public void kill(String name) {
        // Exception handling
        if (!killRingContains(name)) {
            throw new IllegalArgumentException();
        }
        if (gameOver()) {
            throw new IllegalStateException();
        }

        AssassinNode currentKill = killRing;
        if (currentKill.name.toLowerCase().equals(name.toLowerCase())) {
            AssassinNode temp = currentKill.next;
            currentKill.next = null;

            AssassinNode other = temp;
            while (other.next != null) {
                other = other.next;
            }
            currentKill.killer = other.name;

            addGrave(currentKill);

            killRing = temp;
        } else {
            while (!currentKill.next.name.toLowerCase().equals(name.toLowerCase())) {
                currentKill = currentKill.next;
            }

            AssassinNode temp = currentKill.next;
            currentKill.next = temp.next;
            temp.next = null;
            temp.killer = currentKill.name;

            addGrave(temp);
        }
    }

    private void addGrave(AssassinNode kill) {
        AssassinNode currentGrave = graveyard;
        if (currentGrave == null) {
            graveyard = kill;
        } else {
            graveyard = kill;
            graveyard.next = currentGrave;
        }
    }
}
