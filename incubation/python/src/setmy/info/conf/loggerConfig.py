import logging


class Logger:

    conf = None
        
    def init(self, conf):
        self.conf = conf
        logging.basicConfig(filename=self.conf.logDirectory + self.conf.logFileName , format=self.conf.logFormat, level=self.conf.logLevel)
