set CUR_DIR=%~dp0
set PATH=%CUR_DIR%;%PATH%
call protractor ./src/test/js/e2e/protractor.conf.js