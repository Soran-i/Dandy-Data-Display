
package AnalysisComponent;
import java.util.*;

import WorldBankReader.ReaderResults;
import WorldBankReader.WorldBankFacade;
import statsVisualiser.gui.ParamStruct;
import statsVisualiser.gui.ReaderException;

/**
 * This class is a concrete analysis that implements the performAnalysis function from the abstract analysis.
 * Fetches Hospital Beds data and Health Care expenditure data and calculates a the ratio between the two.
 * @author stephan
 *
 */
public class HospitalAnalysis extends Analysis {
	private String  HospitalBedsIndicator = "SH.MED.BEDS.ZS";
	private String  HealthExpendIndicator = "SH.XPD.CHEX.PC.CD";
	
	private String  Label = "Ratio of hospital beds to health care expenditure";
	
	private String  Units = "beds/US$";
	
	private String Title = "Ratio of Hospital beds and Current health expenditure"; 
	
	private WorldBankFacade Reader; 
	
	/**
	 * A constructor for the analysis to create a reader object
	 */
	public HospitalAnalysis(){
		Reader = new WorldBankFacade(); 
	}
	
	/**
	 * Implements the performAnalysis method from the abstract class. 
	 * Fetches Hospital Beds data and Health Care expenditure data and calculates a the ratio between the two.
	 * Also populates the labels, units and title required for the analysis. 
	 *@param params a structure of ParamStruct used to pass parameters to this analysis. 
	 *@return A results structure containing the results of the analyses
	 */
	public ResultsStruct performAnalysis(ParamStruct params) throws ReaderException {
		
		ReaderResults HospitalBeds = new ReaderResults();
		ReaderResults HealthExpend = new ReaderResults();
		try {
			HospitalBeds= Reader.RequestData(HospitalBedsIndicator,params._yearStart,params._yearEnd,params._country); 
			HealthExpend = Reader.RequestData(HealthExpendIndicator,params._yearStart,params._yearEnd,params._country); 
		} catch (ReaderException e) {
			throw e;
		} 

		Vector<Double> HospitalBedsData = HospitalBeds.NumericData;
		Vector<Double> HealthExpendData = HealthExpend.NumericData;
		
		Vector<Double> Ratio = ConvertCalculateRatio(HospitalBedsData,HealthExpendData);
		
		ResultsStruct ResultReturn = new ResultsStruct();
		ResultReturn.Results.add(Ratio);
		
		ResultReturn.Years = HospitalBeds.Years;
		
		ResultReturn.Labels.add(Label); 
		
		ResultReturn.Units.add(Units); 
		 
		ResultReturn.Title = Title;
		
		return ResultReturn; 
		
	}
	
	/**
	 * @param Element1 vector of elements to be divided
	 * @param Element2 vector of elements to divide Element1 by 
	 * @return A vector containing the ratio of Element1/(Element2*1000)
	 */
	private Vector<Double> ConvertCalculateRatio(Vector<Double> Element1,Vector<Double>Element2) {
		Vector<Double> Ratio = new Vector<Double>();
		
		for(int i = 0; i < Element1.size(); i++)
		{
			if (Element2.get(i) != 0){
				Ratio.add(Element1.get(i)/(Element2.get(i)*1000));
			}
			else {
				Ratio.add(0.0);
			}
		}
		
		return Ratio; 
	}
} 