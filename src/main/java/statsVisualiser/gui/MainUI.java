package statsVisualiser.gui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import org.jfree.chart.plot.CategoryPlot;
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

import selectionModule.InitialConfigFetcher;

public class MainUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static MainUI instance;

	public static MainUI getInstance() {
		if (instance == null)
			instance = new MainUI();

		return instance;
	}

	private MainUI() {
		// Set window title
		super("Country Statistics");

		InitialConfigFetcher fetcher = new InitialConfigFetcher();
		
		// Set top bar
		JLabel chooseCountryLabel = new JLabel("Choose a country: ");
		Vector<String> countriesNames = fetcher.getCountriesAvailable();
		
		countriesNames.sort(null);
		JComboBox<String> countriesList = new JComboBox<String>(countriesNames);

		JLabel from = new JLabel("From");
		JLabel to = new JLabel("To");
		Vector<String> years = fetcher.getYearsAvailable();

		JComboBox<String> fromList = new JComboBox<String>(years);
		JComboBox<String> toList = new JComboBox<String>(years);

		JPanel north = new JPanel();
		north.add(chooseCountryLabel);
		north.add(countriesList);
		north.add(from);
		north.add(fromList);
		north.add(to);
		north.add(toList);

		// Set bottom bar
		JButton recalculate = new JButton("Recalculate");
		
        recalculate.addActionListener(new ActionListener(){
     	   public void actionPerformed(ActionEvent ae){
     	      //TODO Matt do this
     	        
     	   }
     	});

		JLabel viewsLabel = new JLabel("Available Views: ");

		Vector<String> viewsNames = fetcher.getViewersAvailable();

		
		JComboBox<String> viewsList = new JComboBox<String>(viewsNames);
		JButton addView = new JButton("+");
		JButton removeView = new JButton("-");

		JLabel methodLabel = new JLabel("        Choose analysis method: ");

		Vector<String> methodsNames = fetcher.getAnalysisAvailable();


		JComboBox<String> methodsList = new JComboBox<String>(methodsNames);

		JPanel south = new JPanel();
		south.add(viewsLabel);
		south.add(viewsList);
		south.add(addView);
		south.add(removeView);

		south.add(methodLabel);
		south.add(methodsList);
		south.add(recalculate);

		JPanel east = new JPanel();

		// Set charts region
		JPanel west = new JPanel();
		west.setLayout(new GridLayout(2, 0));
		createCharts(west);

		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(east, BorderLayout.EAST);
		getContentPane().add(south, BorderLayout.SOUTH);
		getContentPane().add(west, BorderLayout.WEST);
	}

	private void createCharts(JPanel west) {
		createLine(west);
		createTimeSeries(west);
		createBar(west);
		createPie(west);
		createScatter(west);
		createReport(west);

	}

	private void createReport(JPanel west) {
		JTextArea report = new JTextArea();
		report.setEditable(false);
		report.setPreferredSize(new Dimension(400, 300));
		report.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		report.setBackground(Color.white);
		String reportMessage, reportMessage2;

		reportMessage = "Mortality vs Expenses & Hospital Beds\n" + "==============================\n" + "Year 2018:\n"
				+ "\tMortality/1000 births => 5.6\n" + "\tHealth Expenditure per Capita => 10624\n"
				+ "\tHospital Beds/1000 people => 2.92\n" + "\n" + "Year 2017:\n" + "\tMortality/1000 births => 5.7\n"
				+ "\tHealth Expenditure per Capita => 10209\n" + "\tHospital Beds/1000 people => 2.87\n" + "\n"
				+ "Year 2016:\n" + "\tMortality/1000 births => 5.8\n" + "\tHealth Expenditure per Capita => 9877\n"
				+ "\tHospital Beds/1000 people => 2.77\n";

		reportMessage2 = "Unemployment: Mev vs Women\n" + "==========================\n" + "Men=>\n"
				+ "\tEmployed: 96.054%\n" + "\tUnemployed: 3.946%\n" + "\n" + "Women=>\n" + "\tEmployed: 96.163%\n"
				+ "\tUnemployed: 3.837%\n";

		report.setText(reportMessage);
		JScrollPane outputScrollPane = new JScrollPane(report);
		west.add(outputScrollPane);
	}

	private void createScatter(JPanel west) {
		TimeSeries series1 = new TimeSeries("Mortality/1000 births");
		series1.add(new Year(2018), 5.6);
		series1.add(new Year(2017), 5.7);
		series1.add(new Year(2016), 5.8);
		series1.add(new Year(2015), 5.8);
		series1.add(new Year(2014), 5.9);
		series1.add(new Year(2013), 6.0);
		series1.add(new Year(2012), 6.1);
		series1.add(new Year(2011), 6.2);
		series1.add(new Year(2010), 6.4);

		TimeSeries series2 = new TimeSeries("Health Expenditure per Capita");
		series2.add(new Year(2018), 10624);
		series2.add(new Year(2017), 10209);
		series2.add(new Year(2016), 9877);
		series2.add(new Year(2015), 9491);
		series2.add(new Year(2014), 9023);
		series2.add(new Year(2013), 8599);
		series2.add(new Year(2012), 8399);
		series2.add(new Year(2011), 8130);
		series2.add(new Year(2010), 7930);
		TimeSeriesCollection dataset2 = new TimeSeriesCollection();
		dataset2.addSeries(series2);

		TimeSeries series3 = new TimeSeries("Hospital Beds/1000 people");
		series3.add(new Year(2018), 2.92);
		series3.add(new Year(2017), 2.87);
		series3.add(new Year(2016), 2.77);
		series3.add(new Year(2015), 2.8);
		series3.add(new Year(2014), 2.83);
		series3.add(new Year(2013), 2.89);
		series3.add(new Year(2012), 2.93);
		series3.add(new Year(2011), 2.97);
		series3.add(new Year(2010), 3.05);

		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(series1);
		dataset.addSeries(series3);

		XYPlot plot = new XYPlot();
		XYItemRenderer itemrenderer1 = new XYLineAndShapeRenderer(false, true);
		XYItemRenderer itemrenderer2 = new XYLineAndShapeRenderer(false, true);

		plot.setDataset(0, dataset);
		plot.setRenderer(0, itemrenderer1);
		DateAxis domainAxis = new DateAxis("Year");
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(new NumberAxis(""));

		plot.setDataset(1, dataset2);
		plot.setRenderer(1, itemrenderer2);
		plot.setRangeAxis(1, new NumberAxis("US$"));

		plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
		plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

		JFreeChart scatterChart = new JFreeChart("Mortality vs Expenses & Hospital Beds",
				new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

		ChartPanel chartPanel = new ChartPanel(scatterChart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		west.add(chartPanel);
	}

	private void createPie(JPanel west) {
		// Different way to create pie chart
		/*
		 * var dataset = new DefaultPieDataset(); dataset.setValue("Unemployed", 3.837);
		 * dataset.setValue("Employed", 96.163);
		 *  
		 * JFreeChart pieChart = ChartFactory.createPieChart("Women's Unemployment",
		 * dataset, true, true, false);
		 */

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(3.946, "Unemployed", "Men");
		dataset.addValue(96.054, "Employed", "Men");
		dataset.addValue(3.837, "Unemployed", "Women");
		dataset.addValue(96.163, "Employed", "Women");

		JFreeChart pieChart = ChartFactory.createMultiplePieChart("Unemployment: Men vs Women", dataset,
				TableOrder.BY_COLUMN, true, true, false);

		ChartPanel chartPanel = new ChartPanel(pieChart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		west.add(chartPanel);
	}

	private void createBar(JPanel west) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.setValue(5.6, "Mortality/1000 births", "2018");
		dataset.setValue(5.7, "Mortality/1000 births", "2017");
		dataset.setValue(5.8, "Mortality/1000 births", "2016");
		dataset.setValue(5.8, "Mortality/1000 births", "2015");
		dataset.setValue(5.9, "Mortality/1000 births", "2014");
		dataset.setValue(6, "Mortality/1000 births", "2013");
		dataset.setValue(6.1, "Mortality/1000 births", "2012");
		dataset.setValue(6.2, "Mortality/1000 births", "2011");
		dataset.setValue(6.4, "Mortality/1000 births", "2010");

		dataset.setValue(2.92, "Hospital beds/1000 people", "2018");
		dataset.setValue(2.87, "Hospital beds/1000 people", "2017");
		dataset.setValue(2.77, "Hospital beds/1000 people", "2016");
		dataset.setValue(2.8, "Hospital beds/1000 people", "2015");
		dataset.setValue(2.83, "Hospital beds/1000 people", "2014");
		dataset.setValue(2.89, "Hospital beds/1000 people", "2013");
		dataset.setValue(2.93, "Hospital beds/1000 people", "2012");
		dataset.setValue(2.97, "Hospital beds/1000 people", "2011");
		dataset.setValue(3.05, "Hospital beds/1000 people", "2010");

		DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();

		dataset2.setValue(10623, "Health Expenditure per Capita", "2018");
		dataset2.setValue(10209, "Health Expenditure per Capita", "2017");
		dataset2.setValue(9877, "Health Expenditure per Capita", "2016");
		dataset2.setValue(9491, "Health Expenditure per Capita", "2015");
		dataset2.setValue(9023, "Health Expenditure per Capita", "2014");
		dataset2.setValue(8599, "Health Expenditure per Capita", "2013");
		dataset2.setValue(8399, "Health Expenditure per Capita", "2012");
		dataset2.setValue(8130, "Health Expenditure per Capita", "2011");
		dataset2.setValue(7930, "Health Expenditure per Capita", "2010");

		CategoryPlot plot = new CategoryPlot();
		BarRenderer barrenderer1 = new BarRenderer();
		BarRenderer barrenderer2 = new BarRenderer();

		plot.setDataset(0, dataset);
		plot.setRenderer(0, barrenderer1);
		CategoryAxis domainAxis = new CategoryAxis("Year");
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(new NumberAxis(""));

		plot.setDataset(1, dataset2);
		plot.setRenderer(1, barrenderer2);
		plot.setRangeAxis(1, new NumberAxis("US$"));

		plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
		plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

		JFreeChart barChart = new JFreeChart("Mortality vs Expenses & Hospital Beds",
				new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

		// Different way to create bar chart
		/*
		 * dataset = new DefaultCategoryDataset();
		 * 
		 * dataset.addValue(3.946, "Unemployed", "Men"); dataset.addValue(96.054,
		 * "Employed", "Men"); dataset.addValue(3.837, "Unemployed", "Women");
		 * dataset.addValue(96.163, "Employed", "Women"); barChart =
		 * ChartFactory.createBarChart("Unemployment: Men vs Women", "Gender",
		 * "Percentage", dataset, PlotOrientation.VERTICAL, true, true, false);
		 */

		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		west.add(chartPanel);
	}

	private void createLine(JPanel west) {
		XYSeries series1 = new XYSeries("Mortality/1000 births");
		series1.add(2018, 5.6);
		series1.add(2017, 5.7);
		series1.add(2016, 5.8);
		series1.add(2015, 5.8);
		series1.add(2014, 5.9);
		series1.add(2013, 6.0);
		series1.add(2012, 6.1);
		series1.add(2011, 6.2);
		series1.add(2010, 6.4);

		XYSeries series2 = new XYSeries("Health Expenditure per Capita");
		series2.add(2018, 10624);
		series2.add(2017, 10209);
		series2.add(2016, 9877);
		series2.add(2015, 9491);
		series2.add(2014, 9023);
		series2.add(2013, 8599);
		series2.add(2012, 8399);
		series2.add(2011, 8130);
		series2.add(2010, 7930);

		XYSeries series3 = new XYSeries("Hospital Beds/1000 people");
		series3.add(2018, 2.92);
		series3.add(2017, 2.87);
		series3.add(2016, 2.77);
		series3.add(2015, 2.8);
		series3.add(2014, 2.83);
		series3.add(2013, 2.89);
		series3.add(2012, 2.93);
		series3.add(2011, 2.97);
		series3.add(2010, 3.05);

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series1);
		dataset.addSeries(series2);
		dataset.addSeries(series3);

		JFreeChart chart = ChartFactory.createXYLineChart("Mortality vs Expenses & Hospital Beds", "Year", "", dataset,
				PlotOrientation.VERTICAL, true, true, false);

		XYPlot plot = chart.getXYPlot();

		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesPaint(0, Color.RED);
		renderer.setSeriesStroke(0, new BasicStroke(2.0f));

		plot.setRenderer(renderer);
		plot.setBackgroundPaint(Color.white);

		plot.setRangeGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.BLACK);

		plot.setDomainGridlinesVisible(true);
		plot.setDomainGridlinePaint(Color.BLACK);

		chart.getLegend().setFrame(BlockBorder.NONE);

		chart.setTitle(
				new TextTitle("Mortality vs Expenses & Hospital Beds", new Font("Serif", java.awt.Font.BOLD, 18)));

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		west.add(chartPanel);

	}

	private void createTimeSeries(JPanel west) {
		TimeSeries series1 = new TimeSeries("Mortality/1000 births");
		series1.add(new Year(2018), 5.6);
		series1.add(new Year(2017), 5.7);
		series1.add(new Year(2016), 5.8);
		series1.add(new Year(2015), 5.8);
		series1.add(new Year(2014), 5.9);
		series1.add(new Year(2013), 6.0);
		series1.add(new Year(2012), 6.1);
		series1.add(new Year(2011), 6.2);
		series1.add(new Year(2010), 6.4);

		TimeSeries series2 = new TimeSeries("Health Expenditure per Capita");
		series2.add(new Year(2018), 10624);
		series2.add(new Year(2017), 10209);
		series2.add(new Year(2016), 9877);
		series2.add(new Year(2015), 9491);
		series2.add(new Year(2014), 9023);
		series2.add(new Year(2013), 8599);
		series2.add(new Year(2012), 8399);
		series2.add(new Year(2011), 8130);
		series2.add(new Year(2010), 7930);
		TimeSeriesCollection dataset2 = new TimeSeriesCollection();
		dataset2.addSeries(series2);

		TimeSeries series3 = new TimeSeries("Hospital Beds/1000 people");
		series3.add(new Year(2018), 2.92);
		series3.add(new Year(2017), 2.87);
		series3.add(new Year(2016), 2.77);
		series3.add(new Year(2015), 2.8);
		series3.add(new Year(2014), 2.83);
		series3.add(new Year(2013), 2.89);
		series3.add(new Year(2012), 2.93);
		series3.add(new Year(2011), 2.97);
		series3.add(new Year(2010), 3.05);

		TimeSeriesCollection dataset = new TimeSeriesCollection();
		dataset.addSeries(series1);
		dataset.addSeries(series3);

		XYPlot plot = new XYPlot();
		XYSplineRenderer splinerenderer1 = new XYSplineRenderer();
		XYSplineRenderer splinerenderer2 = new XYSplineRenderer();

		plot.setDataset(0, dataset);
		plot.setRenderer(0, splinerenderer1);
		DateAxis domainAxis = new DateAxis("Year");
		plot.setDomainAxis(domainAxis);
		plot.setRangeAxis(new NumberAxis(""));

		plot.setDataset(1, dataset2);
		plot.setRenderer(1, splinerenderer2);
		plot.setRangeAxis(1, new NumberAxis("US$"));

		plot.mapDatasetToRangeAxis(0, 0);// 1st dataset to 1st y-axis
		plot.mapDatasetToRangeAxis(1, 1); // 2nd dataset to 2nd y-axis

		JFreeChart chart = new JFreeChart("Mortality vs Expenses & Hospital Beds",
				new Font("Serif", java.awt.Font.BOLD, 18), plot, true);

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		west.add(chartPanel);

	}

	public static void main(String[] args) {

		JFrame frame = MainUI.getInstance();
		frame.setSize(900, 600);
		frame.pack();
		frame.setVisible(true);
	}
	// TODO Auto-generated method stub

}