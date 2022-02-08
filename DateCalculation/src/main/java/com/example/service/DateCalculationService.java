package com.example.service;

import java.time.LocalDate;
import java.util.List;

import com.example.model.Formula;

public interface DateCalculationService {

	/** 計算式取得 */
	public List<Formula> getFormulas(LocalDate referenceDate);

	/** 計算式登録 */
	public void registerFormula(Formula formula);

	/** 計算式削除 */
	public void deleteFormula(int formulaId);

	/** 更新前の計算式取得 */
	public Formula getFormulaOne(int formulaId);

	/** 更新処理 */
	public void updateFormula(int formulaId, String formulaName, int year, int month, int day);

}
