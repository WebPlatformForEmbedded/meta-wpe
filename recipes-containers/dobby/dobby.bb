SUMMARY = "Dobby Container Manager"
DESCRIPTION = "Dobby “Docker based Thingy” is a tool for managing and running OCI containers using crun"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c466d4ab8a68655eb1edf0bf8c1a8fb8"

SRC_URI = "git://github.com/rdkcentral/Dobby"

#2020-12-14
SRCREV = "2db374a248e1e011a13d7ba643901f7da6972710"
DEPENDS_append = " libnl dbus jsoncpp python3 ctemplate boost yajl systemd"
RDEPENDS_${PN} = "crun (>= 0.14.1)"

S = "${WORKDIR}/git"

# Always build the debug version for now
EXTRA_OECMAKE = "-DCMAKE_BUILD_TYPE=Debug "

# Sky XiOne build
EXTRA_OECMAKE_append_skyxione = "-DLEGACY_COMPONENTS=ON -DRDK_PLATFORM=XI1 "

# Sky Llama build
EXTRA_OECMAKE_append_llama = "-DLEGACY_COMPONENTS=ON -DRDK_PLATFORM=LLAMA "

# Comcast Xi6 build
EXTRA_OECMAKE_append_arrisxi6 = "-DLEGACY_COMPONENTS=OFF -DRDK_PLATFORM=XI6 "

inherit pkgconfig cmake

# We use the cmake standard install, however it doesn't seem to correctly
# add the symlink for the systemd service, so here we do it as a post
# install step

do_install_append () {

    echo "Enabling service in ${D}${sysconfdir}/systemd/system/multi-user.target.wants/dobby.service"
    install -d ${D}${sysconfdir}/systemd/system/multi-user.target.wants
    ln -sf ${systemd_system_unitdir}/dobby.service ${D}${sysconfdir}/systemd/system/multi-user.target.wants/dobby.service
}

# Skip harmless QA issue caused by installing but not shipping buildtime cmake files
INSANE_SKIP_${PN} = "installed-vs-shipped"

# These are the files that end up in the rootfs
FILES_${PN} += "${systemd_system_unitdir}/dobby.service"
FILES_${PN} += "${sysconfdir}/systemd/system/multi-user.target.wants/dobby.service"
FILES_${PN} += "${sysconfdir}/dobby.json"
FILES_${PN} += "${sbindir}/DobbyDaemon"
FILES_${PN} += "${libexecdir}/DobbyInit"
FILES_${PN} += "${bindir}/DobbyTool"
FILES_${PN} += "${libdir}/plugins/dobby/*.so*"

