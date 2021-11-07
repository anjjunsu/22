package cpen221.mp2;

class Element {
    private int index;
    private int value;
    /* Representation Invariant */
    // index is non-negative integer
    // value is integer
    // Every index paired with specific value is to be associated
    // Every value paired with the specific index

    /* Abstraction Function */
    // Ties index and value together

    /*Safety from rep exposure:*/
    // All fields are private and immutable data type

    /**
     * Construct an Element with specified index and value
     * @param index which the specified value is to be associated
     * @param value to be associated with the specified index
     */
    Element(int index, int value) {
        this.index = index;
        this.value = value;
    }

    /**
     * Returns the index which the associated value is paired
     * @return index of this Element
     */
    int getIndex() { return this.index; }

    /**
     * Returns the value to which the specified index is paired
     * @return value of this Element
     */
    int getValue() {
        return this.value;
    }

    /**
     * Prints this Element
     * In form of "index = (this.index), value = (this.value)"
     */
    void print() {
        System.out.println("index = " + this.index + " value = " + this.value);
    }
}
