SUMMARY = "Discretix PlayReady DRM implementation."
HOMEPAGE = "https://www.metrological.com"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://README.md;md5=184cc4b37dd17190ad70309772bbbd8c"

# TODO: needs to be contional to DXDRM_LOCATOR=extern
DEPENDS = "openssl cppsdk"

SRCREV = "474837c846bfea260ff3f7b170cac305a7ad5e71"
PV = "1.0.${SRCREV}"
SRC_URI = "git://git@github.com/Metrological/dxdrm.git;protocol=ssh"

S = "${WORKDIR}/git"

RDEPENDS_${PN} += "libcurl" 

# This resovles: WARNING: QA Issue: No GNU_HASH in the elf binary...
INSANE_SKIP_${PN} = "ldflags"

# dirty but it works
DXDRM_ARCH = "${TARGET_ARCH}"

# TODO: this should be externally contolable
DXDRM_LOCATOR ??= "external"

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
  install -m 644 ${@S}/${DXDRM_LOCATOR}/dxdrm.config ${D}${sysconfdir}/dxdrm
  
  if [ "x$(DXDRM_LOCATOR)" = "xinternal" ]; then \
    install -m 644 ${S}/${DXDRM_LOCATOR}/credentials/* ${D}${sysconfdir}/dxdrm; \
  fi
# TODO: libprovision and  libprovisionproxy not yet available for x86
  if [ "x$(DXDRM_LOCATOR)" = "xexternal" -a -s "${S}/${DXDRM_LOCATOR}/${DXDRM_ARCH}/release/libprovision.so" ]; then \
    install -m 755 ${S}/${DXDRM_LOCATOR}/${DXDRM_ARCH}/release/libprovision.so ${D}${libdir}; \
    install -m 755 ${S}/${DXDRM_LOCATOR}/${DXDRM_ARCH}/release/libprovisionproxy.so ${D}${libdir}; \ 
    install -d  ${D}${includedir}/rpc; \
    install -m 644 ${S}/${DXDRM_LOCATOR}/include/rpc/*.h ${D}${includedir}/rpc; \

    install -d  ${D}${includedir}/provision; \
    install -m 644 ${S}/${DXDRM_LOCATOR}/include/provision/*.h ${D}${includedir}/provision; \
  fi
}
