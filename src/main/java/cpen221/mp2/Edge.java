package cpen221.mp2;

class Edge {
    private int sender;
    private int receiver;
    private int weight;
    /* Representation Invariant */
    // sender is non-negative integer
    // receiver is non-negative integer
    // weight is non-negative integer

    /* Abstraction Function */
    // Represents the relationship between email interaction
    // User ID of email sender, User ID of email receiver, number of email sent from sender to receiver

    /*Safety from rep exposure:*/
    // All fields are private and immutable data type
    Edge(int sender, int receiver, int weight) {
        this.sender = sender;
        this.receiver = receiver;
        this.weight = weight;
    }

    /**
     * Prints the property of this Edge
     * In forms of "sender = (user ID of sender), receiver = (user ID of receiver), weight = (weight)
     */
    void printEdge() {
        System.out.println("sender = " + sender + ", receiver = " + receiver + ", weight = " + weight);
    }

    /**
     * Returns the user ID of sender who sends email
     * @return user ID of sender
     */
    int getSender() { return sender; }

    /**
     * Returns the user ID of receiver who received email
     * @return receiver: user ID of receiver
     */
    int getReceiver() { return receiver;}

    /**
     * Returns the weight which is number of email interactions from sender to receiver
     * @return number of email interactions; weight
     */
    int getWeight()  {return weight;}
}
