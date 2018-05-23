package com.vetClinic.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.vetClinic.admin.UserMaintainer;
import com.vetClinic.dbhelper.DBconfig;
import com.vetClinic.owner.Owner;
import com.vetClinic.patients.Patient;

public class PatientsDAOimpl implements PatientsDAO {

	private JdbcTemplate jdbcTemplate;
	private DataSource dS;
	
	public PatientsDAOimpl() {
		DBconfig config = new DBconfig();
		DataSource dataSource = config.dataSource();
		jdbcTemplate = new JdbcTemplate(dataSource);
		dS = dataSource;
	}
	
	@Override
	public void delete(int patient_id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Patient get(int patient_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Patient> list() {
		List<Patient> patientsList = new ArrayList<Patient>();
		
		String patientsSql = "select p.id, p.rasa, p.gatunek, p.plec, p.imie, p.id_klienta, p.data_urodzenia, p.data_zgonu, p.microchip_id, concat(o.imie, ' ' , o.nazwisko) as owner_name from pacjent p left join klient o on p.id_klienta=o.id;";
		
		try {
			Connection connection = dS.getConnection();
			PreparedStatement statement = connection.prepareStatement(patientsSql);
			
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				Patient patient = new Patient();
				patient.setPatient_id(rs.getInt(1));
				patient.setRace(rs.getString(2));
				patient.setSpecies(rs.getString(3));
				patient.setSex(rs.getBoolean(4));
				patient.setPatient_name(rs.getString(5));
				patient.setOwner_id(rs.getInt(6));
				patient.setBirth_date(rs.getDate(7));
				patient.setDeath_date(rs.getDate(8));
				patient.setMicrochip_id(rs.getInt(9));
				Date dateHelper = new Date();
				dateHelper = rs.getDate(8);
				if(dateHelper!=null) {
					patient.setAlive(true);
				}
				else patient.setAlive(false);
				patient.setOwner_name(rs.getString(10));
				
				patientsList.add(patient);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return patientsList;
	}
	
	
	@Override
	public void addPatient(Patient patient) {
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		
		String race = patient.getRace();
		String species = patient.getSpecies();
		int owner_id = patient.getOwner_id();
		int sex = patient.isSex() ? 1 : 0;
		String name = patient.getPatient_name();
		Date birth_date = patient.getBirth_date();
		int microchip_id = patient.getMicrochip_id();
		String addPatientSql;
		
		if(race!="")
			addPatientSql = "insert into pacjent(rasa, gatunek, plec, imie, id_klienta, data_urodzenia, microchip_id) values('" + race +"','" + species + "','" + sex + "','" + name + "','" + owner_id + "','" + df.format(birth_date) + "','" + microchip_id +  "');";
		else
			addPatientSql = "insert into pacjent(gatunek, plec, imie, id_klienta, data_urodzenia, microchip_id) values('" + species + "','" + sex + "','" + name  + "','" + owner_id + "','" + df.format(birth_date) + "','" + microchip_id +  "');";
		
		
		try {
			Connection connection = dS.getConnection();
			PreparedStatement statement = connection.prepareStatement(addPatientSql);
			statement.executeUpdate();
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
	}

	@Override
	public void updatePatient(Patient patient) {
		// TODO Auto-generated method stub
		
	}
	
}
