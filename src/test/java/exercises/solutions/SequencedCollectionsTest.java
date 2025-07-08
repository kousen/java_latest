package exercises.solutions;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class SequencedCollectionsTest {
    
    @Test
    public void sequencedList() {
        // Use SequencedCollection methods
        List<String> list = new ArrayList<>();
        list.add("first");
        list.add("middle");
        list.add("last");
        
        assertEquals("first", list.getFirst());
        assertEquals("last", list.getLast());
        
        list.addFirst("new-first");
        list.addLast("new-last");
        
        assertEquals("new-first", list.getFirst());
        assertEquals("new-last", list.getLast());
    }
    
    @Test
    public void reversedView() {
        // Use reversed() method
        SequencedCollection<Integer> numbers = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        SequencedCollection<Integer> reversed = numbers.reversed();
        
        assertEquals(List.of(5, 4, 3, 2, 1), new ArrayList<>(reversed));
        
        // Add to reversed view
        reversed.addFirst(6);
        assertEquals(6, numbers.getLast());
    }
    
    @Test
    public void sequencedSet() {
        // Use SequencedSet
        SequencedSet<String> set = new LinkedHashSet<>();
        set.add("a");
        set.add("b");
        set.add("c");
        
        assertEquals("a", set.getFirst());
        assertEquals("c", set.getLast());
        
        SequencedSet<String> reversed = set.reversed();
        assertEquals("c", reversed.getFirst());
    }
    
    @Test
    public void sequencedMap() {
        // Use SequencedMap
        SequencedMap<Integer, String> map = new LinkedHashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        
        Map.Entry<Integer, String> first = map.firstEntry();
        Map.Entry<Integer, String> last = map.lastEntry();
        
        assertEquals(1, first.getKey());
        assertEquals(3, last.getKey());
        
        map.putFirst(0, "zero");
        assertEquals("zero", map.firstEntry().getValue());
    }
}