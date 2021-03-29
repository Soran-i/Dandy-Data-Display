package AnalysisComponent;
import java.util.*;

import WorldBankReader.WorldBankFacadeMocked;


public class HealthMortalityAnalysis extends Analysis {
	private String  HealthExpendIndicator = "SH.XPD.CHEX.PC.CD";
	private String  MortalityIndicator = "SP.DYN.IMRT.IN";
	
	private String  HealthExpendLabel = "C02 emissions";
	private String  MortalityLabel = "Energy Use";
	
	private String  HealthExpendUnits = "US$";
	private String  MortalityUnits = "1/1000 live births";
	
	private String Title = "Current Health Expenditure per capital vs Mortality rate, infant";
	
	private WorldBankFacadeMocked Reader; 
	
	public HealthMortalityAnalysis(){
		Reader = new WorldBankFacadeMocked(); 
	}
	
	public ResultsStruct performAnalysis(ParamStruct params) {
		
		ReaderResults HealthExpend= Reader.RequestData(HealthExpendIndicator,params._yearStart,params._yearEnd,params._country); 
		ReaderResults Mortality = Reader.RequestData(MortalityIndicator,params._yearStart,params._yearEnd,params._country); 
		
		Vector<Double> HealthExpendData = HealthExpend.NumericData; 
		Vector<Double> MortalityData = Mortality.NumericData;
		
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