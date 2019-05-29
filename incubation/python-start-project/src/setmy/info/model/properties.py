class ServerProperties:
    
    def __init__(self, loaded):
        self.host = loaded['host']
        self.port = loaded['port']
        self.templateFolder = loaded['templateFolder']
        self.staticFolder = loaded['staticFolder']


class LoggingProperties:
    
    def __init__(self, loaded):
        self.directory = loaded['directory']
        self.fileName = loaded['fileName']
        self.level = loaded['level']
        self.format = loaded['format']
        self.size = loaded['size']


class RestProperties:
    
    def __init__(self, loaded):
        self.root = loaded['root']


class ApplicationProperties:
    
    def __init__(self, loaded):
        self.server = ServerProperties(loaded['server'])
        self.log = LoggingProperties(loaded['log'])
        self.rest = RestProperties(loaded['rest'])
