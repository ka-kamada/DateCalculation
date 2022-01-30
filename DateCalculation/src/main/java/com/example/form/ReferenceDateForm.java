package com.example.form;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ReferenceDateForm {

	@NotBlank
	private String referenceDate;

}
