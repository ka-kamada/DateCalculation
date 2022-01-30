package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.model.Formula;
import com.example.service.DateCalculationService;

@Controller
@RequestMapping("/date-calculation/formula")
public class FormulaDetailController {

	@Autowired
	private DateCalculationService dateCalculationService;

	// idをリダイレクト
	@PostMapping(value = "/detail", params = "update")
	String postEdit(@RequestParam Integer id) {

		return "redirect:/date-calculation/formula/edit/" + id;
	}

	// 更新する計算式を検索⇒更新画面表示
	@GetMapping("/edit/{id}")
	String getEdit(@PathVariable Integer id, Model model) {

		// 更新する計算式を検索 ⇒ 結果をモデルに登録
		Formula formula = dateCalculationService.getFormulaOne(id);
		model.addAttribute("formula", formula);

		return "formula/edit";
	}

	// 更新処理 ⇒ 完了画面へのリダイレクト
	@PostMapping("/updateOne")
	String postUpdate(Formula form) {

		// 更新処理
		dateCalculationService.updateFormulaOne(form.getFormulaId(), form.getFormulaName(), form.getYear(),
				form.getMonth(), form.getDay());

		return "redirect:/date-calculation/formula/edit/complete/";
	}

	// 更新完了画面表示
	@GetMapping("/edit/complete")
	public String getUpdateComplete() {

		return "formula/editComplete";
	}

	// 削除処理 ⇒ 完了画面へのリダイレクト
	@PostMapping(value = "/detail", params = "delete")
	public String postDelete(@RequestParam Integer id) {

		// 計算式削除
		dateCalculationService.deleteFormula(id);

		return "redirect:/date-calculation/formula/delete/complete/";
	}

	// 削除完了画面表示
	@GetMapping("/delete/complete/")
	public String getDeleteComplete() {

		return "formula/deleteComplete";
	}

}
