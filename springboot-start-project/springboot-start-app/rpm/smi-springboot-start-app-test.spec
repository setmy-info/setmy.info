%global srcname smi-springboot-start-app
%global app springboot-start-app
%global smi_home /opt/setmy.info
%global smi_var /var/opt/setmy.info
%global workdir %{smi_var}/microservice
%global confdir %{smi_home}/etc/%{app}
%global unitdir %{smi_home}/etc/systemd/system
%global profile_marker TEST

Name:           smi-springboot-start-app-test
Version:        %{?smi_version}%{!?smi_version:1.3.0}
Release:        %{?smi_release}%{!?smi_release:1}
Summary:        springboot-start-app Spring Boot service, TEST machine

License:        Proprietary
Vendor:         Hear And See Systems OÜ
URL:            https://setmy.info
Source0:        %{srcname}-%{version}.tar.gz

BuildArch:      noarch
# No dependencies at all: the package installs on any machine, whatever else is there or not. setmy-info-scripts,
# /opt/jdk and the other package of the pair are expected but not required, and the scriptlets cope without them.
# AutoReqProv off, so rpm generates nothing from the content either; the owners in %%files are root only, because
# rpm turns every other owner into a user()/group() requirement.
AutoReqProv:    no

Conflicts:      smi-springboot-start-app

%description
The springboot-start-app Spring Boot service on the TEST machine, Spring
profile "test". The same jar goes to LIVE in smi-springboot-start-app,
which takes profile "live"; the two packages conflict.

  /opt/setmy.info/lib/springboot-start-app.jar                       the executable jar
  /opt/setmy.info/etc/systemd/system/springboot-start-app.service    the service, as microservice
  /etc/systemd/system/springboot-start-app.service                   a symlink to it

The service runs /opt/setmy.info/bin/smi-spring-boot of setmy-info-scripts
as the microservice user, in /var/opt/setmy.info/microservice, which is also
the log directory (springboot-start-app.log). Java is /opt/jdk, installed by
hand and not by a package. The HTTP port is the one of application.yaml.

The machine configuration is not in this package: it comes from the separate
configuration repository and package, as

  /opt/setmy.info/etc/springboot-start-app/springboot-start-app.env   every machine
  /opt/setmy.info/etc/springboot-start-app/machine.env                this environment
  /opt/setmy.info/etc/springboot-start-app/local.env                  by hand, secrets
  /opt/setmy.info/etc/springboot-start-app/springboot-start-app.yaml  extra Spring config

all optional. JVM flags go into JDK_JAVA_OPTIONS of an .env file.

The unit carries one commented line per environment, "# NB! LIVE ONLY: " and
"# NB! TEST ONLY: ". The %post scriptlet removes the prefix of its own
environment, which switches its Spring profile on.

The %pre scriptlet creates the system user microservice when setmy-info-scripts
has not, %post creates the working directory, enables the service and restarts
it when it runs, so a deployment of a new version is a restart. It is started
when the configuration package is already installed; otherwise the
configuration package starts it when it arrives.

%prep
%autosetup -n %{srcname}-%{version}

%build

%install
install -d -m 0755 %{buildroot}%{smi_home}/lib
install -d -m 0755 %{buildroot}%{unitdir}
install -d -m 0755 %{buildroot}%{_sysconfdir}/systemd/system
install -m 0644 %{app}.jar %{buildroot}%{smi_home}/lib/%{app}.jar
install -m 0644 %{app}.service %{buildroot}%{unitdir}/%{app}.service
ln -s %{unitdir}/%{app}.service %{buildroot}%{_sysconfdir}/systemd/system/%{app}.service

%pre
# The service user, the same lines in the application and in the configuration packages: whichever is installed
# first creates it, setmy-info-scripts creates the same name. No home is created, no login shell.
# Never fails the installation.
getent group microservice >/dev/null 2>&1 || groupadd -r microservice || :
getent passwd microservice >/dev/null 2>&1 || \
    useradd -r -g microservice -M -d /var/opt/setmy.info/microservice -s /sbin/nologin \
        -c "setmy.info microservice" microservice || :
if ! getent passwd microservice >/dev/null 2>&1 || ! getent group microservice >/dev/null 2>&1; then
    echo "%{name}: warning: user or group microservice could not be created, installing anyway" >&2
fi
exit 0

%post
# the real file, not the symlink: sed -i would replace the link with a copy
sed -i 's/^\( *\)# NB! %{profile_marker} ONLY: /\1/' %{unitdir}/%{app}.service
install -d -m 0755 %{smi_var}
if getent passwd microservice >/dev/null 2>&1; then
    install -d -m 0750 -o microservice -g microservice %{workdir}
else
    echo "%{name}: user microservice is missing, %{workdir} not created" >&2
fi
restorecon -RF %{workdir} %{smi_home}/lib/%{app}.jar %{unitdir}/%{app}.service >/dev/null 2>&1 || :
systemctl daemon-reload >/dev/null 2>&1 || :
systemctl enable %{app} >/dev/null 2>&1 || :
if [ -e %{confdir}/machine.env ]; then
    systemctl restart %{app} || :
else
    systemctl try-restart %{app} || :
fi

%preun
if [ $1 -eq 0 ] && ! rpm -q --quiet smi-springboot-start-app; then
    systemctl disable --now %{app} >/dev/null 2>&1 || :
fi

%postun
systemctl daemon-reload >/dev/null 2>&1 || :

%files
%attr(0644,root,root) %{smi_home}/lib/%{app}.jar
%verify(not md5 size mtime) %attr(0644,root,root) %{unitdir}/%{app}.service
%{_sysconfdir}/systemd/system/%{app}.service

%changelog
* Thu Nov 12 2026 Imre Tabur <imre.tabur@mail.ee> - 1.2.0-1
- First package: the executable jar in /opt/setmy.info/lib, started by smi-spring-boot as microservice in
  /var/opt/setmy.info/microservice, Spring profile switched on at install time, no environment files
