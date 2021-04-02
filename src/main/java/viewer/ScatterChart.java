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
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;

import AnalysisComponent.*;
import model.*;

/**
* This class is an concrete observer which, when attached to an AnalysisSubject, can display 
* the subject results on a scatter chart. 
*
* @author	Matthew Da Silva
*/
public class ScatterChart extends Viewer {
	private AnalysisSubject _subject;
	private ResultsStruct _results;
	private ChartPanel _chartPanel;
	
	/**
	* This constructor creates an empty ScatterChart object (no related Analysis)
	*/
	public ScatterChart() { }
	
	/**
	* This constructor creates a ScatterChart object and connects it to the provided subject.
	* 
	* @param subject An AnalysisSubject which holds the results to be displayed
	*/
	public ScatterChart(AnalysisSubject subject) {
		_subject = subject;
		update();
	}
	
	/**
	* This method updates the ScatterChart's local representation of the connected AnalysisSubject's 
	* results and creates a scatter chart with these results
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
			
			XYItemRenderer itemrenderer = new XYLineAndShapeRenderer(false, true);
			
			plot.setDataset(i, dataset);
			plot.setRenderer(i, itemrenderer);
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
	* This method gets the scatter chart ChartPanel created during the update() function.
	* 
	* @return A ChartPanel that contains the scatter chart
	*/
	public ChartPanel getChart() {
		return _chartPanel;
	}
	
	/**
	* This method sets the subject of the ScatterChart object so that it can obtain the subject results 
	* in the update() function.
	* 
	* @param subject An AnalysisSubject which holds the results to be displayed
	*/
	public void setSubject(AnalysisSubject subject) {
		_subject = subject;
		update();
	}
}