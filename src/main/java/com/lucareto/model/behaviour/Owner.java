package com.lucareto.model.behaviour;

import java.util.Map;

public interface Owner extends Employeer {

    public Map<String, Object> sellArticle();
    
    public Map<String, Object> payEmployees();
    
}
