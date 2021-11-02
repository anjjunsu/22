package cpen221.mp2;

import java.util.Comparator;

public class NumberComparator implements Comparator<Element> {
    @Override
    public int compare(Element a, Element b) {
        return b.getValue() - a.getValue();  // ASC: a-b, DSC: b-a
    }
}
