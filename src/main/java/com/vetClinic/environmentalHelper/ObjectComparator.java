package com.vetClinic.environmentalHelper;

import java.util.Comparator;

import com.vetClinic.visits.VisitGood;

public class ObjectComparator implements Comparator<VisitGood>  {

	@Override
	public int compare(VisitGood o1, VisitGood o2) {
		Integer id1 = o1.getId();
		Integer id2 = o2.getId();
		// TODO Auto-generated method stub
		return id1.compareTo(id2);
	}

}
