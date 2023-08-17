package org.example;

import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.PropertyKey;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class BundleManager {

    public static final String BUNDLE = "strings";

    private @Nls String sameName(@PropertyKey(resourceBundle = "strings") String key) {
        return ResourceBundle.getBundle(key, Locale.ENGLISH).getString(key);
    }

    private @Nls String sameName(@PropertyKey(resourceBundle = "strings") String key, Object... args) {
        return MessageFormat.format(sameName(key), args);
    }

    private @Nls String differentName(@PropertyKey(resourceBundle = "strings") String key) {
        return ResourceBundle.getBundle(key, Locale.ENGLISH).getString(key);
    }

    private @Nls String differentNameArgs(@PropertyKey(resourceBundle = "strings") String key, Object... args) {
        return MessageFormat.format(sameName(key), args);
    }

    public void testMethodsWithSameName() {
        // OK
        String value = sameName("first.key");
        // Missing "Argument with index '0' is not used in the pattern"
        value = sameName("first.key", "VALUE");


        //OK
        value = sameName("key.with.parameter", "VALUE");
        // Missing "Property 'key.with.parameter' expected 1 parameter, passed 0"
        value = sameName("key.with.parameter");
        // Missing "Argument with index '1' is not used in the pattern"
        value = sameName("key.with.parameter", "VALUE", "VALUE2");


        // Missing "Property 'key.with.two.parameters' expected 2 parameter, passed 0"
        value = sameName("key.with.two.parameters");
        // OK "Property 'key.with.two.parameters' expected 2 parameters, passed 1"
        value = sameName("key.with.two.parameters", "VALUE");
        // OK
        value = sameName("key.with.two.parameters", "VALUE", "VALUE2");


    }

    public void testMethodWithDifferentName(){
        // OK
        String value = differentName("first.key");
        // Missing "Property 'first.key' expected 0 parameter, passed 1"
        value = differentNameArgs("first.key", "VALUE");


        //OK
        value = differentNameArgs("key.with.parameter", "VALUE");
        // Missing "Property 'key.with.parameter' expected 1 parameter, passed 0"
        value = differentNameArgs("key.with.parameter");
        // Missing "Argument with index '1' is not used in the pattern"
        value = differentNameArgs("key.with.parameter", "VALUE", "VALUE2");


        // OK "Property 'key.with.two.parameters' expected 2 parameter, passed 0"
        value = differentNameArgs("key.with.two.parameters");
        // OK "Property 'key.with.two.parameters' expected 2 parameters, passed 1"
        value = differentNameArgs("key.with.two.parameters", "VALUE");
        // OK
        value = differentNameArgs("key.with.two.parameters", "VALUE", "VALUE2");

    }
}
