package it.blogspot.geoframe.core.utils;

import java.util.Comparator;

import it.blogspot.geoframe.core.Key;

public class SortByKeyAscending implements Comparator<Key> {

    @Override
    public int compare(Key key1, Key key2) {
        return key1.compareTo(key2);
    }

}
