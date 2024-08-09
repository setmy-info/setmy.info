from setuptools import setup

setup(
   name='python-start-project',
   version='1.0.0',
   description='Python start project',
   author='Imre Tabur',
   author_email='imre.tabur@mail.ee',
   packages=['python-start-project'],
   install_requires=['wheel', 'bar', 'greek'],
   scripts=[
       'scripts/foo',
       'scripts/bar',
   ]
)
