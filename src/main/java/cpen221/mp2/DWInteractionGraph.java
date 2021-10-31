package cpen221.mp2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class DWInteractionGraph {
    private static final int USER_A = 0;
    private static final int USER_B = 1;
    private static final int TIME = 2;
    private static final int WEIGHT = 2;


    /* ------- Task 1 ------- */
    /* Building the Constructors */

    private HashMap<Integer, LinkedList<Edge>> DWG;
    private List<List<Integer>> emailData = new ArrayList<>();
    private List<List<Integer>> emailDataWithWeight;
    private Set<Integer> userSet = new HashSet<>(); // With no duplicate users
    private List<Integer> userList; // Just convert userSet to userList b/c List is easier to work w/.
    /**
     * Creates a new DWInteractionGraph using an email interaction file.
     * The email interaction file will be in the resources directory.
     *
     * @param fileName the name of the file in the resources
     *                 directory containing email interactions
     */
    public DWInteractionGraph(String fileName) {
        emailData = processData(fileName);
        setEmailDataWithWeight(emailData);

        makeDWI();


        // remove below
        for(List l : emailData) {
            System.out.println(l);
        }
        System.out.println("User Set"+ userSet);
        System.out.println("User List is " + userList);
        System.out.println("===Email data with weight===");
        for (List<Integer> ll : emailDataWithWeight) {
            System.out.println(ll);
        }
        printGraph();
    }

    // Make DWI graph
    private void makeDWI () {
        DWG = new HashMap<Integer, LinkedList<Edge>>();

        for (Integer sender : userList) {
            LinkedList<Edge> tempList = new LinkedList<>();
            for (List data : emailDataWithWeight) {
                if (data.get(USER_A) == sender) {
                    // To prevent rep exposure. Not sure this is needed. But just in case
                    int receiver = (int) data.get(USER_B);
                    int weight = (int) data.get(WEIGHT);
                    tempList.add(new Edge((int) sender, receiver, weight));
                }

            }
            DWG.put(sender, tempList);
        }

    }

    /**
     * Creates a new DWInteractionGraph from a DWInteractionGraph object
     * and considering a time window filter.
     *
     * @param inputDWIG a DWInteractionGraph object
     * @param timeFilter an integer array of length 2: [t0, t1]
     *                   where t0 <= t1. The created DWInteractionGraph
     *                   should only include those emails in the input
     *                   DWInteractionGraph with send time t in the
     *                   t0 <= t <= t1 range.
     */
    public DWInteractionGraph(DWInteractionGraph inputDWIG, int[] timeFilter) {
        List<List<Integer>> dataOfInput = new ArrayList<>(inputDWIG.getData());
        List<List<Integer>> timeFilteredData = new ArrayList<>();
        for (List l : dataOfInput) {
            if ((int) l.get(TIME) >= timeFilter[0] && (int) l.get(TIME) <= timeFilter[1]) {
                timeFilteredData.add(l);
            }
        }

        setEmailDataWithWeight(timeFilteredData);

        makeDWI();

        // Remove
        System.out.println();
        System.out.println("===Time Filtered DWI===");
        printGraph();
    }



    /**
     * Creates a new DWInteractionGraph from a DWInteractionGraph object
     * and considering a list of User IDs.
     *
     * @param inputDWIG a DWInteractionGraph object
     * @param userFilter a List of User IDs. The created DWInteractionGraph
     *                   should exclude those emails in the input
     *                   DWInteractionGraph for which neither the sender
     *                   nor the receiver exist in userFilter.
     */
    public DWInteractionGraph(DWInteractionGraph inputDWIG, List<Integer> userFilter) {
        List<List<Integer>> inputData = new ArrayList<>(inputDWIG.getData());
        Set<List<Integer>> userFilteredSet = new HashSet<>();
        List<List<Integer>> userFilteredData;

        for (Integer user : userFilter) {
            for (List list : inputData) {
                if (list.get(USER_A) == user || list.get(USER_B) == user) {
                    userFilteredSet.add(list);
                }
            }
        }

        userFilteredData = new ArrayList<>(userFilteredSet);
        setEmailDataWithWeight(userFilteredData);

        makeDWI();

        // Remove
        System.out.println();
        System.out.println("===User Filtered DWI===");
        printGraph();
    }

    // For debugging purpose. Nothing special
    private void printGraph() {
        for (Integer sender : DWG.keySet()) {
            System.out.println("-------------------------------------");
            List<Edge> temp = new LinkedList<>();
            temp = DWG.get(sender);
            temp.stream().forEach(x -> x.printEdge());
        }
    }
    /**
     * @return a Set of Integers, where every element in the set is a User ID
     * in this DWInteractionGraph.
     */
    public Set<Integer> getUserIDs() {
        return new HashSet<>(userSet);
    }

    /**
     * @param sender the User ID of the sender in the email transaction.
     * @param receiver the User ID of the receiver in the email transaction.
     * @return the number of emails sent from the specified sender to the specified
     * receiver in this DWInteractionGraph.
     */
    public int getEmailCount(int sender, int receiver) {
        int weight = 0;
        for (List data : emailDataWithWeight) {
            if ((int) data.get(USER_A) == sender && (int) data.get(USER_B) == receiver) {
                weight = (int) data.get(WEIGHT);
            }
        }
        return weight;
    }

    // Make a data [sender, receiver, weight]
    private void setEmailDataWithWeight(List<List<Integer>> data) {
        emailDataWithWeight = new ArrayList<>();

        data.stream().forEach(x -> userSet.add(x.get(USER_A)));
        data.stream().forEach(x -> userSet.add(x.get(USER_B)));

        userList = new ArrayList<>(userSet);

        for (Integer sender : userList) {
            for (Integer receiver : userList) {
                List<Integer> tempList = new ArrayList<>();
                Integer weight = 0;
                for (List<Integer> d : data) {
                    if (d.get(USER_A) == sender && d.get(USER_B) == receiver) {
                        weight++;
                    }
                }
                if (weight > 0) {
                    tempList.add(sender);
                    tempList.add(receiver);
                    tempList.add(weight);

                    emailDataWithWeight.add(tempList);
                }
            }
        }
    }
    private List<List<Integer>> processData(String fileName) {
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

    // defensive copying return
    private List<List<Integer>> getData() {
        return new ArrayList<>(this.emailData);
    }
    /* ------- Task 2 ------- */

    /**
     * Given an int array, [t0, t1], reports email transaction details.
     * Suppose an email in this graph is sent at time t, then all emails
     * sent where t0 <= t <= t1 are included in this report.
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
     * @param N a positive number representing rank. N=1 means the most active.
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
     * @param hours
     * @return the maximum number of users that can be polluted in N hours
     */
    public int MaxBreachedUserCount(int hours) {
        // TODO: Implement this method
        return 0;
    }

}