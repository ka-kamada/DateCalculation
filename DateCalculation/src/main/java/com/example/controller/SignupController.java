package com.example.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.GroupOrder;
import com.example.form.SignupForm;
import com.example.model.MUser;
import com.example.service.UserService;

@Controller
@RequestMapping("/user")
public class SignupController {

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	/** ユーザー登録画面を表示 */
	@GetMapping("/signup")
	public String loadSignup(@ModelAttribute SignupForm form) {

		// ユーザー登録画面に遷移
		return "user/signup";
	}

	/** ユーザー登録処理 */
	@PostMapping("/signup")
	public String signup(@ModelAttribute @Validated(GroupOrder.class) SignupForm form,
			BindingResult bindingResult) {

		// 入力チェック
		if (bindingResult.hasErrors()) {
			return loadSignup(form);
		}

		MUser user = modelMapper.map(form, MUser.class);

		userService.signup(user);

		// ログイン画面にリダイレクト
		return "redirect:/login";
	}

	/** データベース関連の例外処理 */
	@ExceptionHandler(DataAccessException.class)
	public String dataAccessExceptionHandler(DataAccessException e, Model model) {

		// 空文字をセット
		model.addAttribute("error", "");

		// メッセージをModelに登録
		model.addAttribute("message", "システムエラーが発生しました");

		// HTTPのエラーコード（500）をModelに登録
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

		return "error";
	}

	/** その他の例外処理 */
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e, Model model) {

		// 空文字をセット
		model.addAttribute("error", "");

		// メッセージをModelに登録
		model.addAttribute("message", "システムエラーが発生しました");

		// HTTPのエラーコード（500）をModelに登録
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

		return "error";
	}

}
