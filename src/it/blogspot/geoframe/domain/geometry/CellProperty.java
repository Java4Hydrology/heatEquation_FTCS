package it.blogspot.geoframe.domain.geometry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.geotools.graph.util.geom.Coordinate2D;

import it.blogspot.geoframe.core.GridElement;
import it.blogspot.geoframe.core.Key;
import it.blogspot.geoframe.domain.Domain;

public class CellProperty implements Domain {

    final private Geometry domain;
    final private ConcurrentHashMap<Key, Double> data;
    final public String variable;

    public CellProperty(final String variable, final Geometry domain) {
        this.domain = domain;
        this.variable = variable;

        data = new ConcurrentHashMap<Key, Double>(domain.totalCells);
        for (Key key : domain.cells.keySet()) {
            data.put(key, 0.0);
        }
    }

    @Override
    public ConcurrentHashMap<Key, Double> compute() {
        return data;
    }

    public boolean add(final Key key, final Double value) {
        return (data.put(key, value) != null) ? true : false;
    }

    @Override
    public Coordinate2D getCellCoordinate(final Key key) {
        GridElement tmpCell = domain.cells.get(key);
        return tmpCell.getBarycenter();
    }

    @Override
    public List<Double> getListOfValues() {

        List<Double> tmpList = new ArrayList<Double>(domain.totalCells);
        for (Entry<Key, Double> cell : data.entrySet()) tmpList.add(cell.getValue());

        return tmpList;
    }

}
