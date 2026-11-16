package info.setmy.microservice.service;

@FunctionalInterface
public interface EntryPointService {

    int run(String... args) throws Exception;
}
