/*
 * GNU GPL v3 License
 *
 * Copyright 2015 AboutHydrology (Riccardo Rigon)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package it.blogspot.geoframe;

import it.blogspot.geoframe.domain.Domain;
import it.blogspot.geoframe.domain.geometry.CellProperty;
import it.blogspot.geoframe.domain.geometry.Geometry;
import it.blogspot.geoframe.domain.geometry.SideProperty;
import it.blogspot.geoframe.domain.initialConditions.cellIC.TemperatureIC;
import it.blogspot.geoframe.domain.initialConditions.sideIC.KappaIC;

public class heatEq_FTCS {

    static double leftTemperature = 20;
    static double rightTemperature = 0;
    static double leftKappa = 1;
    static double rightKappa = 0.1;

    static GraphPanel mainPanel;

    public static void main (String[] args) {

        Geometry domain = new Geometry(100);

        Domain temperature = new CellProperty("temperature", domain);
        temperature = new TemperatureIC(temperature, leftTemperature, rightTemperature);

        mainPanel = new GraphPanel(temperature.compute());

        Domain kappa = new SideProperty("transmissivity", domain);
        kappa = new KappaIC(kappa, rightKappa, leftKappa);
        kappa.compute();

    }

}
