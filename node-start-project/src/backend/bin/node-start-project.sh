#!/bin/sh
#
# PROVIDE: node-start-project
# REQUIRE: NETWORKING SERVERS DAEMON
# BEFORE:  LOGIN
# KEYWORD: shutdown

. /etc/rc.subr

name=node-start-project
rcvar=node-start-project_enable
extra_commands="status"
status_cmd="status"
#start_cmd="start"
#stop_cmd="stop"
#restart_cmd="restart"

command="/usr/local/bin/node /usr/local/bin/node-start-project"

load_rc_config $name

HOME=/var/run/node-start-project

status() {
    echo "Status for: node-start-project"
}

#
# DO NOT CHANGE THESE DEFAULT VALUES HERE
# SET THEM IN THE /etc/rc.conf FILE
#
node-start-project_enable=${node-start-project_enable-"NO"}
pidfile=${node-start-project_pidfile-"/var/run/node-start-project.pid"}

run_rc_command "$1"
