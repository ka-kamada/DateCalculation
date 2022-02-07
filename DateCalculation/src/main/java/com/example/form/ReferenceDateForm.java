package com.example.form;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ReferenceDateForm {

	@NotNull
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date referenceDate;

}
