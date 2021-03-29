package AnalysisComponent;
import java.util.*;

import WorldBankReader.WorldBankFacadeMocked;

public class ForestAnalysis extends Analysis {
	private String  ForestAreaIndicator = "AG.LND.FRST.ZS";

	private String  ForestAreaLabel = "Forest Area";
	
	private String  ForestAreaUnits = "%";
	
	private String Title = "Average Forest Area (% of land area)"; 
	
	private WorldBankFacadeMocked Reader; 
	
	public ForestAnalysis(){
		Reader = new WorldBankFacadeMocked(); 
	}
	
	public ResultsStruct performAnalysis(ParamStruct params) {
		
		ReaderResults ForestArea = Reader.RequestData(ForestAreaIndicator,params._yearStart,params._yearEnd,params._country); 

		Vector<Double> ForestAreaData = ForestArea.NumericData;
		
		ResultsStruct ResultReturn = new ResultsStruct();
		ResultReturn.Results.add(ForestAreaData);
		
		ResultReturn.Years = ForestArea.Years;
		
		ResultReturn.Labels.add(ForestAreaLabel); 
		
		ResultReturn.Units.add(ForestAreaUnits); 
		
		ResultReturn.Title = Title;
		
		return ResultReturn; 
		
	}
} 