package it.blogspot.geoframe.domain.initialConditions.sideIC;

import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import it.blogspot.geoframe.core.Key;
import it.blogspot.geoframe.domain.Domain;
import it.blogspot.geoframe.domain.initialConditions.InitialConditions;

public class KappaIC extends InitialConditions {

    final private Domain variable;
    final private double leftKappa;
    final private double rightKappa;
    private ConcurrentHashMap<Key, Double> data;

    public KappaIC(final Domain variable, final double leftKappa, final double rightKappa) {
        this.variable = variable;
        this.leftKappa = leftKappa;
        this.rightKappa = rightKappa;
    }

    @Override
    public ConcurrentHashMap<Key, Double> compute() {
        data = variable.compute();

        for (Entry<Key, Double> value : data.entrySet()) {
            if(variable.getCellCoordinate(value.getKey()).x < 0)
                value.setValue(leftKappa);
            else value.setValue(rightKappa);
        }

        return data;
    }

    @Override
    public List<Double> getListOfValues() {
        throw new UnsupportedOperationException("Method not implemented for Geometry class");
    }

}
