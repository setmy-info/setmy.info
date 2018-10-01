//http://localhost:8080/tomcat-start-project/test.groovy
println '<!DOCTYPE html>'
html.html(lang : "en")
{
    head()
    {
        title 'Request Information'
    }
    body()
    {
        span('Hello World', id: 'helloWorld')
        table(border : "2")
        {
            tr()
            {
                td("Context Path");
                td("${request.getContextPath()}");
            }
 
            tr()
            {
                td("HTTP Method");
                td("${request.getMethod()}");
            }
 
            tr()
            {
                td("Path Info ");
                td("${request.getPathInfo()}");
            }
        }
    }
}
