package com.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.model.Formula;

@Mapper
public interface DateCalculationRepository {


	/** 計算式取得 */
	public List<Formula> getFormulas();

	/** 計算式登録 */
	public void setFormula(Formula formula);

	/** 計算式削除 */
	public void deleteFormula(int formulaId);

	/** 更新前の計算式取得 */
	public Formula getFormulaOne(int formulaId);

	/** 更新処理 */
	public void updateFormulaOne(@Param("formulaId") int formulaId, @Param("formulaName") String formulaName,
			@Param("year") int year, @Param("month") int month, @Param("day") int day);

}
