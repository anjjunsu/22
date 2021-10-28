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
import java.util.Scanner;
import java.util.Set;

public class DWInteractionGraph {
    private List<String> StringDataLines = new ArrayList<String>(); // Raw String Data read from txt
    private List<List<Integer>> IntegerDataLines = new ArrayList<>(); // Convert String Data to Integer List
    private Set<Integer> peoplesSet = new HashSet(); // Set of senders (numSenders == numReceivers); used set to remove duplicates
    private List<Integer> peoplesList = new ArrayList<>(); // List of senders (numSenders == numReceivers); used list to easily get elements
    private List<List<Integer>> manufacturedData = new ArrayList<>();
    private Map<Integer, List<Map<Integer, Integer>>> adjacencyList = new HashMap<Integer,List<Map<Integer, Integer>>>();;
        /* ------- Task 1 ------- */
    /* Building the Constructors */

    /**
     * Creates a new DWInteractionGraph using an email interaction file.
     * The email interaction file will be in the resources directory.
     *
     * @param fileName the name of the file in the resources
     *                 directory containing email interactions
     */
    public DWInteractionGraph(String fileName) {
        readStringData(fileName);
        setPeoples();
        setManufacturedData();
        Adjacencylist();

        System.out.println("--List of Integer List");
        IntegerDataLines.forEach(x -> System.out.println(x));
        System.out.println("================================");
        System.out.println("--All manufactured dats: --");
        manufacturedData.forEach(x -> System.out.println(x));
        System.out.printf("==============");
        System.out.println("--Print adjacency list--");

        for(Integer e : adjacencyList.keySet()){
            System.out.print("sender = "+ e + " ");
            for(Map<Integer,Integer> e1 : adjacencyList.get(e)) {
                System.out.print(e1);
        }
            System.out.println();
        }
    }

    // construct Adjacency list
    private void Adjacencylist(){

        for(List<Integer> dataList : manufacturedData){
            Map<Integer, Integer> tempMap = new HashMap<Integer, Integer>();
            tempMap.put(dataList.get(1), dataList.get(2));
            List<Map<Integer, Integer>> tempList = new LinkedList<Map<Integer, Integer>>();
            tempList.add(tempMap);
            adjacencyList.put(dataList.get(0), tempList);
        }
    }

//    public void setEdge(int a,int b){    //method to add an edge
//        List<Integer> edges=adjacencyList.get(a);
//        edges.add(b);
//    }
//
//    public List<Integer> getEdge(int a){
//        return adjacencyList.get(a);
//    }
//
//    public boolean contain(int a,int b){
//        return adjacencyList.get(a).contains(b);
//    }
//
//    public int numofEdges(int a){
//        return adjacencyList.get(a).size();
//    }
//
//    public void removeEdge(int a,int b){
//        adjacencyList.get(a).remove(b);
//    }
//
//    public void removeVertex(int a){
//        adjacencyList.get(a).clear();
//    }
//
//    public void addVertex(int a){
//        adjacencyList.put(a, new LinkedList<Integer>());
//    }


    // Manufacture a raw data
    private void setManufacturedData () {
        Integer highestUserIndex = Collections.max(peoplesList);
        int count;


        for (Integer i : peoplesList) {
            for (Integer j : peoplesList) {
                count = 0;
                for (List list : IntegerDataLines) {

                    if (list.get(0) == (Integer)i && list.get(1) == (Integer) j) {
                        count++;
                    }

                }
                if (count > 0) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(i);
                    temp.add(j);
                    temp.add(count);
                    manufacturedData.add(temp);
                }


            }
        }

    }
    // make set of people are in this data (No duplicate)
    private void setPeoples () {
        List<Integer> tempList;
        for (int i = 0; i < IntegerDataLines.size(); i++) {
            tempList = IntegerDataLines.get(i);
            peoplesSet.add(tempList.get(0));
        }
        peoplesList.addAll(peoplesSet);
    }
    // Get number of people (No duplicate)
    private int getNumPeople (Set<Integer> peoples) {
        return peoplesSet.size();
    }


    // Write spec Junsu. Do I have to write spec for private method?
    private void readStringData (String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                StringDataLines.add(line);
            }
        } catch (IOException ioe) {
            System.out.println("Problem reading file!");
        }
        StringToInteger(StringDataLines);
    }
    // Write Spec Junsu
    private void StringToInteger(List<String> stringList) {

        for (int i = 0; i < stringList.size(); i++) {
            Scanner scanner = new Scanner(stringList.get(i));
            List<Integer> list = new ArrayList<Integer>();
            while (scanner.hasNextInt())
                list.add(scanner.nextInt());
            IntegerDataLines.add(list);
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
        // TODO: Implement this constructor
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
     * @param sender the User ID of the sender in the email transaction.
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
