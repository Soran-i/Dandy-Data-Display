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

public class TextReport extends Viewer {
	private model.AnalysisSubject _subject;
	private ResultsStruct _results;
	private JScrollPane _scrollPane;
	
	public TextReport() { }
	
	public TextReport(model.AnalysisSubject subject) {
		_subject = subject;
	}
	
	public void update() {
		_results = _subject.getResults();
		
		JTextArea report = new JTextArea();
		report.setEditable(false);
		report.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		report.setBackground(Color.white);
		String reportMessage;

		reportMessage = _results.Title + "\n" + "==============================\n";
		for(int i = _results.Years.size() - 1; i >= 0; i--) {
			reportMessage += "Year " + _results.Years.get(i).toString() + ":\n";
			for(int j = 0; j <= _results.Results.size() - 1; j++) {
				reportMessage += "\t" + _results.Labels.get(j) + " => " + _results.Results.get(j).get((_results.Years.size() - 1) - i) + "\n";
			}
			reportMessage += "\n";
		}
		
		report.setText(reportMessage);
		_scrollPane = new JScrollPane(report);
		_scrollPane.setPreferredSize(new Dimension(400, 300));
	}
	
	public JScrollPane getChart() {
		return _scrollPane;
	}
	
	public void setSubject(model.AnalysisSubject subject) {
		_subject = subject;
	}
}