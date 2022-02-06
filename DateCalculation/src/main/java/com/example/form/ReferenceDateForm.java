package com.example.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class ReferenceDateForm {

	@NotBlank
	@Pattern(regexp = "([1-9][0-9]{3})(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])")
	private String referenceDate;

}
