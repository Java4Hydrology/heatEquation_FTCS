package it.blogspot.geoframe.domain;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.geotools.graph.util.geom.Coordinate2D;

import it.blogspot.geoframe.core.Key;

public interface Domain {

    public ConcurrentHashMap<Key, Double> compute();
    public Coordinate2D getCellCoordinate(final Key key);
    public List<Double> getListOfValues();

}
