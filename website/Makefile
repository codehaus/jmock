# Make sure you have installed the catalog files for XHTML on your local filesystem

SITE=jmock@www.jmock.org:public_html


CONTENT=$(shell find content -not -name '*~' -and -not -wholename '*/CVS*')
SKIN=$(shell find templates -not -name '*.xslt' -and -not -name '*~' -and -not -wholename '*/CVS*')
ASSETS=$(shell find assets -not -name '*~' -and -not -wholename '*/CVS*')

OUTDIR=build
OUTPUT=$(CONTENT:content/%=$(OUTDIR)/%) \
       $(SKIN:templates/%=$(OUTDIR)/%) \
       $(ASSETS:assets/%.svg=$(OUTDIR)/%.png)

all: $(OUTPUT)

$(OUTDIR)/index.html: content/news-rss2.xml

$(OUTDIR)/%.html: content/%.html templates/skin.xslt data/versions-*.xml
	@mkdir -p $(dir $@)
	xsltproc \
	  --nodtdattr \
	  --stringparam base $(PWD)/build/local \
	  --stringparam path $*.html \
	  --output $@ \
	  templates/skin.xslt $<

$(OUTDIR)/%: content/%
	@mkdir -p $(dir $@)
	cp $< $@

$(OUTDIR)/%: templates/%
	@mkdir -p $(dir $@)
	cp $< $@

$(OUTDIR)/logo.png: WIDTH=176
$(OUTDIR)/information.png: WIDTH=32
$(OUTDIR)/warning.png: WIDTH=40
$(OUTDIR)/download-it.png: WIDTH=40
$(OUTDIR)/use-it.png: WIDTH=40
$(OUTDIR)/%.png: assets/%.svg
	inkscape --without-gui --export-png=$@ --export-area-drawing --export-area-snap --export-width=$(WIDTH) $<


clean:
	rm -rf build

again: clean all


SCANNED_FILES=content templates assets data Makefile

continually: 
	@while true; do \
	  if make all; \
	  then \
	    condition clear jmock-website-build; \
	  else \
	    condition raise jmock-website-build "jMock Website Build Broken"; \
	  fi ; \
	  inotifywait -r -qq -e modify -e delete $(SCANNED_FILES); \
	done

published: all
	rsync --recursive --checksum --delete --cvs-exclude --compress --stats --verbose --rsh=ssh build/ $(SITE)
