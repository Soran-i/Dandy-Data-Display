package model;

import AnalysisComponent.Analysis;
import AnalysisComponent.C02GDPAnalysis;
import AnalysisComponent.CO2EnergyPolnAnalysis;
import AnalysisComponent.EducationAnalysis;
import AnalysisComponent.EducationHealthAnalysis;
import AnalysisComponent.ForestAnalysis;
import AnalysisComponent.HealthMortalityAnalysis;
import AnalysisComponent.HospitalAnalysis;
import AnalysisComponent.PolnForestAnalysis;
import statsVisualiser.gui.ParamStruct;

public class AnalysisConcreteFactory extends AnalysisFactory {
	
	public Analysis GetAnalysis(ParamStruct params) {
		
		if(params._analysis.equals("Emissions vs Energy vs Pollution")) {
			return new CO2EnergyPolnAnalysis();
		}
		else if(params._analysis.equals("Pollution vs Forest")) {
			return new PolnForestAnalysis();
		}
		else if(params._analysis.equals("C02 vs GDP")) {
			return new C02GDPAnalysis();
		}
		else if(params._analysis.equals("Forest")) {
			return new ForestAnalysis();
		}
		else if(params._analysis.equals("Education Expend")) {
			return new EducationAnalysis();
		}
		else if(params._analysis.equals("Hospital Beds vs Health Expend")) {
			return new HospitalAnalysis();
		}
		else if(params._analysis.equals("Health Expend vs Mortality")) {
			return new HealthMortalityAnalysis();
		}
		else if(params._analysis.equals("Education Expend vs Health Expend")) {
			return new EducationHealthAnalysis();
		}
		else {
			return new CO2EnergyPolnAnalysis();
		}
	}
}
