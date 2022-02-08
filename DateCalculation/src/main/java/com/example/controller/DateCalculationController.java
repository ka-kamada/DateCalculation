package com.example.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String checkReferenceDate(@ModelAttribute @Validated ReferenceDateForm form, BindingResult bindingResult,
			RedirectAttributes redirectAttributes) {

		// 入力チェック
		if (bindingResult.hasErrors()) {
			return loadSearch(form);
		}

		redirectAttributes.addFlashAttribute("reference", form.getReferenceDate());

		return "redirect:/calculation/result";
	}

	// 計算＆計算結果画面表示
	@GetMapping("/result")
	public String executeCalc(@ModelAttribute("reference") LocalDate reference, Model model) {

		// 画面表示の為referenceをString型に変換しモデルに登録
		String referenceDate = reference.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日"));
		model.addAttribute("referenceDate", referenceDate);

		// 基準日をもとに計算処理 ⇒ 結果をモデルに登録
		List<Formula> formulaList = dateCalculationService.getFormulas(reference);
		model.addAttribute("formulaList", formulaList);

		return "calculation/result";
	}
}