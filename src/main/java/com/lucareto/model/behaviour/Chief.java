package com.lucareto.model.behaviour;

public interface Chief extends Editor, Employeer {

    public void createIssue();
    
    public void addArticle(final String articleId);
    
    public void removeArticle(final String articleId);
    
    public void publishedIssue(final String issueId);
    
}
