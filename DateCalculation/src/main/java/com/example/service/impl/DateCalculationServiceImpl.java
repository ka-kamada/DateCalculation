package com.example.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Formula;
import com.example.repository.DateCalculationRepository;
import com.example.service.DateCalculationService;

@Service
public class DateCalculationServiceImpl implements DateCalculationService {

	@Autowired
	private DateCalculationRepository mapper;

	/** 計算式取得＆計算 */
	@Override
	public List<Formula> getFormulas(Date referenceDate) {

		// Calendarクラスのインスタンスを生成
		Calendar referenceCalendar = Calendar.getInstance();
		// 計算式を検索
		List<Formula> list = mapper.getFormulas();

		for (int i = 0; i < list.size(); i++) {
			// referenceCalendarに基準日を設定
			referenceCalendar.setTime(referenceDate);
			// 式をcalcに代入
			Formula calc = list.get(i);


			// 年を計算する
			referenceCalendar.add(Calendar.YEAR, calc.getYear());
			// 月を計算する
			referenceCalendar.add(Calendar.MONTH, calc.getMonth());
			// 年を計算する
			referenceCalendar.add(Calendar.DATE, calc.getDay());

			// 計算結果のフォーマットを設定
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
			// 計算結果をString型に変換
			String result = sdf.format(referenceCalendar.getTime());
			// 計算結果をListに追加
			calc.setDateCaluculationResult(result);
		}

		return list;
	}

	/** 計算式登録 */
	@Override
	public void setFormula(Formula formula) {
		mapper.setFormula(formula);
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
	public void updateFormulaOne(int formulaId, String formulaName, int year, int month, int day) {

		mapper.updateFormulaOne(formulaId, formulaName, year, month, day);

	}

}
