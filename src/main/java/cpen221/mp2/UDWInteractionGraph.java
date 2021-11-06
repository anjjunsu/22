package cpen221.mp2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class UDWInteractionGraph {
    private static final int USER_A = 0;
    private static final int USER_B = 1;
    private static final int TIME = 2;


    // make a graph: superclass of UDWInteractionGraph


    /* ------- Task 1 ------- */
    /* Building the Constructors */

    private Map<List<Integer>, Integer> emailWeightMap = new HashMap<>();
    private List<List<Integer>> emailData = new ArrayList<>();
    private List<Integer> users = new LinkedList<>();
    private List<List<Integer>> userInteractions = new LinkedList<>();
    private Map<Integer, List<Integer>> UDWIG = new HashMap<>();

    /**
     * Creates a new UDWInteractionGraph using an email interaction file.
     * The email interaction file will be in the resources directory.
     *
     * @param fileName the name of the file in the resources
     *                 directory containing email interactions
     */

    public UDWInteractionGraph(String fileName) {
        emailData = Collections.unmodifiableList(makeData(fileName));
        getUDWIG(emailData);
    }

    /**
     * create a new UDWInteractionGraph using Data
     *
     * @param data is not Null
     */
    private void getUDWIG(List<List<Integer>> data) {
        emailData = data;
        emailWeightMap = getEmailWeightMap(data);
        // get userInteractions
        userInteractions = new ArrayList<>();
        emailWeightMap.keySet().forEach(x -> userInteractions.add(x.stream().toList()));
        Set<Integer> userSet = new HashSet<>();
        userInteractions.forEach(userSet::addAll);
        users = userSet.stream().toList();
        getRelations();
    }

    /**
     * get email data which contains sender, receiver and time converted to integer from string
     *
     * @return email data in integer form
     */

    private List<List<Integer>> getUDWI_data() {
        return emailData;
    }

    /**
     * get user interactions, also known as edges
     *
     * @return user interactions
     */

    protected List<List<Integer>> getuserInteractions() {
        return userInteractions;
    }

    /**
     * get users, also known as nodes
     *
     * @return users
     */

    private List<Integer> getUsers() {
        return users;
    }

    /**
     * get weight map
     *
     * @param data data consists of sender, receiver, and time converted to integer from string
     * @return weightMap which represents how many times the emails have been sent between two
     * users
     */

    private Map<List<Integer>, Integer> getEmailWeightMap(List<List<Integer>> data) {
        return makeWeightGraph(data);
    }

    /**
     * make UDWIG by putting each user and his or her interactions with other users
     */

    private void getRelations() {
        for (int i = 0; i < users.size(); i++) {
            Set<Integer> adjacencySet = new HashSet<>();
            int eachUser = users.get(i);
            int sentItself = 0;
            for (int j = 0; j < userInteractions.size(); j++) {
                if ((userInteractions.get(j).get(USER_A) == eachUser) ||
                    (userInteractions.get(j).get(USER_B) == eachUser)) {
                    adjacencySet.add(userInteractions.get(j).get(USER_A));
                    adjacencySet.add(userInteractions.get(j).get(USER_B));

                    if (Objects.equals(userInteractions.get(j).get(USER_A),
                        userInteractions.get(j).get(USER_B))) {
                        sentItself = 1;
                    }
                }
            }
            if (sentItself != 1) {
                adjacencySet.remove(eachUser);
            }
            sentItself = 0;
            UDWIG.put(eachUser, adjacencySet.stream().toList());
        }
    }

    /**
     * make weight map
     *
     * @param data data consists of sender, receiver, and time converted to integer from string
     * @return weightMap which represents how many times the emails have been sent between two
     * users
     */

    private Map<List<Integer>, Integer> makeWeightGraph(List<List<Integer>> data) {
        // key: user A, value: weight between each user
        Map<List<Integer>, Integer> emailWeightMap = new HashMap<>();
        Set<List<Integer>> userListToExclude = new HashSet<>();

        for (int i = 0; i < data.size(); i++) {
            List<Integer> userList = new ArrayList<>();
            List<Integer> userListComplement = new ArrayList<>();
            userList.add(data.get(i).get(USER_A));
            userList.add(data.get(i).get(USER_B));
            userListComplement.add(data.get(i).get(USER_B));
            userListComplement.add(data.get(i).get(USER_A));
            if (!userListToExclude.contains(userList)) {
                emailWeightMap.put(userList, addAllWeight(data.get(i).get(USER_A),
                    data.get(i).get(USER_B), data));
            }
            userListToExclude.add(userList);
            userListToExclude.add(userListComplement);
        }

        return emailWeightMap;
    }

    /**
     * add all weights between user1 and user2
     *
     * @param userA user1
     * @param userB user2
     * @param data  email data
     * @return count of emails sent between user1 and user2
     */

    private int addAllWeight(int userA, int userB, List<List<Integer>> data) {
        List<List<Integer>> dataNeeded = new ArrayList<>();
        for (List<Integer> integers : data) {
            int user1 = integers.get(USER_A);
            int user2 = integers.get(USER_B);
            if ((user1 == userA && user2 == userB) ||
                (user1 == userB && user2 == userA)) {
                dataNeeded.add(integers);
            }
        }
        int weight = 0;
        for (int i = 0; i < dataNeeded.size(); i++) {
            weight++;
        }
        return weight;
    }

    /**
     * convert data from string to integer
     *
     * @param fileName file name
     * @return data that has been converted from string to integer. It consists of sender,
     * receiver and time
     */

    private List<List<Integer>> makeData(String fileName) {
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

    /**
     * converts from string to integer
     *
     * @param fileLine each line of file
     * @return each fileline that has been converted from string to integer
     */

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
        List<List<Integer>> dataOfInput = inputUDWIG.getUDWI_data();
        List<List<Integer>> dataTimeConstrained = new ArrayList<>();

        for (int i = 0; i < dataOfInput.size(); i++) {
            int t = dataOfInput.get(i).get(TIME);
            if (t >= timeFilter[0] && t <= timeFilter[1]) {
                dataTimeConstrained.add(dataOfInput.get(i));
            }
        }

        getUDWIG(dataTimeConstrained);
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
            if ((userFilter.contains(eachData.get(USER_A)) ||
                userFilter.contains(eachData.get(USER_B)))) {
                data.add(eachData);
            }
        }

        getUDWIG(data);
    }

    /**
     * Creates a new UDWInteractionGraph from a DWInteractionGraph object.
     *
     * @param inputDWIG a DWInteractionGraph object
     */
    public UDWInteractionGraph(DWInteractionGraph inputDWIG) {
        emailData = inputDWIG.getDWI_data();
        getUDWIG(emailData);
    }

    /**
     * @return a Set of Integers, where every element in the set is a User ID
     * in this UDWInteractionGraph.
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
        List<Integer> userComplement = new ArrayList<>();

        user.add(sender);
        user.add(receiver);
        userComplement.add(receiver);
        userComplement.add(sender);

        if (emailWeightMap.containsKey(user)) {
            return emailWeightMap.get(user);
        } else if (emailWeightMap.containsKey(userComplement)) {
            return emailWeightMap.get(userComplement);
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
        List<List<Integer>> dataToPreserve = emailData;
        List<List<Integer>> dataOfInput = emailData;
        List<List<Integer>> dataTimeConstrained = new ArrayList<>();
        int[] reportActivity = new int[2];
        int totalTransaction = 0;
        List<Integer> weightList = new ArrayList<>();

        for (int i = 0; i < dataOfInput.size(); i++) {
            int t = dataOfInput.get(i).get(TIME);
            if (t >= timeWindow[0] && t <= timeWindow[1]) {
                dataTimeConstrained.add(dataOfInput.get(i));
            }
        }
        getUDWIG(dataTimeConstrained);
        reportActivity[0] = getUsers().size();
        emailWeightMap.forEach((a, b) -> weightList.add(b));
        for (Integer integer : weightList) {
            totalTransaction += integer;
        }
        reportActivity[1] = totalTransaction;

        // get original UDWIG
        getUDWIG(dataToPreserve);
        return reportActivity;
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
        int[] reportOnUse = new int[2];
        int numberOfEmails = 0;
        int uniqueUserInteractedWith = 0;
        Set<List<Integer>> notUniqueInteractions = new HashSet<>();

        for (List<Integer> emailDatum : emailData) {
            List<Integer> notUniquePair = new ArrayList<>();
            List<Integer> notUniquePairComplement = new ArrayList<>();
            notUniquePair.add(emailDatum.get(USER_A));
            notUniquePair.add(emailDatum.get(USER_B));
            notUniquePairComplement.add(emailDatum.get(USER_B));
            notUniquePairComplement.add(emailDatum.get(USER_A));

            if (emailDatum.get(USER_A) == userID || emailDatum.get(USER_B) == userID) {
                numberOfEmails++;
                if (!notUniqueInteractions.contains(notUniquePair) &&
                    !notUniqueInteractions.contains(notUniquePairComplement)) {
                    uniqueUserInteractedWith++;
                }
            }
            notUniqueInteractions.add(notUniquePair);
            notUniqueInteractions.add(notUniquePairComplement);
        }

        reportOnUse[0] = numberOfEmails;
        reportOnUse[1] = uniqueUserInteractedWith;
        return reportOnUse;
    }

    /**
     * @param N a positive number representing rank. N=1 means the most active.
     * @return the User ID for the Nth most active user
     */
    public int NthMostActiveUser(int N) {
        List<List<Integer>> userList = new ArrayList<>(emailWeightMap.keySet());
        int[] userTotalEmailList = new int[users.size()];
        int[] eachUserList = new int[users.size()];
        int nthEmailWeight = 0;
        int smallestN = 0;

        for (int i = 0; i < users.size(); i++) {
            int userEmailTotal = 0;
            for (int j = 0; j < userList.size(); j++) {
                if (Objects.equals(userList.get(j).get(USER_A), users.get(i))) {
                    List<Integer> userPair1 = new ArrayList<>();
                    userPair1.add(users.get(i));
                    userPair1.add(userList.get(j).get(USER_B));
                    userEmailTotal += emailWeightMap.get(userPair1);
                } else if (Objects.equals(userList.get(j).get(USER_B), users.get(i))) {
                    List<Integer> userPair2 = new ArrayList<>();
                    userPair2.add(userList.get(j).get(USER_A));
                    userPair2.add(users.get(i));
                    userEmailTotal += emailWeightMap.get(userPair2);
                }
            }
            userTotalEmailList[i] = userEmailTotal;
            eachUserList[i] = i;
        }

        for (int c = 0; c < users.size() - 1; c++) {
            for (int k = 0; k < users.size() - c - 1; k++) {
                if (userTotalEmailList[c] < userTotalEmailList[c + 1]) {
                    int temp1 = userTotalEmailList[c];
                    userTotalEmailList[c] = userTotalEmailList[c + 1];
                    userTotalEmailList[c + 1] = temp1;

                    int temp2 = eachUserList[c];
                    eachUserList[c] = eachUserList[c + 1];
                    eachUserList[c + 1] = temp2;
                }
            }
        }
        nthEmailWeight = userTotalEmailList[N - 1];
        smallestN = eachUserList[N - 1];

        for (int m = 0; m < users.size(); m++) {
            if (userTotalEmailList[m] == nthEmailWeight && eachUserList[m] < smallestN) {
                smallestN = eachUserList[m];
            }
        }

        return smallestN;
    }


    /* ------- Task 3 ------- */

    /**
     * @return the number of completely disjoint graph
     * components in the UDWInteractionGraph object.
     */
    public int NumberOfComponents() {
        Set<Set<Integer>> componentSet = new HashSet<>();
        Set<Integer> userSet = new HashSet<>();

        findComponents(componentSet, userSet);

        return componentSet.size();
    }

    /**
     * @param userID1 the user ID for the first user
     * @param userID2 the user ID for the second user
     * @return whether a path exists between the two users
     */
    public boolean PathExists(int userID1, int userID2) {
        Set<Set<Integer>> componentSet = new HashSet<>();
        Set<Integer> userSet = new HashSet<>();
        List<Set<Integer>> componentList = new ArrayList<>();

        findComponents(componentSet, userSet);

        componentList = componentSet.stream().toList();
        for (int i = 0; i < componentList.size(); i++) {
            if (componentList.get(i).contains(userID1) && componentList.get(i).contains(userID2)) {
                return true;
            }
        }

        return false;
    }

    private void findComponents(Set<Set<Integer>> componentSet, Set<Integer> userSet) {
        for (int i = 0; i < users.size(); i++) {
            Set<Integer> path = new HashSet<>();
            int eachUser = users.get(i);
            if (!userSet.contains(eachUser)) {
                path.add(eachUser);
                getNumberOfComponenets(eachUser, path);
                componentSet.add(path);
            }
            userSet.addAll(path.stream().toList());
        }
    }

    private void getNumberOfComponenets(int eachUser, Set<Integer> path) {
        for (int i = 0; i < UDWIG.get(eachUser).size(); i++) {
            if (!path.contains(UDWIG.get(eachUser).get(i))) {
                path.add(UDWIG.get(eachUser).get(i));
                getNumberOfComponenets(UDWIG.get(eachUser).get(i), path);
            }
        }
    }

}