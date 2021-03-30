package AnalysisComponent;
import java.text.DecimalFormat;
import java.util.*;

import WorldBankReader.ReaderResults;
import WorldBankReader.WorldBankFacade;

public class ForestAnalysis extends Analysis {
	private String  ForestAreaIndicator = "AG.LND.FRST.ZS";

	private String  ForestAreaLabel = "Forest Area";
	
	private String  ForestAreaUnits = "%";
	
	private String Title = "Average Forest Area (% of land area)"; 
	
	private WorldBankFacade Reader; 
	
	public ForestAnalysis(){
		Reader = new WorldBankFacade(); 
	}
	
	public ResultsStruct performAnalysis(ParamStruct params) throws Exception {
		
		ReaderResults ForestArea = Reader.RequestData(ForestAreaIndicator,params._yearStart,params._yearEnd,params._country); 

		Vector<Double> ForestAreaData = ForestArea.NumericData;
		ForestAreaData = DivBy100(ForestAreaData);
		
		ResultsStruct ResultReturn = new ResultsStruct();
		ResultReturn.Results.add(ForestAreaData);
		
		ResultReturn.Years = ForestArea.Years;
		
		ResultReturn.Labels.add(ForestAreaLabel); 
		
		ResultReturn.Units.add(ForestAreaUnits); 
		
		ResultReturn.Title = Title;
		
		return ResultReturn; 
		
	}
	
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