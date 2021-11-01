package cpen221.mp2;

class Edge {
    int sender;
    int receiver;
    int weight;

    Edge(int sender, int receiver, int weight) {
        this.sender = sender;
        this.receiver = receiver;
        this.weight = weight;
    }

    void printEdge() {
        System.out.println("sender = " + sender + ", receiver = " + receiver + ", weight = " + weight);
    }
    int getSender() { return sender; }

    int getReceiver() { return receiver;}

    int getWeight()  {return weight;}
}
