package com.lucareto.services;

import spark.Spark;

public class Dummy {
    
    public static void test() {
        Spark.get("/test", (req, res) -> {
            return "Hello";
        });
    }

}
