package com.example.demo.dtos;

import java.util.Map;

public class CalculationRequest {
	 private String formula;
	    private Map<String, Double> values;
		public String getFormula() {
			return formula;
		}
		public void setFormula(String formula) {
			this.formula = formula;
		}
		public Map<String, Double> getValues() {
			return values;
		}
		public void setValues(Map<String, Double> values) {
			this.values = values;
		}
	    
	    
}
