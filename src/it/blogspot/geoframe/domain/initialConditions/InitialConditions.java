package it.blogspot.geoframe.domain.initialConditions;

import java.util.concurrent.ConcurrentHashMap;

import org.geotools.graph.util.geom.Coordinate2D;

import it.blogspot.geoframe.core.Key;
import it.blogspot.geoframe.domain.Domain;

public abstract class InitialConditions implements Domain {

    @Override
    abstract public ConcurrentHashMap<Key, Double> compute();

    @Override
    public Coordinate2D getCellCoordinate(final Key key) {
        throw new UnsupportedOperationException("Method not implemented for Initial Conditions");
    }
}
