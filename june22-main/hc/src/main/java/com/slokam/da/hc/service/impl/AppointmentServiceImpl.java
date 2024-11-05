package com.slokam.da.hc.service.impl;

import java.util.Date;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.slokam.da.hc.consta.ErrorCodesEnum;
import com.slokam.da.hc.dao.AppointmentDAO;
import com.slokam.da.hc.dao.PatientDAO;
import com.slokam.da.hc.entity.Appointment;
import com.slokam.da.hc.entity.AppointmentStatus;
import com.slokam.da.hc.entity.Patient;
import com.slokam.da.hc.exception.PatientException;
import com.slokam.da.hc.service.IAppointmentService;

@Service
public class AppointmentServiceImpl implements IAppointmentService {
	private static Logger LOGGER = LoggerFactory.getLogger(AppointmentServiceImpl.class);
	@Autowired
	private AppointmentDAO appointDao;

	@Autowired
	private PatientDAO patientDao;

	@Override
	public void createAppointment(Appointment appointment) throws PatientException {
		LOGGER.trace("Entered into createAppointment");
		try {
			if (appointment != null) {

				appointment.setTaken(new Date());
				if (appointment.getPatient().getId() != null) {
					Optional<Patient> opt = patientDao.findById(appointment.getPatient().getId());
					if (opt.isPresent()) {
						appointment.setPatient(opt.get());
					} else {
						LOGGER.error("Patient is not found for saving appointment");
						throw new PatientException("Patient Not Found", null, ErrorCodesEnum.PATIEANT_NOTFOUND);
					}

				}
				LOGGER.debug("Appointment Details Before Save ::" + appointment);
				appointDao.save(appointment);
				LOGGER.info("Appointment Details Before Save ::" + appointment);
			}
		} catch (PatientException p) {
			LOGGER.error("PatientException ");
			throw p;
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("PatientException ");
			throw new PatientException("DB problem", e);
		}
		LOGGER.trace("Exit from createAppointment");
	}

	@Override
	public Appointment updateStatus(Integer aid, Integer asid) throws PatientException {
		LOGGER.trace("Entered into updateStatus");

		Optional<Appointment> opt = appointDao.findById(aid);

		Appointment ap = null;
		if (opt.get() == null) {
			LOGGER.error("Appointment Not Found");
			throw new PatientException("Appointment Not Found", null, ErrorCodesEnum.APPOINTMENT_NOTFOUND);
		} else {
			if (opt.isPresent()) {

				ap = opt.get();
				LOGGER.debug("Appointment data to be updated :" + ap.toString());
				AppointmentStatus status = new AppointmentStatus();
				status.setId(asid);
				ap.setStatus(status);
				appointDao.save(ap);
				LOGGER.info("Appointment updated successfully");
			} else {
				LOGGER.warn("Appointment Not Found");
				throw new PatientException("Appointment Not Found", null, ErrorCodesEnum.APPOINTMENT_NOTFOUND);

			}

			LOGGER.trace("Exit  from updateStatus");
			return ap;

		}
	}
}
