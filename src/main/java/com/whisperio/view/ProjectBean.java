/**
 * (C) Copyright 2014 Whisper.io.
 *
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors: Maxime ESCOURBIAC
 */
package com.whisperio.view;

import com.whisperio.data.entity.Project;
import com.whisperio.data.entity.Release;
import com.whisperio.data.entity.Sprint;
import com.whisperio.data.jpa.ProjectController;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

/**
 * Bean used by the project selection page.
 *
 * @author Maxime ESCOURBIAC
 */
@ManagedBean(name = "projectBean")
@RequestScoped
public class ProjectBean implements Serializable {

    @ManagedProperty(value = "#{sessionBean}")
    private SessionBean sessionBean;

    /**
     * Creates a new instance of projectsBean
     */
    public ProjectBean() {
    }

    /**
     * Generating Release Burndown Chart.
     *
     * @return Generated Release BurndownChart.
     */
    public LineChartModel getReleaseBurndownChart() {
        //Init release burndown chart
        LineChartModel releaseBurdownChart = new LineChartModel();
        releaseBurdownChart.setTitle("Release Burndown Chart");
        releaseBurdownChart.setLegendPosition("e");
        releaseBurdownChart.setShowPointLabels(true);
        releaseBurdownChart.getAxes().put(AxisType.X, new CategoryAxis("Sprint"));
        Axis yAxis = releaseBurdownChart.getAxis(AxisType.Y);
        yAxis.setLabel("Story Points");
        yAxis.setMin(0);
        yAxis.setMax(0);

        //Retrive active release.
        Project selectedProject = sessionBean.getSelectedProject();
        ProjectController projectController = new ProjectController();
        Release activeRelease = projectController.getProjectActiveRelease(selectedProject);

        if (activeRelease != null) {
            List<Sprint> sprints = activeRelease.getSprints();
            if (sprints != null && sprints.size() > 0) {

                //Draw complete line.
                ChartSeries completed = new ChartSeries();
                BigDecimal averageVelocity = BigDecimal.ZERO;
                int sprintNumber = 0;

                BigDecimal releaseRemainingPointEndOfSprint = BigDecimal.ZERO;
                for (Sprint sprint : sprints) {
                    sprintNumber = sprint.getSprintNumber();
                    releaseRemainingPointEndOfSprint = sprint.getReleaseRemainingPointEndOfSprint();
                    completed.set(sprintNumber, releaseRemainingPointEndOfSprint);
                    //Max Y
                    yAxis.setMax(Math.max((int) yAxis.getMax(), releaseRemainingPointEndOfSprint.intValue()) + 10);
                    //Compute average velocity.
                    averageVelocity = averageVelocity.add(sprint.getVelocity().divide(new BigDecimal(sprints.size()), MathContext.DECIMAL128));
                }
                completed.setLabel("Completed");
                releaseBurdownChart.addSeries(completed);

                //Draw estimation line
                if (averageVelocity.compareTo(BigDecimal.ZERO) > 0) {
                    ChartSeries estimation = new ChartSeries();
                    estimation.setLabel("Estimation");
                    while (releaseRemainingPointEndOfSprint.compareTo(BigDecimal.ZERO) >= 0) {
                        estimation.set(sprintNumber, releaseRemainingPointEndOfSprint);
                        ++sprintNumber;
                        releaseRemainingPointEndOfSprint = releaseRemainingPointEndOfSprint.add(averageVelocity.negate());
                    }

                    if (releaseRemainingPointEndOfSprint.compareTo(BigDecimal.ZERO) != 0) {
                        estimation.set(sprintNumber, BigDecimal.ZERO);
                    }
                    releaseBurdownChart.addSeries(estimation);
                }
            }
        }
        return releaseBurdownChart;
    }

    /**
     * Session Bean.
     *
     * @return Session Bean.
     */
    public SessionBean getSessionBean() {
        return sessionBean;
    }

    /**
     * Session Bean.
     *
     * @param sessionBean Session Bean.
     */
    public void setSessionBean(SessionBean sessionBean) {
        this.sessionBean = sessionBean;
    }

}
