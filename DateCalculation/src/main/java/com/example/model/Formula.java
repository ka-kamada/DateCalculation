package com.example.model;

import lombok.Data;

@Data
public class Formula {

	private int formulaId;
	private String formulaName;
	private int year;
	private int month;
	private int day;
	private String dateCaluculationResult;

}
