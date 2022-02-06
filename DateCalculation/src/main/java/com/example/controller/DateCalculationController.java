package com.example.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

import com.example.form.ReferenceDateForm;
import com.example.model.Formula;
import com.example.service.DateCalculationService;

@Controller
@RequestMapping("/calculation")
public class DateCalculationController {

	@Autowired
	private DateCalculationService dateCalculationService;

	// 検索画面表示
	@GetMapping("/search")
	public String loadSearch(@ModelAttribute ReferenceDateForm form) {
		return "calculation/search";
	}

	// 基準日の入力チェック⇒計算処理へリダイレクト
	@PostMapping("/search")
	public String checkReferenceDate(@ModelAttribute @Validated ReferenceDateForm form,
			BindingResult bindingResult) {

		// 入力チェック
		if (bindingResult.hasErrors()) {
			return loadSearch(form);
		}

		return "redirect:/calculation/result/" + form.getReferenceDate();
	}

	// 計算＆計算結果画面表示
	@GetMapping("/result/{reference}")
	public String executeCalc(@PathVariable String reference, Model model) throws ParseException {

		// referenceをDate型に変換
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date referenceDate = sdf.parse(reference);

		// 基準日をもとに計算処理 ⇒ 結果をモデルに登録
		List<Formula> formulaList = dateCalculationService.getFormulas(referenceDate);
		model.addAttribute("reerence", reference);
		model.addAttribute("formulaList", formulaList);

		return "calculation/result";
	}
}