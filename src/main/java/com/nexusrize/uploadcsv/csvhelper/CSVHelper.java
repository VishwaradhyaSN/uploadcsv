package com.nexusrize.uploadcsv.csvhelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.nexusrize.uploadcsv.model.CsvFileModel;

public class CSVHelper {
	
public static List<CsvFileModel> csvToDb(InputStream is){
	List<CsvFileModel> csvfilemodel=new ArrayList<>();
	 try {
		BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		CSVParser csvParser =new CSVParser(fileReader,CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
		
		List<CSVRecord> records = csvParser.getRecords();
		for (CSVRecord csvRecord : records) {
			CsvFileModel csv=new CsvFileModel(csvRecord.get("c_name"), csvRecord.get("c_batch_no"), csvRecord.get("c_batch_no"), Integer.parseInt(csvRecord.get("n_balance_qty")), csvRecord.get("c_packaging"), csvRecord.get("c_unique_code"), csvRecord.get("c_schemes"), Double.parseDouble(csvRecord.get("n_mrp")), csvRecord.get("c_manufacturer"), Long.parseLong(csvRecord.get("hsn_code")));
			csvfilemodel.add(csv);
	}
		
	 }catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return csvfilemodel;
	
	
	
	
}

}
