package com.nexusrize.uploadcsv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nexusrize.uploadcsv.model.CsvFileModel;

@Repository
public interface CsvRepository extends JpaRepository<CsvFileModel, Integer> {
	
	@Query("select a from CsvFileModel a where c_name LIKE %:name%")
	List<CsvFileModel> findbycname(String name);

}
