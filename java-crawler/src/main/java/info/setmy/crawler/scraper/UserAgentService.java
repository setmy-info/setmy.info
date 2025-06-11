package info.setmy.crawler.scraper;

import java.util.concurrent.ThreadLocalRandom;

import static info.setmy.crawler.scraper.Constants.USER_AGENTS;

public class UserAgentService {

    public static final UserAgentService userAgentService = new UserAgentService();

    public String randomUserAgent() {
        return randomUserAgent(USER_AGENTS);
    }

    public String randomUserAgent(String[] userAgents) {
        int randomIndex = ThreadLocalRandom.current().nextInt(userAgents.length);
        return userAgents[randomIndex];
    }
}
