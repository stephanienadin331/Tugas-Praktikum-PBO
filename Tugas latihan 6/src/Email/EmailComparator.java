package Email;

import java.util.Comparator;

public class EmailComparator implements Comparator<String> {
    @Override
    public int compare(String e1, String e2) {
        return e1.compareToIgnoreCase(e2);
    }
}

