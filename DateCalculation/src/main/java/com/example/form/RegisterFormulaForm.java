package com.example.form;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Range;

import lombok.Data;

@Data
public class RegisterFormulaForm {

	@NotBlank(groups = ValidGroup1.class)
	private String formulaName;

	@NotBlank(groups = ValidGroup1.class)
	@Range(min = -999999999, max = 999999999, groups = ValidGroup2.class)
	private String year;

	@NotBlank(groups = ValidGroup1.class)
	@Range(min = -999999999, max = 999999999, groups = ValidGroup2.class)
	private String month;

	@NotBlank(groups = ValidGroup1.class)
	@Range(min = -999999999, max = 999999999, groups = ValidGroup2.class)
	private String day;

}
