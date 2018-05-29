package com.vetClinic.DAO;

import java.util.List;

import com.vetClinic.examination.Examination;
import com.vetClinic.visits.Visit;

public interface ExaminationDAO  {
	public int addExamination(Examination examination);

	public void scheduleExamination(Examination examination);

	public List<Examination> list();

	public List<Examination> list(int examination_id);

	public Examination get(int id);
}
