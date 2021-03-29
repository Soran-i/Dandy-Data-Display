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

public class LineChart extends Viewer {
	private model.AnalysisSubject _subject;
	private ResultsStruct _results;
	private ChartPanel _chartPanel;
	
	public LineChart() { }
	
	public LineChart(model.AnalysisSubject subject) {
		_subject = subject;
	}
	
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

		JFreeChart chart = new JFreeChart("Mortality vs Expenses & Hospital Beds",
				new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

		_chartPanel = new ChartPanel(chart);
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