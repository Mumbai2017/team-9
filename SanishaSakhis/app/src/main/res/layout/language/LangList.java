package com.sumit.dell_pc.sanisasakhis.language;

/**
 * Created by DELL_PC on 7/25/2017.
 */

public class LangList {
    private String langDemo;
    private String langName;

    public LangList(String langDemo, String langName) {
        this.langDemo = langDemo;
        this.langName = langName;
    }

    public String getLangDemo() {
        return langDemo;
    }

    public String getLangName() {
        return langName;
    }
}
