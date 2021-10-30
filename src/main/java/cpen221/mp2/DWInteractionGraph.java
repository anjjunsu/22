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

public class DWInteractionGraph {
    private static final int USER_A = 0;
    private static final int USER_B = 1;
    private static final int TIME = 2;

    /* ------- Task 1 ------- */
    /* Building the Constructors */

    private Map<List<Integer>, Integer> emailWeightMap = new HashMap<>();
    private List<List<Integer>> emailData = new ArrayList<>();
    private List<Integer> users = new LinkedList<>();
    private List<List<Integer>> userInteractions = new LinkedList<>();
    private Map<Integer, List<Integer>> DWIG = new HashMap<>();

    /**
     * Creates a new DWInteractionGraph using an email interaction file.
     * The email interaction file will be in the resources directory.
     *
     * @param fileName the name of the file in the resources
     *                 directory containing email interactions
     */
    public DWInteractionGraph(String fileName) {
        emailData = makeDwGraph(fileName);
        getDWIG(emailData);
    }

    /**
     * create a new UDWInteractionGraph using Data
     *
     * @param data is not Null
     */
    private void getDWIG(List<List<Integer>> data) {
        emailData = data;
        emailWeightMap = getEmailWeightMap(data);
        // get userInteractions
        emailWeightMap.keySet().forEach(x -> userInteractions.add(x.stream().toList()));
        Set<Integer> userSet = new HashSet<>();
        userInteractions.forEach(userSet::addAll);
        users = userSet.stream().toList();
        getRelations();
    }

    protected List<List<Integer>> getData() {
        return emailData;
    }

    protected List<List<Integer>> getuserInteractions() {
        return userInteractions;
    }

    private List<Integer> getUsers() {
        return users;
    }

    private Map<List<Integer>, Integer> getEmailWeightMap(List<List<Integer>> data) {
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
            DWIG.put(eachUser, adjacencySet.stream().toList());
        }
    }


    private Map<List<Integer>, Integer> makeWeightGraph(List<List<Integer>> data) {
        // key: user A, value: weight between each user
        Map<List<Integer>, Integer> emailWeightMap = new HashMap<>();
        Set<List<Integer>> userSetToExclude = new HashSet<>();

        for (int i = 0; i < data.size(); i++) {
            List<Integer> userList = new ArrayList<>();
            userList.add(data.get(i).get(USER_A));
            userList.add(data.get(i).get(USER_B));
            if (!userSetToExclude.contains(userList)) {

                emailWeightMap.put(userList, addAllWeight(data.get(i).get(USER_A),
                    data.get(i).get(USER_B), data));
            }
            userSetToExclude.add(userList);
        }

        return emailWeightMap;
    }

    private int addAllWeight(int userA, int userB, List<List<Integer>> data) {
        List<List<Integer>> dataNeeded = new ArrayList<>();
        for (List<Integer> integers : data) {
            int user1 = integers.get(USER_A);
            int user2 = integers.get(USER_B);
            if ((user1 == userA) && (user2 == userB)) {
                dataNeeded.add(integers);
            }
        }
        int weight = 0;
        for (int i = 0; i < dataNeeded.size(); i++) {
            weight++;
        }
        return weight;
    }

    private List<List<Integer>> makeDwGraph(String fileName) {
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
     * Creates a new DWInteractionGraph from a DWInteractionGraph object
     * and considering a time window filter.
     *
     * @param inputDWIG  a DWInteractionGraph object
     * @param timeFilter an integer array of length 2: [t0, t1]
     *                   where t0 <= t1. The created DWInteractionGraph
     *                   should only include those emails in the input
     *                   DWInteractionGraph with send time t in the
     *                   t0 <= t <= t1 range.
     */
    public DWInteractionGraph(DWInteractionGraph inputDWIG, int[] timeFilter) {
        List<List<Integer>> dataOfInput = inputDWIG.getData();
        List<List<Integer>> UDWTimeConstrained = new ArrayList<>();

        for (int i = 0; i < dataOfInput.size(); i++) {
            int t = dataOfInput.get(i).get(TIME);
            if (t >= timeFilter[0] && t <= timeFilter[1]) {
                UDWTimeConstrained.add(dataOfInput.get(i));
            }
        }

        getDWIG(UDWTimeConstrained);
    }

    /**
     * Creates a new DWInteractionGraph from a DWInteractionGraph object
     * and considering a list of User IDs.
     *
     * @param inputDWIG  a DWInteractionGraph object
     * @param userFilter a List of User IDs. The created DWInteractionGraph
     *                   should exclude those emails in the input
     *                   DWInteractionGraph for which neither the sender
     *                   nor the receiver exist in userFilter.
     */
    public DWInteractionGraph(DWInteractionGraph inputDWIG, List<Integer> userFilter) {
        List<List<Integer>> data = new ArrayList<>();

        for (int i = 0; i < inputDWIG.emailData.size(); i++) {
            List<Integer> eachData = inputDWIG.emailData.get(i);
            if ((userFilter.contains(eachData.get(USER_A)) ||
                userFilter.contains(eachData.get(USER_B)))) {
                data.add(eachData);
            }
        }

        getDWIG(data);
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
        List<Integer> user = new ArrayList<>();
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
     * Given an int array, [t0, t1], reports email transaction details.
     * Suppose an email in this graph is sent at time t, then all emails
     * sent where t0 <= t <= t1 are included in this report.
     *
     * @param timeWindow is an int array of size 2 [t0, t1] where t0<=t1.
     * @return an int array of length 3, with the following structure:
     * [NumberOfSenders, NumberOfReceivers, NumberOfEmailTransactions]
     */
    public int[] ReportActivityInTimeWindow(int[] timeWindow) {
        // TODO: Implement this method
        return null;
    }

    /**
     * Given a User ID, reports the specified User's email transaction history.
     *
     * @param userID the User ID of the user for which the report will be
     *               created.
     * @return an int array of length 3 with the following structure:
     * [NumberOfEmailsSent, NumberOfEmailsReceived, UniqueUsersInteractedWith]
     * If the specified User ID does not exist in this instance of a graph,
     * returns [0, 0, 0].
     */
    public int[] ReportOnUser(int userID) {
        // TODO: Implement this method
        return null;
    }

    /**
     * @param N               a positive number representing rank. N=1 means the most active.
     * @param interactionType Represent the type of interaction to calculate the rank for
     *                        Can be SendOrReceive.Send or SendOrReceive.RECEIVE
     * @return the User ID for the Nth most active user in specified interaction type.
     * Sorts User IDs by their number of sent or received emails first. In the case of a
     * tie, secondarily sorts the tied User IDs in ascending order.
     */
    public int NthMostActiveUser(int N, SendOrReceive interactionType) {
        // TODO: Implement this method
        return -1;
    }

    /* ------- Task 3 ------- */

    /**
     * performs breadth first search on the DWInteractionGraph object
     * to check path between user with userID1 and user with userID2.
     *
     * @param userID1 the user ID for the first user
     * @param userID2 the user ID for the second user
     * @return if a path exists, returns aa list of user IDs
     * in the order encountered in the search.
     * if no path exists, should return null.
     */
    public List<Integer> BFS(int userID1, int userID2) {
        // TODO: Implement this method
        return null;
    }

    /**
     * performs depth first search on the DWInteractionGraph object
     * to check path between user with userID1 and user with userID2.
     *
     * @param userID1 the user ID for the first user
     * @param userID2 the user ID for the second user
     * @return if a path exists, returns aa list of user IDs
     * in the order encountered in the search.
     * if no path exists, should return null.
     */
    public List<Integer> DFS(int userID1, int userID2) {
        // TODO: Implement this method
        return null;
    }

    /* ------- Task 4 ------- */

    /**
     * Read the MP README file carefully to understand
     * what is required from this method.
     *
     * @param hours
     * @return the maximum number of users that can be polluted in N hours
     */
    public int MaxBreachedUserCount(int hours) {
        // TODO: Implement this method
        return 0;
    }

}
