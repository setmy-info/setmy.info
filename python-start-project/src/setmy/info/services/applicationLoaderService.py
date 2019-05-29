
from setmy.info.services.yamlLoaderService import YamlLoaderService
from setmy.info.model.properties import ApplicationProperties


class ApplicationLoaderService:

    CONFIG_FILE_NAME = "./config/application.yml"

    __instance = None

    def __init__(self):
        self.yamlLoaderService = YamlLoaderService.getInstance()

    def loadApplicationProperties(self):
        loaded = self.load()
        return ApplicationProperties(loaded)

    def load(self):
        self.loaded = self.yamlLoaderService.load(ApplicationLoaderService.CONFIG_FILE_NAME)
        return self.loaded

    @staticmethod
    def getInstance():
        if ApplicationLoaderService.__instance is None:
            ApplicationLoaderService.__instance = ApplicationLoaderService()
        return ApplicationLoaderService.__instance
