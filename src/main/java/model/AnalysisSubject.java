package model;

import java.util.Vector;

import AnalysisComponent.*;


/**
* This class is an concrete subject which contains the results of an analysis for its Viewers 
* to observe. 
* <p>
* AnalysisSubject inherits from ViewerSubject.
* It holds an Analysis type object which can perform an analysis and store the results within
* the AnalysisSubject for reference by the attached observers.
*
* @author	Matthew Da Silva
*/
public class AnalysisSubject extends ViewerSubject {
	private ResultsStruct _results;
	private ParamStruct _params;
	private Analysis _analysis;
	
	/**
	* This constructor creates an empty AnalysisSubject object (no related Analysis)
	*/
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
	
	/**
	* This constructor creates an AnalysisSubject with a defined analysis. 
	* It automatically creates results by performing the provided analysis.
	* 
	* @param params A ParamStruct which contains all of the desired parameters for the Analysis
	*/
	public AnalysisSubject(ParamStruct params) {
		setParams(params);
		//_results = _analysis.performAnalysis(_params);
		notify_viewers();
	}
	
	
	/**
	* This method sets the subject's parameters.
	* <p>
	* Parameters define the analysis type and are passed to said analysis when obtaining the results of the analysis.
	* 
	* @param params A ParamStruct which contains all of the desired parameters for the Analysis
	*/
	public void setParams(ParamStruct params) {
		_params = params;
		
		if(params._analysis == "Emissions vs Energy vs Pollution") {
			_analysis = new CO2EnergyPolnAnalysis();
		}
		else if(params._analysis == "Pollution vs Forest") {
			_analysis = new PolnForestAnalysis();
		}
		else if(params._analysis == "C02 vs GDP") {
			_analysis = new C02GDPAnalysis();
		}
		else if(params._analysis == "Forest") {
			_analysis = new ForestAnalysis();
		}
		else if(params._analysis == "Education Expend") {
			_analysis = new EducationAnalysis();
		}
		else if(params._analysis == "Hospital Beds vs Health Expend") {
			_analysis = new HospitalAnalysis();
		}
		else if(params._analysis == "Health Expend vs Mortality") {
			_analysis = new HealthMortalityAnalysis();
		}
		else if(params._analysis == "Education Expend vs Health Expend") {
			_analysis = new EducationHealthAnalysis();
		}
		else {
			_analysis = new CO2EnergyPolnAnalysis();
		}
	}
	
	/**
	* This method performs the currently stored analysis and updates the results and attached viewers.
	*/
	public void recalculate() {
		if(_analysis != null) {
			//_results = _analysis.performAnalysis(_params);
			notify_viewers();
		}
	}
	
	/**
	* This method returns the stored results obtained from the analysis.
	*/
	public ResultsStruct getResults() {
		return _results;
	}
}