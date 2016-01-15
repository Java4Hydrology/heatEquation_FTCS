package it.blogspot.geoframe.domain.geometry;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.geotools.graph.util.geom.Coordinate2D;

import it.blogspot.geoframe.core.GridElement;
import it.blogspot.geoframe.core.Key;
import it.blogspot.geoframe.core.gridElement.Line;
import it.blogspot.geoframe.core.gridElement.Point;
import it.blogspot.geoframe.domain.Domain;

public class Geometry implements Domain {

    final public ConcurrentHashMap<Key, GridElement> cells;
    final public ConcurrentHashMap<Key, GridElement> sides;

    final private Coordinate2D left = new Coordinate2D(-1.0, 0.0);
    final private Coordinate2D right = new Coordinate2D(+1.0, 0.0);

    final public double cellSize;
    final public int totalCells;
    final public int totalSides;

    public Geometry(final int totalCells) {
        this.totalCells = totalCells;
        this.totalSides = totalCells + 1;
        this.cellSize = (right.x - left.x) / totalCells;

        this.cells = new ConcurrentHashMap<Key, GridElement>(this.totalCells);
        this.sides = new ConcurrentHashMap<Key, GridElement>(this.totalSides);

        cellInitialization();
        sideInitialization();
    }

    @Override
    public ConcurrentHashMap<Key, Double> compute() {
        throw new UnsupportedOperationException("Method not implemented for Geometry class"); 
    };

    @Override
    public Coordinate2D getCellCoordinate(final Key key) {
        throw new UnsupportedOperationException("Method not implemented for Geometry class");
    }

    @Override
    public List<Double> getListOfValues() {
        throw new UnsupportedOperationException("Method not implemented for Geometry class");
    }

    private void cellInitialization() {
        firstCell();
        for (int i = 1; i < totalCells - 1; i++) {
            Key tmpKey = new Key(i);
            ArrayList<Key> tmpNeighbors = new ArrayList<Key>(2);
            tmpNeighbors.add(new Key(i-1));
            tmpNeighbors.add(new Key(i+1));
            Line tmpLine = new Line(tmpKey, tmpNeighbors, new Coordinate2D(left.x + (i + 0.5) * cellSize, 0.0));
            cells.put(tmpKey, tmpLine);
        }
        lastCell();
    }

    private void sideInitialization() {
        firstSide();
        for (int i = 1; i < totalSides - 1; i++) {
            Key tmpKey = new Key(i);
            ArrayList<Key> tmpNeighbors = new ArrayList<Key>(2);
            tmpNeighbors.add(new Key(i-1));
            tmpNeighbors.add(new Key(i+1));
            Point tmpPoint = new Point(tmpKey, tmpNeighbors, new Coordinate2D(left.x + i * cellSize, 0.0));
            sides.put(tmpKey, tmpPoint);
        }
        lastSide();
    }

    private void firstCell() {
        Key tmpKey = new Key(0);
        ArrayList<Key> tmpNeighbors = new ArrayList<Key>(1);
        tmpNeighbors.add(new Key(1));
        Line tmpLine = new Line(tmpKey, tmpNeighbors, new Coordinate2D(left.x + 0.5 * cellSize, 0.0));
        cells.put(tmpKey, tmpLine);
    }

    private void firstSide() {
        Key tmpKey = new Key(0);
        ArrayList<Key> tmpNeighbors = new ArrayList<Key>(1);
        tmpNeighbors.add(new Key(1));
        Point tmpPoint = new Point(tmpKey, tmpNeighbors, new Coordinate2D(left.x, 0.0));
        sides.put(tmpKey, tmpPoint);
    }

    private void lastCell() {
        Key tmpKey = new Key(totalCells - 1);
        ArrayList<Key> tmpNeighbors = new ArrayList<Key>(1);
        tmpNeighbors.add(new Key(totalCells - 2));
        Line tmpLine = new Line(tmpKey, tmpNeighbors, new Coordinate2D(right.x - 0.5 * cellSize, 0.0));
        cells.put(tmpKey, tmpLine);
    }

    private void lastSide() {
        Key tmpKey = new Key(totalSides);
        ArrayList<Key> tmpNeighbors = new ArrayList<Key>(1);
        tmpNeighbors.add(new Key(totalSides - 1));
        Point tmpPoint = new Point(tmpKey, tmpNeighbors, new Coordinate2D(right.x, 0.0));
        sides.put(tmpKey, tmpPoint);
    }

}
