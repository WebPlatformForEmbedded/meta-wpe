# TODO: FIX WARNING: QA Issue: dxdrm requires libgenerics.so, but no providers in its RDEPENDS [file-rdeps]
SUMMARY = "Discretix PlayReady DRM implementation."
HOMEPAGE = "https://www.metrological.com"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://README.md;md5=184cc4b37dd17190ad70309772bbbd8c"

# Set dxdrm config
DXDRM_CONFIG ??= "external"
DXDRM_CONFIG_append_arm = " arm"
DXDRM_CONFIG_append_x86 = " x86"

# Add OpenSSL and CPPSDK to the dependency list if external provisioning is used.
DEPENDS += '\
    ${@bb.utils.contains("DXDRM_CONFIG", "external", "openssl cppsdk", "",d)} \
    '
# Don't add any whitspace to DXDRM_ARCH and DXDRM_LOCATOR ;-)
DXDRM_ARCH = '\
${@bb.utils.contains("DXDRM_CONFIG", "arm", "arm", "",d)}\
${@bb.utils.contains("DXDRM_CONFIG", "x86", "i686", "",d)}\
'
DXDRM_LOCATOR = '\
${@bb.utils.contains("DXDRM_CONFIG","external", "external", "",d)}\
${@bb.utils.contains("DXDRM_CONFIG","internal", "internal", "",d)}\
'
RDEPENDS_${PN} += "libcurl"

# This resovles: WARNING: QA Issue: No GNU_HASH in the elf binary...
INSANE_SKIP_${PN} = "ldflags"

SRCREV = "1618e7a55a06d4738279cff56f5310cdb53e928e"
PV = "1.0.gitr${SRCPV}"
SRC_URI = "git://git@github.com/Metrological/dxdrm.git;protocol=ssh"

S = "${WORKDIR}/git"

# Add the libraries to the correct package
FILES_SOLIBSDEV = ""
FILES_${PN} += "${libdir}/lib*.so"   

do_install() {
  install -d ${D}${libdir}
  install -m 755 ${S}/${DXDRM_LOCATOR}/${DXDRM_ARCH}/release/libDxDrm.so ${D}${libdir}
  install -m 755 ${S}/${DXDRM_LOCATOR}/${DXDRM_ARCH}/release/libTrusted.so ${D}${libdir}

  install -d ${D}${includedir}/dxdrm
  install -m 644 ${S}/${DXDRM_LOCATOR}/include/*.h ${D}${includedir}/dxdrm

  install -d ${D}${sysconfdir}/dxdrm
  install -m 644 ${@S}/external/dxdrm.config ${D}${sysconfdir}/dxdrm
  
  install -d ${D}${libdir}/pkgconfig
  install -m 644 ${@S}/external/dxdrm.pc ${D}${libdir}/pkgconfig
  
  if ${@bb.utils.contains("DXDRM_CONFIG","internal", "true", "false",d)}; then \
    install -m 644 ${S}/${DXDRM_LOCATOR}/credentials/* ${D}${sysconfdir}/dxdrm; \
  fi

  echo "BRAM DEBUG ${@bb.utils.contains("DXDRM_CONFIG","external", "true", "false",d)}"
  
  if ${@bb.utils.contains("DXDRM_CONFIG","external", "true", "false",d)}; then \
    install -m 755 ${S}/${DXDRM_LOCATOR}/${DXDRM_ARCH}/release/libprovision.so ${D}${libdir}; \
    install -m 755 ${S}/${DXDRM_LOCATOR}/${DXDRM_ARCH}/release/libprovisionproxy.so ${D}${libdir}; \ 
    install -m 644 ${@S}/external/provision.pc ${D}${libdir}/pkgconfig
    
    install -d  ${D}${includedir}/rpc; \
    install -m 644 ${S}/${DXDRM_LOCATOR}/include/rpc/*.h ${D}${includedir}/rpc; \

    install -d  ${D}${includedir}/provision; \
    install -m 644 ${S}/${DXDRM_LOCATOR}/include/provision/*.h ${D}${includedir}/provision; \
  fi
}
