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

4. Solution levels

    1: Only JWT check with symmetric keys. No session cancellation (JWT revoke). Fully stateless solutions. No central solutions cache for apps. Single node solutions. App backend as GW.
    2: DB JWT check with symmetric keys. DB based session cancellation and expiration (JWT revoke). Single node solutions. App backend as GW. No central cache solutions for apps.
    3. DB JWT check with symmetric and assymetric keys. DB based session cancellation and expiration (JWT revoke). Multi node solutions. App backend as GW. No central cache solutions for apps.
    4. Central Cache and session storage. JWT and session revoke in cache systems. Multi node solutions. Multi HW servers only.
    5. Central Cache and session storage. JWT and session revoke in identiti management systems. Multi node systesm. Health checks. API GW (rate limiting, security, identity management etc). Multiple hardware (servers, network nodes, powwersuplies, UPS etc) nodes. Storage systems.

