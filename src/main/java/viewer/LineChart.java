package viewer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;

import AnalysisComponent.*;
import model.*;

/**
* This class is an concrete observer which, when attached to an AnalysisSubject, can display 
* the subject results on a line chart. 
*
* @author	Matthew Da Silva
*/
public class LineChart extends Viewer {
	private AnalysisSubject _subject;
	private ResultsStruct _results;
	private ChartPanel _chartPanel;
	
	/**
	* This constructor creates an empty LineChart object (no related Analysis)
	*/
	public LineChart() { }
	
	/**
	* This constructor creates a LineChart object and connects it to the provided subject.
	* 
	* @param subject An AnalysisSubject which holds the results to be displayed
	*/
	public LineChart(AnalysisSubject subject) {
		_subject = subject;
		update();
	}
	
	/**
	* This method updates the LineChart's local representation of the connected AnalysisSubject's 
	* results and creates a line chart with these results
	*/
	public void update() {
		_results = _subject.getResults();
		
		XYPlot plot = new XYPlot();
		for(int i = 0; i <= _results.Results.size() - 1; i++) {
			TimeSeries series = new TimeSeries(_results.Labels.get(i));
			for(int j = 0; j <= _results.Years.size() - 1; j++) {
				series.add(new Year(_results.Years.get(j)), _results.Results.get(i).get(j));
			}
			
			TimeSeriesCollection dataset = new TimeSeriesCollection();
			dataset.addSeries(series);
			
			XYSplineRenderer splinerenderer = new XYSplineRenderer();
			
			plot.setDataset(i, dataset);
			plot.setRenderer(i, splinerenderer);
			plot.setRangeAxis(i, new NumberAxis(_results.Units.get(i)));
			
			plot.mapDatasetToRangeAxis(i, i); // ith dataset to ith y-axis
		}

		DateAxis domainAxis = new DateAxis("Year");
		plot.setDomainAxis(domainAxis);

		JFreeChart chart = new JFreeChart(_results.Title,
				new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

		_chartPanel = new ChartPanel(chart);
		_chartPanel.setPreferredSize(new Dimension(400, 300));
		_chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		_chartPanel.setBackground(Color.white);
	}
	
	/**
	* This method gets the line chart ChartPanel created during the update() function.
	* 
	* @return A ChartPanel that contains the line chart
	*/
	public ChartPanel getChart() {
		return _chartPanel;
	}
	
	/**
	* This method sets the subject of the LineChart object so that it can obtain the subject results 
	* in the update() function.
	* 
	* @param subject An AnalysisSubject which holds the results to be displayed
	*/
	public void setSubject(AnalysisSubject subject) {
		_subject = subject;
		update();
	}
}