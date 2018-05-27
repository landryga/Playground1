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
import com.vetClinic.environmentalHelper.DateParser;
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
		String visitSql = "select v.id, v.data, v.id_lekarza, v.id_pacjenta, v.opis, p.imie, concat(u.imie, concat(' ',u.nazwisko)) as lekarz, concat(o.imie, concat(' ',o.nazwisko)) as owner_name  from wizyta v" + 
				"				 left join pacjent p on v.id_pacjenta = p.id" + 
				"				 left join uzytkownik u on v.id_lekarza = u.id " + 
				"				 left join klient o on p.id_klienta = o.id" + 
				" where v.id = " + visit_id + ";";
		Date datereference = new Date();
		
		Visit visit = new Visit();
		
		try {
			Connection connection = dS.getConnection();
			PreparedStatement visit_statement = connection.prepareStatement(visitSql);
			
			ResultSet visit_rs = visit_statement.executeQuery();
			
			while (visit_rs.next()) {
				visit.setVisitId(visit_rs.getInt(1));
				visit.setVisit_date(visit_rs.getDate(2).toString());
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
		int max_id = 0;
		
		
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
				visit.setVisit_date(visit_rs.getDate(2).toString());
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
		
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH");
		
		String date_string =  df.format(date);
		
		//TODO order by czy_ropoczeta???
		String visitsSql = "(select v.id, v.data, v.id_lekarza, v.id_pacjenta, v.opis, p.imie, concat(u.imie, concat(' ',u.nazwisko)) as doctor_name, concat(o.imie, concat(' ',o.nazwisko)) as owner_name, v.czy_rozpoczeta from wizyta v" + 
				"				 left join pacjent p on v.id_pacjenta = p.id" + 
				"				 left join uzytkownik u on v.id_lekarza = u.id " + 
				"				 left join klient o on p.id_klienta = o.id" + 
				" where  v.data = str_to_date('" + date_string + "','%m/%d/%Y %H') and v.czy_rozpoczeta = 0 order by v.data asc) union all " +
				"(select v.id, v.data, v.id_lekarza, v.id_pacjenta, v.opis, p.imie, concat(u.imie, concat(' ',u.nazwisko)) as doctor_name, concat(o.imie, concat(' ',o.nazwisko)) as owner_name, v.czy_rozpoczeta  from wizyta v" + 
				"				 left join pacjent p on v.id_pacjenta = p.id" + 
				"				 left join uzytkownik u on v.id_lekarza = u.id " + 
				"				 left join klient o on p.id_klienta = o.id" + 
				" where  v.czy_rozpoczeta = 0 and v.data > str_to_date('" + date_string + "','%m/%d/%Y %H') order by v.czy_rozpoczeta desc) union all " +
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
				visit.setVisit_date(visit_rs.getDate(2).toString());
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
	public List<Visit> listAllVisits() {
		List<Visit> visitList = new ArrayList<Visit>();
		Date date = new Date();
		
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		
		String date_string =  df.format(date);
		
		//TODO order by czy_ropoczeta???
		String visitsSql = "select v.id, v.data, v.id_lekarza, v.id_pacjenta, v.opis, p.imie, concat(u.imie, concat(' ',u.nazwisko)) as doctor_name, concat(o.imie, concat(' ',o.nazwisko)) as owner_name, v.czy_rozpoczeta from wizyta v" + 
				"				 left join pacjent p on v.id_pacjenta = p.id" + 
				"				 left join uzytkownik u on v.id_lekarza = u.id " + 
				"				 left join klient o on p.id_klienta = o.id;";
		
		System.out.println(visitsSql);
		
		Date current_date = new Date();
		
		
		
		try {
			Connection connection = dS.getConnection();
			PreparedStatement statement = connection.prepareStatement(visitsSql);
			
			ResultSet visit_rs = statement.executeQuery();
			
			while(visit_rs.next()) {
				Visit visit = new Visit();
				visit.setVisitId(visit_rs.getInt(1));
				
				String finalDate = DateParser.convertFormat(visit_rs.getString(2), "yyyy-MM-dd hh:mm:ss");
				
				visit.setVisit_date(finalDate);
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
		
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH");
		
		String date_string =  df.format(date);
		
		String visitSql = "(select v.id, v.data, v.id_lekarza, v.id_pacjenta, v.opis, p.imie, concat(u.imie, concat(' ',u.nazwisko)) as doctor_name, concat(o.imie, concat(' ',o.nazwisko)) as owner_name, v.czy_rozpoczeta from wizyta v" + 
				"				 left join pacjent p on v.id_pacjenta = p.id" + 
				"				 left join uzytkownik u on v.id_lekarza = u.id " + 
				"				 left join klient o on p.id_klienta = o.id" + 
				" where  v.data = str_to_date('" + date_string + "','%m/%d/%Y %H') and v.czy_rozpoczeta = 0 and p.id = " + patient_id + "  order by v.data asc) union all " +
				"(select v.id, v.data, v.id_lekarza, v.id_pacjenta, v.opis, p.imie, concat(u.imie, concat(' ',u.nazwisko)) as doctor_name, concat(o.imie, concat(' ',o.nazwisko)) as owner_name, v.czy_rozpoczeta from wizyta v" + 
				"				 left join pacjent p on v.id_pacjenta = p.id" + 
				"				 left join uzytkownik u on v.id_lekarza = u.id " + 
				"				 left join klient o on p.id_klienta = o.id" + 
				" where  v.czy_rozpoczeta = 0 and v.data > str_to_date('" + date_string + "','%m/%d/%Y %H') and p.id = " + patient_id + " ) union all " +
				"(select v.id, v.data, v.id_lekarza, v.id_pacjenta, v.opis, p.imie, concat(u.imie, concat(' ',u.nazwisko)) as doctor_name, concat(o.imie, concat(' ',o.nazwisko)) as owner_name, v.czy_rozpoczeta from wizyta v" + 
				"				 left join pacjent p on v.id_pacjenta = p.id" + 
				"				 left join uzytkownik u on v.id_lekarza = u.id " + 
				"				 left join klient o on p.id_klienta = o.id" + 
				" where v.czy_rozpoczeta = 1 and p.id = " + patient_id + " ) ;";
		
		System.out.println(visitSql);
		
		Date current_date = new Date();
		
		try {
			Connection connection = dS.getConnection();
			PreparedStatement statement = connection.prepareStatement(visitSql);
			
			ResultSet visit_rs = statement.executeQuery();
			
			while(visit_rs.next()) {
				Visit visit = new Visit();
				visit.setVisitId(visit_rs.getInt(1));
				visit.setVisit_date(visit_rs.getDate(2).toString());
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
	public List<Visit> list(String email) {
		List<Visit> visitList = new ArrayList<Visit>();
		
		Date date = new Date();
		
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		
		String date_string =  df.format(date);
		
		String visitSql = "(select v.id, v.data, v.id_lekarza, v.id_pacjenta, v.opis, p.imie, concat(u.imie, concat(' ',u.nazwisko)) as doctor_name, concat(o.imie, concat(' ',o.nazwisko)) as owner_name, v.czy_rozpoczeta from wizyta v" + 
				"				 left join pacjent p on v.id_pacjenta = p.id" + 
				"				 left join uzytkownik u on v.id_lekarza = u.id " + 
				"				 left join klient o on p.id_klienta = o.id" + 
				" where  v.data = str_to_date('" + date_string + "','%m/%d/%Y %H') and v.czy_rozpoczeta = 0 and u.email = '" + email + "'  order by v.data asc) union all " +
				"(select v.id, v.data, v.id_lekarza, v.id_pacjenta, v.opis, p.imie, concat(u.imie, concat(' ',u.nazwisko)) as doctor_name, concat(o.imie, concat(' ',o.nazwisko)) as owner_name, v.czy_rozpoczeta from wizyta v" + 
				"				 left join pacjent p on v.id_pacjenta = p.id" + 
				"				 left join uzytkownik u on v.id_lekarza = u.id " + 
				"				 left join klient o on p.id_klienta = o.id" + 
				" where  v.czy_rozpoczeta = 0 and u.email = '" + email + "' and v.data > str_to_date('" + date_string + "','%m/%d/%Y %H') order by v.czy_rozpoczeta asc) union all " +
				"(select v.id, v.data, v.id_lekarza, v.id_pacjenta, v.opis, p.imie, concat(u.imie, concat(' ',u.nazwisko)) as doctor_name, concat(o.imie, concat(' ',o.nazwisko)) as owner_name, v.czy_rozpoczeta from wizyta v" + 
				"				 left join pacjent p on v.id_pacjenta = p.id" + 
				"				 left join uzytkownik u on v.id_lekarza = u.id " + 
				"				 left join klient o on p.id_klienta = o.id" + 
				" where  v.czy_rozpoczeta = 1 and u.email = '" + email + "' order by v.czy_rozpoczeta asc) ;";
		
		System.out.println(visitSql);
		
		Date current_date = new Date();
		
		try {
			Connection connection = dS.getConnection();
			PreparedStatement statement = connection.prepareStatement(visitSql);
			
			ResultSet visit_rs = statement.executeQuery();
			
			while(visit_rs.next()) {
				Visit visit = new Visit();
				visit.setVisitId(visit_rs.getInt(1));
				visit.setVisit_date(visit_rs.getDate(2).toString());
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
		
		
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy HH");
		
		Date date = new Date();
		
		date_string =  df.format(date);
		
		String addvisitSql = "insert into wizyta(data, id_lekarza, id_pacjenta, opis, czy_rozpoczeta, cena) values(str_to_date('" + date_string + "','%m/%d/%Y %H')," + doctor_id + "," + patient_id +",'" + visit_description + "', " + is_actual+ ", " + visit.getPrice() + ");";
		
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
	public boolean scheduleVisit(Visit visit) {
		int visit_id;
		int doctor_id = visit.getDoctor_id();
		Integer patient_id = null;
		String visit_description = visit.getVisit_description();
		String date_string;
		int is_actual = 0;
		
		if(visit.getPatient_id()!=0) {
			patient_id = visit.getPatient_id();
		}
		
		date_string = DateParser.convertVisitFormat(visit.getVisit_date().toString(), "dd/MM/yyyy HH");
		
		System.out.println(date_string);
		
		//sprawdz czy planowana przez uzytkownika wizyta nie nachodzi na inna, istniejaca juz w systemie
		String checkDateSql = "select v.id, v.data from wizyta v left join uzytkownik u on v.id_lekarza = u.id " + 
				"left join uzytkownik_rola ur on u.id = ur.uzytkownik_id " + 
				"left join dyzur d on d.uzytkownik_id = u.id " + 
				"where ur.rola_id = 3 " + 
				"and (v.data < DATE_ADD(str_to_date('" + date_string + "','%m/%d/%Y %H'), INTERVAL 2 HOUR) and str_to_date('" + date_string + "','%m/%d/%Y %H')< DATE_ADD(v.data, INTERVAL 2 HOUR)) " + 
				"and u.id = "+ visit.getDoctor_id() + ";";
		
		//sprawdz czy wizyta jest w trakcie dyzuru danego lekarza
		String checkShiftOverlapSql = "select d.rozpoczecie, d.id from uzytkownik u  " + 
				"left join uzytkownik_rola ur on u.id = ur.uzytkownik_id " + 
				"left join dyzur d on d.uzytkownik_id = u.id " + 
				"where ur.rola_id = 3 " + 
				"and (d.rozpoczecie <= str_to_date('" + date_string + "','%m/%d/%Y %H') and date_add(str_to_date('" + date_string + "','%m/%d/%Y %H'), interval 2 HOUR)<= d.zakonczenie) " + 
				"and u.id = "+ visit.getDoctor_id() + ";";
		
		System.out.println(checkDateSql);
		System.out.println(checkShiftOverlapSql);
		
		String addvisitSql = "insert into wizyta(data, id_lekarza, id_pacjenta, opis, czy_rozpoczeta, cena) values(str_to_date('" + date_string + "','%m/%d/%Y %H')," + doctor_id + "," + patient_id +",'" + visit_description + "', " + is_actual+ ", 0);";
		
		System.out.println(addvisitSql);
		
		try {
			Connection connection = dS.getConnection();
			PreparedStatement statement = connection.prepareStatement(addvisitSql, Statement.RETURN_GENERATED_KEYS);
			
			PreparedStatement statementCheck = connection.prepareStatement(checkDateSql);
			PreparedStatement statementOverlap = connection.prepareStatement(checkShiftOverlapSql);
			
			ResultSet check_rs = statementCheck.executeQuery();
			ResultSet overlap_rs = statementOverlap.executeQuery();
			
			if(check_rs.next()|| !overlap_rs.next()) {
				statementCheck.close();
				connection.close();
				
				return false;
			}
			
			
			int affected_rows = statement.executeUpdate();
			
			ResultSet generatedKeys = statement.getGeneratedKeys();
			
			if (generatedKeys.next()) {
				visit_id = generatedKeys.getInt(1);
            }
            else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
			
			statement.close();
			connection.close();
			
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return true;
		
	}


	@Override
	public void closeVisit(Visit visit) {
		

		int is_actual = 1;
		
		String updateVisitSql = "update wizyta set opis = '" + visit.getVisit_description() + "', czy_rozpoczeta = " + is_actual + ", cena = '" + visit.getPrice() +  "' where id = " + visit.getVisitId() + ";";
		
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
