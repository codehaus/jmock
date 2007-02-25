# Make sure you have installed the catalog files for XHTML on your local filesystem

SITE=howard.doc.ic.ac.uk:/homes/np2/public_html/temp/jmock-website-test


CONTENT=$(shell find content -not -name '*~' -and -not -wholename '*/CVS*')
SKIN=$(shell find templates -not -name '*.xslt' -and -not -name '*~' -and -not -wholename '*/CVS*')
ASSETS=$(shell find assets -not -name '*~' -and -not -wholename '*/CVS*')

OUTDIR=build
OUTPUT=$(CONTENT:content/%=$(OUTDIR)/%) \
       $(SKIN:templates/%=$(OUTDIR)/%) \
       $(ASSETS:assets/%.svg=$(OUTDIR)/%.png)

all: $(OUTPUT)

$(OUTDIR)/index.html: data/versions-*.xml

$(OUTDIR)/%.html: content/%.html templates/skin.xslt
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

$(OUTDIR)/%.png: assets/%.svg
	inkscape --without-gui --export-png=$@ --export-area-drawing --export-area-snap --export-width=176 $<

clean:
	rm -rf build

again: clean all

SCANNED_FILES=content templates assets data Makefile

continually: 
	@while true; do \
	  if make all; \
	  then \
	    echo clear jMock Website Build | nc localhost 10000 ; \
	  else \
	    echo raise jMock Website Build | nc localhost 10000 ; \
	  fi ; \
	  inotifywait -r -qq -e modify -e delete $(SCANNED_FILES); \
	done

published: all
	rsync --recursive --delete --cvs-exclude --compress --stats --verbose --rsh=ssh build/ $(SITE)
