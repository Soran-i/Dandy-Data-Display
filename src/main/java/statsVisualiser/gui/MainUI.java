package statsVisualiser.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import selectionModule.InitialConfigFetcher;
import selectionModule.SelectionHandler;
import model.AnalysisSubject;
import viewer.*;

public class MainUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static MainUI instance;

	private Map<String, Viewer> existingViewers = new HashMap<String, Viewer>();
	
	private AnalysisSubject _subject;
	private JPanel _center;
	private String _prevAnalysis;
	private String _prevStartYear;
	private String _prevEndYear;
	private String _prevCountry;
	private String _prevViewer;

	public static MainUI getInstance() {
		if (instance == null)
			instance = new MainUI();

		return instance;
	}

	private MainUI() {
		// Set window title

		super("Country Statistics");

		InitialConfigFetcher fetcher = new InitialConfigFetcher();
		final SelectionHandler handler = new SelectionHandler();
		final ParamStruct paramStruct = new ParamStruct();

		InitializeParamStruct(paramStruct, fetcher, handler);

		// Set top bar
		JLabel chooseCountryLabel = new JLabel("Choose a country: ");
		Vector<String> countriesNames = fetcher.getCountriesAvailable();
		
		countriesNames.sort(null);
		final JComboBox<String> countriesList = new JComboBox<String>(countriesNames);
		_prevCountry = (String) countriesList.getItemAt(0);
		
		countriesList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String countryName = (String) countriesList.getSelectedItem();
				try {
					setCountryParam(countryName, paramStruct, handler);
					_subject.setParams(paramStruct);
					_prevCountry = countryName;
				} catch (CountryAnalysisException e) {
					countriesList.setSelectedItem(_prevCountry);
					System.out.println(e);
				}
			}
		});

		JLabel from = new JLabel("From");
		JLabel to = new JLabel("To");
		Vector<String> years = fetcher.getYearsAvailable();

		final JComboBox<String> fromList = new JComboBox<String>(years);
		_prevStartYear = (String) fromList.getItemAt(0);
		
		fromList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String StartYear = (String) fromList.getSelectedItem();
				try {
					setStartYear(StartYear, paramStruct, handler);
					_subject.setParams(paramStruct);
					_prevStartYear = StartYear;
				} catch (StartYearException e) {
					fromList.setSelectedItem(_prevStartYear);
					System.out.println(e);
				}
			}
		});

		final JComboBox<String> toList = new JComboBox<String>(years);
		toList.setSelectedItem(years.lastElement());
		_prevEndYear = years.lastElement();
		
		toList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String EndYear = (String) toList.getSelectedItem();
				try {
					setEndYear(EndYear, paramStruct, handler);
					_subject.setParams(paramStruct);
					_prevEndYear = EndYear;
				} catch (EndYearException e) {
					toList.setSelectedItem(_prevEndYear);
					System.out.println(e);
				}
			}
		});

		JPanel north = new JPanel();
		north.add(chooseCountryLabel);
		north.add(countriesList);
		north.add(from);
		north.add(fromList);
		north.add(to);
		north.add(toList);

		// Set bottom bar
		JButton recalculate = new JButton("Recalculate");

		recalculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				Object[] keySet = existingViewers.keySet().toArray();
				clearViewers();
				try {
					_subject.recalculate();
				} catch (ReaderException e) {
					System.out.println(e);
				}
				for(Object index:keySet) {
					addViewer((String) index);
				}
			}
		});

		JLabel viewsLabel = new JLabel("Available Views: ");

		Vector<String> viewsNames = fetcher.getViewersAvailable();

		final JComboBox<String> viewsList = new JComboBox<String>(viewsNames);
		_prevViewer = (String) viewsList.getItemAt(0);
		
		viewsList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				String ViewerSelection = (String) viewsList.getSelectedItem();
				try {
					CheckViewerParam(ViewerSelection, paramStruct, handler);
					_prevViewer = ViewerSelection;
				} catch (ViewerAnalysisException e) {
					viewsList.setSelectedItem(_prevViewer);
					System.out.println(e);
				}
			}
		});

		JButton addView = new JButton("+");
		JButton removeView = new JButton("-");

		JLabel methodLabel = new JLabel("        Choose analysis method: ");

		Vector<String> methodsNames = fetcher.getAnalysisAvailable();

		final JComboBox<String> methodsList = new JComboBox<String>(methodsNames);
		_prevAnalysis = (String) methodsList.getSelectedItem();
		
		methodsList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				String analysisID = (String) methodsList.getSelectedItem();
				if(!analysisID.equals(_prevAnalysis)) {
					clearViewers();
					_prevAnalysis = analysisID;
					paramStruct._analysis = analysisID;
					_subject.setParams(paramStruct);
				}
			}
		});

		JPanel south = new JPanel();
		south.add(viewsLabel);
		south.add(viewsList);
		south.add(addView);
		south.add(removeView);

		south.add(methodLabel);
		south.add(methodsList);
		south.add(recalculate);

		// Set charts region
		_center = new JPanel();
		_center.setLayout(new GridLayout(2, 0));
		_center.setPreferredSize(new Dimension(1200, 600));
		
		_subject = new AnalysisSubject();
		try {
			_subject = new AnalysisSubject(paramStruct);
		} catch (ReaderException e) {
			System.out.println(e);
		}

		addView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selection = viewsList.getSelectedItem().toString();
				addViewer(selection);
			}
		});

		removeView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selection = viewsList.getSelectedItem().toString();
				if (selection.equals("Pie Chart")) {
					if (existingViewers.containsKey(selection)) {
						PieChart pieChart = (PieChart) existingViewers.get(selection);
						_subject.detach(pieChart);
						_center.remove(pieChart.getChart());

						existingViewers.remove(selection);
					} else {
						System.out.println(selection + " isn't being displayed!");
					}
				}
				if (selection.equals("Line Chart")) {
					if (existingViewers.containsKey(selection)) {
						LineChart lineChart = (LineChart) existingViewers.get(selection);
						_subject.detach(lineChart);
						_center.remove(lineChart.getChart());

						existingViewers.remove(selection);
					} else {
						System.out.println(selection + " isn't being displayed!");
					}
				}
				if (selection.equals("Bar Chart")) {
					if (existingViewers.containsKey(selection)) {
						BarChart barChart = (BarChart) existingViewers.get(selection);
						_subject.detach(barChart);
						_center.remove(barChart.getChart());

						existingViewers.remove(selection);
					} else {
						System.out.println(selection + " isn't being displayed!");
					}
				}
				if (selection.equals("Scatter Chart")) {
					if (existingViewers.containsKey(selection)) {
						ScatterChart scatterChart = (ScatterChart) existingViewers.get(selection);
						_subject.detach(scatterChart);
						_center.remove(scatterChart.getChart());

						existingViewers.remove(selection);
					} else {
						System.out.println(selection + " isn't being displayed!");
					}
				}
				if (selection.equals("Text Report")) {
					if (existingViewers.containsKey(selection)) {
						TextReport textReport = (TextReport) existingViewers.get(selection);
						_subject.detach(textReport);
						_center.remove(textReport.getChart());

						existingViewers.remove(selection);
					} else {
						System.out.println(selection + " isn't being displayed!");
					}
				}
				_center.revalidate();
				_center.repaint();
			}
		});

		getContentPane().add(north, BorderLayout.NORTH);
		getContentPane().add(south, BorderLayout.SOUTH);
		getContentPane().add(_center, BorderLayout.CENTER);
	}

	private void setCountryParam(String countryName, ParamStruct paramStruct, SelectionHandler handler)
			throws CountryAnalysisException {
		if (handler.CheckAnalysisCountry(paramStruct._analysis, countryName)) {
			String CountryId = handler.CountryLookup(countryName);
			_prevCountry = CountryId;
			paramStruct._country = CountryId;
			// return paramStruct;
		} else {
			throw new CountryAnalysisException("Incompatible analysis and country selected");
		}
	}

	private void setEndYear(String EndYear, ParamStruct paramStruct, SelectionHandler handler) throws EndYearException {
		if (handler.CheckYears(paramStruct._yearStart, EndYear)) {
			_prevEndYear = EndYear;
			paramStruct._yearEnd = EndYear;
		} else {
			throw new EndYearException("Incompatible end year selected");
		}
	}

	private void setStartYear(String StartYear, ParamStruct paramStruct, SelectionHandler handler)
			throws StartYearException {
		if (handler.CheckYears(StartYear, paramStruct._yearEnd)) {
			_prevStartYear = StartYear;
			paramStruct._yearStart = StartYear;
		} else {
			throw new StartYearException("Incompatible Start year selected");
		}
	}

	private void CheckViewerParam(String ViewerName, ParamStruct paramStruct, SelectionHandler handler)
			throws ViewerAnalysisException {
		if (!handler.CheckAnalysisViewer(paramStruct._analysis, ViewerName)) {
			throw new ViewerAnalysisException("Incompatible analysis and Viewer selected");
		}
	}

	private void InitializeParamStruct(ParamStruct params, InitialConfigFetcher fetcher, SelectionHandler handler) {
		Vector<String> methodsNames = fetcher.getAnalysisAvailable();
		Vector<String> countriesNames = fetcher.getCountriesAvailable();
		Vector<String> years = fetcher.getYearsAvailable();

		params._analysis = methodsNames.get(0);
		params._yearStart = years.get(0);
		params._yearEnd = years.get(years.size()-1);

		String CountryId = handler.CountryLookup(countriesNames.get(0));
		params._country = CountryId;
	}
	
	private void clearViewers() {
		Object[] keySet = existingViewers.keySet().toArray();
		for(Object index:keySet) {
			if (index.equals("Pie Chart")) {
				PieChart pieChart = (PieChart) existingViewers.get(index);
				_subject.detach(pieChart);
				_center.remove(pieChart.getChart());
				existingViewers.remove(index);
			}
			if (index.equals("Line Chart")) {
				LineChart lineChart = (LineChart) existingViewers.get(index);
				_subject.detach(lineChart);
				_center.remove(lineChart.getChart());
				existingViewers.remove(index);
			}
			if (index.equals("Bar Chart")) {
				BarChart barChart = (BarChart) existingViewers.get(index);
				_subject.detach(barChart);
				_center.remove(barChart.getChart());
				existingViewers.remove(index);
			}
			if (index.equals("Scatter Chart")) {
				ScatterChart scatterChart = (ScatterChart) existingViewers.get(index);
				_subject.detach(scatterChart);
				_center.remove(scatterChart.getChart());
				existingViewers.remove(index);
			}
			if (index.equals("Text Report")) {
				TextReport textReport = (TextReport) existingViewers.get(index);
				_subject.detach(textReport);
				_center.remove(textReport.getChart());
				existingViewers.remove(index);
			}
		}
		_center.revalidate();
		_center.repaint();
	}
	
	private void addViewer(String type) {
		if (type.equals("Pie Chart")) {
			if (!existingViewers.containsKey(type)) {
				PieChart pieChart = new PieChart(_subject);
				_subject.attach(pieChart);
				_center.add(pieChart.getChart());

				existingViewers.put(type, pieChart);
			} else {
				System.out.println(type + " already exists!");
			}

		}
		if (type.equals("Line Chart")) {
			if (!existingViewers.containsKey(type)) {
				LineChart lineChart = new LineChart(_subject);
				_subject.attach(lineChart);
				_center.add(lineChart.getChart());

				existingViewers.put(type, lineChart);
			} else {
				System.out.println(type + " already exists!");
			}
		}
		if (type.equals("Bar Chart")) {
			if (!existingViewers.containsKey(type)) {
				BarChart barChart = new BarChart(_subject);
				_subject.attach(barChart);
				_center.add(barChart.getChart());

				existingViewers.put(type, barChart);
			} else {
				System.out.println(type + " already exists!");
			}
		}
		if (type.equals("Scatter Chart")) {
			if (!existingViewers.containsKey(type)) {
				ScatterChart scatterChart = new ScatterChart(_subject);
				_subject.attach(scatterChart);
				_center.add(scatterChart.getChart());

				existingViewers.put(type, scatterChart);
			} else {
				System.out.println(type + " already exists!");
			}
		}
		if (type.equals("Text Report")) {
			if (!existingViewers.containsKey(type)) {
				TextReport textReport = new TextReport(_subject);
				_subject.attach(textReport);
				_center.add(textReport.getChart());

				existingViewers.put(type, textReport);
			} else {
				System.out.println(type + " already exists!");
			}
		}
		_center.revalidate();
		_center.repaint();
	}
	
	public static void main(String[] args) {

		JFrame frame = MainUI.getInstance();
		frame.setSize(900, 600);
		frame.pack();
		frame.setVisible(true);
	}
	// TODO Auto-generated method stub

}