package cpen221.mp2;

// Since all of the classes are in the same package, it should suffice to use package-private
class Vertex {
    private int sender;

    Vertex (int sender) {
        this.sender = sender;
    }

    int getVertex() { return sender; }
}
