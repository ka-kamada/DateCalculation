package com.example.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.GroupOrder;
import com.example.form.RegisterFormulaForm;
import com.example.model.Formula;
import com.example.service.DateCalculationService;

@Controller
@RequestMapping("/calculation/formula")
public class FormulaRegisterController {

	@Autowired
	private DateCalculationService dateCalculationService;

	@Autowired
	private ModelMapper modelMapper;

	// 計算式登録画面表示
	@GetMapping("/register")
	public String loadRegister(@ModelAttribute RegisterFormulaForm form) {

		return "formula/register";
	}

	// 登録処理 ⇒ 検索画面表示へのリダイレクト
	@PostMapping("/register")
	public String executeRegister(@ModelAttribute @Validated(GroupOrder.class) RegisterFormulaForm form,
			BindingResult bindingResult) {

		// 入力チェック
		if (bindingResult.hasErrors()) {
			return loadRegister(form);
		}

		// 型変換（ResisterForulaForm ⇒ Formula）をして登録処理
		Formula formula = modelMapper.map(form, Formula.class);
		dateCalculationService.registerFormula(formula);

		return "redirect:/calculation/search/";
	}

}
