package com.nexusrize.uploadcsv.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nexusrize.uploadcsv.model.CsvFileModel;
import com.nexusrize.uploadcsv.service.CSVService;

@RestController
@RequestMapping("/api/csv")
public class MainController {
	
	@Autowired
	CSVService csvservce;
	
	@PostMapping("/uploadCSV")
	public ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file ) {
		
		String save = csvservce.save(file);
		if(save.equals("sucess")) {
			return ResponseEntity.status(HttpStatus.OK).body("saveed sucessfully");
		}else {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("not saved");
		}
	}
	
	@GetMapping("/searchMedicine")
	public ResponseEntity<List<CsvFileModel>> searchMedicine(@RequestParam String search){
		List<CsvFileModel> find = csvservce.find(search);
	
			return ResponseEntity.status(HttpStatus.OK).body(find);	
		
	}
	
	@GetMapping("/getbookbyid/{id}")
	public ResponseEntity<Optional<CsvFileModel>> serchbyid(@PathVariable int id){
		Optional<CsvFileModel> findbydeatilswithID = csvservce.findbydeatilswithID(id);
		System.out.println(findbydeatilswithID);
		return ResponseEntity.status(HttpStatus.OK).body(findbydeatilswithID);
		
	}
	
	
	@PostMapping("/uploaddata")
	public ResponseEntity<String> saveDetails(@RequestBody CsvFileModel csvmodel){
		System.out.println(csvmodel.getC_batch_no());
		int date = csvservce.savetheDate(csvmodel);
		if (date!=0) {
			return ResponseEntity.status(HttpStatus.OK).body("the data is saved");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("the data is not saved");

		}
		
		
	}
	
	

}
