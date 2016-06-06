package com.lucareto.db;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.lucareto.model.Article;

public class ArticleDAO {

    private static Map<String, Article> articles = new HashMap<>();
    
    public Article getArticle(String articleId) {
        return articles.get(articleId);
    }
    
    public Stream<Article> getArticles(Integer limit, Integer skip, Predicate<Article> filter) {
        if(Objects.nonNull(limit) && Objects.nonNull(skip))
            return articles.values().stream().skip(skip).limit(limit).filter(filter);
        else if(Objects.nonNull(limit))
            return articles.values().stream().limit(limit).filter(filter);
        else if(Objects.nonNull(skip))
            return articles.values().stream().skip(skip).filter(filter);
        else return articles.values().stream().filter(filter);
    }
    
    public boolean isArticleExist(final String articleId) {
       return articles.containsKey(articleId);
    }
    
    public void removeArticle(final String articleId) {
        removeArticle(articleId, null);
    }
    
    public void removeArticle(final String articleId, final String sectionId) {
        removeArticle(articleId, sectionId, null);
    }
    
    public void removeArticle(final String articleId, final String sectionId, final String authorId) {
        Optional.of(articles.get(articleId)).ifPresent(article -> {
            if(Objects.nonNull(sectionId) && Objects.nonNull(authorId)) {
                if(article.getSection().equals(sectionId) && article.getAuthorId().equals(authorId))
                    articles.remove(articleId);
            } else if(Objects.nonNull(sectionId)) {
                if(article.getSection().equals(sectionId))
                    articles.remove(articleId);
            } else articles.remove(articleId);
        });
    }
    
    public void addArticle(final Article article) {
        articles.put(article.getId(), article);
    }
    
    public void updateArticle(final Article updatedArticle) {
        Optional.of(updatedArticle)
            .filter(article -> Objects.nonNull(article.getId()))
            .ifPresent(upArticle -> {
                Article current = articles.get(upArticle.getId());
                articles.put(upArticle.getId(), upArticle.copyPropertyTo(current));
            
        });
    }
    
}
