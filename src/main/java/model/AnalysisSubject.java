package model;


import AnalysisComponent.*;
import statsVisualiser.gui.ReaderException;
import statsVisualiser.gui.ParamStruct;


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
	private AnalysisFactory _analyfactory;
	
	/**
	* This constructor creates an empty AnalysisSubject object (no related Analysis)
	*/
	public AnalysisSubject() {}
	
	/**
	* This constructor creates an AnalysisSubject with a defined analysis. 
	* It automatically creates results by performing the provided analysis.
	* 
	* @param params A ParamStruct which contains all of the desired parameters for the Analysis
	*/
	public AnalysisSubject(ParamStruct params) throws ReaderException {
		setParams(params);
		
		try {
			_results = _analysis.performAnalysis(_params);
		} catch (ReaderException e) {
			throw(e);
		}
		
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
		_analyfactory = new AnalysisConcreteFactory(); 
		_analysis = _analyfactory.GetAnalysis(params);
		
//		if(params._analysis.equals("Emissions vs Energy vs Pollution")) {
//			_analysis = new CO2EnergyPolnAnalysis();
//		}
//		else if(params._analysis.equals("Pollution vs Forest")) {
//			_analysis = new PolnForestAnalysis();
//		}
//		else if(params._analysis.equals("C02 vs GDP")) {
//			_analysis = new C02GDPAnalysis();
//		}
//		else if(params._analysis.equals("Forest")) {
//			_analysis = new ForestAnalysis();
//		}
//		else if(params._analysis.equals("Education Expend")) {
//			_analysis = new EducationAnalysis();
//		}
//		else if(params._analysis.equals("Hospital Beds vs Health Expend")) {
//			_analysis = new HospitalAnalysis();
//		}
//		else if(params._analysis.equals("Health Expend vs Mortality")) {
//			_analysis = new HealthMortalityAnalysis();
//		}
//		else if(params._analysis.equals("Education Expend vs Health Expend")) {
//			_analysis = new EducationHealthAnalysis();
//		}
//		else {
//			_analysis = new CO2EnergyPolnAnalysis();
//		}
	}
	
	/**
	* This method performs the currently stored analysis and updates the results and attached viewers.
	*/
	public void recalculate() throws ReaderException {
		if(_analysis != null) {
			
			//try {
				_results = _analysis.performAnalysis(_params);
			//} catch (ReaderException e) {
			//	throw(e);
			//}
			
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