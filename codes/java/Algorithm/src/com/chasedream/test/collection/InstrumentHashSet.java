package cn.chasedream.collection;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;

public class InstrumentHashSet<E> extends HashSet<E> {
    private int addCount = 0;

    public InstrumentHashSet() {

    }

    public InstrumentHashSet(int initCap, float loadFactor) {
        super(initCap, loadFactor);
    }

    @Override
    public boolean add(E e) {
        //addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }

    public int getAddCount() {
        return addCount;
    }

    public static void main(String[] args) {
        InstrumentHashSet<String> sets = new InstrumentHashSet<>();
        sets.addAll(Arrays.asList("snap","crackle", "cap"));
        System.out.println(sets.getAddCount());
    }
}
