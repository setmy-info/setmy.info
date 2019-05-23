class NewConfig:
    '''All props here for all config'''
    ''' Make it abstract ? '''

    host = None
    port = None

    restRoot = None

    logDirectory = None
    logFileName = None
    logLevel = None

    def __init__(self):
        '''
        Constructor
        '''


class NewDefaultConfig(NewConfig):

    def __init__(self):
        '''
        Constructor
        '''       


class NewEnvironmentConfig(NewConfig):

    def __init__(self):
        '''
        Constructor
        '''       


class NewFileConfig(NewConfig):

    def __init__(self):
        '''
        Constructor
        '''       


class NewCmdLineConfig(NewConfig):

    def __init__(self):
        '''
        Constructor
        '''       
