package com.apap.tugas1.model;

import java.io.Serializable;
import java.util.List;
import java.util.Comparator;
import java.sql.Date;
import java.lang.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pegawai")
public class PegawaiModel implements Serializable, Comparable<PegawaiModel> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "nip", nullable = false, unique = true)
	private String nip;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "nama", nullable = false)
	private String nama;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "tempat_lahir", nullable = false)
	private String tempatLahir;
	
	@NotNull
	@Column(name = "tanggal_lahir")
	private Date tanggalLahir;
	
	@NotNull
	@Size(max = 255)
	@Column(name = "tahun_masuk", nullable = false)
	private String tahunMasuk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_instansi", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private InstansiModel instansi;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "jabatan_pegawai", 
			joinColumns = @JoinColumn(name = "id_pegawai", referencedColumnName = "id"), 
			inverseJoinColumns = @JoinColumn(name = "id_jabatan", referencedColumnName = "id")
	)
    private List<JabatanModel> jabatan;
	
	public PegawaiModel() {
		
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	
	public void setNip(String nip) {
		this.nip = nip;
	}
	
	public String getNip() {
		return nip;
	}
	
	public void setNama(String nama) {
		this.nama = nama;
	}
	
	public String getNama() {
		return nama;
	}
	
	public void setTempatLahir(String tempatLahir) {
		this.tempatLahir = tempatLahir;
	}
	
	public String getTempatLahir() {
		return tempatLahir;
	}

	public void setTanggalLahir(Date tanggalLahir) {
		this.tanggalLahir = tanggalLahir;
	}
	
	public Date getTanggalLahir() {
		return tanggalLahir;
	}
	
	public void setTahunMasuk(String tahunMasuk) {
		this.tahunMasuk = tahunMasuk;
	}
	
	public String getTahunMasuk() {
		return tahunMasuk;
	}
	
	public void setInstansi(InstansiModel instansi) {
		this.instansi = instansi;
	}
	
	public InstansiModel getInstansi() {
		return instansi;
	}
	
	public void setJabatan(List<JabatanModel> jabatan) {
		this.jabatan = jabatan;
	}
	
	public List<JabatanModel> getJabatan() {
		return jabatan;
	}
	
	public int compareTo(PegawaiModel o) {
	    return getTanggalLahir().compareTo(o.getTanggalLahir());
	  }
}
