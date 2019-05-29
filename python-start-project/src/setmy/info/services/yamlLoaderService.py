import yaml

class YamlLoaderService:

    __instance = None

    def load(self, fileName):
        with open(fileName, 'r') as stream:
            try:
                return yaml.safe_load(stream)
            except yaml.YAMLError as exc:
                raise exc
        return None

    @staticmethod
    def getInstance():
        if YamlLoaderService.__instance is None:
            YamlLoaderService.__instance = YamlLoaderService()
        return YamlLoaderService.__instance
