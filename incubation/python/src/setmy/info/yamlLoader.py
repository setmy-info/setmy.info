import yaml


class YamlLoader:

    def load(self, fileName):
        with open(fileName, 'r') as stream:
            try:
                return yaml.safe_load(stream)
            except yaml.YAMLError as exc:
                raise exc
        return None
