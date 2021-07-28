package ru.page;

import com.codeborne.selenide.*;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class YanMarketPageSearch {

    private String selectorManufacturer = "//fieldset[@data-autotest-id=7893318]//label";
    private String selectorWait = "//div[@data-apiary-widget-id='/content/pager']";
    private String selectorDropDown = "//button[contains(@id,'dropdown-control')]";
    private String selectorButShowItem = "//div[contains(@aria-labelledby,'dropdown-control')]//button";
    private String selectorProduct = "//article[@data-autotest-id='product-snippet']";
    private String selectorNextPage = "//a[@aria-label='Следующая страница']";

    private String nameProduct;

    @Step("Выбираем производителя {name}")
    public YanMarketPageSearch manufacturer (String name){
            nameProduct = name;
            SelenideElement manuf = $$x(selectorManufacturer).filter(text(name)).first();
            manuf.click();
            String id = manuf.$x("./input").getAttribute("id").split("_")[1];
            $x(selectorWait+"//a[contains(@href,'"+id+"')]").shouldBe(appear);
            return this;
    }

    @Step("меняем колличество элементов страницы на 12")
    public YanMarketPageSearch show12Items(){
        $x(selectorDropDown).click();
        $$x(selectorButShowItem).first().click();
        $$x(selectorProduct).shouldHave(CollectionCondition.size(12));
        return this;
    }

    @Step("Проверяем, что в выборку попали только {nameProduct}")
    public YanMarketPageSearch checkSampleProduct(){
        List<String> list = new ArrayList<>();
        int count = 2;
        do {
            ElementsCollection elements = $$x(selectorProduct);
            for (SelenideElement element : elements) {
                list.add(element.$x(".//h3").getAttribute("textContent"));
            }
            $x(selectorNextPage).click();
            $x("//a[@aria-label='Страница "+count+" (текущая)']").shouldBe(exist);
            count++;
        }
       while ($x(selectorNextPage).exists());
        Assertions.assertTrue(list.stream().allMatch(x -> x.contains(nameProduct)), "в выборку попали не только "+nameProduct);
        return this;
    }
}
