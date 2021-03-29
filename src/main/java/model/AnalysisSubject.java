package model;

import java.util.Vector;

import org.jfree.data.time.Year;

import viewer.ResultsStruct;

public class AnalysisSubject extends ViewerSubject {
	private viewer.ResultsStruct _results;
	private String _analysis;
	
	public AnalysisSubject() {
		_results = new ResultsStruct();
		Vector<Double> temp = new Vector<Double>();
		temp.add(6.4);
		temp.add(6.2);
		temp.add(6.1);
		temp.add(6.0);
		temp.add(5.9);
		temp.add(5.8);
		temp.add(5.8);
		temp.add(5.7);
		temp.add(5.6);
		_results.Results.add(temp);
		temp = new Vector<Double>();
		temp.add(7930.0);
		temp.add(8130.0);
		temp.add(8399.0);
		temp.add(8599.0);
		temp.add(9023.0);
		temp.add(9491.0);
		temp.add(9877.0);
		temp.add(10209.0);
		temp.add(10624.0);
		_results.Results.add(temp);
		temp = new Vector<Double>();
		temp.add(3.05);
		temp.add(2.97);
		temp.add(2.93);
		temp.add(2.89);
		temp.add(2.83);
		temp.add(2.8);
		temp.add(2.77);
		temp.add(2.87);
		temp.add(2.92);
		_results.Results.add(temp);
		
		for(int i = 2010; i <= 2018; i++) {
			_results.Years.add(i);
		}
		
		_results.Labels.add("Mortality/1000 births");
		_results.Labels.add("Health Expenditure per Capita");
		_results.Labels.add("Hospital Beds/1000 people");
		
		_results.Units.add("");
		_results.Units.add("US$");
		_results.Units.add("");
		
		_results.Title = "Mortality vs Expenses & Hospital Beds";
		
		notify_viewers();
	}
	
	public AnalysisSubject(String analysis) {
		_analysis = analysis;
		//_results = _analysis.performAnalysis();
		notify_viewers();
	}
	
	public void setAnalysis(String analysis) {
		_analysis = analysis;
		//_results = _analysis.performAnalysis();
		notify_viewers();
	}
	
	public void recalculate() {
		if(_analysis != null) {
			//_results = _analysis.performAnalysis();
			notify_viewers();
		}
	}
	
	public viewer.ResultsStruct getResults() {
		return _results;
	}
}