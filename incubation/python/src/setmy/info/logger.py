import logging

from setmy.info.constants import FORMAT


class Logger:

    conf = None

    def __init__(self):
        '''
        Constructor
        '''
        
    def init(self, conf):
        self.conf = conf
        logging.basicConfig(filename='log.log', format=FORMAT, level=logging.DEBUG)
