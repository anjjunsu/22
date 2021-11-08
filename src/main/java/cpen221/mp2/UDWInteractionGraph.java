package cpen221.mp2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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

    private Map<List<Integer>, Integer> emailWeightMap = new HashMap<>();
    private List<List<Integer>> emailData = new ArrayList<>();
    private List<Integer> users = new LinkedList<>();
    private List<List<Integer>> userInteractions = new LinkedList<>();
    private Map<Integer, List<Integer>> UDWIG = new HashMap<>();

    /* Representation Invariant */
    // emailWeightMap, emailData, users, userInteractions, UDWIG are all not null, and do not
    // contain null. They contain non-negative integer.

    /* Abstraction Function */
    // emailWeightMap represents number of email interactions between other users of each user.
    // emailData represents data of emails that keep track of when an email was sent from one user
    //           to the other user.
    // "users" represents all users that have been involved in email transactions.
    // userInteractions represents which users were involved in the email transactions for
    //                  each user.
    // UDWIG represents all email transactions between all users by a map.

    /*Safety from rep exposure:*/
    // All fields are private
    // users is a mutable List, but getUserIDs() makes a defensive copy of users it returns.
    // UDWIG and emailWeightMap are a mutable Map, emailData and userUnteractions are a mutable List of Integer List,
    // and userList is a mutable List. But these types are never passed or returned in a public operation.

    /**
     * Creates a new UDWInteractionGraph using an email interaction file. When email interaction
     * file is passed, get the email data and then call getUDWIG method since working with
     * email data is very convenient to modify UDWIG.
     * The email interaction file will be in the resources directory.
     *
     * @param fileName the name of the file in the resources, directory containing
     *                 email interactions. File does not contain any negative integer string.
     * @throws IOException if an I/O error occurs
     */

    public UDWInteractionGraph(String fileName) {
        emailData = Collections.unmodifiableList(makeData(fileName));
        getUDWIG(emailData);
        checkRep();
    }

    /**
     * create a new UDWInteractionGraph using Data. Set all properties of UDWInteractionGraph.
     *
     * @param data is not Null and does not contain negative integer.
     */
    private void getUDWIG(List<List<Integer>> data) {
        emailData = data;
        emailWeightMap = getEmailWeightMap(data);
        // get userInteractions
        userInteractions = new ArrayList<>();
        emailWeightMap.keySet().forEach(x -> userInteractions.add(new ArrayList<>(x)));
        Set<Integer> userSet = new HashSet<>();
        userInteractions.forEach(userSet::addAll);
        users = new ArrayList<>(userSet);
        getRelations();
        checkRep();
    }

    /**
     * get email data which contains sender, receiver and time converted to integer from string.
     *
     * @return email data in integer form. If data was empty, return empty list.
     */

    private List<List<Integer>> getUDWI_data() {
        return emailData;
    }

    /**
     * get nodes in the graph. If graph is empty, return empty list.
     *
     * @return users
     */

    private List<Integer> getUsers() {
        return new ArrayList<>(users);
    }

    /**
     * get a map that maps node to amount of times sending/receiving emails.
     *
     * @param data data consists of sender, receiver, and time converted to integer from string.
     *             data does not contain negative integer.
     * @return weightMap which represents how many times the emails have been sent between two
     * users
     */

    private Map<List<Integer>, Integer> getEmailWeightMap(List<List<Integer>> data) {
        checkRep();
        return makeWeightGraph(data);
    }

    /**
     * make an adjacency graph.
     */

    private void getRelations() {
        for (int i = 0; i < users.size(); i++) {
            Set<Integer> adjacencySet = new HashSet<>();
            int eachUser = users.get(i);
            int sentItself = 0;
            // for each user, get all the other users that have sent/received
            // email to/from the user.
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
            UDWIG.put(eachUser, new ArrayList<>(adjacencySet));
        }
    }

    /**
     * make a map that maps node to amount of times sending/receiving emails.
     *
     * @param data data consists of sender, receiver, and time converted to integer from string.
     *             data does not contain negative integer.
     * @return weightMap which represents how many times the emails have been sent between two
     * users
     */

    private Map<List<Integer>, Integer> makeWeightGraph(List<List<Integer>> data) {
        // key: sender and receiver. value: weight between each user.
        Map<List<Integer>, Integer> emailWeightMap = new HashMap<>();
        Set<List<Integer>> userListToExclude = new HashSet<>();

        // userListComplement needs to be considered since weightMap does not divide sender and
        // receiver for undirected graph
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

        checkRep();
        return emailWeightMap;
    }

    /**
     * add all number of emails sent/received between user1 and user2
     *
     * @param userA user1
     * @param userB user2
     * @param data  email data. data does not contain any negative integer.
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
        checkRep();
        return weight;
    }

    /**
     * convert data from string to integer. If file is empty, return an empty list.
     *
     * @param fileName file name. File does not contain any negative integer string.
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
        checkRep();
        return dataInteger;
    }

    /**
     * converts each line of file from string to integer, and pack it into a list.
     *
     * @param fileLine each line of file. fileline does not contain negative integer string.
     * @return each fileline that has been converted from string to integer
     */

    private List<Integer> stringToInteger(String fileLine) {
        List<Integer> integerList = new ArrayList<>();
        String[] fileLineParts = fileLine.split("\\s+");

        for (String fileLinePart : fileLineParts) {
            integerList.add(Integer.parseInt(fileLinePart));
        }
        checkRep();
        return integerList;
    }


    /**
     * Creates a new UDWInteractionGraph from a UDWInteractionGraph object by getting rid of data
     * that are not within the time range.
     *
     * @param inputUDWIG a UDWInteractionGraph object. It is not null.
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
        checkRep();
    }


    /**
     * Creates a new UDWInteractionGraph from a UDWInteractionGraph object
     * by getting rid of data where specified users are not included.
     *
     * @param inputUDWIG a UDWInteractionGraph object. It is not null.
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
        checkRep();
    }

    /**
     * Creates a new UDWInteractionGraph from a DWInteractionGraph object.
     *
     * @param inputDWIG a DWInteractionGraph object. It is not null.
     */
    public UDWInteractionGraph(DWInteractionGraph inputDWIG) {
        emailData = inputDWIG.getDWI_data();
        getUDWIG(emailData);
        checkRep();
    }

    /**
     * return set of user IDs.
     *
     * @return a Set of Integers, where every element in the set is a User ID
     * in this UDWInteractionGraph.
     */
    public Set<Integer> getUserIDs() {
        Set<Integer> IDSet = new HashSet<>(users);
        return IDSet;
    }

    /**
     * return number of times emails sent from a specified sender to a receiver.
     *
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
        } else {
            return emailWeightMap.getOrDefault(userComplement, 0);
        }
    }

    /* ------- Task 2 ------- */

    /**
     * get total email transactions within a certain time range.
     *
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
        checkRep();
        return reportActivity;
    }

    /**
     * get number of email interaction and number of unique email interaction, that is, email sent
     * or received only once for a specified user ID.
     *
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
     * get Nth most active user, that is, a user with Nth most email interactions.
     *
     * @param N a positive number representing rank. N=1 means the most active.
     * @return the User ID for the Nth most active user
     */
    public int NthMostActiveUser(int N) {
        List<List<Integer>> userList = new ArrayList<>(emailWeightMap.keySet());
        int[] userTotalEmailList = new int[users.size()];
        int[] eachUserList = new int[users.size()];

        // eachUserList is there to indicate which email weight belongs to which
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
            eachUserList[i] = users.get(i);
        }
        // use bubble sort to sort both email weight list and the user at the same time
        for (int c = 0; c < users.size() - 1; c++) {
            for (int k = 0; k < users.size() - c - 1; k++) {
                if (userTotalEmailList[k] < userTotalEmailList[k + 1]) {
                    swap(k, userTotalEmailList);
                    swap(k, eachUserList);
                }
                if (eachUserList[k] > eachUserList[k + 1] &&
                    userTotalEmailList[k] == userTotalEmailList[k + 1]) {
                    swap(k, eachUserList);
                }
            }
        }
        return (N > eachUserList.length) ? -1 : eachUserList[N - 1];
    }

    /**
     * swap an element from the array at the specified index with its adjacent element
     *
     * @param k           index of the element to swap
     * @param arrayToSwap array that needs its two elements to be swapped
     */

    private void swap(int k, int[] arrayToSwap) {
        int temp = arrayToSwap[k];
        arrayToSwap[k] = arrayToSwap[k + 1];
        arrayToSwap[k + 1] = temp;
    }



    /* ------- Task 3 ------- */

    /**
     * return number of components in a graph, where a component is a part of graph that is
     * completely disjointed by other parts of graph.
     *
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
     * check whether path exists between two specified users. Return true if path does exist, false
     * otherwise.
     *
     * @param userID1 the user ID for the first user
     * @param userID2 the user ID for the second user
     * @return whether a path exists between the two users
     */
    public boolean PathExists(int userID1, int userID2) {
        Set<Set<Integer>> componentSet = new HashSet<>();
        Set<Integer> userSet = new HashSet<>();
        List<Set<Integer>> componentList = new ArrayList<>();

        findComponents(componentSet, userSet);

        componentList = new ArrayList<>(componentSet);
        for (int i = 0; i < componentList.size(); i++) {
            if (componentList.get(i).contains(userID1) && componentList.get(i).contains(userID2)) {
                return true;
            }
        }

        return false;
    }

    /**
     * find number of components in a graph
     *
     * @param componentSet a set that contains components.
     * @param userSet      a set that contains users that have been added to a component already.
     */

    private void findComponents(Set<Set<Integer>> componentSet, Set<Integer> userSet) {
        for (int i = 0; i < users.size(); i++) {
            Set<Integer> path = new HashSet<>();
            int eachUser = users.get(i);
            if (!userSet.contains(eachUser)) {
                path.add(eachUser);
                findPath(eachUser, path);
                componentSet.add(path);
            }
            userSet.addAll(new ArrayList<>(path));
        }
    }

    /**
     * find path of a specified user, where path in this context means a set of users that the
     * specified user can get to.
     *
     * @param eachUser a specified user
     * @param path     set os users where specified user can get to.
     */

    private void findPath(int eachUser, Set<Integer> path) {
        for (int i = 0; i < UDWIG.get(eachUser).size(); i++) {
            if (!path.contains(UDWIG.get(eachUser).get(i))) {
                path.add(UDWIG.get(eachUser).get(i));
                findPath(UDWIG.get(eachUser).get(i), path);
            }
        }
    }

    /**
     * check if representation invariants are held. Since checking if each element is non-negative
     * is expensive, we write this condition on precondition of some methods that need
     * representation invariant checking.
     */

    private void checkRep() {
        assert emailWeightMap != null;
        assert emailData != null;
        assert users != null;
        assert userInteractions != null;
        assert UDWIG != null;
    }
}