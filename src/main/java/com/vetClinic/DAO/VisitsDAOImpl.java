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
import com.vetClinic.admin.UserMaintainer;
import com.vetClinic.dbhelper.DBconfig;
import com.vetClinic.visits.Visit;

import org.joda.time.DateTimeComparator;

public class VisitsDAOImpl implements VisitsDAO{

	private JdbcTemplate jdbcTemplate;
	private DataSource dS;
	
	public VisitsDAOImpl() {
		DBconfig config = new DBconfig();
		DataSource dataSource = config.dataSource();
		jdbcTemplate = new JdbcTemplate(dataSource);
		dS = dataSource;
	}
	
	@Override
	public Visit get(int visit_id) {
		String visitSql = "select v.id, v.data, v.id_lekarza, v.id_pacjenta, v.opis, p.name, concat(u.imie, concat(' ',u.nazwisko)) as lekarz, concat(o.imie, concat(' ',o.nazwisko)) as owner_name  from wizyta v" + 
				"				 left join pacjent p on v.patient_id = p.id" + 
				"				 left join uzytkownik u on v.id_lekarza = u.id " + 
				"				 left join klient o on p.id_klienta = o.id" + 
				"where v.id = " + visit_id;
		Date datereference = new Date();
		
		Visit visit = new Visit();
		
		try {
			Connection connection = dS.getConnection();
			PreparedStatement visit_statement = connection.prepareStatement(visitSql);
			
			ResultSet visit_rs = visit_statement.executeQuery();
			
			while (visit_rs.next()) {
				visit.setVisitId(visit_rs.getInt(1));
				visit.setVisit_date(visit_rs.getDate(2));
				datereference = visit_rs.getDate(2);
				visit.setDoctor_id(visit_rs.getInt(3));
				visit.setPatient_id(visit_rs.getInt(4));
				visit.setVisit_description(visit_rs.getString(5));
				visit.setPatient_name(visit_rs.getString(6));
				visit.setDoctor_name(visit_rs.getString(7));
				visit.setOwner_name(visit_rs.getString(8));
			}
			//TODO: add today, past, active configuration
					
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		return visit;
	}
	
	//TODO - change it to appropriate functionality
	@Override
	public Visit getLastVisit() {
		String maxSql = "select max(id) from wizyta;";
		int max_id = 0;;
		
		
		try {
			Connection connection = dS.getConnection();
			PreparedStatement id_statement = connection.prepareStatement( maxSql);
			
			ResultSet max_rs = id_statement.executeQuery();
			
			while (max_rs.next()) {
				max_id = max_rs.getInt(1);
			}
			//TODO: add today, past, active configuration
					
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		
		String visitSql = "select max(v.id), v.data, u.id, v.id_pacjenta, v.opis, p.imie, concat(o.imie, concat(' ',o.nazwisko)) as doctor_name, concat(o.imie, concat(' ',o.nazwisko)) as owner_name  from wizyta v" + 
				"				 left join pacjent p on v.id_pacjenta = p.id" + 
				"				 left join klient o on p.id_klienta = o.id" + 
				"				 left join uzytkownik u on v.id_lekarza = u.id" +
				" where v.id = " + max_id;
		Date datereference = new Date();
		
		Visit visit = new Visit();
		
		try {
			Connection connection = dS.getConnection();
			PreparedStatement visit_statement = connection.prepareStatement(visitSql);
			
			ResultSet visit_rs = visit_statement.executeQuery();
			
			while (visit_rs.next()) {
				visit.setVisitId(visit_rs.getInt(1));
				visit.setVisit_date(visit_rs.getDate(2));
				datereference = visit_rs.getDate(2);
				visit.setDoctor_id(visit_rs.getInt(3));
				visit.setPatient_id(visit_rs.getInt(4));
				visit.setVisit_description(visit_rs.getString(5));
				visit.setPatient_name(visit_rs.getString(6));
				visit.setDoctor_name(visit_rs.getString(7));
				visit.setOwner_name(visit_rs.getString(8));
			}
			//TODO: add today, past, active configuration
					
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		return visit;
	}

	@Override
	public List<Visit> list() {
		List<Visit> visitList = new ArrayList<Visit>();
		Date date = new Date();
		
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		
		String date_string =  df.format(date);
		
		//TODO order by czy_ropoczeta???
		String visitsSql = "(select v.id, v.data, v.id_lekarza, v.id_pacjenta, v.opis, p.imie, concat(u.imie, concat(' ',u.nazwisko)) as doctor_name, concat(o.imie, concat(' ',o.nazwisko)) as owner_name, v.czy_rozpoczeta from wizyta v" + 
				"				 left join pacjent p on v.id_pacjenta = p.id" + 
				"				 left join uzytkownik u on v.id_lekarza = u.id " + 
				"				 left join klient o on p.id_klienta = o.id" + 
				" where  v.data = str_to_date('" + date_string + "','%m/%d/%Y') and v.czy_rozpoczeta = 0 order by v.data asc) union all " +
				"(select v.id, v.data, v.id_lekarza, v.id_pacjenta, v.opis, p.imie, concat(u.imie, concat(' ',u.nazwisko)) as doctor_name, concat(o.imie, concat(' ',o.nazwisko)) as owner_name, v.czy_rozpoczeta  from wizyta v" + 
				"				 left join pacjent p on v.patient_id = p.id" + 
				"				 left join uzytkownik u on v.id_lekarza = u.id " + 
				"				 left join klient o on p.id_klienta = o.id" + 
				" where  v.czy_rozpoczeta = 0 and v.data > str_to_date('" + date_string + "','%m/%d/%Y') order by v.czy_rozpoczeta desc) union all " +
				"(select v.id, v.data, v.id_lekarza, v.id_pacjenta, v.opis, p.imie, concat(u.imie, concat(' ',u.nazwisko)) as doctor_name, concat(o.imie, concat(' ',o.nazwisko)) as owner_name, v.czy_rozpoczeta  from wizyta v" + 
				"				 left join pacjent p on v.id_pacjenta = p.id" + 
				"				 left join uzytkownik u on v.id_lekarza = u.id " + 
				"				 left join klient o on p.id_klienta = o.id" + 
				" where  v.czy_rozpoczeta = 1 order by v.czy_rozpoczeta desc) ;";
		
		System.out.println(visitsSql);
		
		Date current_date = new Date();
		
		try {
			Connection connection = dS.getConnection();
			PreparedStatement statement = connection.prepareStatement(visitsSql);
			
			ResultSet visit_rs = statement.executeQuery();
			
			while(visit_rs.next()) {
				Visit visit = new Visit();
				visit.setVisitId(visit_rs.getInt(1));
				visit.setVisit_date(visit_rs.getDate(2));
				Date datereference = visit_rs.getDate(2);
				visit.setDoctor_id(visit_rs.getInt(3));
				visit.setPatient_id(visit_rs.getInt(4));
				visit.setVisit_description(visit_rs.getString(5));
				visit.setPatient_name(visit_rs.getString(6));
				visit.setDoctor_name(visit_rs.getString(7));
				visit.setOwner_name(visit_rs.getString(8));
				
				int date_comparator = DateTimeComparator.getDateOnlyInstance().compare(current_date, datereference);
				
				visit.setToday(false);
				visit.setPast(true);
				visit.setActive(false);
				
				if(visit_rs.getInt(9) == 0) {
					visit.setPast(false);
				}
				
				if(date_comparator==0) {
					visit.setToday(true);
				}
				
				if(visit.isToday()&&!visit.isPast())
					visit.setActive(true);
				
				visitList.add(visit);
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return visitList;
	}
	
	@Override
	public List<Visit> list(int patient_id) {
		List<Visit> visitList = new ArrayList<Visit>();
		
		Date date = new Date();
		
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		
		String date_string =  df.format(date);
		
		String visitSql = "(select v.id, v.data, v.id_lekarza, v.id_pacjenta, v.opis, p.imie, concat(u.imie, concat(' ',u.nazwisko)) as doctor_name, concat(o.imie, concat(' ',o.nazwisko)) as owner_name, v.czy_rozpoczeta from wizyta v" + 
				"				 left join pacjent p on v.id_pacjenta = p.id" + 
				"				 left join uzytkownik u on v.id_lekarza = u.id " + 
				"				 left join klient o on p.id_klienta = o.id" + 
				" where  v.data = str_to_date('" + date_string + "','%m/%d/%Y') and v.czy_rozpoczeta = 0 and p.id = " + patient_id + "  order by v.data asc) union all " +
				"(select v.id, v.data, v.id_lekarza, v.id_pacjenta, v.opis, p.imie, concat(u.imie, concat(' ',u.nazwisko)) as doctor_name, concat(o.imie, concat(' ',o.nazwisko)) as owner_name, v.czy_rozpoczeta from wizyta v" + 
				"				 left join pacjent p on v.id_pacjenta = p.id" + 
				"				 left join uzytkownik u on v.id_lekarza = u.id " + 
				"				 left join klient o on p.id_klienta = o.id" + 
				" where  v.czy_rozpoczeta = 0 and v.data > str_to_date('" + date_string + "','%m/%d/%Y') and p.id = " + patient_id + " order by v.czy_rozpoczeta desc) union all " +
				"(select v.id, v.data, v.id_lekarza, v.id_pacjenta, v.opis, p.imie, concat(u.imie, concat(' ',u.nazwisko)) as doctor_name, concat(o.imie, concat(' ',o.nazwisko)) as owner_name, v.czy_rozpoczeta from wizyta v" + 
				"				 left join pacjent p on v.id_pacjenta = p.id" + 
				"				 left join uzytkownik u on v.id_lekarza = u.id " + 
				"				 left join klient o on p.id_klienta = o.id" + 
				" where  v.czy_rozpoczeta = 1 and p.id = " + patient_id + " order by v.czy_rozpoczeta desc) ;";
		
		System.out.println(visitSql);
		
		Date current_date = new Date();
		
		try {
			Connection connection = dS.getConnection();
			PreparedStatement statement = connection.prepareStatement(visitSql);
			
			ResultSet visit_rs = statement.executeQuery();
			
			while(visit_rs.next()) {
				Visit visit = new Visit();
				visit.setVisitId(visit_rs.getInt(1));
				visit.setVisit_date(visit_rs.getDate(2));
				Date datereference = visit_rs.getDate(2);
				visit.setDoctor_id(visit_rs.getInt(3));
				visit.setPatient_id(visit_rs.getInt(4));
				visit.setVisit_description(visit_rs.getString(5));
				visit.setPatient_name(visit_rs.getString(6));
				visit.setDoctor_name(visit_rs.getString(7));
				visit.setOwner_name(visit_rs.getString(8));
				
				int date_comparator = DateTimeComparator.getDateOnlyInstance().compare(datereference, current_date );
				
				visit.setToday(false);
				visit.setPast(true);
				visit.setActive(false);
				
				if(visit_rs.getInt(9) == 0) {
					visit.setPast(false);
				}
				
				if(date_comparator==0) {
					visit.setToday(true);
				}
				
				if(visit.isToday()&&!visit.isPast())
					visit.setActive(true);
				
				visitList.add(visit);
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return visitList;
	}
	
	@Override
	public List<Visit> list(String doctor_name) {
		List<Visit> visitList = new ArrayList<Visit>();
		
		Date date = new Date();
		
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		
		String date_string =  df.format(date);
		
		String visitSql = "(select v.id, v.data, v.id_lekarza, v.id_pacjenta, v.opis, p.imie, concat(u.imie, concat(' ',u.nazwisko)) as doctor_name, concat(o.imie, concat(' ',o.nazwisko)) as owner_name, v.czy_rozpoczeta from wizyta v" + 
				"				 left join pacjent p on v.id_pacjenta = p.id" + 
				"				 left join uzytkownik u on v.id_lekarza = u.id " + 
				"				 left join klient o on p.id_klienta = o.id" + 
				" where  v.data = str_to_date('" + date_string + "','%m/%d/%Y') and v.czy_rozpoczeta = 0 and concat(u.imie, concat(' ',u.nazwisko)) = '" + doctor_name + "'  order by v.data asc) union all " +
				"(select v.id, v.data, v.id_lekarza, v.id_pacjenta, v.opis, p.imie, concat(u.imie, concat(' ',u.nazwisko)) as doctor_name, concat(o.imie, concat(' ',o.nazwisko)) as owner_name, v.czy_rozpoczeta from wizyta v" + 
				"				 left join pacjent p on v.id_pacjenta = p.id" + 
				"				 left join uzytkownik u on v.id_lekarza = u.id " + 
				"				 left join klient o on p.id_klienta = o.id" + 
				" where  v.czy_rozpoczeta = 0 and concat(u.imie, concat(' ',u.nazwisko)) = '" + doctor_name + "' and v.data > str_to_date('" + date_string + "','%m/%d/%Y') order by v.czy_rozpoczeta asc) union all " +
				"(select v.id, v.data, v.id_lekarza, v.id_pacjenta, v.opis, p.imie, concat(u.imie, concat(' ',u.nazwisko)) as doctor_name, concat(o.imie, concat(' ',o.nazwisko)) as owner_name, v.czy_rozpoczeta from wizyta v" + 
				"				 left join pacjent p on v.id_pacjenta = p.id" + 
				"				 left join uzytkownik u on v.id_lekarza = u.id " + 
				"				 left join klient o on p.id_klienta = o.id" + 
				" where  v.czy_rozpoczeta = 1 and concat(u.imie, concat(' ',u.nazwisko)) = '" + doctor_name + "' order by v.czy_rozpoczeta asc) ;";
		
		System.out.println(visitSql);
		
		Date current_date = new Date();
		
		try {
			Connection connection = dS.getConnection();
			PreparedStatement statement = connection.prepareStatement(visitSql);
			
			ResultSet visit_rs = statement.executeQuery();
			
			while(visit_rs.next()) {
				Visit visit = new Visit();
				visit.setVisitId(visit_rs.getInt(1));
				visit.setVisit_date(visit_rs.getDate(2));
				Date datereference = visit_rs.getDate(2);
				visit.setDoctor_id(visit_rs.getInt(3));
				visit.setPatient_id(visit_rs.getInt(4));
				visit.setVisit_description(visit_rs.getString(5));
				visit.setPatient_name(visit_rs.getString(6));
				visit.setDoctor_name(visit_rs.getString(7));
				visit.setOwner_name(visit_rs.getString(8));
				
				int date_comparator = DateTimeComparator.getDateOnlyInstance().compare(current_date, datereference);
				
				visit.setToday(false);
				visit.setPast(true);
				visit.setActive(false);
				
				if(visit_rs.getInt(9) == 0) {
					visit.setPast(false);
				}
				
				if(date_comparator==0) {
					visit.setToday(true);
				}
				
				if(visit.isToday()&&!visit.isPast())
					visit.setActive(true);
				
				visitList.add(visit);
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return visitList;
	}

	@Override
	public int addVisit(Visit visit) {
		int visit_id = 0;
		int doctor_id = visit.getDoctor_id();
		int patient_id = visit.getPatient_id();
		String visit_description = null;
		String date_string;
		int is_actual = 1;
		
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		
		Date date = new Date();
		
		date_string =  df.format(date);
		
		String addvisitSql = "insert into wizyta(data, id_lekarza, id_pacjenta, opis, czy_rozpoczeta) values(str_to_date('" + date_string + "','%m/%d/%Y')," + doctor_id + "," + patient_id +",'" + visit_description + "', " + is_actual+ ");";
		
		System.out.println(addvisitSql);
		
		try {
			Connection connection = dS.getConnection();
			PreparedStatement statement = connection.prepareStatement(addvisitSql, Statement.RETURN_GENERATED_KEYS);
			
			int affected_rows = statement.executeUpdate();
			
			ResultSet generatedKeys = statement.getGeneratedKeys();
			
			if (generatedKeys.next()) {
				visit_id = generatedKeys.getInt(1);
				visit.setVisitId(visit_id);
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
			
			statement.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return visit_id;
	}
	

	@Override
	public void scheduleVisit(Visit visit) {
		int visit_id;
		int doctor_id = visit.getDoctor_id();
		int patient_id = visit.getPatient_id();
		String visit_description = visit.getVisit_description();
		String date_string;
		int is_actual = 0;
		
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		
		Date date = visit.getVisit_date();
		
		date_string =  df.format(date);
		System.out.println(date_string);
		
		String addvisitSql = "insert into wizyta(data, id_lekarza, id_pacjenta, opis, czy_rozpoczeta) values(str_to_date('" + date_string + "','%m/%d/%Y')," + doctor_id + "," + patient_id +",'" + visit_description + "', " + is_actual+ ");";
		
		System.out.println(addvisitSql);
		
		try {
			Connection connection = dS.getConnection();
			PreparedStatement statement = connection.prepareStatement(addvisitSql, Statement.RETURN_GENERATED_KEYS);
			
			int affected_rows = statement.executeUpdate();
			
			ResultSet generatedKeys = statement.getGeneratedKeys();
			
			if (generatedKeys.next()) {
				visit_id = generatedKeys.getInt(1);
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
			
			statement.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}


	@Override
	public void updateVisit(Visit visit) {
		int visit_id;
		
		visit_id = visit.getVisitId();
	
		String visit_description = visit.getVisit_description();

		int is_actual = 1;
		
		String updateVisitSql = "update wizyta set opis = '" + visit_description + "', czy_rozpoczeta = " + is_actual + ", id_lekarza = " + visit.getDoctor_id() + " where id = " + visit_id + ";";
		
		//TEST
		System.out.println(updateVisitSql);
		
		try {
			Connection connection = dS.getConnection();
			PreparedStatement statement = connection.prepareStatement(updateVisitSql);
			
			statement.executeUpdate();
			
			
			statement.close();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Override
	public void updateGoodsUsage(Visit visit) {
		// TODO Auto-generated method stub
		
	}
	

}
