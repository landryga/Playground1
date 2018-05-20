package com.vetClinic.finances;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.vetClinic.DAO.FinanceDAOimpl;

@Service
public class FinanceService {
	
	private static List<FinanceDay> days = new ArrayList<FinanceDay>();

	public List<FinanceDay> retrieveFinanceDays() {
		FinanceDAOimpl dao = new FinanceDAOimpl();
		
		days = dao.list();
			
		return days;
	}
}
