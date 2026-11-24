#!/bin/sh

set -e

NEW_VERSION="${1:?Usage: update-versions.sh <new-version> [changelog line]  (e.g. 1.3.0-SNAPSHOT or 1.3.0)}"
CHANGELOG_LINE="${2:-}"
MVN="${MVN:-mvn}"

if [ ! -f pom.xml ] || [ ! -d springboot-start-project ]; then
    printf 'pom.xml not found: run this from the project root\n' >&2
    exit 1
fi

case "${NEW_VERSION}" in
    *-SNAPSHOT) NEW_BASE="${NEW_VERSION%-SNAPSHOT}" ;;
    *) NEW_BASE="${NEW_VERSION}" ;;
esac

case "${NEW_BASE}" in
    ''|*[!0-9.]*|.*|*.|*..*) printf 'not a version: %s\n' "${NEW_VERSION}" >&2; exit 1 ;;
esac

OLD_VERSION=$(sed -n 's|^    <version>\(.*\)</version>.*|\1|p' pom.xml | head -1)
OLD_BASE="${OLD_VERSION%-SNAPSHOT}"

if [ -z "${OLD_VERSION}" ]; then
    printf 'no project <version> in pom.xml\n' >&2
    exit 1
fi

if [ "${OLD_VERSION}" = "${NEW_VERSION}" ]; then
    printf 'already at %s\n' "${NEW_VERSION}" >&2
    exit 1
fi

OLD_RE=$(printf '%s' "${OLD_VERSION}" | sed 's/[.]/\\./g')
OLD_BASE_RE=$(printf '%s' "${OLD_BASE}" | sed 's/[.]/\\./g')

"${MVN}" -q versions:set -DnewVersion="${NEW_VERSION}" -DprocessAllModules=true -DgenerateBackupPoms=false

POMS=$(find . -name pom.xml \
    -not -path './.git/*' -not -path '*/target/*' -not -path './submodules/*' \
    -not -path './attic/*' -not -path './incubation/*' -not -path '*/archived/*')

for POM in ${POMS}; do
    awk -v old="<version>${OLD_VERSION}</version>" -v new="<version>${NEW_VERSION}</version>" '
        /<groupId>/ { group = $0; sub(/.*<groupId>/, "", group); sub(/<\/groupId>.*/, "", group) }
        group == "info.setmy" && index($0, old) { sub(old, new) }
        { print }
    ' "${POM}" > "${POM}.update-versions" && mv "${POM}.update-versions" "${POM}"
done

sed -i "s/DOCKER_PROJECT_VERSION=\"${OLD_RE}\"/DOCKER_PROJECT_VERSION=\"${NEW_VERSION}\"/" tomcat-start-project/Makefile
sed -i "s/org\.label-schema\.version=\"${OLD_RE}\"/org.label-schema.version=\"${NEW_VERSION}\"/" tomcat-start-project/Dockerfile
sed -i "s/tomcat-start-project-${OLD_RE}\.war/tomcat-start-project-${NEW_VERSION}.war/g" \
    tomcat-start-project/Dockerfile tomcat-start-project/Jailfile
sed -i "s|info\.setmy/java-clojure \"${OLD_RE}\"|info.setmy/java-clojure \"${NEW_VERSION}\"|" \
    java-clojure/project.clj java-clojure/deps.edn

SPRINGBOOT_APP_DIR=springboot-start-project/springboot-start-app

for SPEC in "${SPRINGBOOT_APP_DIR}"/rpm/*.spec; do
    sed -i "s/%{!?smi_version:[0-9][0-9.]*}/%{!?smi_version:${NEW_BASE}}/" "${SPEC}"
done

sed -i "s/\`${OLD_RE}\` gives version \`${OLD_BASE_RE}\`/\`${NEW_VERSION}\` gives version \`${NEW_BASE}\`/" \
    "${SPRINGBOOT_APP_DIR}/src/site/markdown/rpm.md"

if [ -n "${CHANGELOG_LINE}" ]; then
    ENTRY=$(mktemp)
    {
        printf '* %s Imre Tabur <imre.tabur@mail.ee> - %s-1\n' \
            "$(LC_ALL=C date '+%a %b %d %Y')" "${NEW_BASE}"
        printf -- '- %s\n' "${CHANGELOG_LINE}"
        printf '\n'
    } > "${ENTRY}"
    for SPEC in "${SPRINGBOOT_APP_DIR}"/rpm/*.spec; do
        sed -i "/^%changelog\$/r ${ENTRY}" "${SPEC}"
    done
    rm -f "${ENTRY}"
fi

printf '%s -> %s\n' "${OLD_VERSION}" "${NEW_VERSION}"
grep -H '^Version:' "${SPRINGBOOT_APP_DIR}"/rpm/*.spec

LEFTOVERS=$(grep -rlF "${OLD_VERSION}" --include=pom.xml --include=Makefile --include=Dockerfile --include=Jailfile \
    --include=project.clj --include=deps.edn --include='*.spec' \
    --exclude-dir=.git --exclude-dir=target --exclude-dir=submodules --exclude-dir=attic \
    --exclude-dir=incubation --exclude-dir=archived . 2>/dev/null || :)

if [ -n "${LEFTOVERS}" ]; then
    printf 'still carrying %s:\n%s\n' "${OLD_VERSION}" "${LEFTOVERS}" >&2
fi

if command -v rpmspec >/dev/null 2>&1; then
    for SPEC in "${SPRINGBOOT_APP_DIR}"/rpm/*.spec; do
        rpmspec -q "${SPEC}" >/dev/null || printf 'BROKEN %s\n' "${SPEC}"
    done
fi
