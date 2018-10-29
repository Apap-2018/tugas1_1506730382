package com.apap.tugas1.repository;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.ProvinsiModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface InstansiDB extends JpaRepository<InstansiModel, Long> {
	InstansiModel findById(long id);
	List<InstansiModel> findByProvinsi(ProvinsiModel provinsi);
}

