package com.pozhenskii.Lab2.task1;

import java.util.Date;

public abstract class A {
    public String varA1 = "aaa";
    public int varA2;

    public int foo(Date dt) {
        return 0;
    }

    public void buzz() {
    }

    public void setVarA1(String varA1) {
        this.varA1 = varA1;
    }

    public void setVarA2(int varA2) {
        if(varA2 >= 0 && varA2 < 100) {
            this.varA2 = varA2;
        }
    }
}
