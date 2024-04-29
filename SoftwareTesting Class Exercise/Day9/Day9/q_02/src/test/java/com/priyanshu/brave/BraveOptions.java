package com.priyanshu.brave;

import org.openqa.selenium.chrome.ChromeOptions;

public class BraveOptions extends ChromeOptions {

    private static final String BRAVE_BROWSER_PATH = "C:\\Program Files\\BraveSoftware\\Brave-Browser\\Application\\brave.exe";

    public BraveOptions() {
        super();
        this.setBinary(BRAVE_BROWSER_PATH);
    }

}
