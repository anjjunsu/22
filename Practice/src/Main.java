import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {

        String[] words = {"f", "abc", "de"};

        Arrays.sort(words, (s1, s2) -> s1.length() - s2.length());


    }
}
