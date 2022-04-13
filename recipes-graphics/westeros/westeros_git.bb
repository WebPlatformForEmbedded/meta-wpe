SUMMARY = "This receipe compiles the westeros compositor component"
DESCRIPTION = " Wayland Compositor: Westeros is a light-weight Wayland compositor library.\
    It uses the Wayland protocols, and is designed to be compatible with applications built\
    to use Wayland compositors."

require westeros.inc

DEPENDS:append = " \
    ${WESTEROS_BACKEND} \
    westeros-simplebuffer \
    westeros-simpleshell \
"

SRC_URI:append = " \
    file://0001-Use-intptr_t-to-avoid-precision-errors-on-aarch64.patch \
    file://0002-Add_VCX_flags_support.patch \
    file://0003-Set-default-resolution-to-1080.patch \
"
S = "${WORKDIR}/git"

inherit autotools pkgconfig

PACKAGECONFIG ??= "incapp inctest increndergl incsbprotocol xdgv5"

PACKAGECONFIG:append = "${@bb.utils.contains("DISTRO_FEATURES", "x11", " x11", "", d)}"
# as far as only drm resolution module is available so, enabled only for DRM
PACKAGECONFIG:append = " ${@bb.utils.contains('WESTEROS_BACKEND', 'westeros-soc-drm', 'modules', '', d)}"

PACKAGECONFIG[incapp] = "--enable-app=yes"
PACKAGECONFIG[inctest] = "--enable-test=yes"
PACKAGECONFIG[incplayer] = "--enable-player=yes"
PACKAGECONFIG[increndergl] = "--enable-rendergl=yes"
PACKAGECONFIG[incsbprotocol] = "--enable-sbprotocol=yes"
PACKAGECONFIG[xdgv4] = "--enable-xdgv4=yes"
PACKAGECONFIG[xdgv5] = "--enable-xdgv5=yes"
PACKAGECONFIG[x11] = ",,freeglut"
PACKAGECONFIG[modules] = "--enable-modules=yes"

WESTEROS_BACKEND ??= "westeros-soc-drm"

SECURITY_CFLAGS:remove = "-fpie"
SECURITY_CFLAGS:remove = "-pie"

do_compile:prepend() {
   export SCANNER_TOOL=${STAGING_BINDIR_NATIVE}/wayland-scanner
   oe_runmake -C ${S}/protocol
}

def is_drm_enabled(d):
    return bb.utils.contains('WESTEROS_BACKEND', 'westeros-soc-drm', '1', '0', d)

do_install:append () {
   install -D -m 0644 ${S}/systemd/westeros-env ${D}${sysconfdir}/default/westeros-env
   install -D -m 0755 ${S}/systemd/westeros-init ${D}${bindir}/westeros-init
   if [ -f ${D}${bindir}/westeros-init -a ${@is_drm_enabled(d)} -eq 1 ]; then
       sed -i -e '/--renderer/i export WESTEROS_DRM_MODE=1920x1080' ${D}${bindir}/westeros-init
       sed -i -e '/--renderer/i export LD_PRELOAD=/usr/lib/libwesteros_gl.so.0' ${D}${bindir}/westeros-init
       sed -i -e '/--renderer/ s#$# --module /usr/lib/libresolutionmodule.so#' ${D}${bindir}/westeros-init
   fi
}

FILES_SOLIBSDEV = ""
FILES:${PN} += "${libdir}/*.so"

RDEPENDS:${PN} = "xkeyboard-config"

INSANE_SKIP:${PN} += "dev-so"

