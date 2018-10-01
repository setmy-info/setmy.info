def os = System.getProperty("os.name").toLowerCase()
def buildCommand
if((os.indexOf("bsd") >= 0 ||os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0 || os.indexOf("aix") > 0 )) {
    buildCommand= """./build.sh"""
} else if((os.indexOf("win") >= 0)) {
    buildCommand= """./build.bat"""
}
def buildProc = buildCommand.execute()
buildProc.waitFor()
