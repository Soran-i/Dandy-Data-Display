package AnalysisComponent;
import java.util.*;

import WorldBankReader.ReaderResults;
import WorldBankReader.WorldBankFacade;
import statsVisualiser.gui.ParamStruct;
import statsVisualiser.gui.ReaderException;

/**
 * This class is a concrete analysis that implements the performAnalysis function from the abstract analysis.
 * Fetches the C02 Emissions data, Energy use data and Air Pollution data with no calculations
 * @author stephan
 *
 */
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
	
	/**
	 * Implements the performAnalysis method from the abstract class. 
	 * Requests the C02 Emissions data, Energy Use data and Air Pollution data and forms results structure. 
	 * Also populates the labels, units and title required for the analysis. 
	 *@param params a structure of ParamStruct used to pass parameters to this analysis. 
	 *@return A results structure containing the results of the analyses
	 */
	public ResultsStruct performAnalysis(ParamStruct params) throws ReaderException {
		ReaderResults CO2Emis = new ReaderResults();
		ReaderResults EnergyUse = new ReaderResults();
		ReaderResults AirPoln = new ReaderResults();
		//try {
			CO2Emis = Reader.RequestData(C02EmissionIndicator,params._yearStart,params._yearEnd,params._country);
			EnergyUse = Reader.RequestData(EnergyUseIndicator,params._yearStart,params._yearEnd,params._country); 
			AirPoln = Reader.RequestData(AirPolnIndicator,params._yearStart,params._yearEnd,params._country);
		//} catch (ReaderException e) {
		//	throw e;
		//} 
			
		
		Vector<Double> C02EmisData = CO2Emis.NumericData; 
		Vector<Double> EnergyUseData = EnergyUse.NumericData;
		Vector<Double> AirPolnsData = AirPoln.NumericData;
		
		if (Reader.checkIfAllNull(C02EmisData) && Reader.checkIfAllNull(EnergyUseData) && Reader.checkIfAllNull(AirPolnsData)) {
			throw new ReaderException("All elements in analysis are zero");
		}
		
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
