package cpen221.mp2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UDWInteractionGraph {
    private static final int USER_A = 0;
    private static final int USER_B = 1;
    private static final int DATA_WEIGHT = 2;

    /* ------- Task 1 ------- */
    /* Building the Constructors */

    /**
     * Creates a new UDWInteractionGraph using an email interaction file.
     * The email interaction file will be in the resources directory.
     *
     * @param fileName the name of the file in the resources
     * directory containing email interactions
     */

    private Map<Integer, List<List<Integer>>> udwGraph = new HashMap<>();
    Map<Set<Integer>, Integer> weightMap = new HashMap<>();

    public UDWInteractionGraph(String fileName) {
        makeWeightGraph(makeDataEachLine(fileName));
        System.out.println(weightMap);
    }

    private void makeWeightGraph(List<List<Integer>> dataEachLine) {
        // key: user A, value: weight between each user
        Map<Set<Integer>, Integer> weightMap = new HashMap<>();
        Set<Set<Integer>> userSetToExclude = new HashSet<>();

        for (int i = 0; i < dataEachLine.size() - 1; i++) {
            Set<Integer> userSet = new HashSet<>();
            userSet.add(dataEachLine.get(i).get(USER_A));
            userSet.add(dataEachLine.get(i).get(USER_B));
            if (!userSetToExclude.contains(userSet)) {
                weightMap.put(userSet, addAllWeight(dataEachLine.get(i).get(USER_A),
                    dataEachLine.get(i).get(USER_B), dataEachLine));
            }
            userSetToExclude.add(userSet);
        }

        this.weightMap = weightMap;
    }

    private int addAllWeight(int userA, int userB, List<List<Integer>> dataEachLine) {
        List<List<Integer>> dataNeeded = new ArrayList<>();
        for (int i = 0; i < dataEachLine.size(); i++) {
            int user1 = dataEachLine.get(i).get(USER_A);
            int user2 = dataEachLine.get(i).get(USER_B);

            if ((user1 == userA || user1 == userB) &&
                (user2 == userA || user2 == userB)) {
                dataNeeded.add(dataEachLine.get(i));
            }
        }
        int weight = 0;
        for (int i = 0; i < dataNeeded.size(); i++) {
            weight += dataNeeded.get(i).get(DATA_WEIGHT);
        }
        return weight;
    }


    private List<List<Integer>> makeDataEachLine(String fileName) {
        List<List<Integer>> DataEachLine = new LinkedList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            for (String fileLine = reader.readLine();
                 fileLine != null;
                 fileLine = reader.readLine()) {
                DataEachLine.add(stringToInteger(fileLine));
            }
            reader.close();
        } catch (IOException ioe) {
            System.out.println("Problem reading file!");
        }

        return DataEachLine;
    }

    private List<Integer> stringToInteger(String fileLine) {
        List<Integer> integerList = new ArrayList<>();
        String[] fileLineParts = fileLine.split("\\s+");

        for (String fileLinePart : fileLineParts) {
            integerList.add(Integer.parseInt(fileLinePart));
        }

        return integerList;
    }


    /**
     * Creates a new UDWInteractionGraph from a UDWInteractionGraph object
     * and considering a time window filter.
     *
     * @param inputUDWIG a UDWInteractionGraph object
     * @param timeFilter an integer array of length 2: [t0, t1]
     *                   where t0 <= t1. The created UDWInteractionGraph
     *                   should only include those emails in the input
     *                   UDWInteractionGraph with send time t in the
     *                   t0 <= t <= t1 range.
     */
    public UDWInteractionGraph(UDWInteractionGraph inputUDWIG, int[] timeFilter) {
        // TODO: Implement this constructor
    }

    /**
     * Creates a new UDWInteractionGraph from a UDWInteractionGraph object
     * and considering a list of User IDs.
     *
     * @param inputUDWIG a UDWInteractionGraph object
     * @param userFilter a List of User IDs. The created UDWInteractionGraph
     *                   should exclude those emails in the input
     *                   UDWInteractionGraph for which neither the sender
     *                   nor the receiver exist in userFilter.
     */
    public UDWInteractionGraph(UDWInteractionGraph inputUDWIG, List<Integer> userFilter) {
        // TODO: Implement this constructor
    }

    /**
     * Creates a new UDWInteractionGraph from a DWInteractionGraph object.
     *
     * @param inputDWIG a DWInteractionGraph object
     */
    public UDWInteractionGraph(DWInteractionGraph inputDWIG) {
        // TODO: Implement this constructor
    }

    /**
     * @return a Set of Integers, where every element in the set is a User ID
     * in this DWInteractionGraph.
     */
    public Set<Integer> getUserIDs() {
        // TODO: Implement this getter method
        return null;
    }

    /**
     * @param sender   the User ID of the sender in the email transaction.
     * @param receiver the User ID of the receiver in the email transaction.
     * @return the number of emails sent from the specified sender to the specified
     * receiver in this DWInteractionGraph.
     */
    public int getEmailCount(int sender, int receiver) {
        // TODO: Implement this getter method
        return 0;
    }

    /* ------- Task 2 ------- */

    /**
     * @param timeWindow is an int array of size 2 [t0, t1]
     *                   where t0<=t1
     * @return an int array of length 2, with the following structure:
     * [NumberOfUsers, NumberOfEmailTransactions]
     */
    public int[] ReportActivityInTimeWindow(int[] timeWindow) {
        // TODO: Implement this method
        return null;
    }

    /**
     * @param userID the User ID of the user for which
     *               the report will be created
     * @return an int array of length 2 with the following structure:
     * [NumberOfEmails, UniqueUsersInteractedWith]
     * If the specified User ID does not exist in this instance of a graph,
     * returns [0, 0].
     */
    public int[] ReportOnUser(int userID) {
        // TODO: Implement this method
        return null;
    }

    /**
     * @param N a positive number representing rank. N=1 means the most active.
     * @return the User ID for the Nth most active user
     */
    public int NthMostActiveUser(int N) {
        // TODO: Implement this method
        return -1;
    }

    /* ------- Task 3 ------- */

    /**
     * @return the number of completely disjoint graph
     * components in the UDWInteractionGraph object.
     */
    public int NumberOfComponents() {
        // TODO: Implement this method
        return 0;
    }

    /**
     * @param userID1 the user ID for the first user
     * @param userID2 the user ID for the second user
     * @return whether a path exists between the two users
     */
    public boolean PathExists(int userID1, int userID2) {
        // TODO: Implement this method
        return false;
    }

}
