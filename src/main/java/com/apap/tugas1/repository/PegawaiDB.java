package com.apap.tugas1.repository;

import java.math.BigInteger;
import java.util.List;

import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PegawaiDB extends JpaRepository<PegawaiModel, Long> {
	PegawaiModel findByNip(String nip);
	PegawaiModel findById(long id);
	List<PegawaiModel> findAllByJabatan(JabatanModel jabatan);
	List<PegawaiModel> findAllByInstansi(InstansiModel instansi);
	List<PegawaiModel> findAllByOrderByTanggalLahirAsc();
	List<PegawaiModel> findAllByOrderByTanggalLahirDesc();
	
}
