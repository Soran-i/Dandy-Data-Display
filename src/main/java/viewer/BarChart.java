package viewer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.LayeredBarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import AnalysisComponent.*;
import model.*;

/**
* This class is an concrete observer which, when attached to an AnalysisSubject, can display 
* the subject results on a bar chart. 
*
* @author	Matthew Da Silva
*/
public class BarChart extends Viewer {
	private AnalysisSubject _subject;
	private ResultsStruct _results;
	private ChartPanel _chartPanel;
	
	/**
	* This constructor creates an empty BartChart object (no related Analysis)
	*/
	public BarChart() { }
	
	/**
	* This constructor creates a BartChart object and connects it to the provided subject.
	* 
	* @param subject An AnalysisSubject which holds the results to be displayed
	*/
	public BarChart(AnalysisSubject subject) {
		_subject = subject;
	}
	
	/**
	* This method updates the BarChart's local representation of the connected AnalysisSubject's 
	* results and creates a bar chart with these results
	*/
	public void update() {
		_results = _subject.getResults();
		
		CategoryPlot plot = new CategoryPlot();
		for(int i = 0; i <= _results.Results.size() - 1; i++) {
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			for(int j = 0; j <= _results.Years.size() - 1; j++) {
				dataset.setValue(_results.Results.get(i).get(j), _results.Labels.get(i), _results.Years.get(j));
			}
			
			LayeredBarRenderer barrenderer = new LayeredBarRenderer();
			barrenderer.setSeriesBarWidth(0, 0.3 + 0.3*i);
			
			plot.setDataset(i, dataset);
			plot.setRenderer(i, barrenderer);
			plot.setRangeAxis(i, new NumberAxis(_results.Units.get(i)));
			
			plot.mapDatasetToRangeAxis(i, i); // ith dataset to ith y-axis
		}

		CategoryAxis domainAxis = new CategoryAxis("Year");
		plot.setDomainAxis(domainAxis);

		JFreeChart chart = new JFreeChart("Mortality vs Expenses & Hospital Beds",
				new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

		_chartPanel = new ChartPanel(chart);
		_chartPanel.setPreferredSize(new Dimension(400, 300));
		_chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		_chartPanel.setBackground(Color.white);
	}
	
	/**
	* This method gets the bar chart ChartPanel created during the update() function.
	* 
	* @return A ChartPanel that contains the bar chart
	*/
	public ChartPanel getChart() {
		return _chartPanel;
	}
	
	/**
	* This method sets the subject of the BarChart object so that it can obtain the subject results 
	* in the update() function.
	* 
	* @param subject An AnalysisSubject which holds the results to be displayed
	*/
	public void setSubject(AnalysisSubject subject) {
		_subject = subject;
	}
}