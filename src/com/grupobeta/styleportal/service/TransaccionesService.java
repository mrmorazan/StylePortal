package com.grupobeta.styleportal.service;

import java.util.List;

import com.grupobeta.styleportal.domain.CustomerPolyPm;

public interface TransaccionesService extends Service {

	List<CustomerPolyPm> loadAllCustomerActivesPolyPm();
}
