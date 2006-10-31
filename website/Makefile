# Make sure you have installed the catalog files for XHTML on your local filesystem


CONTENT=$(shell find content -not -name '*~' -and -not -wholename '*/CVS*')
SKIN=$(shell find templates -not -name '*.xslt' -and -not -name '*~' -and -not -wholename '*/CVS*')

OUTDIR=build/local
OUTPUT_LOCAL=$(CONTENT:content/%=$(OUTDIR)/%) $(SKIN:templates/%=$(OUTDIR)/%)

DISTDIR=build/dist
OUTPUT_DIST=$(CONTENT:content/%=$(DISTDIR)/%) $(SKIN:templates/%=$(DISTDIR)/%)

local: $(OUTPUT_LOCAL)
dist: $(OUTPUT_DIST)

$(OUTDIR)/index.html $(DISTDIR)/index.html: data/versions-*.xml

$(OUTDIR)/%.html: content/%.html templates/skin.xslt
	@mkdir -p $(dir $@)
	xsltproc \
	  --nodtdattr \
	  --stringparam base $(PWD)/build/local \
	  --stringparam path $*.html \
	  --output $@ \
	  templates/skin.xslt $<

$(DISTDIR)/%.html: content/%.html templates/skin.xslt
	@mkdir -p $(dir $@)
	xsltproc \
	  --nodtdattr \
	  --stringparam base http://www.jmock.org \
	  --stringparam path $*.html \
	  --output $@ \
	  templates/skin.xslt $<

$(OUTDIR)/% $(DISTDIR)/%: content/%
	@mkdir -p $(dir $@)
	cp $< $@

$(OUTDIR)/% $(DISTDIR)/%: templates/%
	@mkdir -p $(dir $@)
	cp $< $@

clean:
	rm -rf build

rebuild-local: clean local

rebuild-dist: clean dist
