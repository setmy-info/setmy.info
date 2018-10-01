//http://localhost:8080/tomcat-start-project/json.groovy
import ee.pub.model.GPerson
response.contentType = 'application/json'
def jsonBuilder = new groovy.json.JsonBuilder()
def person = new GPerson(first: "Imre", last: "Tabur")
jsonBuilder(person: person)
def str = jsonBuilder.toPrettyString()
println str
