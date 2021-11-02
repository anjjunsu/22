package cpen221.mp2;

class Element {
    private int index;
    private int value;

    Element(int index, int value) {
        this.index = index;
        this.value = value;
    }

    int getIndex() { return this.index; }
    int getValue() {
        return this.value;
    }

    void print() {
        System.out.println("index = " + this.index + " value = " + this.value);
    }
}
