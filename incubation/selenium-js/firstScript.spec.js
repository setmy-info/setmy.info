const {By, Builder, Browser, WebDriver, Capabilities} = require('selenium-webdriver');
const firefox = require('selenium-webdriver/firefox');
const chrome = require('selenium-webdriver/chrome');
const {suite} = require('selenium-webdriver/testing');
const assert = require("assert");

let firefoxCapabilities = Capabilities.firefox();
let firefoxOptions = new firefox.Options();
let chromeCapabilities = Capabilities.chrome();
let chromeOptions = new chrome.Options();

let browsers = [/*Browser.CHROME, */Browser.FIREFOX];

suite(function (env) {

    describe('First script', function () {

        let driver;

        before(async function () {
            //driver = await new Builder().forBrowser('chrome').build();
            driver = await new Builder()
                .usingServer("http://192.168.1.74:4444")
                .withCapabilities(firefoxCapabilities)
                .forBrowser('firefox')
                .setFirefoxOptions(firefoxOptions)
                //.withCapabilities(chromeCapabilities)
                //.forBrowser('chrome')
                //.setFirefoxOptions(chromeOptions)
                .build();
        });

        after(async function () {
            await driver.quit();
        });

        it('First Selenium script', async function () {

            await driver.manage().setTimeouts({implicit: 500});

            await driver.get('https://www.selenium.dev/selenium/web/web-form.html');

            let title = await driver.getTitle();
            let textBox = await driver.findElement(By.name('my-text'));
            let passwordBox = await driver.findElement(By.name('my-password'));
            let textArea = await driver.findElement(By.name('my-textarea'));
            let submitButton = await driver.findElement(By.css('button'));

            textBox.sendKeys('Selenium');
            passwordBox.sendKeys('Password');
            textArea.sendKeys('Hello, World!');

            assert.equal("Web form", title);

            submitButton.click();

            await driver.manage().setTimeouts({implicit: 1000});

            let message = await driver.findElement(By.id('message'));
            let value = await message.getText();
            assert.equal("Received!", value);
        });
    });
}, {browsers: browsers});
