package com.apap.tugas1.service;

import com.apap.tugas1.model.*;
import com.apap.tugas1.repository.*;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService {
	@Autowired
	private PegawaiDB pegawaiDB;
	
	@Override
	public List<PegawaiModel> getAllPegawai() {
		return pegawaiDB.findAll();
	}
	
	@Override
	public List<PegawaiModel> getAllPegawaiInInstansi(List<PegawaiModel> listPegawai, InstansiModel instansi) {
		List<PegawaiModel> listTemp = new ArrayList<PegawaiModel>();
		for (int i = 0; i < listPegawai.size(); i++) {
			PegawaiModel temp = listPegawai.get(i);
			if (temp.getInstansi() == instansi) {
				listTemp.add(temp);
			}
		}
		return listTemp;
	}
	
	@Override
	public List<PegawaiModel> getAllPegawaiInJabatan(List<PegawaiModel> listPegawai, JabatanModel jabatan) {
		List<PegawaiModel> listTemp = new ArrayList<PegawaiModel>();
		for (int i = 0; i < listPegawai.size(); i++) {
			PegawaiModel temp = listPegawai.get(i);
			List<JabatanModel> tempJabatan = temp.getJabatan();
			for (int j = 0; j < tempJabatan.size(); i++) {
				if (tempJabatan.get(j) == jabatan) {
					listTemp.add(temp);
				}
			}
		}
		return listTemp;
	}
	
	@Override
	public PegawaiModel getPegawaiDetailByNip(String nip) {
		return pegawaiDB.findByNip(nip);
	}
	
	@Override
	public PegawaiModel getPegawaiDetailById(long id) {
		return pegawaiDB.findById(id);
	}
	
	@Override
	public void addPegawai(PegawaiModel pegawai) {
		pegawaiDB.save(pegawai);
	}
	
	@Override
	public int getGajiTerbesar(List<JabatanModel> jabatan, double persentaseTunjangan) {
		double gajiAwal = 0;
		for (int i = 0; i < jabatan.size(); i++) {
			double gajiCompared = 0;
			double gajiPokok = jabatan.get(i).getGajiPokok();
			gajiCompared = (gajiPokok * (persentaseTunjangan / 100)) + gajiPokok;
			if (gajiAwal < gajiCompared) {
				gajiAwal = gajiCompared;
			}
		}
		int gaji = (int) gajiAwal;
		return gaji;
	}
	
	@Override
	public PegawaiModel getPegawaiTertua(List<PegawaiModel> pegawai) {
		Collections.sort(pegawai);
		return pegawai.get(0);
	}
	
	@Override
	public PegawaiModel getPegawaiTermuda(List<PegawaiModel> pegawai) {
		Collections.sort(pegawai, Collections.reverseOrder());
		return pegawai.get(0);
	}
	
	@Override
	public String generateDate(Date tanggalLahir) {
		LocalDate localDate = tanggalLahir.toLocalDate();
		String hariLahir = Integer.toString(localDate.getDayOfMonth());
		String bulanLahir = "";
		if (localDate.getMonthValue() < 10) {
			bulanLahir += "0" + Integer.toString(localDate.getMonthValue());
		} else {
			bulanLahir += Integer.toString(localDate.getMonthValue());
		}
		String tahunLahir = Integer.toString(localDate.getYear()).substring(2);
		String tglLahir = hariLahir + bulanLahir + tahunLahir;
		return tglLahir;
	}
	
	@Override
	public String generateLastTwoDigits(String digits) {
		String lastDigit = "";
		int count = 1;
		List<PegawaiModel> listPegawai = getAllPegawai();
		for (int i = 0; i < listPegawai.size(); i++) {
			String compared = listPegawai.get(i).getNip().substring(0, 13);
			if (compared.equals(digits)) {
				count++;
			}
		}
		if (count < 10) {
			lastDigit += "0" + Integer.toString(count);
		} else {
			lastDigit = Integer.toString(count);
		}
		return lastDigit;
	}
}
