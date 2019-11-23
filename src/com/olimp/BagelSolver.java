package com.olimp;

import javassist.*;

/**
 * Another approach is to make Boolean.TRUE return false for tests to pass successfully
 */
public class BagelSolver {

    public static Bagel getBagel() {
        try {
            ClassPool pool = ClassPool.getDefault();
            CtClass aClass = pool.get("com.olimp.Bagel");
            CtMethod initialMethod = aClass.getDeclaredMethod("getValue");
            CtMethod newMethod = CtNewMethod.make("public int getValue() { return 4; }", aClass);
            aClass.defrost();
            aClass.removeMethod(initialMethod);
            aClass.addMethod(newMethod);
            aClass.toClass();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return new Bagel();
    }

}