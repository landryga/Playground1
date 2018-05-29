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

import com.mysql.jdbc.Statement;
import com.vetClinic.dbhelper.DBconfig;
import com.vetClinic.examination.Examination;
import com.vetClinic.examination.ExaminationType;
import com.vetClinic.visits.Visit;

public class ExaminationDAOimpl implements ExaminationDAO {
	
	private JdbcTemplate jdbcTemplate;
	private DataSource dS;
	
	public ExaminationDAOimpl() {
		DBconfig config = new DBconfig();
		DataSource dataSource = config.dataSource();
		jdbcTemplate = new JdbcTemplate(dataSource);
		dS = dataSource;
	}
	
	public int addExamination(Examination examination) {
		
		int examination_id = 0;
		
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		
		Date date = new Date();
		
		String date_string =  df.format(date);
		
		String examinationSql = "insert into badania(data, pacjent_id, badanie_identyfikator, wartosc, czy_aktywne) values(" +
								"str_to_date('" + date_string + "','%m/%d/%Y %H:%i')," + examination.getPatient_id() + "," + examination.getType_id() + ",'" + examination.getResult() + "'," + 0 + ");" ;
		
		System.out.println(examinationSql);
		
		try {
			Connection connection = dS.getConnection();
			PreparedStatement examination_statement = connection.prepareStatement(examinationSql, Statement.RETURN_GENERATED_KEYS);
			
			int affected_rows = examination_statement.executeUpdate();
			
			ResultSet generatedKeys = examination_statement.getGeneratedKeys();
			
			if (generatedKeys.next()) {
				examination_id = generatedKeys.getInt(1);
				examination.setId(examination_id); 
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
			
			examination_statement.close();
			connection.close();
			
			//TODO: add today, past, active configuration
					
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		return examination_id;
	}

	public void scheduleExamination(Examination examination) {
		int examination_id = 0;
		
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		
		Date date = new Date();
		
		String date_string =  df.format(date);
		
		String examinationSql = "insert into badania(data, pacjent_id, badanie_identyfikator, wartosc, czy_aktywne) values(" +
				"str_to_date('" + date_string + "','%m/%d/%Y %H:%i')," + examination.getPatient_id() + "," + examination.getType_id() + ",'" + examination.getResult() + "'," + 1 + ");" ;

		
		try {
			Connection connection = dS.getConnection();
			PreparedStatement examination_statement = connection.prepareStatement(examinationSql, Statement.RETURN_GENERATED_KEYS);
			
			int affected_rows = examination_statement.executeUpdate();
			
			ResultSet generatedKeys = examination_statement.getGeneratedKeys();
			
			if (generatedKeys.next()) {
				examination_id = generatedKeys.getInt(1);
				examination.setId(examination_id); 
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
			
			examination_statement.close();
			connection.close();
			
			//TODO: add today, past, active configuration
					
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
	}

	public List<Examination> list() {
		List<Examination> examinationList = new ArrayList<Examination>();
		
		String examinationSql = "select e.id, e.data, e.pacjent_id, p.imie, e.wartosc, e.czy_aktywne, e.badanie_identyfikator, bs.nazwa, concat(k.imie, concat(' ',k.nazwisko)) as owner_name, k.id as owner_id   from badania e" + 
				"				 left join pacjent p on e.pacjent_id = p.id" + 
				"				 left join klient k on p.id_klienta = k.id" + 
				"				 left join badanie_slownik bs on e.badanie_identyfikator = bs.id ;";
		
		
		
		try {
			Connection connection = dS.getConnection();
			PreparedStatement examination_statement = connection.prepareStatement(examinationSql);
			
			ResultSet examination_rs = examination_statement.executeQuery();
			
			while (examination_rs.next()) {
				Examination examination = new Examination();
				examination.setId(examination_rs.getInt(1));
				examination.setDate(examination_rs.getDate(2).toString());
				examination.setPatient_id(examination_rs.getInt(3));
				examination.setResult(examination_rs.getString(5));
				examination.setPatient_name(examination_rs.getString(4));
				
				if(examination_rs.getInt(6)==0) {
					examination.setActive(false);
				}
				else {
					examination.setActive(true);
				}
				
				examination.setType_id(examination_rs.getInt(7));
				examination.setType_name(examination_rs.getString(8));
				examination.setOwnerName(examination_rs.getString(9));
				examination.setOwnerId(examination_rs.getInt(10));
				
				examinationList.add(examination);
			}
			//TODO: add today, past, active configuration
					
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		return examinationList;
	}

	public List<Examination> list(int patient_id) {
		
		List<Examination> examinationList = new ArrayList<Examination>();
		
		String examinationSql = "select e.id, e.data, e.pacjent_id, p.imie, e.wartosc, e.czy_aktywne, e.badanie_identyfikator, bs.nazwa, concat(k.imie, concat(' ',k.nazwisko)) as owner_name, k.id as owner_id   from badania e" + 
				"				 left join pacjent p on e.pacjent_id = p.id" + 
				"				 left join klient k on p.id_klienta = k.id" + 
				"				 left join badanie_slownik bs on e.badanie_identyfikator = bs.id " +
				" where p.id = " + patient_id + ";";
		
		
		
		try {
			Connection connection = dS.getConnection();
			PreparedStatement examination_statement = connection.prepareStatement(examinationSql);
			
			ResultSet examination_rs = examination_statement.executeQuery();
			
			while (examination_rs.next()) {
				Examination examination = new Examination();
				examination.setId(examination_rs.getInt(1));
				examination.setDate(examination_rs.getDate(2).toString());
				examination.setPatient_id(examination_rs.getInt(3));
				examination.setResult(examination_rs.getString(5));
				examination.setPatient_name(examination_rs.getString(4));
				
				if(examination_rs.getInt(6)==0) {
					examination.setActive(false);
				}
				else {
					examination.setActive(true);
				}
				
				examination.setType_id(examination_rs.getInt(7));
				examination.setType_name(examination_rs.getString(8));
				examination.setOwnerName(examination_rs.getString(9));
				examination.setOwnerId(examination_rs.getInt(10));
				
				examinationList.add(examination);
			}
			//TODO: add today, past, active configuration
					
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		return examinationList;
	}

	public Examination get(int id) {
		String examinationSql = "select e.id, e.data, e.pacjent_id, p.imie, e.wartosc, e.czy_aktywne, e.badanie_identyfikator, bs.nazwa, concat(k.imie, concat(' ',k.nazwisko)) as owner_name, k.id as owner_id   from badania e" + 
				"				 left join pacjent p on e.pacjent_id = p.id" + 

				"				 left join klient k on p.id_klienta = k.id" + 
				"				 left join badanie_slownik bs on e.badanie_identyfikator = bs.id " +
				" where e.id = " + id + ";";
		
		Examination examination = new Examination();
		
		try {
			Connection connection = dS.getConnection();
			PreparedStatement examination_statement = connection.prepareStatement(examinationSql);
			
			ResultSet examination_rs = examination_statement.executeQuery();
			
			while (examination_rs.next()) {
				examination.setId(examination_rs.getInt(1));
				examination.setDate(examination_rs.getDate(2).toString());
				examination.setPatient_id(examination_rs.getInt(3));
				examination.setResult(examination_rs.getString(5));
				examination.setPatient_name(examination_rs.getString(4));
				
				if(examination_rs.getInt(6)==0) {
					examination.setActive(false);
				}
				else {
					examination.setActive(true);
				}
				
				examination.setType_id(examination_rs.getInt(7));
				examination.setType_name(examination_rs.getString(8));
				examination.setOwnerName(examination_rs.getString(9));
				examination.setOwnerId(examination_rs.getInt(10));
			}
			//TODO: add today, past, active configuration
					
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		return examination;
	}
	
	public List<ExaminationType> listExaminationTypes() {
		
		List<ExaminationType> examinationTypeList = new ArrayList<ExaminationType>();
		
		String examinationSql = "select id, nazwa, jednostka   from badanie_slownik ";
		
		
		
		try {
			Connection connection = dS.getConnection();
			PreparedStatement examination_statement = connection.prepareStatement(examinationSql);
			
			ResultSet examination_rs = examination_statement.executeQuery();
			
			while (examination_rs.next()) {
				ExaminationType examinationt = new ExaminationType();
				examinationt.setId(examination_rs.getInt(1));
				examinationt.setName(examination_rs.getString(2));
				examinationt.setUom(examination_rs.getString(3));
				
				examinationTypeList.add(examinationt);
			}
			//TODO: add today, past, active configuration
					
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		return examinationTypeList;
		
	}
}
