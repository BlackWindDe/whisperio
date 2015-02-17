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
import com.whisperio.data.jpa.ReleaseController;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
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

    private LineChartModel releaseBurdownChart;

    /**
     * Creates a new instance of projectsBean
     */
    public ProjectBean() {
    }

    @PostConstruct
    public void init() {
        createReleaseBurdownChart();
    }

    public LineChartModel getReleaseBurndownChart() {
        return releaseBurdownChart;
    }

    /**
     * Create the release burdown chart.
     */
    private void createReleaseBurdownChart() {
        Project selectedProject = sessionBean.getSelectedProject();
        ReleaseController releaseController = new ReleaseController();
        Release activeRelease = releaseController.getProjectActiveRelease(selectedProject);
        releaseBurdownChart = new LineChartModel();
        releaseBurdownChart.setTitle("Release Burndown Chart");
        releaseBurdownChart.setLegendPosition("e");
        releaseBurdownChart.setShowPointLabels(true);
        releaseBurdownChart.getAxes().put(AxisType.X, new CategoryAxis("Sprint"));
        Axis yAxis = releaseBurdownChart.getAxis(AxisType.Y);
        yAxis.setLabel("Story Points");
        yAxis.setMin(0);
        yAxis.setMax(200);

        if (activeRelease != null) {
            List<Sprint> sprints = activeRelease.getSprints();
            if (sprints != null && sprints.size() > 0) {
                ChartSeries completed = new ChartSeries();
                completed.setLabel("Completed");
                for (Sprint sprint : sprints) {
                    completed.set(sprint.getSprintNumber(), sprint.getReleaseRemainingPointEndOfSprint());
                }
                releaseBurdownChart.addSeries(completed);

                ChartSeries estimation = new ChartSeries();
                estimation.setLabel("Estimation");
                //To do : Generate estimation chart.
                releaseBurdownChart.addSeries(estimation);
            }
        }
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
