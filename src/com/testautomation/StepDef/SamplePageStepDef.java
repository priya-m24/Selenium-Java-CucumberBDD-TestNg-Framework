package com.testautomation.StepDef;


import com.testautomation.Page.SamplePage;
import com.testautomation.Utility.ExcelHandler;
import com.testautomation.Utility.PropertiesFileReader;
import com.testautomation.Utility.TestDataHandler;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class SamplePageStepDef {
	
	SamplePage samp = new SamplePage();

	PropertiesFileReader obj = new PropertiesFileReader();
	TestDataHandler testdata = new TestDataHandler();

	ExcelHandler handler = new ExcelHandler();
	
	@Given("Hit the Url")
	public void Hit_the_Url() throws Exception {

	samp.launch_samplPage();
	
	    
	}
	
	@Then("Enter the form details")
	public void Enter_the_form_details() throws Exception {

	
	samp.enter_FormDetails();
	
	    
	}

	@Then("Click on submit")
	public void Click_on_submit() throws Exception {
	
		samp.click_Submit();
	
		    
	}
}
