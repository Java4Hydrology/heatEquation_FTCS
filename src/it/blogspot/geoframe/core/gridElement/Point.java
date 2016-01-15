package it.blogspot.geoframe.core.gridElement;

import java.util.ArrayList;

import org.geotools.graph.util.geom.Coordinate2D;

import it.blogspot.geoframe.core.GridElement;
import it.blogspot.geoframe.core.Key;

public class Point implements GridElement {

    final public Key ID;
    final public ArrayList<Key> neighbors;
    final private Coordinate2D barycenter;

    public Point(final Key ID, final ArrayList<Key> neighbors, final Coordinate2D barycenter) {
        this.ID = ID;
        this.neighbors = neighbors;
        this.barycenter = barycenter;
    }

    @Override
    public Coordinate2D getBarycenter() {
        return barycenter;
    }

}
