package AnalysisComponent;
import java.util.*;

import WorldBankReader.WorldBankFacade;

public class PolnForestAnalysis extends Analysis {
	private String  AirPolnIndicator = "EN.ATM.PM25.MC.M3";
	private String  ForestAreaIndicator = "AG.LND.FRST.ZS";
	
	private String  AirPolnLabel = "PM2.5 air pollution, mean annual exposure";
	private String  ForestAreaLabel = "Forest Area";
	
	private String  AirPolnUnits = "micrograms/m^3";
	private String  ForestAreaUnits = "%";
	
	private String Title = " PM2.5 air Pollution vs Forest Area"; 
	
	private WorldBankFacade Reader; 
	
	public PolnForestAnalysis(){
		Reader = new WorldBankFacade(); 
	}
	
	public ResultsStruct performAnalysis(ParamStruct params) {
		
		ReaderResults ForestArea = Reader.RequestData(ForestAreaIndicator,params._yearStart,params._yearEnd,params._country); 
		ReaderResults AirPoln = Reader.RequestData(AirPolnIndicator,params._yearStart,params._yearEnd,params._country); 

		Vector<Double> ForestAreaData = ForestArea.NumericData;
		Vector<Double> AirPolnsData = AirPoln.NumericData;
		
		ResultsStruct ResultReturn = new ResultsStruct();
		ResultReturn.Results.add(ForestAreaData);
		ResultReturn.Results.add(AirPolnsData); 
		
		ResultReturn.Years = ForestArea.Years;
		
		ResultReturn.Labels.add(AirPolnLabel); 
		ResultReturn.Labels.add(ForestAreaLabel); 
		
		ResultReturn.Units.add(AirPolnUnits); 
		ResultReturn.Units.add(ForestAreaUnits); 
		
		ResultReturn.Title = Title;
		
		return ResultReturn; 
		
	}
} 