package com.apap.tugas1.service;

import com.apap.tugas1.model.*;
import java.util.List;

public interface InstansiService {
	InstansiModel getInstansiDetailById(long id);
	List<InstansiModel> getAllInstansi();
}
