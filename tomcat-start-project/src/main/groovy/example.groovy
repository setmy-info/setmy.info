#!/usr/bin/env groovy

// /opt/groovy-2.0.5/bin/groovy

//On windows in .gsh or .groovy exec command line: "C:\groovy\bin\groovy.bat" "%1" %*
//Linux: groovy ./example.groovy -d "Directory" -h -n 1234 -s false
//   Or: ./example.groovy -d "Directory" -h -n 1234 -s false
import groovy.swing.SwingBuilder
import java.awt.BorderLayout as BL
import groovy.json.*
import groovy.xml.MarkupBuilder
//import org.custommonkey.xmlunit.*
//@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.5.2')
//import groovyx.net.http.*
    
def myFirstName = "Imre"
def myLastName = "Tabur"
def jsonText = '''
{
    "message": {
        "header": {
            "from": "Imre",
            "to": ["Groovy Users", "Java Users"]
        },
        "body": "Check out Groovy's gr8 JSON support."
    }
}       
'''
def xmlText = '''
<data>
  <person>
    <firstname>Imre</firstname>
    <lastname>Tabur</lastname>
    <street>Laagna</street>
    <city>Tallinn</city>
  </person>
  <financial>
    <netincome>120000000</netincome>
  </financial>
</data>
'''

class ExamplePerson {
    String firstName, lastName
}

def cl = new CliBuilder(usage: 'groovy clitest -d "dir" [-h] [-n "number"] [arguments]*')
cl.h(longOpt:'help', 'Show usage information and quit')
cl.d(argName:'dir', longOpt:'directory', args:1, required:true, 'Directory to Search, REQUIRED')
cl.n(argName:'number', longOpt:'number', args:1, required:false, 'Number of something')
cl.s(argName:'swing', longOpt:'swing', args:1, required:false, 'Boolean or something')
def opt = cl.parse(this.args)
if (opt.d) println "Directory: ${opt.d}"
if (opt.n) println "Number: ${opt.n}"
if (opt.s) println "Swing: ${opt.s}"

if(opt.s.toBoolean()) {
    count = 0
    new SwingBuilder().edt {
        frame(title:'Frame', size:[300,300], show: true) {
            borderLayout()
            textlabel = label(text:"Click the button!", constraints: BL.NORTH)
            button(text:'Click Me',
                actionPerformed: {count++; textlabel.text = "Clicked ${count} time(s)."; println "clicked"},
                constraints:BL.SOUTH)
        }
    }
} else {

    println "Hello, world ${myFirstName} ${myLastName}"
    for (arg in this.args ) {
        println "Argument:" + arg;
    }
    def json = new JsonSlurper().parseText(jsonText)
    def header = json.message.header
    assert header.from == 'Imre'
    assert header.to[0] == 'Groovy Users'
    assert header.to[1] == 'Java Users'
    assert json.message.body == "Check out Groovy's gr8 JSON support."
	
    def xmlData = new XmlSlurper().parseText(xmlText)
    assert xmlData.person.firstname == 'Imre'
	
    def person = new ExamplePerson(firstName: "Imre", lastName: "Tabur")
    def jsonBuilder = new JsonBuilder()
    jsonBuilder person
    println jsonBuilder.toString()
	
    assert "y".toBoolean()
    assert 'TRUE'.toBoolean()
    assert '  trUe  '.toBoolean()
    assert " y".toBoolean()
    assert "1".toBoolean()

    assert ! 'other'.toBoolean()
    assert ! '0'.toBoolean()
    assert ! 'no'.toBoolean()
    assert ! '  FalSe'.toBoolean()
    
    
    def writer = new StringWriter()
    def xml = new MarkupBuilder(writer)
    //Closures - Formal Definition 
    xml.records() {
        personality(firstName: "Imre", lastName: "Tabur", birtDate: new Date().parse("dd.MM.yyyy","23.12.1975")) {
            country("Estonia")
            record(type: "country", population: 1294236)
        }
    }
    println writer.toString()
    

    //def http = new HTTPBuilder("http://www.google.com")
    //def html = http.get([:])
    //println html
}
