package it.blogspot.geoframe.domain.initialConditions.cellIC;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import it.blogspot.geoframe.core.Key;
import it.blogspot.geoframe.domain.Domain;
import it.blogspot.geoframe.domain.initialConditions.InitialConditions;

public class TemperatureIC extends InitialConditions {

    final private Domain variable;
    final private double leftTemperature;
    final private double rightTemperature;
    private ConcurrentHashMap<Key, Double> data;

    public TemperatureIC(final Domain variable, final double leftTemperature, final double rightTemperature) {
        this.variable = variable;
        this.leftTemperature = leftTemperature;
        this.rightTemperature = rightTemperature;
    }

    @Override
    public ConcurrentHashMap<Key, Double> compute() {
        data = variable.compute();

        for (Entry<Key, Double> value : data.entrySet()) {
            if(variable.getCellCoordinate(value.getKey()).x < 0) {
                value.setValue(leftTemperature);
            } else {
                value.setValue(rightTemperature);
            }
        }

        return data;
    }

    @Override
    public List<Double> getListOfValues() {

        List<Double> tmpList = new ArrayList<Double>();
        for (Entry<Key, Double> cell : data.entrySet()) tmpList.add(cell.getValue());

        return tmpList;
    }

}
