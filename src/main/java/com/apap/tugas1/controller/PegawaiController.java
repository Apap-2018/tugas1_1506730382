package com.apap.tugas1.controller;

import com.apap.tugas1.model.*;
import com.apap.tugas1.service.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PegawaiController {
	@Autowired
	private PegawaiService pegawaiService;

	@Autowired
	private InstansiService instansiService;
	
	@Autowired
	private ProvinsiService provinsiService;
	
	@Autowired
	private JabatanService jabatanService;
	
	@RequestMapping(value = "/pegawai", method = RequestMethod.GET)
	private String viewPegawaiByNip(String nip, Model model) {
		PegawaiModel pegawai = pegawaiService.getPegawaiDetailByNip(nip);
		InstansiModel instansi = instansiService.getInstansiDetailById(pegawai.getInstansi().getId());
		ProvinsiModel provinsi = provinsiService.getProvinsiDetailById(instansi.getProvinsi().getId());
		int gaji = pegawaiService.getGajiTerbesar(pegawai.getJabatan(), provinsi.getPresentaseTunjangan());
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("gaji", gaji);
		return "view-pegawai";
	}
	
	@RequestMapping(value = "/pegawai/cari", method = RequestMethod.GET)
	private String viewPegawai(Model model) {
		List<PegawaiModel> pegawai = pegawaiService.getAllPegawai();
		model.addAttribute("pegawai", pegawai);
		return "list-pegawai";
	}
	
	@RequestMapping(value = "/pegawai/tambah")
	private String add(Model model) {
		List<ProvinsiModel> listProvinsi = provinsiService.getAllProvinsi();
		model.addAttribute("pegawai", new PegawaiModel());
		model.addAttribute("listProvinsi", listProvinsi);
		return "add-pegawai";
	}
	
	@RequestMapping(value = "/pegawai/ubah", method = RequestMethod.GET)
	private String updatePegawai(long idPegawai, Model model) {
		PegawaiModel archive = pegawaiService.getPegawaiDetailById(idPegawai);
		model.addAttribute("pegawai", archive);
		return "update-pegawai";
	}
	
	@RequestMapping(value = "/pegawai/termuda-tertua")
	private String cariTermudaTertua(@RequestParam(value="idInstansi") long id, Model model) {
		List<PegawaiModel> archive = instansiService.getInstansiDetailById(id).getPegawaiInstansi();
		PegawaiModel pegawaiMuda = pegawaiService.getPegawaiTermuda(archive);
		PegawaiModel pegawaiTua = pegawaiService.getPegawaiTertua(archive);
		model.addAttribute("pegawaiTermuda", pegawaiMuda);
		model.addAttribute("pegawaiTertua", pegawaiTua);
		return "termuda-tertua";
	}
}
