package viewer;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import AnalysisComponent.*;
import model.*;

/**
* This class is an concrete observer which, when attached to an AnalysisSubject, can display 
* the subject results on a text report. 
*
* @author	Matthew Da Silva
*/
public class TextReport extends Viewer {
	private AnalysisSubject _subject;
	private ResultsStruct _results;
	private JScrollPane _scrollPane;
	
	/**
	* This constructor creates an empty TextReport object (no related Analysis)
	*/
	public TextReport() { }
	
	/**
	* This constructor creates a TextReport object and connects it to the provided subject.
	* 
	* @param subject An AnalysisSubject which holds the results to be displayed
	*/
	public TextReport(AnalysisSubject subject) {
		_subject = subject;
	}
	
	/**
	* This method updates the TextReport's local representation of the connected AnalysisSubject's 
	* results and creates a text report with these results
	*/
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
	
	/**
	* This method gets the text report JScrollPane created during the update() function.
	* 
	* @return A JScrollPane that contains the text report
	*/
	public JScrollPane getChart() {
		return _scrollPane;
	}
	
	/**
	* This method sets the subject of the TextReport object so that it can obtain the subject results 
	* in the update() function.
	* 
	* @param subject An AnalysisSubject which holds the results to be displayed
	*/
	public void setSubject(AnalysisSubject subject) {
		_subject = subject;
	}
}