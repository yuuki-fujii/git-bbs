package com.example.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CommentForm {

	/** コメント者名 */
	@Size(min=1, max=50, message="1-50文字で名前を入力してください")
	private String name;
	/** コメント内容 */
	@NotBlank(message="内容を入力してください")
	private String content;
	/** 記事ID */
	private String articleId;

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

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	@Override
	public String toString() {
		return "CommentForm [name=" + name + ", content=" + content + ", articleId=" + articleId + "]";
	}

}
