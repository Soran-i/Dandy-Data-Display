package AnalysisComponent;
import java.util.*;

import WorldBankReader.ReaderResults;
import WorldBankReader.WorldBankFacade;


public class CO2EnergyPolnAnalysis extends Analysis {
	private String  C02EmissionIndicator = "EN.ATM.CO2E.PC";
	private String  EnergyUseIndicator = "EG.USE.PCAP.KG.OE";
	private String  AirPolnIndicator = "EN.ATM.PM25.MC.M3";
	
	private String  CO2EmissLabel = "C02 emissions";
	private String  EnergyUseLabel = "Energy Use";
	private String  AirPolnLabel = "PM2.5 air pollution, mean annual exposure";
	
	private String  CO2EmissUnits = "t/person";
	private String  EnergyUseUnits = "kg/person";
	private String  AirPolnUnits = "micrograms/m^3";
	
	private String Title = "C02 emissions vs energy use vs PM2.5 air Pollution"; 
	
	private WorldBankFacade Reader; 
	
	public CO2EnergyPolnAnalysis(){
		Reader = new WorldBankFacade(); 
	}
	
	public ResultsStruct performAnalysis(ParamStruct params) throws Exception {
		
		ReaderResults CO2Emis= Reader.RequestData(C02EmissionIndicator,params._yearStart,params._yearEnd,params._country); 
		ReaderResults EnergyUse = Reader.RequestData(EnergyUseIndicator,params._yearStart,params._yearEnd,params._country); 
		ReaderResults AirPoln = Reader.RequestData(AirPolnIndicator,params._yearStart,params._yearEnd,params._country);
		
		Vector<Double> C02EmisData = CO2Emis.NumericData; 
		Vector<Double> EnergyUseData = EnergyUse.NumericData;
		Vector<Double> AirPolnsData = AirPoln.NumericData;
		
		//String CO2EmissUnits = CO2Emis.units; 
		//String EnergyUseUnits =  EnergyUse.units; 
		//String AirPolnUnits =  AirPoln.units; 
		
		ResultsStruct ResultReturn = new ResultsStruct();
		ResultReturn.Results.add(C02EmisData);
		ResultReturn.Results.add(EnergyUseData); 
		ResultReturn.Results.add(AirPolnsData); 
		
		ResultReturn.Years = CO2Emis.Years;
		
		ResultReturn.Labels.add(CO2EmissLabel); 
		ResultReturn.Labels.add(EnergyUseLabel); 
		ResultReturn.Labels.add(AirPolnLabel); 
		
		ResultReturn.Units.add(CO2EmissUnits); 
		ResultReturn.Units.add(EnergyUseUnits); 
		ResultReturn.Units.add(AirPolnUnits); 
		
		ResultReturn.Title = Title;
		
		return ResultReturn; 
		
	}
} 
