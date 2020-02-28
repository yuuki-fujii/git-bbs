package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Article;
import com.example.form.ArticleForm;
import com.example.form.CommentForm;
import com.example.repository.ArticleRepository;

@Controller
@RequestMapping("/insert-article")
public class InsertArticleController {
	
	@Autowired
	private ArticleRepository articleRepository;

	@ModelAttribute
	public ArticleForm setUpArticleForm() {
		return new ArticleForm();
	}

	@ModelAttribute
	public CommentForm setUpCommentForm() {
		return new CommentForm();
	}
	
	/**
	 * 記事を投稿する.
	 * 
	 * @param articleForm 投稿記事のフォーム
	 * @param result      入力チェックエラー
	 * @param model       リクエストスコープ
	 * @return 初期画面へのリダイレクト（エラー時は直接、初期画面）
	 */
	@RequestMapping("")
	public String insertArticle(@Validated ArticleForm articleForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "forward:/bbs";
		}

		Article article = new Article();
		BeanUtils.copyProperties(articleForm, article);
		articleRepository.insert(article);
		return "redirect:/insert-article/to-index";
	}
	
	/**
	 * 初期画面を表示する.
	 * 
	 * @param model リクエストスコープ
	 * @return 初期画面
	 */
	@RequestMapping("/to-index")
	public String redirect(Model model) {
		return "forward:/bbs";
	}

}
