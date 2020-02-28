package com.example.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 投稿された記事を表すフォーム.
 * 
 * @author mayumiono
 *
 */
public class ArticleForm {

	/** 投稿者名 */
	@Size(min = 1, max = 50, message = "名前は1-50文字で入力してください")
	private String name;
	/** 投稿内容 */
	@NotBlank(message = "本文を入力してください")
	private String content;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "ArticleForm [name=" + name + ", content=" + content + "]";
	}

}
