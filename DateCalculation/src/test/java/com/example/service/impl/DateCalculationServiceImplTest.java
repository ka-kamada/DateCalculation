package com.example.service.impl;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.model.Formula;

@SpringBootTest
@ActiveProfiles
public class DateCalculationServiceImplTest {

	@Autowired
	DateCalculationServiceImpl service;

	@Test
	void 計算式取得1件() {
		Formula formula = service.getFormulaOne(1);
		assertThat(formula.getFormulaId()).isEqualTo(1);
	}

}
