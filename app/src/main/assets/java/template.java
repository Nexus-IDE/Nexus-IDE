package com.my.newproject;

public class Main {

    // Variables

    // Variables#string
    public String defString;
    public static String constString = "I AM A STATIC";
    String withoutPrivacyString;
    static String withoutPrivacyConstString = "I AM A STATIC";

    // Variables#int
    public int defInt;
    public static int constInt = 0;
    int withoutPrivacyInt;
    static int withoutPrivacyConstInt = 0;

    // Variables#boolean
    public boolean defBoolean;
    public static boolean constBoolean = false;
    boolean withoutPrivacyBoolean;
    static boolean withoutPrivacyConstBoolean = true;

    // Methods

    // methods#void
    private void myVoidMethod() {
        // method content
    }

    public static void myStaticVoidMethod() {
        // method content
    }

    // methods#string
    public String myStringMethod() {
        // method content
        return "Hello, World!";
    }

    public static String myStaticStringMethod() {
        // method content
        return "I am a static method.";
    }

    // and more...
}