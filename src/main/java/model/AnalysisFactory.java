package model;

import AnalysisComponent.Analysis;
import statsVisualiser.gui.ParamStruct;

public abstract class AnalysisFactory {
	abstract public Analysis GetAnalysis(ParamStruct params);
}
