package AnalysisComponent;
import java.text.DecimalFormat;
import java.util.*;

import ExceptionsPack.ReaderException;
import WorldBankReader.ReaderResults;
import WorldBankReader.WorldBankFacade;
import statsVisualiser.gui.ParamStruct;


/**
 * This class is a concrete analysis that implements the performAnalysis function from the abstract analysis.
 * Fetches the forest Area data and coverts the percentages to decimals before returning the results structure
 * @author stephan
 *
 */
public class ForestAnalysis extends Analysis {
	private String  ForestAreaIndicator = "AG.LND.FRST.ZS";

	private String  ForestAreaLabel = "Forest Area";
	
	private String  ForestAreaUnits = "%";
	
	private String Title = "Average Forest Area (% of land area)"; 
	
	private WorldBankFacade Reader; 
	
	/**
	 * A constructor for the analysis to create a reader object
	 */
	public ForestAnalysis(){
		Reader = new WorldBankFacade(); 
	}
	
	/**
	 * Implements the performAnalysis method from the abstract class. 
	 * Requests the forest area data for the requested parameters
	 * Also populates the labels, units and title required for the analysis. 
	 *@param params a structure of ParamStruct used to pass parameters to this analysis. 
	 *@return A results structure containing the results of the analyses
	 */
	public ResultsStruct performAnalysis(ParamStruct params) throws ReaderException {
		
		ReaderResults ForestArea = new ReaderResults();
		//try {
			ForestArea = Reader.RequestData(ForestAreaIndicator,params._yearStart,params._yearEnd,params._country);
		//} catch (ReaderException e) {
		//	throw e;
		//} 

		Vector<Double> ForestAreaData = ForestArea.NumericData;
		
		if (Reader.checkIfAllNull(ForestAreaData)) {
			throw new ReaderException("All elements in analysis are zero");
		}
		
		ForestAreaData = DivBy100(ForestAreaData);
		
		ResultsStruct ResultReturn = new ResultsStruct();
		ResultReturn.Results.add(ForestAreaData);
		
		ResultReturn.Years = ForestArea.Years;
		
		ResultReturn.Labels.add(ForestAreaLabel); 
		
		ResultReturn.Units.add(ForestAreaUnits); 
		
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
		
		DecimalFormat df = new DecimalFormat("#.#######");
		
		for(int i = 0; i < Element1.size(); i++)
		{
			Ratio.add(Double.valueOf(df.format(Element1.get(i)/100.)));
		}
		
		return Ratio; 
	}	

} 