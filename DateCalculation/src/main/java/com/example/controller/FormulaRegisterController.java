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
@RequestMapping("/date-calculation/formula")
public class FormulaRegisterController {

	@Autowired
	private DateCalculationService dateCalculationService;

	@Autowired
	private ModelMapper modelMapper;

	// 登録画面表示
	@GetMapping("/register")
	public String getRegister(@ModelAttribute RegisterFormulaForm form) {

		return "formula/register";
	}

	// 登録処理 ⇒ 完了画面へのリダイレクト
	@PostMapping("/register")
	public String postRegister(@ModelAttribute @Validated(GroupOrder.class) RegisterFormulaForm form,
			BindingResult bindingResult) {

		// 入力チェック結果
		if (bindingResult.hasErrors()) {
			return getRegister(form);
		}

		// 型変換（ResisterForulaForm ⇒ Formula）をして登録処理
		Formula formula = modelMapper.map(form, Formula.class);
		dateCalculationService.setFormula(formula);

		return "redirect:/date-calculation/formula/register/complete/";
	}

	// 登録完了画面表示
	@GetMapping("/register/complete/")
	public String getRegisterComplete() {

		return "formula/registerComplete";
	}

}
