# Make sure you have installed the catalog files for XHTML on your local filesystem

OUTDIR=build/local
BASE=$(PWD)/build/local

CONTENT=$(shell find content -not -wholename '*/CVS*')
SKIN=$(shell find templates -not -name *.xslt -and -not -wholename '*/CVS*')
OUTPUT=$(CONTENT:content/%=$(OUTDIR)/%) $(SKIN:templates/%=$(OUTDIR)/%)

all: $(OUTPUT)

$(OUTDIR)/index.html: data/versions.xml

$(OUTDIR)/%.html: content/%.html templates/skin.xslt
	@mkdir -p $(OUTDIR)
	xsltproc \
	  --nodtdattr \
	  --stringparam base $(BASE) \
	  --stringparam path $*.html \
	  --output $@ \
	  templates/skin.xslt $<

$(OUTDIR)/%: content/%
	@mkdir -p $(OUTDIR)
	cp $< $@

$(OUTDIR)/%: templates/%
	@mkdir -p $(OUTDIR)
	cp $< $@

dist:
	$(MAKE) BASE=http://www.jmock.org OUTDIR=build/dist clean all

clean:
	rm -rf $(OUTDIR)

pristine:
	rm -rf build

again: clean all
