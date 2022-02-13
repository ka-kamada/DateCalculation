package com.example.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.form.GroupOrder;
import com.example.form.RegisterFormulaForm;
import com.example.model.Formula;
import com.example.service.DateCalculationService;

@Controller
@RequestMapping("/calculation/formula")
public class FormulaDetailController {

	@Autowired
	private DateCalculationService dateCalculationService;

	@Autowired
	private ModelMapper modelMapper;

	// 計算式IDをリダイレクト
	@PostMapping(value = "/detail", params = "update")
	String redirecUpdateId(@RequestParam Integer formulaId) {

		return "redirect:update/" + formulaId;
	}

	// 更新する計算式を検索⇒更新画面表示
	@GetMapping("/update/{formulaId}")
	String loadUpdateFormula(@PathVariable Integer formulaId, Model model) {

		// 更新する計算式を検索 ⇒ 結果をモデルに登録
		Formula formula = dateCalculationService.getFormulaOne(formulaId);

		// 型変換（Forula ⇒ ResisterForulaForm）
		RegisterFormulaForm form = modelMapper.map(formula, RegisterFormulaForm.class);

		// 結果をモデルに登録
		model.addAttribute("form", form);

		return "formula/update";
	}

	// 更新処理 ⇒ 検索画面表示へリダイレクト
	@PostMapping("/update")
	String executeUpdateFormula(@ModelAttribute @Validated(GroupOrder.class) RegisterFormulaForm form,
			BindingResult bindingResult, Model model) {

		// 入力チェック
		if (bindingResult.hasErrors()) {
			return "redirect:update/" + form.getFormulaId();
		}

		// 型変換（ResisterForulaForm ⇒ Formula）
		Formula formula = modelMapper.map(form, Formula.class);

		// 更新処理
		dateCalculationService.updateFormula(formula.getFormulaId(), formula.getFormulaName(), formula.getYear(),
				formula.getMonth(), formula.getDay());

		return "redirect:/calculation/search/";
	}

	// 削除処理 ⇒ 検索画面表示へリダイレクト
	@PostMapping(value = "/detail", params = "delete")
	public String executeDeleteFormula(@RequestParam Integer formulaId) {

		// 削除処理
		dateCalculationService.deleteFormula(formulaId);

		return "redirect:/calculation/search/";
	}

}
