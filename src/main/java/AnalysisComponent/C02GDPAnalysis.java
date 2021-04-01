package AnalysisComponent;
import java.util.*;

import WorldBankReader.ReaderResults;
import WorldBankReader.WorldBankFacade;

/**
 * This class is a concrete analysis that implements the performAnalysis function from the abstract analysis.
 * Fetches the C02 Emissions data and GDP data and forms a results structure.
 * @author stephan
 *
 */
public class C02GDPAnalysis extends Analysis {

	private String  C02EmissionIndicator = "EN.ATM.CO2E.PC";
	private String  GDPIndicator = "NY.GDP.PCAP.CD";
	
	private String  Label = "C02 Emissions and GDP Per capita";
	
	private String  Units = "metric tons per capita/US$";
	
	private String Title = "Ratio of C02 emissions and GDP per capita"; 
	
	private WorldBankFacade Reader; 
	
	/**
	 * A constructor for the analysis to create a reader object
	 */
	public C02GDPAnalysis(){
		Reader = new WorldBankFacade(); 
	}
	
	/**
	 * Implements the performAnalysis method from the abstract class. 
	 * Requests the C02 Emissions data, Energy Use data and Air Pollution data and forms results structure. 
	 * Also populates the labels, units and title required for the analysis. 
	 *@param params a structure of ParamStruct used to pass parameters to this analysis. 
	 *@return A results structure containing the results of the analyses
	 */
	public ResultsStruct performAnalysis(ParamStruct params) throws Exception {
		
		ReaderResults C02Emissions = Reader.RequestData(C02EmissionIndicator,params._yearStart,params._yearEnd,params._country); 
		ReaderResults GDP = Reader.RequestData(GDPIndicator,params._yearStart,params._yearEnd,params._country); 

		Vector<Double> C02EmissionsData = C02Emissions.NumericData;
		Vector<Double> GDPData = GDP.NumericData;
		
		Vector<Double> Ratio = CalculateRatio(C02EmissionsData,GDPData);
		
		ResultsStruct ResultReturn = new ResultsStruct();
		ResultReturn.Results.add(Ratio);
		
		ResultReturn.Years = C02Emissions.Years;
		
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
	private Vector<Double> CalculateRatio(Vector<Double> Element1,Vector<Double>Element2) {
		Vector<Double> Ratio = new Vector<Double>();
		
		for(int i = 0; i < Element1.size(); i++)
		{
			if (Element2.get(i) != 0){
				Ratio.add( Element1.get(i)/Element2.get(i));
			}
			else {
				Ratio.add(0.0);
			}
		}
		
		return Ratio; 
	}
} 