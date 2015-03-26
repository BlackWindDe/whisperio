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
import com.whisperio.data.jpa.ReleaseController;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;

/**
 * Bean used by the Obeya page.
 *
 * @author Maxime ESCOURBIAC
 */
@ManagedBean(name = "obeyaBean")
@ViewScoped
public class ObeyaBean implements Serializable {

    @ManagedProperty(value = "#{sessionBean}")
    private SessionBean sessionBean;

    /**
     * Creates a new instance of projectsBean
     */
    public ObeyaBean() {
    }

    /**
     * Test if the project got an active release.
     *
     * @return True if the project has an active release.
     */
    public boolean hasActiveRelease() {
        ProjectController projectController = new ProjectController();
        return (projectController.getProjectActiveRelease(sessionBean.getSelectedProject()) != null);
    }

    /**
     * Test if the active release got closed sprints.
     *
     * @return True if the active release got closed sprints
     */
    public boolean hasActiveReleaseClosedSprint() {
        ProjectController projectController = new ProjectController();
        ReleaseController releaseController = new ReleaseController();
        Release projectActiveRelease = projectController.getProjectActiveRelease(sessionBean.getSelectedProject());
        return (projectActiveRelease == null
                ? false : !releaseController.getReleaseClosedSprints(projectActiveRelease).isEmpty());
    }

    /**
     * Test if the selected project got closed sprints.
     *
     * @return True if the selected project got closed sprints
     */
    public boolean hasClosedSprint() {
        ProjectController projectController = new ProjectController();
        List<Sprint> closedSprints = projectController.getProjectClosedSprints(sessionBean.getSelectedProject());
        return (!closedSprints.isEmpty());
    }

    /**
     * Get the current release of the selected project.
     *
     * @return The current release of the selected project.
     */
    public Release getSelectedProjectCurrentRelease() {
        ProjectController projectController = new ProjectController();
        return projectController.getProjectActiveRelease(sessionBean.getSelectedProject());
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
        ProjectController projectController = new ProjectController();
        ReleaseController releaseController = new ReleaseController();
        Release activeRelease = projectController.getProjectActiveRelease(sessionBean.getSelectedProject());

        if (activeRelease != null) {
            List<Sprint> closedSprints = releaseController.getReleaseClosedSprints(activeRelease);
            if (closedSprints != null) {

                //Draw complete line.
                int sprintNumber = 0;
                BigDecimal releaseRemainingPointEndOfSprint = BigDecimal.ZERO;
                ChartSeries completed = new ChartSeries();
                completed.setLabel("Completed");

                for (Sprint closedSprint : closedSprints) {
                    //Update stats.
                    sprintNumber = closedSprint.getSprintNumber();
                    releaseRemainingPointEndOfSprint = closedSprint.getReleaseRemainingPointEndOfSprint();
                    yAxis.setMax(Math.max((int) yAxis.getMax(), releaseRemainingPointEndOfSprint.intValue()) + 10);
                    //Draw point.
                    completed.set(sprintNumber, releaseRemainingPointEndOfSprint);
                }
                releaseBurdownChart.addSeries(completed);

                //Draw estimation line
                BigDecimal averageVelocity = releaseController.getReleaseAverageVelocity(activeRelease);
                if (averageVelocity.compareTo(BigDecimal.ZERO) > 0) {
                    ChartSeries estimation = new ChartSeries();
                    estimation.setLabel("Estimation");
                    //Loop to estimate release remaining work.
                    while (releaseRemainingPointEndOfSprint.compareTo(BigDecimal.ZERO) >= 0) {
                        estimation.set(sprintNumber++, releaseRemainingPointEndOfSprint);
                        releaseRemainingPointEndOfSprint = releaseRemainingPointEndOfSprint.add(averageVelocity.negate());
                    }
                    //Finalize the estimation graph.
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
     * Generating Project Velocity Chart.
     *
     * @return Generated Project velocity chart.
     */
    public BarChartModel getVelocityChart() {

        //Init velocity chart
        BarChartModel velocityChart = new BarChartModel();
        velocityChart.setTitle("Velocity");
        velocityChart.setLegendPosition("e");
        velocityChart.setShowPointLabels(true);
        velocityChart.getAxes().put(AxisType.X, new CategoryAxis("Sprint"));
        Axis yAxis = velocityChart.getAxis(AxisType.Y);
        yAxis.setLabel("Story Points Done");
        yAxis.setMin(0);
        yAxis.setMax(0);

        //Retrive releases.
        Project selectedProject = sessionBean.getSelectedProject();
        List<Release> releases = selectedProject.getReleases();

        //Init serie
        ReleaseController releaseController = new ReleaseController();
        ChartSeries releaseVelocity = new ChartSeries();
        releaseVelocity.setLabel("Velocity");

        //Draw velocity.
        for (Release release : releases) {
            List<Sprint> closedSprints = releaseController.getReleaseClosedSprints(release);
            for (Sprint closedSprint : closedSprints) {
                yAxis.setMax(Math.max((int) yAxis.getMax(), closedSprint.getVelocity().intValue()) + 10);
                releaseVelocity.set(release.getReleaseNumber() + "." + closedSprint.getSprintNumber(),
                        closedSprint.getVelocity());
            }
        }
        velocityChart.addSeries(releaseVelocity);
        return velocityChart;
    }

    /**
     * Generating Business Value Chart.
     *
     * @return Business Value Chart.
     */
    public LineChartModel getBusinessValueChart() {
        LineChartModel businessValueChart = new LineChartModel();
        businessValueChart.setTitle("Business Value Chart");
        businessValueChart.setLegendPosition("e");
        businessValueChart.setShowPointLabels(true);
        businessValueChart.getAxes().put(AxisType.X, new CategoryAxis("Sprint"));
        Axis yAxis = businessValueChart.getAxis(AxisType.Y);
        yAxis.setLabel("Business Value");
        yAxis.setMin(0);
        yAxis.setMax(0);

        Project selectedProject = sessionBean.getSelectedProject();
        List<Release> releases = selectedProject.getReleases();
        ReleaseController releaseController = new ReleaseController();
        BigDecimal businessValue = BigDecimal.ZERO;
        String lastX = null;
        BigDecimal lastY = null;

        for (Release release : releases) {
            List<Sprint> closedSprints = releaseController.getReleaseClosedSprints(release);
            ChartSeries businessValueSerie = new ChartSeries();
            businessValueSerie.setLabel(release.getName());

            //Merge two release series
            if (lastX != null && lastY != null) {
                businessValueSerie.set(lastX, lastY);
            }

            for (Sprint closedSprint : closedSprints) {
                businessValue = businessValue.add(closedSprint.getBusinessValueDone());
                lastX = release.getReleaseNumber() + "." + closedSprint.getSprintNumber();
                lastY = businessValue;
                businessValueSerie.set(lastX, lastY);

            }
            businessValueChart.addSeries(businessValueSerie);
        }
        yAxis.setMax(businessValue.intValue() * 1.20);
        return businessValueChart;
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
