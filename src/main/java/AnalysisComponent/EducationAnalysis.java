package AnalysisComponent;
import java.text.DecimalFormat;
import java.util.*;

import ExceptionsPack.ReaderException;
import WorldBankReader.ReaderResults;
import WorldBankReader.WorldBankFacade;
import statsVisualiser.gui.ParamStruct;

/**
 * This class is a concrete analysis that implements the performAnalysis function from the abstract analysis.
 * fetches the education data and returns a results structure
 * @author stephan
 *
 */
public class EducationAnalysis extends Analysis {
	private String  EducationIndicator = "SE.XPD.TOTL.GD.ZS";

	private String  EducationLabel = "Education expenditure";
	
	private String  EducationUnits = "%";
	
	private String Title = "Government Expenditure on education, total (% of GDP)"; 
	
	private WorldBankFacade Reader; 
	
	/**
	 * A constructor for the analysis to create a reader object
	 */
	public EducationAnalysis(){
		Reader = new WorldBankFacade(); 
	}
	
	/**
	 * Implements the performAnalysis method from the abstract class. 
	 * Requests the Education data
	 * Also populates the labels, units and title required for the analysis. 
	 *@param params a structure of ParamStruct used to pass parameters to this analysis. 
	 *@return A results structure containing the results of the analyses
	 */
	public ResultsStruct performAnalysis(ParamStruct params) throws ReaderException {
		
		ReaderResults Education = new ReaderResults();
		//try {
			Education = Reader.RequestData(EducationIndicator,params._yearStart,params._yearEnd,params._country);
		//} catch (ReaderException e) {
		//	throw e;
		//} 

		Vector<Double> EducationData = Education.NumericData;
		
		if (Reader.checkIfAllNull(EducationData)) {
			throw new ReaderException("All elements in analysis are zero");
		}
		
		EducationData =  DivBy100(EducationData); 
		
		ResultsStruct ResultReturn = new ResultsStruct();
		ResultReturn.Results.add(EducationData);
		
		ResultReturn.Years = Education.Years;
		
		ResultReturn.Labels.add(EducationLabel); 
		
		ResultReturn.Units.add(EducationUnits); 
		
		ResultReturn.Title = Title;
		
		return ResultReturn; 
		
	}
	
	
	/**
	 * Method to divide all vector elements by 100 to turn a percentage to a decimal
	 * @param Element1 a vector of percentages
	 * @return a vector of decimals that were percentages
	 */
	private Vector<Double> DivBy100(Vector<Double> Element1) {
		Vector<Double> Ratio = new Vector<Double>();
		
		DecimalFormat df = new DecimalFormat("#.####");
		
		for(int i = 0; i < Element1.size(); i++)
		{
			Ratio.add(Double.valueOf(df.format(Element1.get(i)/100.)));
		}
		
		return Ratio; 
	}
	
} 