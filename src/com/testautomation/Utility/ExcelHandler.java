package com.testautomation.Utility;

import java.util.Map;
import java.util.TreeMap;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class ExcelHandler 
{
	
	Fillo fillo =new Fillo(); 
	//Connection conn=null;
	
	public  Map<String,String> getTestDataInMap(String testDataFile,String sheetName,String testCaseId)
	{
		Map<String,String> TestDataInMap=new TreeMap<String,String>();		
		String query=String.format("SELECT * FROM %s WHERE Run='Yes' and TestCaseId='%s'",sheetName,testCaseId);
		
		Recordset recordset=null;
		try
		{
			
			recordset=fillo.getConnection(testDataFile).executeQuery(query);
		//	recordset=((com.codoid.products.fillo.Connection) conn).executeQuery(query);
			while(recordset.next())
			{
				for(String field:recordset.getFieldNames())
				{
					TestDataInMap.put(field, recordset.getField(field));
				}
			}
			//conn.close();
		}
		catch(FilloException e)
		{
			e.printStackTrace();	
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			//conn.close();
		}
		
		return TestDataInMap;		
	}
	
	
	public  void UpdateTestResultsToExcel(String testDataFilePath,String sheetName,String tcstatus,String testCaseId,String actualTitle)
	{
		
		try{
			
			String query=String.format("UPDATE %s SET actualTitle='%s',TestCaseStatus='%s' WHERE TestCaseID='%s'", sheetName,actualTitle,tcstatus,testCaseId);
			new Fillo().getConnection(testDataFilePath).executeUpdate(query);
			

			//conn.executeQuery(query);
		} catch(FilloException e){
			e.printStackTrace();
		}		
	}

}
