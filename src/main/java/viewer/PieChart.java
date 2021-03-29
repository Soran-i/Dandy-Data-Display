package viewer;

import java.util.*;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.MultiplePiePlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.chart.util.TableOrder;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class PieChart extends Viewer {
	private model.AnalysisSubject _subject;
	private ResultsStruct _results;
	private ChartPanel _chartPanel;
	
	public PieChart() { }
	
	public PieChart(model.AnalysisSubject subject) {
		_subject = subject;
	}
	
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
	
	public ChartPanel getChart() {
		return _chartPanel;
	}
	
	public void setSubject(model.AnalysisSubject subject) {
		_subject = subject;
	}
}