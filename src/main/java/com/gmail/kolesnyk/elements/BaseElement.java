package com.gmail.kolesnyk.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

public abstract class BaseElement {

    private final WebElement componentElement;

    public BaseElement(WebElement element) {
        DefaultElementLocatorFactory parentContext = new DefaultElementLocatorFactory(element);
        PageFactory.initElements(parentContext, this);
        this.componentElement = element;
    }

    protected WebElement getComponentElement() {
        return componentElement;
    }
}
