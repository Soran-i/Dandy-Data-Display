package AnalysisComponent;
import java.util.*;

import WorldBankReader.ReaderResults;
import WorldBankReader.WorldBankFacade;

public class C02GDPAnalysis extends Analysis {

	private String  C02EmissionIndicator = "EN.ATM.CO2E.PC";
	private String  GDPIndicator = "NY.GDP.PCAP.CD";
	
	private String  Label = "C02 Emissions and GDP Per capita";
	
	private String  Units = "metric tons per capita/US$";
	
	private String Title = "Ratio of C02 emissions and GDP per capita"; 
	
	private WorldBankFacade Reader; 
	
	public C02GDPAnalysis(){
		Reader = new WorldBankFacade(); 
	}
	
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