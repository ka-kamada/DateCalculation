package com.example.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Formula;
import com.example.repository.DateCalculationMapper;
import com.example.service.DateCalculationService;

@Service
public class DateCalculationServiceImpl implements DateCalculationService {

	@Autowired
	private DateCalculationMapper mapper;

	/** 計算式取得＆計算 */
	@Override
	public List<Formula> getFormulas(LocalDate referenceDate) {

		// 計算式を検索
		List<Formula> list = mapper.getFormulas();

		for (int i = 0; i < list.size(); i++) {

			// 式をcalcに代入
			Formula calc = list.get(i);

			// 年を計算する
			LocalDate resultYear = referenceDate.plusYears(calc.getYear());
			// 月を計算する
			LocalDate resultYearMonth = resultYear.plusMonths(calc.getMonth());
			// 年を計算する
			LocalDate resultYearMonthDay = resultYearMonth.plusDays(calc.getDay());

			// 計算結果をString型に変換
			String result = resultYearMonthDay.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日"));
			// 計算結果をListに追加
			calc.setDateCaluculationResult(result);
		}

		return list;
	}

	/** 計算式登録 */
	@Override
	public void registerFormula(Formula formula) {
		mapper.registerFormula(formula);
	}

	/** 計算式削除 */
	@Override
	public void deleteFormula(int formulaId) {
		mapper.deleteFormula(formulaId);
	}

	/** 更新前の計算式取得 */
	@Override
	public Formula getFormulaOne(int formulaId) {

		Formula formula = mapper.getFormulaOne(formulaId);

		return formula;

	}

	/** 更新処理 */
	@Override
	public void updateFormula(int formulaId, String formulaName, int year, int month, int day) {

		mapper.updateFormula(formulaId, formulaName, year, month, day);

	}

}
