package viewer;

import java.awt.Color;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Vector;

import javax.swing.BorderFactory;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.MultiplePiePlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.util.TableOrder;
import org.jfree.data.category.DefaultCategoryDataset;

import AnalysisComponent.*;
import model.*;

/**
* This class is an concrete observer which, when attached to an AnalysisSubject, can display 
* the subject results on a pie chart. 
*
* @author	Matthew Da Silva
*/
public class PieChart extends Viewer {
	private AnalysisSubject _subject;
	private ResultsStruct _results;
	private ChartPanel _chartPanel;
	
	/**
	* This constructor creates an empty PieChart object (no related Analysis)
	*/
	public PieChart() { }
	
	/**
	* This constructor creates a PieChart object and connects it to the provided subject.
	* 
	* @param subject An AnalysisSubject which holds the results to be displayed
	*/
	public PieChart(AnalysisSubject subject) {
		_subject = subject;
	}
	
	/**
	* This method updates the PieChart's local representation of the connected AnalysisSubject's 
	* results and creates a pie chart with these results
	*/
	public void update() {
		//_results = _subject.getResults();
		_results = new ResultsStruct();
		Vector<Double> temp = new Vector<Double>();
		temp.add(0.3946);
		temp.add(0.26054);
		temp.add(0.53837);
		temp.add(0.76163);
		_results.Results.add(temp);
		_results.Years.add(2016);
		_results.Years.add(2017);
		_results.Years.add(2018);
		_results.Years.add(2019);
		_results.Labels.add("Forest Area");
		_results.Units.add("%");
		_results.Title = "Forest Area (% Land of Area)";
		
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(int i = 0; i <= _results.Results.size() - 1; i++) {
			double resultAvg = 0;
			for(int j = 0 ; j <= _results.Results.get(i).size() - 1; j++) {
				resultAvg += _results.Results.get(i).get(j);
			}
			resultAvg = resultAvg / _results.Results.get(i).size();
			String label = _results.Labels.get(i);
			
			dataset.addValue(resultAvg, label, "Average Over Time Period");
			dataset.addValue((1.0 - resultAvg), "", "Average Over Time Period");
		}
		
		
		JFreeChart pieChart = ChartFactory.createMultiplePieChart(_results.Title, dataset,
				TableOrder.BY_COLUMN, false, true, false);
		
		MultiplePiePlot multiPlot = (MultiplePiePlot) pieChart.getPlot();
		JFreeChart obj = multiPlot.getPieChart();
		PiePlot plot = (PiePlot) obj.getPlot();
		
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} ({2})",
				NumberFormat.getNumberInstance(), new DecimalFormat("0.00%")));
		
		_chartPanel = new ChartPanel(pieChart);
		_chartPanel.setPreferredSize(new Dimension(400, 300));
		_chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		_chartPanel.setBackground(Color.white);
	}
	
	/**
	* This method gets the pie chart ChartPanel created during the update() function.
	* 
	* @return A ChartPanel that contains the pie chart
	*/
	public ChartPanel getChart() {
		return _chartPanel;
	}
	
	/**
	* This method sets the subject of the PieChart object so that it can obtain the subject results 
	* in the update() function.
	* 
	* @param subject An AnalysisSubject which holds the results to be displayed
	*/
	public void setSubject(AnalysisSubject subject) {
		_subject = subject;
	}
}