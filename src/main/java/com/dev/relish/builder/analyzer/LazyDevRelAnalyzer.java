package com.dev.relish.builder.analyzer;

import com.dev.relish.builder.analyzer.DevRelAnalyzer;

public class LazyDevRelAnalyzer implements DevRelAnalyzer {
    @Override
    public String findMostCriticalActivity() {
        return "nothing to do, all good";
    }
}
