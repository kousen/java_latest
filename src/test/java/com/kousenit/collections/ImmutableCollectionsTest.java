package com.kousenit.collections;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SuppressWarnings("DataFlowIssue")
public class ImmutableCollectionsTest {

    private Integer[] intArgs(int n) {
        return IntStream.rangeClosed(1, n)
                .boxed()
                .toArray(Integer[]::new);
    }

    @Test
    public void createImmutableList() {
        IntStream.rangeClosed(1, 10)
                .forEach(n -> {
                    List<Integer> intList = List.of(intArgs(n));
                    assertEquals(n, intList.size());
                    assertEquals(1, intList.getFirst().intValue());
                    assertEquals(n, intList.getLast().intValue());
                });
    }

    @Test
    public void showImmutabilityAdd() {
        List<Integer> intList = List.of(1, 2, 3);
        assertThrows(UnsupportedOperationException.class, () -> intList.add(99));
    }

    @Test
    public void showImmutabilityClear() {
        List<Integer> intList = List.of(1, 2, 3);
        assertThrows(UnsupportedOperationException.class, intList::clear);
    }

    @Test
    public void showImmutabilityRemove() {
        List<Integer> intList = List.of(1, 2, 3);
        assertThrows(UnsupportedOperationException.class, intList::removeFirst);
    }

    @Test
    public void showImmutabilityReplace() {
        List<Integer> intList = List.of(1, 2, 3);
        assertThrows(UnsupportedOperationException.class, () -> intList.replaceAll(n -> -n));
    }

    @Test
    public void showImmutabilitySet() {
        List<Integer> intList = List.of(1, 2, 3);
        assertThrows(UnsupportedOperationException.class, () -> intList.set(0, 99));
    }

    @Test
    public void areWeImmutableOrArentWe() {
        List<Holder> holders = List.of(new Holder(1), new Holder(2));
        assertEquals(1, holders.getFirst().getX());

        holders.getFirst().setX(4);
        assertEquals(4, holders.getFirst().getX());
    }

    @Test
    public void testVarargsList() {
        List<Integer> intList = List.of(intArgs(11));
        assertEquals(11, intList.size());
        assertEquals( 1, intList.getFirst().intValue());
        assertEquals(11, intList.getLast().intValue());
    }
}