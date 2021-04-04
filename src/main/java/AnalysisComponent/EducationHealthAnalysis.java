package AnalysisComponent;
import java.util.*;

import ExceptionsPack.ReaderException;
import WorldBankReader.ReaderResults;
import WorldBankReader.WorldBankFacade;
import statsVisualiser.gui.ParamStruct;

/**
 * This class is a concrete analysis that implements the performAnalysis function from the abstract analysis.
 * fetches the education data and health expenditure data, calculates the ratio and returns a results structure
 * @author stephan
 *
 */
public class EducationHealthAnalysis extends Analysis {
	private String  EducationIndicator = "SE.XPD.TOTL.GD.ZS";
	private String  HealthExpendGDPIndicator = "SH.XPD.CHEX.GD.ZS";

	private String  Label = "Ratio of Health Expenditure and Current Health Expenditure";
	
	private String  Units = "%/%";
	
	private String Title = "Ratio of Government expenditure on education, total and Current health expenditure"; 
	
	private WorldBankFacade Reader; 
	
	/**
	 * A constructor for the analysis to create a reader object
	 */
	public EducationHealthAnalysis(){
		Reader = new WorldBankFacade(); 
	}
	
	/**
	 * Implements the performAnalysis method from the abstract class. 
	 * Requests the education data and health expenditure as a percentage of GDP and calculates the ratio between the two
	 * Also populates the labels, units and title required for the analysis. 
	 *@param params a structure of ParamStruct used to pass parameters to this analysis. 
	 *@return A results structure containing the results of the analyses
	 */
	public ResultsStruct performAnalysis(ParamStruct params) throws ReaderException {
		
		ReaderResults Education = new ReaderResults();
		ReaderResults HealthExpendGDP = new ReaderResults();
		//try {
			Education = Reader.RequestData(EducationIndicator,params._yearStart,params._yearEnd,params._country);
			HealthExpendGDP = Reader.RequestData(HealthExpendGDPIndicator,params._yearStart,params._yearEnd,params._country); 
		//} catch (ReaderException e) {
		//	throw e;
		//} 

		Vector<Double> EducationData = Education.NumericData;
		Vector<Double> HealthExpendGDPData = HealthExpendGDP.NumericData;

		if (Reader.checkIfAllNull(EducationData) && Reader.checkIfAllNull(HealthExpendGDPData)) {
			throw new ReaderException("All elements in analysis are zero");
		}
		
		Vector<Double> Ratio = ConvertCalculateRatio(EducationData,HealthExpendGDPData);
		
		ResultsStruct ResultReturn = new ResultsStruct();
		ResultReturn.Results.add(Ratio);
		
		ResultReturn.Years = Education.Years;
		
		ResultReturn.Labels.add(Label); 
		
		ResultReturn.Units.add(Units); 
		 
		ResultReturn.Title = Title;
		
		return ResultReturn; 
		
	}
	
	/**
	 * @param Element1 vector of elements to be divided
	 * @param Element2 vector of elements to divide Element1 by
	 * @return A vector containing the ratio of Element1/Element2
	 */
	private Vector<Double> ConvertCalculateRatio(Vector<Double> Element1,Vector<Double>Element2) {
		Vector<Double> Ratio = new Vector<Double>();
		
		for(int i = 0; i < Element1.size(); i++)
		{
			if (Element2.get(i) != 0){
				Ratio.add(Element1.get(i)/Element2.get(i));
			}
			else {
				Ratio.add(0.0);
			}
		}
		
		return Ratio; 
	}
	
} 
