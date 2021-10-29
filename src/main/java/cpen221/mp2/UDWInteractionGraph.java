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
import java.util.Objects;
import java.util.Set;

public class UDWInteractionGraph {
    private static final int USER_A = 0;
    private static final int USER_B = 1;
    private static final int TIME = 2;


    // make a graph: superclass of UDWInteractionGraph


    /* ------- Task 1 ------- */
    /* Building the Constructors */

    private Map<Set<Integer>, Integer> emailWeightMap = new HashMap<>();
    private List<List<Integer>> emailData = new ArrayList<>();
    private List<Integer> users = new LinkedList<>();
    private List<List<Integer>> userInteractions = new LinkedList<>();
    private Map<Integer, List<Integer>> UDWIG = new HashMap<>();

    /**
     * Creates a new UDWInteractionGraph using an email interaction file.
     * The email interaction file will be in the resources directory.
     *
     * @param fileName the name of the file in the resources
     * directory containing email interactions
     */

    public UDWInteractionGraph(String fileName) {
        emailData = makeUdwGraph(fileName);
        getUDWIG(emailData);
    }

    /**
     * create a new UDWInteractionGraph using Data
     * @param data is not Null
     */
    private void getUDWIG(List<List<Integer>> data) {
        emailData = data;
        emailWeightMap = getEmailWeightMap(data);
        // get userInteractions
        emailWeightMap.keySet().forEach(x -> userInteractions.add(x.stream().toList()));
        Set<Integer> userSet = new HashSet<>();
        userInteractions.forEach(userSet::addAll);
        users = userSet.stream().toList();
        getRelations();
    }

    private List<List<Integer>> getData() {
        return emailData;
    }

    private List<List<Integer>> getuserInteractions() {
        return userInteractions;
    }

    private List<Integer> getUsers() {
        return users;
    }

    private Map<Set<Integer>, Integer> getEmailWeightMap(List<List<Integer>> data) {
        return makeWeightGraph(data);
    }


    private void getRelations() {
        for (int i = 0; i < users.size(); i++) {
            Set<Integer> adjacencySet = new HashSet<>();
            int eachUser = users.get(i);
            for (int j = 0; j < userInteractions.size(); j++) {
                if ((userInteractions.get(j).get(USER_A) == eachUser) ||
                    (userInteractions.get(j).get(USER_B) == eachUser)) {
                    adjacencySet.add(userInteractions.get(j).get(USER_A));
                    adjacencySet.add(userInteractions.get(j).get(USER_B));
                }
            }
            adjacencySet.remove(eachUser);
            UDWIG.put(eachUser, adjacencySet.stream().toList());
        }
    }

    private Map<Set<Integer>, Integer> makeWeightGraph(List<List<Integer>> data) {
        // key: user A, value: weight between each user
        Map<Set<Integer>, Integer> emailWeightMap = new HashMap<>();
        Set<Set<Integer>> userSetToExclude = new HashSet<>();

        for (int i = 0; i < data.size(); i++) {
            Set<Integer> userSet = new HashSet<>();
            userSet.add(data.get(i).get(USER_A));
            userSet.add(data.get(i).get(USER_B));
            if (!userSetToExclude.contains(userSet)) {
                emailWeightMap.put(userSet, addAllWeight(data.get(i).get(USER_A),
                    data.get(i).get(USER_B), data));
            }
            userSetToExclude.add(userSet);
        }

        return emailWeightMap;
    }

    private int addAllWeight(int userA, int userB, List<List<Integer>> data) {
        List<List<Integer>> dataNeeded = new ArrayList<>();
        for (List<Integer> integers : data) {
            int user1 = integers.get(USER_A);
            int user2 = integers.get(USER_B);
            if ((user1 == userA || user1 == userB) &&
                (user2 == userA || user2 == userB)) {
                dataNeeded.add(integers);
            }
        }
        int weight = 0;
        for (int i = 0; i < dataNeeded.size(); i++) {
            weight++;
        }
        return weight;
    }

    private List<List<Integer>> makeUdwGraph(String fileName) {
        List<List<Integer>> dataInteger = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            for (String fileLine = reader.readLine();
                 fileLine != null;
                 fileLine = reader.readLine()) {
                dataInteger.add(stringToInteger(fileLine));
            }
            reader.close();
        } catch (IOException ioe) {
            System.out.println("Problem reading file!");
        }

        return dataInteger;
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
        List<List<Integer>> dataOfInput = inputUDWIG.getData();
        List<List<Integer>> UDWTimeConstrained = new ArrayList<>();

        for (int i = 0; i < dataOfInput.size(); i++) {
            int t = dataOfInput.get(i).get(TIME);
            if (t >= timeFilter[0] && t <= timeFilter[1]) {
                UDWTimeConstrained.add(dataOfInput.get(i));
            }
        }

        getUDWIG(UDWTimeConstrained);
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

        List<List<Integer>> data = new ArrayList<>();

        for (int i = 0; i < inputUDWIG.emailData.size(); i++) {
            List<Integer> eachData = inputUDWIG.emailData.get(i);
            if ((userFilter.contains(eachData.get(USER_A)) &&
                userFilter.contains(eachData.get(USER_B)))) {
                data.add(eachData);
            }
        }

        getUDWIG(data);
        users = inputUDWIG.users;
    }

    /**
     * Creates a new UDWInteractionGraph from a DWInteractionGraph object.
     *
     * @param inputDWIG a DWInteractionGraph object
     */
    public UDWInteractionGraph(DWInteractionGraph inputDWIG) {
//        getUDWIG(inputDWIG.getEmailData());
    }

    /**
     * @return a Set of Integers, where every element in the set is a User ID
     * in this DWInteractionGraph.
     */
    public Set<Integer> getUserIDs() {
        Set<Integer> IDSet = new HashSet<>(users);
        return IDSet;
    }

    /**
     * @param sender   the User ID of the sender in the email transaction.
     * @param receiver the User ID of the receiver in the email transaction.
     * @return the number of emails sent from the specified sender to the specified
     * receiver in this DWInteractionGraph.
     */
    public int getEmailCount(int sender, int receiver) {
        Set<Integer> user = new HashSet<>();
        user.add(sender);
        user.add(receiver);

        if (emailWeightMap.containsKey(user)) {
            return emailWeightMap.get(user);
        } else {
            return 0;
        }
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