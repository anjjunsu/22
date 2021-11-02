package cpen221.mp2;

public class Element {
    private int index;
    private int value;

    public Element(int index, int value) {
        this.index = index;
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return value + " " + index;
    }

    public void print() {
        System.out.println(this);
    }
}
