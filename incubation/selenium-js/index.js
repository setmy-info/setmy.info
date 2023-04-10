const {By, Builder, Browser, WebDriver, Capabilities} = require('selenium-webdriver');
const firefox = require('selenium-webdriver/firefox');
const chrome = require('selenium-webdriver/chrome');
const {suite} = require('selenium-webdriver/testing');
const assert = require("assert");

console.log("Hello, World!");

let firefoxCapabilities = Capabilities.firefox();
let firefoxOptions = new firefox.Options();
let chromeCapabilities = Capabilities.chrome();
let chromeOptions = new chrome.Options();

let driver = new Builder()
    .usingServer("http://192.168.1.74:4444")
    .withCapabilities(firefoxCapabilities)
    .forBrowser('firefox')
    .setFirefoxOptions(firefoxOptions)
    //.withCapabilities(chromeCapabilities)
    //.forBrowser('chrome')
    //.setFirefoxOptions(chromeOptions)
    .build();

driver.get('https://www.selenium.dev/selenium/web/web-form.html');

//driver.manage().setTimeouts({implicit: 1000});

let title = driver.getTitle();
let textBox = driver.findElement(By.name('my-text'));
let passwordBox = driver.findElement(By.name('my-password'));
let textArea = driver.findElement(By.name('my-textarea'));

textBox.sendKeys('Selenium');
passwordBox.sendKeys('Password');
textArea.sendKeys('Hello, World!');

//let submitButton = driver.findElement(By.css('button'));
//submitButton.click();

/*
assert.equal("Web form", title);


submitButton.click();

let message = driver.findElement(By.id('message'));
let value = message.getText();
assert.equal("Received!", value);
*/
//driver.quit();
