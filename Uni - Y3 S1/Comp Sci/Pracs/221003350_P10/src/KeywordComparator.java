import java.util.Comparator;

public class KeywordComparator implements Comparator<String> {
    public int compare(String a, String b) throws ClassCastException { 
    	if(a.toLowerCase().contains(b.toLowerCase()))
    		return 0;
    	return a.trim().compareToIgnoreCase(b.trim());    	
    }
}
