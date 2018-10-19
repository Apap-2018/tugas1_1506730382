package com.apap.tugas1.controller;

import com.apap.tugas1.model.*;
import com.apap.tugas1.service.*;

import java.util.List;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JabatanController {
	@Autowired
	private JabatanService jabatanService;
	
	@Autowired
	private PegawaiService pegawaiService;

	@Autowired
	private InstansiService instansiService;
	
	@Autowired
	private ProvinsiService provinsiService;
	
	@RequestMapping("/")
	private String home(Model model) {
		List<JabatanModel> jabatanArchive = jabatanService.getAllJabatan();
		List<InstansiModel> instansiArchive = instansiService.getAllInstansi();
		model.addAttribute("listJabatan", jabatanArchive);
		model.addAttribute("listInstansi", instansiArchive);
		return "home";
	}
	
	@RequestMapping(value = "/jabatan/view", method = RequestMethod.GET)
	private String view(long idJabatan, Model model) {
		JabatanModel result = jabatanService.getJabatanDetailById(idJabatan);
		int jumlahPegawai = jabatanService.getJumlahPegawai(result);
		model.addAttribute("jabatan", result);
		model.addAttribute("jumlahPegawai", jumlahPegawai);
		return "view-jabatan";
	}
	
	@RequestMapping(value = "/jabatan/tambah")
	private String add(Model model) {
		List<ProvinsiModel> provinsi = provinsiService.getAllProvinsi(); 
		model.addAttribute("jabatan", new JabatanModel());
		model.addAttribute("provinsiList", provinsi);
		return "add-jabatan";
	}
	
	@RequestMapping(value = "/jabatan/tambah", method = RequestMethod.POST)
	private String addJabatanSubmit(@ModelAttribute JabatanModel jabatan, Model model) {
		jabatanService.addJabatan(jabatan);
		model.addAttribute("jabatan", jabatan);
		return "jabatan-added";
	}
	
	@RequestMapping(value = "/jabatan/ubah", method = RequestMethod.GET)
	private String updateJabatan(long idJabatan, Model model) {
		JabatanModel archive = jabatanService.getJabatanDetailById(idJabatan);
		model.addAttribute("jabatan", archive);
		return "update-jabatan";
	}
	
	@RequestMapping(value = "/jabatan/ubah", method = RequestMethod.POST)
	private String updateJabatan(@ModelAttribute JabatanModel jabatan , Model model) {
		jabatanService.addJabatan(jabatan);
		model.addAttribute("jabatan", jabatan);
        return "jabatan-updated";
	}
	
	@RequestMapping(value = "/jabatan/hapus")
	private String deleteJabatan(@RequestParam("idJabatan") long idJabatan, Model model) {
		JabatanModel jabatan = jabatanService.getJabatanDetailById(idJabatan);
		model.addAttribute("jabatan", jabatan);
		if (!jabatan.getPegawai().isEmpty()) {
			return "jabatan-not-deleted";
		} else {
			jabatanService.deleteJabatan(jabatan);
			return "jabatan-deleted";
		}
	}

	@RequestMapping(value = "/jabatan/viewall")
	private String viewall(Model model) {
		List<JabatanModel> jabatan = jabatanService.getAllJabatan();
		HashMap<JabatanModel, Integer> jabatanDanJmlhPegawai = new HashMap<JabatanModel, Integer>();
		for (int i = 0; i < jabatan.size(); i++) {
			int jmlhPegawai = 0;
			jmlhPegawai = jabatanService.getJumlahPegawai(jabatan.get(i));
			jabatanDanJmlhPegawai.put(jabatan.get(i), jmlhPegawai);
		}
		model.addAttribute("jabatan", jabatanDanJmlhPegawai);
		return "view-all-jabatan";
	}
}
