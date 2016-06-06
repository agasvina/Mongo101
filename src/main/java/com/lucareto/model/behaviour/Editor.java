package com.lucareto.model.behaviour;

import com.lucareto.model.Status;

public interface Editor extends Journalist {
    
    public void nominateArticle(final String articleId, final Status status, final String section);

}
