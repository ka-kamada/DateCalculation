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
@RequestMapping("/date-calculation")
public class DateCalculationController {

	@Autowired
	private DateCalculationService dateCalculationService;

	// トップ画面表示
	@GetMapping("/index.html")
	public String getTop(@ModelAttribute ReferenceDateForm form) {
		return "calculation/index";
	}

	// ここから検索処理
	// 入力値を受け取りそのまま検索結果画面へリダイレクト
	@PostMapping("/index.html")
	public String postResult(@ModelAttribute @Validated ReferenceDateForm form,
			BindingResult bindingResult) {

		// 入力チェック結果
		if (bindingResult.hasErrors()) {
			return getTop(form);
		}

		return "redirect:/date-calculation/result/" + form.getReferenceDate();
	}

	// 入力値をもとに検索＆計算結果画面表示
	@GetMapping("/result/{reference}")
	public String getResult(@PathVariable String reference, Model model) throws ParseException {

		// referenceをDate型に変換（無駄なコードがないか要確認）
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date referenceDate = sdf.parse(reference);

		// 基準日をもとに計算処理 ⇒ 結果をモデルに登録
		List<Formula> formulaList = dateCalculationService.getFormulas(referenceDate);
		model.addAttribute("reerence", reference);
		model.addAttribute("formulaList", formulaList);

		return "calculation/result";
	}
}