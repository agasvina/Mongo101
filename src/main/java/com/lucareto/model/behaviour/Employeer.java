package com.lucareto.model.behaviour;

public interface Employeer {

    public void hireNewJournalist();
    
    public void rehireJournalist(final String journalistId);
    
    public void fireJournalist(final String journalistId);
    
}
