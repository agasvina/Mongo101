package com.lucareto.model.behaviour;

import java.util.List;

import com.lucareto.model.Article;

public interface Journalist {
    
    public List<Article> getArticles(final String sectionId);
    
    public Article getArticle(final String articleId, final String sectionId, final String authorId);
    
    public void writeArticle(final String authorId, final String sectionId);
    
    public void deleteArticle(final String articleId, final String sectionId, final String authorId);
    
}
