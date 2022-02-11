package com.example.service.impl;

import java.time.LocalDate;
import java.time.Period;
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

		// 繰り返し処理内で使うフォーマットを設定
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日");

		// リスト内の計算式を実行し結果をFormulaに格納
		for (Formula formula : list) {
			String result = referenceDate.plus(Period.of(formula.getYear(), formula.getMonth(), formula.getDay())).format(dtf);
			formula.setDateCaluculationResult(result);
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
