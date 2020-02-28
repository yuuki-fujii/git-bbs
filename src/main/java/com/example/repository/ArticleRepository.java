package com.example.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.domain.Article;
import com.example.domain.Comment;

/**
 * データベースのarticlesテーブルにアクセスするためのリポジトリ.
 * 
 * @author yuuki
 *
 */
@Repository
public class ArticleRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	/**
	 * 結合したテーブルを表示するResultSetExtractor.
	 */
	private static final ResultSetExtractor<List<Article>>  ARTICLE_RESULT_SET_EXTRACTOR
	= (rs)->{
		List <Article> articleList = new ArrayList<>();
		
		int beforeAritcleId = 0;
		
		List <Comment> commentList = null;
		
		while (rs.next()) {
			Integer nowArticleId = rs.getInt("article_id");
			
			if (beforeAritcleId != nowArticleId) {
				Article article = new Article();
				article.setId(nowArticleId);
				article.setName(rs.getString("article_name"));
				article.setContent(rs.getString("article_content"));
				commentList = new ArrayList<>();
				article.setCommmentList(commentList);
				articleList.add(article);
			}
			
			if(rs.getInt("comment_id") != 0) {
				Comment comment = new Comment();
				comment.setId(rs.getInt("comment_id"));
				comment.setName(rs.getString("comment_name"));
				comment.setContent(rs.getString("comment_content"));
				comment.setArticleId(rs.getInt("comment_article_id"));
				
				commentList.add(comment);
			}		
			
			beforeAritcleId = nowArticleId;
		}
		return articleList;
	};
	
	/**
	 * 全ての記事を取得する.
	 * 
	 * @return DBに保存されている全ての記事
	 */
	public List<Article> findAll(){
		String sql = "SELECT a.id AS article_id , a.name AS article_name, a.content AS article_content," +
					 "c.id AS comment_id, c.name AS comment_name, c.content AS comment_content, c.article_id AS comment_article_id " + 
					 "FROM articles a LEFT JOIN comments c " + 
					 "ON a.id = c.article_id ORDER BY a.id DESC;";
		List<Article> articleList = template.query(sql, ARTICLE_RESULT_SET_EXTRACTOR);
		return articleList;
	}
	
}
