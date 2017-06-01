package com.ssm.framework.utils.international;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import org.springframework.context.support.ResourceBundleMessageSource;

public class ResourceBundleMessage extends ResourceBundleMessageSource {
    private String[] extendBaseNames;
    
    @Override
    public void setBasenames(String... basenames) {
        String tempBaseName = basenames[0].toString();
        int length = tempBaseName.split(",").length;
        extendBaseNames = new String[length];

        for (int i = 0; i < length; i++) {
            extendBaseNames[i] = tempBaseName.split(",")[i];
        }
        super.setBasenames(extendBaseNames);
    }
    
    @Override
    protected MessageFormat createMessageFormat(String msg, Locale locale) {
        String escapedMsg = msg;
        if (escapedMsg == null) {
            escapedMsg = "";
        }
        escapedMsg = escapedMsg.replaceAll("'", "''");
        return new MessageFormat(escapedMsg, locale);
    }

    public Set<String> getKeys(String basename, Locale locale) {
        ResourceBundle bundle = getResourceBundle(basename, locale);
        if (bundle == null) {
            return new HashSet<String>();
        } else {
            return bundle.keySet();
        }
    }
}
