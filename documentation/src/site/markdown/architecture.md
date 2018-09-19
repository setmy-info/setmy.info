# Architecture

Why predefined architecture?

1. Most of the important is that, decision are made (you like it or not), that means some kind of plan are made.
    - More time - no need to spend time to argue, where oposide sides should prove something, prepare for proving,
    search information for proving, reading and listening eachother, misunderstanding,  etc etc

2. Bad plan is a better than no plan.

## Principles

1. *Same version*. All components should have same version, simple to remember and add ass depndency.

2. *POM similarity*. java-model, java-service and springboot-start-project are made as base and other should follow these structures 1:1 as much as possible.

3. *Standardizing and stability as mush as possible*. So third partys (private companies etc) should have possibility to make tool top on all project
possibilites - component locations in folder structure, module/component folder structure, libraries and modules, tools, frameworks used.

## Decisions

### Decided tools, components and libraries

1. Linux: in priority order (higher to lower) CentOS, Fedora linux, Debian, FreeBSD and OpenIndiana.
2. Docker, Jail and Zone.
3. Kubernetes for Docker.



x. Maven with maven site, where site have integrated reports: JavaDoc (for main and test code), pitest, OWASP dependencies check, JaCoCo unit test coverage, style check, version notes, todo notes, findbugs.
x. Hibernate
x. Spring and spring boot, but as much as possible in standard way (javax.*).

x. 


