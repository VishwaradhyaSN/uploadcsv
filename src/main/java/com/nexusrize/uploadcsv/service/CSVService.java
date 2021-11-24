package com.nexusrize.uploadcsv.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.nexusrize.uploadcsv.csvhelper.CSVHelper;
import com.nexusrize.uploadcsv.model.CsvFileModel;
import com.nexusrize.uploadcsv.repository.CsvRepository;

@Service
public class CSVService {
	
	@Autowired
	CsvRepository repository;
	
	@Transactional
	public String save(MultipartFile file) {
		List<CsvFileModel> saveAll = null;
		try {
			List<CsvFileModel> csvToDb = CSVHelper.csvToDb(file.getInputStream());
			saveAll = repository.saveAll(csvToDb);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (saveAll.size()>0) {
			return "sucess";
		} else {
			return "failed";

		}
		
	}
	
	public List<CsvFileModel> find(String serchparm) {
		List<CsvFileModel> findbycname = repository.findbycname(serchparm);
		return findbycname;
	}
	
	
	public Optional<CsvFileModel> findbydeatilswithID(int serchbyid){
		Optional<CsvFileModel> id = repository.findById(serchbyid);
		return id;
		
	}
	
	@Transactional
	public int savetheDate(CsvFileModel csv) {
		CsvFileModel save = repository.save(csv);
		int id = save.getId();
		//System.out.println("the id is "+id);
		return id;
	}

}
