package com.yinuo.api.config;

import com.yinuo.api.model.config.WebPatterns;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Title.
 * <p>
 * Description.
 *
 * @author Bill Lv {@literal <billcc.lv@hotmail.com>}
 * @version 1.0
 * @since 2016-05-28
 */
public class WebPatternConfig {
    static final Logger LOGGER = LoggerFactory.getLogger(WebPatternConfig.class);

    static class WebPatternConfigHolder {
        static final WebPatternConfig INSTANCE = new WebPatternConfig();
    }

    public static WebPatternConfig getInstance() {
        return WebPatternConfigHolder.INSTANCE;
    }

    private String[] antPatterns;

    private List<String> appClientPatterns;

    private WebPatternConfig() {
        InputStream input = getClass().getClassLoader().getResourceAsStream("web-pattern.yaml");
        Constructor constructor = new Constructor(WebPatterns.class);
        TypeDescription webPatternsDescription = new TypeDescription(WebPatterns.class);
        webPatternsDescription.putListPropertyType("antPatterns", String.class);
        webPatternsDescription.putListPropertyType("appClientPatterns", String.class);
        constructor.addTypeDescription(webPatternsDescription);
        Yaml yaml = new Yaml(constructor);
        WebPatterns webPatterns = (WebPatterns) yaml.load(input);
        antPatterns = webPatterns.getAntPatterns().toArray(new String[0]);
        appClientPatterns = webPatterns.getAppClientPatterns();
    }

    public String[] getAntPatterns() {
        return antPatterns;
    }

    public Boolean isAppClientPattern(String input) {
        return appClientPatterns.stream().filter(p -> matchPattern(p, input)).count() > 0;
    }

    private Boolean matchPattern(String patternStr, String input) {
        final String newPatternStr = patternStr.replaceAll("\\*\\*", "\\\\S*");
        Pattern pattern = Pattern.compile(newPatternStr);
        return pattern.matcher(input).matches();
    }
}
