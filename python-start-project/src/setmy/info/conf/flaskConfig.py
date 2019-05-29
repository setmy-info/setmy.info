class FlaskConfig:

    DEBUG = False
    TESTING = False
    CSRF_ENABLED = True
    SECRET_KEY = 'NEED TO CHANGE THAT'
        

class ProductionConfig(FlaskConfig):
    DEBUG = False


class StagingConfig(FlaskConfig):
    DEVELOPMENT = True
    DEBUG = True


class DevelopmentConfig(FlaskConfig):
    DEVELOPMENT = True
    DEBUG = True


class TestingConfig(FlaskConfig):
    TESTING = True
