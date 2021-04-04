package AnalysisComponent;
import java.util.*;

import ExceptionsPack.ReaderException;
import WorldBankReader.ReaderResults;
import WorldBankReader.WorldBankFacade;
import statsVisualiser.gui.ParamStruct;

/**
 * This class is a concrete analysis that implements the performAnalysis function from the abstract analysis.
 * Fetches health expenditure per capita and the indant mortality rate and returns a results structure
 * @author stephan
 *
 */
public class HealthMortalityAnalysis extends Analysis {
	private String  HealthExpendIndicator = "SH.XPD.CHEX.PC.CD";
	private String  MortalityIndicator = "SP.DYN.IMRT.IN";
	
	private String  HealthExpendLabel = "Health Expenditure per cap";
	private String  MortalityLabel = "Mortality Rate, Infant";
	
	private String  HealthExpendUnits = "US$";
	private String  MortalityUnits = "1/1000 live births";
	
	private String Title = "Current Health Expenditure per capital vs Mortality rate, infant";
	
	private WorldBankFacade Reader; 
	
	/**
	 * A constructor for the analysis to create a reader object
	 */
	public HealthMortalityAnalysis(){
		Reader = new WorldBankFacade(); 
	}
	
	/**
	 * Implements the performAnalysis method from the abstract class. 
	 * Fetches health expenditure per capita and the indant mortality rate and returns a results structure
	 * Also populates the labels, units and title required for the analysis. 
	 *@param params a structure of ParamStruct used to pass parameters to this analysis. 
	 *@return A results structure containing the results of the analyses
	 */
	public ResultsStruct performAnalysis(ParamStruct params) throws ReaderException {
		
		ReaderResults HealthExpend = new ReaderResults();
		ReaderResults Mortality = new ReaderResults();
		//try {
			HealthExpend= Reader.RequestData(HealthExpendIndicator,params._yearStart,params._yearEnd,params._country); 
			Mortality = Reader.RequestData(MortalityIndicator,params._yearStart,params._yearEnd,params._country); 
		//} catch (ReaderException e) {
		//	throw e;
		//} 
		
		Vector<Double> HealthExpendData = HealthExpend.NumericData; 
		Vector<Double> MortalityData = Mortality.NumericData;
		
		if (Reader.checkIfAllNull(HealthExpendData) && Reader.checkIfAllNull(MortalityData)) {
			throw new ReaderException("All elements in analysis are zero");
		}
		
		ResultsStruct ResultReturn = new ResultsStruct();
		ResultReturn.Results.add(HealthExpendData);
		ResultReturn.Results.add(MortalityData); 
		
		ResultReturn.Years = HealthExpend.Years;
		
		ResultReturn.Labels.add(HealthExpendLabel); 
		ResultReturn.Labels.add(MortalityLabel); 
		
		ResultReturn.Units.add(HealthExpendUnits); 
		ResultReturn.Units.add(MortalityUnits); 
		
		ResultReturn.Title = Title;
		
		return ResultReturn; 
		
	}
} 