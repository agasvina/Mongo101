package com.lucareto.db;

public class DBHolder {
    
    private static final ArticleDAO articleDAO = new ArticleDAO();
    
    private static final PersonDAO personDAO = new PersonDAO();

    public static ArticleDAO getArticledao() {
        return articleDAO;
    }

    public static PersonDAO getPersondao() {
        return personDAO;
    }
}
